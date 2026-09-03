package org.example.produitservice.mapper;

import lombok.Builder;
import org.example.produitservice.dtos.ProduitDTO;
import org.example.produitservice.entity.Produit;

@Builder
public class ProduitMapper {
    public static ProduitDTO toDTO (Produit produit) {

        return ProduitDTO.builder().
                id(produit.getId()).
                nom(produit.getNom()).
                prix(produit.getPrix()).
                stock(produit.getStock()).

                build();
    }
    public static Produit toEntity (ProduitDTO produit) {
        return Produit.builder().
                id(produit.getId()).
                nom(produit.getNom()).
                prix(produit.getPrix()).
                stock(produit.getStock()).

                build();


    }
}
