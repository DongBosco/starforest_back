package com.project.starforest.repository;

import com.project.starforest.domain.Member;
import com.project.starforest.dto.member.MemberDTO;
import com.project.starforest.dto.member.RegisterDTO;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MemberRepository extends JpaRepository<Member, String> {

	@EntityGraph(attributePaths = {"memberRoleList"})
	@Query("SELECT m " +
			"FROM Member m " +
			"WHERE m.email = :email")
	Member getWithRole(@Param("email") String email);

	@Query("SELECT m " +
			"FROM Member m " +
			"WHERE m.email = :email")
	Member findByEmail(@Param("email") String email);
}
