package com.project.starforest.repository;

import com.project.starforest.domain.Member;
import com.project.starforest.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    @Query("SELECT u " +
            "FROM UserInfo u " +
            "WHERE u.id = :id")
    UserInfo getUserInfoById(@Param("id") Long id);
}
