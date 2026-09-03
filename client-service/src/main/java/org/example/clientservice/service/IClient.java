package org.example.clientservice.service;

import org.example.clientservice.dtos.ClientDTO;

import java.util.List;

public interface IClient  {
    ClientDTO saveClient(ClientDTO clientDTO);
    ClientDTO getClientById(Integer id);
    ClientDTO getClientByEmail(String email);
    List<ClientDTO> getAllClients();
    ClientDTO updateClient(Integer id, ClientDTO clientDTO);
    void deleteClient(Integer id);
}
