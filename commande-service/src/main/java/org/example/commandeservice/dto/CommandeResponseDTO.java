package org.example.commandeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommandeResponseDTO {

    private Integer id;

    private Integer quantite;

    private ClientDTO client;

    private ProduitDTO produit;
}