package org.example.produitservice.controller;

import org.example.produitservice.dtos.ProduitDTO;
import org.example.produitservice.service.IProduit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produits")

public class ProduitController {

    private final IProduit produitService;

    public ProduitController(IProduit produitService) {
        this.produitService = produitService;
    }

    @PostMapping
    public ResponseEntity<ProduitDTO> saveProduit(
            @RequestBody ProduitDTO produitDTO) {

        ProduitDTO savedProduit = produitService.saveProduit(produitDTO);

        return new ResponseEntity<>(savedProduit, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduitDTO> getProduitById(
            @PathVariable Integer id) {

        ProduitDTO produit = produitService.getProduitById(id);

        return ResponseEntity.ok(produit);
    }

    @GetMapping
    public ResponseEntity<List<ProduitDTO>> getAllProduits() {

        List<ProduitDTO> produits = produitService.getAllProduits();

        return ResponseEntity.ok(produits);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProduitDTO> updateProduit(
            @PathVariable Integer id,
            @RequestBody ProduitDTO produitDTO) {

        ProduitDTO updatedProduit =
                produitService.updateProduit(id, produitDTO);

        return ResponseEntity.ok(updatedProduit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(
            @PathVariable Integer id) {

        produitService.deleteProduit(id);

        return ResponseEntity.noContent().build();
    }
}