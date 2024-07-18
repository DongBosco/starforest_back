package com.project.starforest.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Log4j2
@Entity
@Table(name = "product_review")
public class ProductReview {

    @Id
    private int id;

    @Column(name = "productId", nullable = false)
    private int productId;

    @Column(name = "userId", nullable = false)
    private int userId;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(name = "createdAt", nullable = true)
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "email", insertable = false, updatable = false)
    private Member member;

    public void changeContent(String content) {
        if (content != null && !content.trim().isEmpty() && content.length() >= 10) {
            this.content = content;
        } else {
            throw new IllegalArgumentException("내용을 10자이상 입력하여 주세요.");
        }
    }

    public void changeCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}