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
    private Long id;

    private Integer image_index;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "camp_id", referencedColumnName = "id")
    private CampImage camp_site_id;

    @Column(columnDefinition = "TEXT")
    private String image_url;

    private Timestamp created_at;

}