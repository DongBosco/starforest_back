package com.project.starforest.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "camp_site_like")
class CampSiteLike {

    @Id
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CampSiteId", referencedColumnName = "id")
    private CampSiteLike campSiteLike;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "UserId", referencedColumnName = "email")
    private Member user;

    private Timestamp createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public void updateTimestamp() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }
}