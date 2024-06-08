package com.springactionsdeploy.api.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springactionsdeploy.api.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	boolean existsByNickname(String nickname);
	Optional<Member> findByEmail(String email);

}
