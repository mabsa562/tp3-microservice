package org.example.produitservice.service;

import org.example.produitservice.dtos.ProduitDTO;

import java.util.List;

public interface IProduit {
    ProduitDTO saveProduit(ProduitDTO produitDTO);

    ProduitDTO getProduitById(Integer id);

    List<ProduitDTO> getAllProduits();

    ProduitDTO updateProduit(Integer id, ProduitDTO produitDTO);

    void deleteProduit(Integer id);
}
