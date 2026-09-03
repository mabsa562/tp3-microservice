package org.example.commandeservice.feign;

import org.example.commandeservice.dto.ProduitDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "produit-service")

public interface ProduitFeign {
    @GetMapping("/produits/{id}")
    ProduitDTO getProduitById(@PathVariable("id") Integer id);
}
