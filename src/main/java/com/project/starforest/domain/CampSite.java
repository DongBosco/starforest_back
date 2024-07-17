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
public class CampSite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 255)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String lineIntro;

    @Column(columnDefinition = "TEXT")
    private String intro;

    private Integer allar;

    @Column(columnDefinition = "TEXT")
    private String featureNm;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isGlamp;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isCarvan;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isAuto;

    @Column(length = 30)
    private String sigunguNm;

    @Column(columnDefinition = "TEXT")
    private String add1;

    @Column(length = 100)
    private String add2;

    private Double mapX;
    private Double mapY;

    @Column(length = 20)
    private String tel;

    @Column(columnDefinition = "TEXT")
    private String homepage;

    private Integer price;

    @Column(nullable = true, columnDefinition = "TINYINT(1)")
    private Boolean animalCmgCl;

    @Column(columnDefinition = "TEXT")
    private String glampInnerFclty;

    @Column(columnDefinition = "TEXT")
    private String caravInnerFclty;

    @Column(columnDefinition = "TEXT")
    private String posblFcltyCl;

    @Column(columnDefinition = "TEXT")
    private String themaEnvrnCl;

    @Column(length = 20)
    private String brazielCl;

    @Column(columnDefinition = "TEXT")
    private String sbrsCl;

    @Column(length = 255)
    private String eqpmnLendCl;

    @Column(columnDefinition = "TEXT")
    private String firstImageUrl;

    @Column(name = "location")
    private Point location;
}