package org.example.commandeservice.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.commandeservice.dto.ClientDTO;
import org.example.commandeservice.dto.CommandeDTO;
import org.example.commandeservice.dto.CommandeResponseDTO;
import org.example.commandeservice.dto.ProduitDTO;
import org.example.commandeservice.entity.Commande;
import org.example.commandeservice.feign.ClientFeign;
import org.example.commandeservice.feign.ProduitFeign;
import org.example.commandeservice.repository.CommandeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandeServiceImpl implements ICommande {

    private final CommandeRepository commandeRepository;
    private final ClientFeign clientFeign;
    private final ProduitFeign produitFeign;

    public CommandeServiceImpl(
            CommandeRepository commandeRepository,
            ClientFeign clientFeign,
            ProduitFeign produitFeign) {

        this.commandeRepository = commandeRepository;
        this.clientFeign = clientFeign;
        this.produitFeign = produitFeign;
    }

    @Override
    @Transactional
    public CommandeResponseDTO saveCommande(CommandeDTO commandeDTO) {

        // Vérifier que le client existe
        ClientDTO client = clientFeign.getClientById(
                commandeDTO.getClientId()
        );

        if (client == null) {
            throw new EntityNotFoundException(
                    "Client introuvable avec l'id : "
                            + commandeDTO.getClientId()
            );
        }

        // Vérifier que le produit existe
        ProduitDTO produit = produitFeign.getProduitById(
                commandeDTO.getProduitId()
        );

        if (produit == null) {
            throw new EntityNotFoundException(
                    "Produit introuvable avec l'id : "
                            + commandeDTO.getProduitId()
            );
        }

        // Création de la commande
        Commande commande = Commande.builder()
                .clientId(commandeDTO.getClientId())
                .produitId(commandeDTO.getProduitId())
                .quantite(commandeDTO.getQuantite())
                .build();

        Commande savedCommande = commandeRepository.save(commande);

        // Retourner la commande enrichie
        return CommandeResponseDTO.builder()
                .id(savedCommande.getId())
                .quantite(savedCommande.getQuantite())
                .client(client)
                .produit(produit)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public CommandeResponseDTO getCommandeById(Integer id) {

        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Commande introuvable avec l'id : " + id
                        )
                );

        // Récupérer le client avec Feign
        ClientDTO client = clientFeign.getClientById(
                commande.getClientId()
        );

        // Récupérer le produit avec Feign
        ProduitDTO produit = produitFeign.getProduitById(
                commande.getProduitId()
        );

        // Construire la réponse enrichie
        return CommandeResponseDTO.builder()
                .id(commande.getId())
                .quantite(commande.getQuantite())
                .client(client)
                .produit(produit)
                .build();
    }
    @Override
    @Transactional(readOnly = true)
    public List<CommandeResponseDTO> getAllCommandes() {

        return commandeRepository.findAll()
                .stream()
                .map(commande -> {

                    ClientDTO client = clientFeign.getClientById(
                            commande.getClientId()
                    );

                    ProduitDTO produit = produitFeign.getProduitById(
                            commande.getProduitId()
                    );

                    return CommandeResponseDTO.builder()
                            .id(commande.getId())
                            .quantite(commande.getQuantite())
                            .client(client)
                            .produit(produit)
                            .build();
                })
                .collect(Collectors.toList());
    }
}