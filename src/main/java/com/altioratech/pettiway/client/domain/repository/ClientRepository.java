package com.altioratech.pettiway.client.domain.repository;

import com.altioratech.pettiway.client.domain.model.Client;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository {
    Client save(Client client);
    Optional<Client> findByUserId(UUID userId);
    Optional<Client> findById(UUID id);
}
