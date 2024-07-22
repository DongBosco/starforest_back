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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "email", insertable = false, updatable = false)
    private Member member;

    @Column(nullable = false)
    private String content;

    @Column(name = "created_at", nullable = true)
    private Timestamp created_at;

    public void changeContent(String content) {
        if (content != null && !content.trim().isEmpty() && content.length() >= 10) {
            this.content = content;
        } else {
            throw new IllegalArgumentException("내용을 10자이상 입력하여 주세요.");
        }
    }

    public void changeCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}