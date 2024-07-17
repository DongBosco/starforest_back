package com.project.starforest.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "camp_image")
public class CampImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer imageIndex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campId", referencedColumnName = "id")
    private CampImage campSiteId;

    @Column(columnDefinition = "TEXT")
    private String imageURL;

    private Timestamp createdAt;

    // Additional methods can be added here if necessary, such as update operations or complex business logic.
}