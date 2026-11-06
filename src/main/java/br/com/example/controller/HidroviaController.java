package br.com.example.controller;

import br.com.example.repository.HidroviaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class HidroviaController {

    @Autowired
    private HidroviaRepository repository;

    @GetMapping("/areaHidroviasDoMunicipio/{codigo}")
    public Double areaHidroviasDoMunicipio(@PathVariable String codigo) {
        Double result = repository.areaHidroviasDoMunicipio(codigo);
        return result == null ? 0.0 : result;
    }
}
