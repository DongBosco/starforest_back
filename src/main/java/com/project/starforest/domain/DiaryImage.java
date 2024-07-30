package com.project.starforest.domain;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Entity
@Table(name = "diary_image")
public class DiaryImage {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "diary_id", referencedColumnName = "id")
    private Diary diary;

    @Column(columnDefinition = "TEXT")
    private String image_url;

    private LocalDateTime created_at;

    public void changeId(Long id) {
        this.id = id;
    }

    public void changeDiary(Diary diary) {
        this.diary = diary;
    }

    public void changeImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void changeCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}