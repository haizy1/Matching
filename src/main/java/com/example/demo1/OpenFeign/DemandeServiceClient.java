package com.example.demo1.OpenFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "demande-service",url="http://localhost:1016")
public interface DemandeServiceClient {

    @GetMapping("/Demande/find/{id}")
    Demande getDemandeById(@PathVariable("id") int id);
}
