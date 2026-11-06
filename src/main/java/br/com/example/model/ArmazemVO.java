package br.com.example.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.locationtech.jts.geom.Geometry;

/** Armazém para desenhar no mapa (ponto + dados que aparecem no popup). */
@Data
@AllArgsConstructor
public class ArmazemVO implements Serializable {
    private String municipio;
    private String uf;
    private Double capacidade;   // toneladas
    private Geometry geometria;
}
