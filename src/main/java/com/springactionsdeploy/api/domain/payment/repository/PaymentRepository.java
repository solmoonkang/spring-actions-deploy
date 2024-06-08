package com.springactionsdeploy.api.domain.payment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springactionsdeploy.api.domain.member.entity.Member;
import com.springactionsdeploy.api.domain.payment.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	//결제정보 최근 승인일 순
	Page<Payment> findAllByMemberOrderByApprovedAtDesc(Member member, Pageable pageable);
}
