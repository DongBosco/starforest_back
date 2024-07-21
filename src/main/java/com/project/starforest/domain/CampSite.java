package com.project.starforest.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.geo.Point;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Table(name = "camp_site")
@Entity
public class CampSite {

    @Id
    private Long id;

    @Column(length = 255)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String line_intro;

    @Column(columnDefinition = "TEXT")
    private String intro;

    private Integer allar;

    @Column(columnDefinition = "TEXT")
    private String feature_nm;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean is_glamp;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean is_carvan;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean is_auto;

    @Column(length = 30)
    private String sigungu_nm;

    @Column(columnDefinition = "TEXT")
    private String add1;

    @Column(length = 100)
    private String add2;

    private Double mapx;
    private Double mapy;

    @Column(length = 20)
    private String tel;

    @Column(columnDefinition = "TEXT")
    private String homepage;

    private Integer price;

    @Column(nullable = true, columnDefinition = "TINYINT(1)")
    private Boolean animal_cmg_cl;

    @Column(columnDefinition = "TEXT")
    private String glamp_inner_fclty;

    @Column(columnDefinition = "TEXT")
    private String carav_inner_fclty;

    @Column(columnDefinition = "TEXT")
    private String posbl_fclty_cl;

    @Column(columnDefinition = "TEXT")
    private String thema_envrn_cl;

    @Column(length = 20)
    private String braziel_cl;

    @Column(columnDefinition = "TEXT")
    private String sbrs_cl;

    @Column(length = 255)
    private String eqpmn_lend_cl;

    @Column(columnDefinition = "TEXT")
    private String first_image_url;

    @Column(name = "location")
    private Point location;
}