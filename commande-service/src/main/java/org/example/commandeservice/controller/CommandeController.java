package org.example.commandeservice.controller;

import org.example.commandeservice.dto.CommandeDTO;
import org.example.commandeservice.dto.CommandeResponseDTO;
import org.example.commandeservice.service.ICommande;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commandes")
public class CommandeController {

    private final ICommande commandeService;

    public CommandeController(ICommande commandeService) {
        this.commandeService = commandeService;
    }

    // Créer une commande
    @PostMapping
    public ResponseEntity<CommandeResponseDTO> saveCommande(
            @RequestBody CommandeDTO commandeDTO) {

        CommandeResponseDTO commande =
                commandeService.saveCommande(commandeDTO);

        return new ResponseEntity<>(
                commande,
                HttpStatus.CREATED
        );
    }

    // Récupérer une commande par son ID
    @GetMapping("/{id}")
    public ResponseEntity<CommandeResponseDTO> getCommandeById(
            @PathVariable Integer id) {

        CommandeResponseDTO commande =
                commandeService.getCommandeById(id);

        return ResponseEntity.ok(commande);
    }

    // Récupérer toutes les commandes
    @GetMapping
    public ResponseEntity<List<CommandeResponseDTO>> getAllCommandes() {

        List<CommandeResponseDTO> commandes =
                commandeService.getAllCommandes();

        return ResponseEntity.ok(commandes);
    }
}