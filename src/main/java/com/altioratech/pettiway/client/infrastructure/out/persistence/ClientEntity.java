package com.altioratech.pettiway.client.infrastructure.out.persistence;

import com.altioratech.pettiway.user.infrastructure.out.persistence.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // 🔹 Relación 1:1 con User
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity user;

    private UUID locationId;

    private String dniOrDocument;
    private String preferredPayment;
    private Boolean newsletter;

    @ElementCollection
    @CollectionTable(name = "client_pets", joinColumns = @JoinColumn(name = "client_id"))
    @Column(name = "pet_id")
    private List<UUID> petIds = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "client_bookings", joinColumns = @JoinColumn(name = "client_id"))
    @Column(name = "booking_id")
    private List<UUID> bookingIds = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "client_purchases", joinColumns = @JoinColumn(name = "client_id"))
    @Column(name = "purchase_id")
    private List<UUID> purchaseIds = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "client_products", joinColumns = @JoinColumn(name = "client_id"))
    @Column(name = "product_id")
    private List<UUID> productIds = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

