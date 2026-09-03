package org.example.produitservice.service;

import jakarta.persistence.EntityExistsException;
import lombok.Builder;
import org.example.produitservice.Exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.produitservice.dtos.ProduitDTO;
import org.example.produitservice.entity.Produit;
import org.example.produitservice.mapper.ProduitMapper;
import org.example.produitservice.repository.ProduitRepository;

import java.util.List;
import java.util.stream.Collectors;
@Builder
@Service
public class ProduitServiceImpl implements IProduit {
    private final ProduitRepository produitRepository;
    public ProduitServiceImpl(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Override
    @Transactional
    public ProduitDTO saveProduit(ProduitDTO produitDTO) {

        if (produitRepository.existsByNom(produitDTO.getNom())) {
            throw new EntityExistsException("Un produit avec ce nom existe déjà");
        }

        Produit produit = ProduitMapper.toEntity(produitDTO);

        Produit savedProduit = produitRepository.save(produit);

        return ProduitMapper.toDTO(savedProduit);
    }


    @Override
    @Transactional(readOnly = true)
    public ProduitDTO getProduitById(Integer id) {

        Produit produit = produitRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Produit non trouvé avec l'id : " + id)
                );

        return ProduitMapper.toDTO(produit);
    }


    @Override
    @Transactional(readOnly = true)
    public List<ProduitDTO> getAllProduits() {

        return produitRepository.findAll().stream()
                .map(ProduitMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public ProduitDTO updateProduit(Integer id, ProduitDTO produitDTO) {

        Produit produit = produitRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Produit non trouvé avec l'id : " + id)
                );

        produit.setNom(produitDTO.getNom());
        produit.setPrix(produitDTO.getPrix());
        produit.setStock(produitDTO.getStock());

        Produit updatedProduit = produitRepository.save(produit);

        return ProduitMapper.toDTO(updatedProduit);
    }


    @Override
    @Transactional
    public void deleteProduit(Integer id) {

        Produit produit = produitRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Produit non trouvé avec l'id : " + id)
                );

        produitRepository.delete(produit);
    }
}
