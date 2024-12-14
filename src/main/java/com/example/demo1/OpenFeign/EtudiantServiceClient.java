package com.example.demo1.OpenFeign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "etudiant-service", url = "http://localhost:1016")
public interface EtudiantServiceClient {
    @GetMapping("/etudiant/find/{id}")
    Etudiant getEtudiantById(@PathVariable("id") int id);
}