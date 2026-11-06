package br.com.example.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.locationtech.jts.geom.Geometry;

/** Parte das hidrovias (polígonos) que fica dentro de um município, com a área em km². */
@Data
@AllArgsConstructor
public class HidroviaAreaVO implements Serializable {
    private String municipio;
    private Double areaKm2;
    private Geometry geometria;
}
