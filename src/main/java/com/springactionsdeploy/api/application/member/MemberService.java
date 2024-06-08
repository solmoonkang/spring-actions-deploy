package com.springactionsdeploy.api.application.member;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.springactionsdeploy.api.domain.auth.entity.AuthUser;
import com.springactionsdeploy.api.domain.member.entity.Member;
import com.springactionsdeploy.api.domain.member.repository.MemberRepository;
import com.springactionsdeploy.api.dto.member.request.MemberUpdateDto;
import com.springactionsdeploy.api.dto.member.request.NicknameDuplicationCheckDto;
import com.springactionsdeploy.api.dto.member.request.SignUpDto;
import com.springactionsdeploy.api.dto.member.response.MemberProfileResDto;
import com.springactionsdeploy.global.error.exception.NotFoundException;
import com.springactionsdeploy.global.error.model.ErrorCode;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

	private final MemberRepository memberRepository;
	private final NicknameValidator nicknameValidator;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public void signUp(SignUpDto signUpDto) {
		final String encodedPassword = passwordEncoder.encode(signUpDto.password());
		final Member member = Member.signup(signUpDto, encodedPassword);

		memberRepository.save(member);
	}

	public MemberProfileResDto getMemberProfile(AuthUser authUser) {
		final Member member = findMemberByEmail(authUser.email());

		return convertToMemberProfileResDto(member);
	}

	@Transactional
	public void updateMemberProfile(AuthUser authUser, MemberUpdateDto memberUpdateDto) {
		final Member member = findMemberByEmail(authUser.email());
		final String encodedPassword = passwordEncoder.encode(memberUpdateDto.password());

		nicknameValidator.validate(memberUpdateDto.nickname());
		member.updateMemberProfile(memberUpdateDto, encodedPassword);
	}

	public void checkNicknameDuplication(NicknameDuplicationCheckDto nicknameDuplicationCheckDto) {
		nicknameValidator.validate(nicknameDuplicationCheckDto.nickname());
	}

	private Member findMemberByEmail(String email) {
		return memberRepository.findByEmail(email)
			.orElseThrow(() -> new NotFoundException(ErrorCode.FAIL_MEMBER_NOT_FOUND));
	}

	private MemberProfileResDto convertToMemberProfileResDto(Member member) {
		return new MemberProfileResDto(member.getEmail(), member.getNickname(), member.getIntroduce(),
			member.getBank(), member.getAccount(), member.getProfileImage());
	}
}
