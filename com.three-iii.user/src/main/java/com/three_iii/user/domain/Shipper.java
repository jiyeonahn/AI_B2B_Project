package com.three_iii.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "p_shipper")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shipper {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ShipperType type;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false, name = "hub_id")
    private UUID hubId;

    private Shipper(ShipperType type, User user, UUID hubId) {
        this.type = type;
        this.user = user;
        this.hubId = hubId;
    }

    public static Shipper of(ShipperType type, User user, UUID hubId) {
        return new Shipper(type, user, hubId);
    }
}
