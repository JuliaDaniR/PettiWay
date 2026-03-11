package com.altioratech.pettiway.client.infrastructure.in.rest.dto;

public record UpdateClientProfileFrontendDTO(
        String dniOrDocument,
        String preferredPayment,
        Boolean newsletter,
        String placeId
) {
}
