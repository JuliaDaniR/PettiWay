package com.altioratech.pettiway.client.infrastructure.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientJpaRepository extends JpaRepository<ClientEntity, UUID> {

    Optional<ClientEntity> findByUserId(UUID userId);
}
