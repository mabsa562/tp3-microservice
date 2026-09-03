package org.example.commandeservice.repository;

import org.example.commandeservice.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository  extends JpaRepository<Commande, Integer> {
}
