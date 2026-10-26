
package org.example.clientservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.clientservice.dtos.ClientDTO;
import org.example.clientservice.service.IClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final IClient clientService;

    private static final Logger log =
            LoggerFactory.getLogger(ClientController.class);

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(
            @Valid @RequestBody ClientDTO clientDTO) {

        log.info("Demande de création d'un client reçue");

        ClientDTO createdClient = clientService.saveClient(clientDTO);

        log.info("Client créé avec succès, id={}", createdClient.getId());

        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(
            @PathVariable Integer id) {

        log.info("Recherche d'un client par id={}", id);

        ClientDTO client = clientService.getClientById(id);

        log.debug("Client récupéré avec succès, id={}", id);

        return ResponseEntity.ok(client);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ClientDTO> getClientByEmail(
            @PathVariable String email) {

        log.info("Recherche d'un client par email");

        ClientDTO client = clientService.getClientByEmail(email);

        log.debug("Client récupéré par email avec succès");

        return ResponseEntity.ok(client);
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {

        log.info("Demande de récupération de tous les clients");

        List<ClientDTO> clients = clientService.getAllClients();

        log.info("Nombre de clients récupérés : {}", clients.size());

        return ResponseEntity.ok(clients);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(
            @PathVariable Integer id,
            @Valid @RequestBody ClientDTO clientDTO) {

        log.info("Demande de modification du client id={}", id);

        ClientDTO updatedClient = clientService.updateClient(id, clientDTO);

        log.info("Client modifié avec succès, id={}", id);

        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(
            @PathVariable Integer id) {

        log.info("Demande de suppression du client id={}", id);

        clientService.deleteClient(id);

        log.info("Client supprimé avec succès, id={}", id);

        return ResponseEntity.noContent().build();
    }
}
