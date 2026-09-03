package org.example.commandeservice.feign;

import org.example.commandeservice.dto.ClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client-service")

public interface ClientFeign {

    @GetMapping("/clients/{id}")
    ClientDTO getClientById(@PathVariable("id") Integer id);
}
