package com.springactionsdeploy.api.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springactionsdeploy.api.domain.member.entity.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
}
