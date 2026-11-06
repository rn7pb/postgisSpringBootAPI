package br.com.example.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.locationtech.jts.geom.Geometry;

@Data
@Entity
@Table(name = "armazens_2014")
public class Armazem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gid;
    @Column(name = "uf")
    private String uf;
    @Column(name = "municipio")
    private String municipio;
    @Column(name = "geocodigo")
    private Integer geocodigo;
    @Column(name = "cap_ton")
    private Double capacidade;
    @Column(name = "geom")
    private Geometry geometria;
}
