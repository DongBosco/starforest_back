package com.project.starforest.domain;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

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
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "diaryId", referencedColumnName = "id")
    private Diary diary;

    @Column(columnDefinition = "TEXT")
    private String imageURL;

    private Timestamp createdAt;

    public void changeId(int id) {
        this.id = id;
    }

    public void changeDiary(Diary diary) {
        this.diary = diary;
    }

    public void changeImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void changeCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}