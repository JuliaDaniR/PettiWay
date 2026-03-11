package com.altioratech.pettiway.client.infrastructure.out.persistence;

import com.altioratech.pettiway.client.domain.model.Client;
import com.altioratech.pettiway.client.domain.repository.ClientRepository;
import com.altioratech.pettiway.client.infrastructure.out.persistence.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClientRepositoryAdapter implements ClientRepository {

    private final ClientJpaRepository jpaRepository;
    private final ClientMapper mapper;

    @Override
    public Client save(Client client) {
        ClientEntity entity = mapper.toEntity(client);
        ClientEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Client> findByUserId(UUID userId) {
        return jpaRepository.findByUserId(userId)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Client> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
}