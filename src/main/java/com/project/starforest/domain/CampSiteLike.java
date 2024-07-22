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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "camp_site_id", referencedColumnName = "id")
    private CampSite camp_site_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "email")
    private Member user;

    private Timestamp created_at;

    @PrePersist
    protected void onCreate() {
        this.created_at = new Timestamp(System.currentTimeMillis());
    }

    public void updateTimestamp() {
        this.created_at = new Timestamp(System.currentTimeMillis());
    }
}