package com.altioratech.pettiway.client.application.usecase;

import com.altioratech.pettiway.client.domain.model.Client;
import com.altioratech.pettiway.client.domain.repository.ClientRepository;
import com.altioratech.pettiway.client.infrastructure.in.rest.dto.UpdateClientProfileDTO;
import com.altioratech.pettiway.location.application.usecase.SaveLocationUseCase;
import com.altioratech.pettiway.location.application.usecase.UpdateLocationUseCase;
import com.altioratech.pettiway.location.domain.model.Location;
import com.altioratech.pettiway.user.application.service.ProfileCompletionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateClientProfileUseCase {

    private final ClientRepository clientRepository;
    private final SaveLocationUseCase saveLocationUseCase;
    private final UpdateLocationUseCase updateLocationUseCase;
    private final ProfileCompletionService profileCompletionService;

    @Transactional
    public Client execute(UUID userId, UpdateClientProfileDTO dto) {

        // 🔹 Buscar cliente por el ID de usuario
        Client client = clientRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // 🔹 Actualizar datos personales
        if (dto.dniOrDocument() != null) client.setDniOrDocument(dto.dniOrDocument());
        if (dto.preferredPayment() != null) client.setPreferredPayment(dto.preferredPayment());
        if (dto.newsletter() != null) client.setNewsletter(dto.newsletter());

        // 🔹 Manejar ubicación (si se proporciona)
        if (dto.location() != null) {
            Location newLocation = Location.fromDTO(dto.location());

            UUID newLocationId;
            if (client.getLocationId() == null) {
                // Nueva ubicación → guardar
                newLocationId = saveLocationUseCase.execute(newLocation).getId();
            } else {
                // Ubicación existente → actualizar
                newLocationId = updateLocationUseCase.execute(client.getLocationId(), newLocation).getId();
            }
            client.setLocationId(newLocationId);
        }

        // 🔹 Actualizar fecha
        client.setUpdatedAt(LocalDateTime.now());

        // 🔹 Guardar cliente actualizado
        Client updated = clientRepository.save(client);

        // 🔹 Actualizar estado de perfil completo (según lógica global)
        profileCompletionService.updateProfileCompletion(client.getUser());

        return updated;
    }
}
