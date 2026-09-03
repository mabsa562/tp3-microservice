package org.example.produitservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProduitDTO {
    private Integer id;

    private String nom;

    private Double prix;

    private Integer stock;
}
