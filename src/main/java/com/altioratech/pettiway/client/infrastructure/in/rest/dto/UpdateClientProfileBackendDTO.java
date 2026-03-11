package com.altioratech.pettiway.client.infrastructure.in.rest.dto;

import com.altioratech.pettiway.location.application.dto.LocationDTO;

public record UpdateClientProfileBackendDTO(
        String dniOrDocument,
        String preferredPayment,
        Boolean newsletter,
        LocationDTO location
) {
}
