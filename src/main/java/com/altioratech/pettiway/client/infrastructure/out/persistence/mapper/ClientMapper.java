package com.altioratech.pettiway.client.infrastructure.out.persistence.mapper;

import com.altioratech.pettiway.client.domain.model.Client;
import com.altioratech.pettiway.client.infrastructure.out.persistence.ClientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientEntity toEntity(Client domain);

    Client toDomain(ClientEntity entity);
}