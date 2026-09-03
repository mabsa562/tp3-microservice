package org.example.produitservice.repository;

import org.example.produitservice.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {
    boolean existsByNom(String nom);

}
