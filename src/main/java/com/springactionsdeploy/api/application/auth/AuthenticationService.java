package com.springactionsdeploy.api.application.auth;


import static com.springactionsdeploy.global.common.util.AuthConstant.*;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.springactionsdeploy.api.domain.member.entity.Member;
import com.springactionsdeploy.api.domain.member.repository.MemberRepository;
import com.springactionsdeploy.api.dto.auth.TokenReqDto;
import com.springactionsdeploy.api.dto.auth.TokenResDto;
import com.springactionsdeploy.api.dto.member.request.LoginReqDto;
import com.springactionsdeploy.api.dto.member.response.LoginResDto;
import com.springactionsdeploy.global.error.exception.BadRequestException;
import com.springactionsdeploy.global.error.exception.NotFoundException;
import com.springactionsdeploy.global.error.model.ErrorCode;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtProviderService jwtProviderService;

	@Transactional
	public LoginResDto login(LoginReqDto loginReqDto, HttpServletResponse response) {
		final Member member = findByMemberEmail(loginReqDto.email());
		validatePasswordMatch(loginReqDto.password(), member.getPassword());

		final String accessToken = jwtProviderService.generateToken(member.getEmail(), member.getNickname());

		response.setHeader(ACCESS_TOKEN_HEADER, accessToken);

		return new LoginResDto(accessToken);
	}

	public TokenResDto reissue(TokenReqDto tokenReqDto, HttpServletResponse response) {
		final String newAccessToken = jwtProviderService.reGenerateToken(tokenReqDto.expiredToken());

		response.setHeader(ACCESS_TOKEN_HEADER, newAccessToken);
		response.setStatus(HttpServletResponse.SC_CREATED);

		return new TokenResDto(newAccessToken);
	}

	private Member findByMemberEmail(String email) {
		return memberRepository.findByEmail(email)
			.orElseThrow(() -> new NotFoundException(ErrorCode.FAIL_MEMBER_NOT_FOUND));
	}

	private void validatePasswordMatch(String password, String encodedPassword) {
		if (!passwordEncoder.matches(password, encodedPassword)) {
			throw new BadRequestException(ErrorCode.FAIL_INCORRECT_PASSWORD);
		}
	}
}
