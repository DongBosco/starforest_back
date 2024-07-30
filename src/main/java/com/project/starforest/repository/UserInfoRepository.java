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
            "WHERE u.user_email = :email")
    UserInfo getUserInfoByEmail(@Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(u) > 0 " +
            "THEN true " +
            "ELSE false " +
            "END " +
            "FROM UserInfo u " +
            "WHERE u.nick_name = :nick_name")
    boolean existsByNickName(@Param("nick_name") String nick_name);
}
