package br.com.example.controller;

import br.com.example.model.SugestaoVO;
import br.com.example.repository.SugestaoRepository;
import io.swagger.v3.oas.annotations.Hidden;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
@CrossOrigin(origins = "*")
public class SugestaoController {

    private static final int MAXIMO = 150;

    @Autowired
    private SugestaoRepository repository;

    // "jo" -> "jo%" (começa com), sem diferenciar maiúsculas de minúsculas
    private String padrao(String q) {
        return q.trim().toLowerCase() + "%";
    }

    @GetMapping("/sugestoes/municipios")
    public List<SugestaoVO> municipios(@RequestParam String q) {
        return repository.sugerirMunicipios(padrao(q), PageRequest.of(0, MAXIMO));
    }

    @GetMapping("/sugestoes/aeroportos")
    public List<SugestaoVO> aeroportos(@RequestParam String q) {
        return repository.sugerirAeroportos(padrao(q), PageRequest.of(0, MAXIMO));
    }

    @GetMapping("/sugestoes/portos")
    public List<SugestaoVO> portos(@RequestParam String q) {
        return repository.sugerirPortos(padrao(q), PageRequest.of(0, MAXIMO));
    }

    @GetMapping("/sugestoes/armazens")
    public List<SugestaoVO> armazens(@RequestParam String q) {
        return repository.sugerirArmazens(padrao(q), PageRequest.of(0, MAXIMO));
    }

    @GetMapping("/sugestoes/ferrovias")
    public List<String> ferrovias(@RequestParam String q) {
        return repository.sugerirFerrovias(padrao(q), PageRequest.of(0, MAXIMO));
    }

    @GetMapping("/sugestoes/rodovias")
    public List<String> rodovias(@RequestParam String q) {
        return repository.sugerirRodovias(q.trim() + "%").stream().sorted().limit(MAXIMO).toList();
    }

    @GetMapping("/sugestoes/tiposRodovia")
    public List<String> tiposRodovia() {
        return repository.listarTiposRodovia();
    }
}
