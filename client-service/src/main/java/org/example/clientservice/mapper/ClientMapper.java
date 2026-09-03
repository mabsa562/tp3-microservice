package org.example.clientservice.mapper;

import org.example.clientservice.dtos.ClientDTO;
import org.example.clientservice.entity.Client;

public class ClientMapper {
    public static ClientDTO toDTO (Client client) {

        return ClientDTO.builder().
                id(client.getId()).
                nom(client.getNom()).
                prenom(client.getPrenom()).
                email(client.getEmail()).
                telephone(client.getTelephone()).

                build();
    }
    public static Client toEntity (ClientDTO client) {
        return Client.builder().
                id(client.getId()).
                nom(client.getNom()).
                prenom(client.getPrenom()).
                email(client.getEmail()).
                telephone(client.getTelephone()).

                build();


    }

}
