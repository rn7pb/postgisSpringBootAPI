package br.com.example.repository;

import br.com.example.model.Hidrovia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HidroviaRepository extends JpaRepository<Hidrovia, Integer> {

    // Consulta 10 - intersects: área (em km²) das hidrovias (polígonos) que ficam dentro de um município.
    // SQL nativo porque usa ST_Intersection/ST_Area; ST_MakeValid evita erro de topologia em polígonos inválidos.
    @Query(value = "select cast(coalesce(sum(ST_Area(CAST(ST_CollectionExtract(ST_Intersection(ST_MakeValid(m.geom), ST_MakeValid(h.geom)), 3) AS geography))), 0) / 1000000 as double precision) "
            + "from br_municipios_2025 m join hidrovias_poligono_2014 h on ST_Intersects(m.geom, h.geom) where m.cd_mun = :codigo",
            nativeQuery = true)
    Double areaHidroviasDoMunicipio(@Param("codigo") String codigo);
}
