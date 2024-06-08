package com.springactionsdeploy.api.application.member;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import com.springactionsdeploy.api.domain.member.repository.MemberRepository;
import com.springactionsdeploy.global.error.exception.ConflictException;
import com.springactionsdeploy.global.error.model.ErrorCode;

@Component
@RequiredArgsConstructor
public class NicknameValidator {

	private final MemberRepository memberRepository;

	public void validate(String nickname) {
		if (nickname == null || nickname.trim().isEmpty()) {
			throw new IllegalArgumentException(String.valueOf(ErrorCode.FAIL_INVALID_NICKNAME_FORMAT));
		}
		if (memberRepository.existsByNickname(nickname)) {
			throw new ConflictException(ErrorCode.FAIL_NICKNAME_CONFLICT);
		}
	}
}
