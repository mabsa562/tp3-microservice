package org.example.clientservice.service;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.example.clientservice.Exception.ResourceNotFoundException;
import org.example.clientservice.dtos.ClientDTO;
import org.example.clientservice.entity.Client;
import org.example.clientservice.mapper.ClientMapper;
import org.example.clientservice.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClient {

    private final ClientRepository clientRepository;
    @Override
    @Transactional
    public ClientDTO saveClient(ClientDTO clientDTO) {
        if (clientRepository.existsByEmail(clientDTO.getEmail())) {
            throw new EntityExistsException("Un client avec cet email existe déjà");
        }
        Client client = ClientMapper.toEntity(clientDTO);

        Client savedClient = clientRepository.save(client);
        return ClientMapper.toDTO(savedClient);
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDTO getClientById(Integer id) {
        Client client =clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client non trouvé avec l'id : " + id));
        return ClientMapper.toDTO(client);
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDTO getClientByEmail(String email) {
        Client client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Client non trouvé avec l'email : " + email));
        return ClientMapper.toDTO(client);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(ClientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ClientDTO updateClient(Integer id, ClientDTO clientDTO) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membre non trouvé avec l'id : " + id));

        existingClient.setNom(clientDTO.getNom());
        existingClient.setPrenom(clientDTO.getPrenom());
        existingClient.setTelephone(clientDTO.getTelephone());

        if (!existingClient.getEmail().equals(clientDTO.getEmail()) && clientRepository.existsByEmail(clientDTO.getEmail())) {
            throw new EntityExistsException("Un autre membre avec cet email existe déjà");
        }
        existingClient.setEmail(clientDTO.getEmail());

        Client updatedClient = clientRepository.save(existingClient);
        return ClientMapper.toDTO(updatedClient);
    }

    @Override
    @Transactional
    public void deleteClient(Integer id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client non trouvé avec l'id : " + id);
        }
        clientRepository.deleteById(id);
    }


}
