package com.altioratech.pettiway.client.infrastructure.in.rest;

import com.altioratech.pettiway.client.application.usecase.UpdateClientProfileUseCase;
import com.altioratech.pettiway.client.infrastructure.in.rest.dto.UpdateClientProfileBackendDTO;
import com.altioratech.pettiway.client.infrastructure.in.rest.dto.UpdateClientProfileDTO;
import com.altioratech.pettiway.client.infrastructure.in.rest.dto.UpdateClientProfileFrontendDTO;
import com.altioratech.pettiway.location.application.dto.LocationDTO;
import com.altioratech.pettiway.location.infrastructure.out.geocoding.GeocodingServiceAdapter;
import com.altioratech.pettiway.user.application.service.AuthenticatedUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/clients")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
@Tag(name = "Client", description = "Endpoints para gestionar clientes y su perfil asociado")
public class ClientController {

    private final UpdateClientProfileUseCase updateClientProfileUseCase;
    private final GeocodingServiceAdapter geocodingService;
    private final AuthenticatedUserService authenticatedUserService;

    // 🔹 FRONTEND: recibe placeId
    @PutMapping("/update-profile-frontend")
    @Operation(
            summary = "Actualizar perfil de cliente (frontend)",
            description = """
                    Permite que el cliente autenticado actualice su información personal y ubicación usando `placeId`.
                    El servicio obtiene la ubicación completa desde la API de geocodificación.
                    """
    )
    public ResponseEntity<?> updateProfileFrontend(@RequestBody UpdateClientProfileFrontendDTO dto) {
        var user = authenticatedUserService.getAuthenticatedUser();
        try {
            LocationDTO locationDTO = null;

            if (dto.placeId() != null && !dto.placeId().isBlank()) {
                locationDTO = geocodingService.getLocationFromPlaceId(dto.placeId());
            }

            var updated = updateClientProfileUseCase.execute(user.getId(), new UpdateClientProfileDTO(
                    dto.dniOrDocument(),
                    dto.preferredPayment(),
                    dto.newsletter(),
                    locationDTO
            ));

            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "Perfil de cliente actualizado correctamente (frontend)",
                    "data", updated
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "status", "error",
                    "message", e.getMessage()
            ));
        }
    }

    // 🔹 BACKEND: recibe LocationDTO completa
    @PutMapping("/update-profile-backend")
    @Operation(
            summary = "Actualizar perfil de cliente (backend)",
            description = """
                    Permite actualizar el perfil del cliente enviando una `LocationDTO` completa.
                    Utilizado por paneles administrativos o integraciones internas.
                    """
    )
    public ResponseEntity<?> updateProfileBackend(@RequestBody UpdateClientProfileBackendDTO dto) {
        var user = authenticatedUserService.getAuthenticatedUser();
        try {
            var updated = updateClientProfileUseCase.execute(user.getId(), new UpdateClientProfileDTO(
                    dto.dniOrDocument(),
                    dto.preferredPayment(),
                    dto.newsletter(),
                    dto.location()
            ));

            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "Perfil de cliente actualizado correctamente (backend)",
                    "data", updated
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "status", "error",
                    "message", e.getMessage()
            ));
        }
    }
}

