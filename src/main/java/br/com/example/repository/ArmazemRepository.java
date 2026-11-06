package br.com.example.repository;

import br.com.example.model.Armazem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArmazemRepository extends JpaRepository<Armazem, Integer> {

    // Consulta 9 - within: capacidade total (em toneladas) dos armazéns que estão dentro de um município
    @Query(value = "select sum(a.capacidade) from Armazem a, Municipio m where within(a.geometria, m.geometria) = true and m.codigo = :codigo")
    Double capacidadeArmazensDoMunicipio(@Param("codigo") String codigo);
}
