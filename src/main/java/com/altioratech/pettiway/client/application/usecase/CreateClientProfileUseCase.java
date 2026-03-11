package com.altioratech.pettiway.client.application.usecase;

import com.altioratech.pettiway.client.domain.model.Client;
import com.altioratech.pettiway.client.domain.repository.ClientRepository;
import com.altioratech.pettiway.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateClientProfileUseCase {

    private final ClientRepository clientRepository;

    public void execute(User user) {
        Client client = Client.builder()
                .user(user)
                .newsletter(false)
                .createdAt(LocalDateTime.now())
                .build();

        clientRepository.save(client);
    }
}
