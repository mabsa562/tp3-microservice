package org.example.commandeservice.service;

import org.example.commandeservice.dto.CommandeDTO;
import org.example.commandeservice.dto.CommandeResponseDTO;

import java.util.List;

public interface ICommande {

    CommandeResponseDTO saveCommande(CommandeDTO commandeDTO);

    CommandeResponseDTO getCommandeById(Integer id);
    List<CommandeResponseDTO> getAllCommandes();
}