package br.com.example.controller;

import br.com.example.repository.ArmazemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ArmazemController {

    @Autowired
    private ArmazemRepository repository;

    @GetMapping("/capacidadeArmazensDoMunicipio/{codigo}")
    public Double capacidadeArmazensDoMunicipio(@PathVariable String codigo) {
        Double result = repository.capacidadeArmazensDoMunicipio(codigo);
        return result == null ? 0.0 : result;
    }
}
