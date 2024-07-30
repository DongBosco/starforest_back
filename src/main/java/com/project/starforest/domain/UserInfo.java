package com.project.starforest.domain;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import lombok.extern.log4j.Log4j2;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Log4j2
@Table(name = "user_info")
public class UserInfo {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinColumn(name = "user_email", referencedColumnName = "email", insertable = false, updatable = false)
        private Member user_email;

        @Column(name = "name", length = 20, nullable = false)
        private String name;

        @Column(name = "profile_url",columnDefinition = "TEXT")
        private String profile_url;

        @Column(name = "introduce", length = 200)
        private String introduce;

        @Column(name = "nick_name", length = 20, nullable = false, unique = true)
        private String nick_name;

        @Column(name = "login_type")
        private Integer login_type;

        @Column(name = "grade")
        private Integer grade;

}
