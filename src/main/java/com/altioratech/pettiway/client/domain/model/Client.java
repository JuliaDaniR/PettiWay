package com.altioratech.pettiway.client.domain.model;

import com.altioratech.pettiway.user.domain.model.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    private UUID id;                     // ID del perfil client (no del user)
    private User user;                 // Relación con User
    private UUID locationId;             // Referencia a la ubicación principal

    private String preferredPayment;     // Método de pago preferido
    private String addressDetail;        // Detalle adicional (piso, depto)
    private String dniOrDocument;        // Documento de identidad
    private Boolean newsletter;          // Suscripción a novedades

    private List<UUID> petIds;           // Mascotas del cliente
    private List<UUID> purchaseIds;      // Compras realizadas
    private List<UUID> productIds;       // Productos publicados (si vende)
    private List<UUID> bookingIds;       // Reservas de servicios

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}