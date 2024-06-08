package com.springactionsdeploy.api.dto.member.response;

public record MemberProfileResDto(
	String email,
	String nickname,
	String profileImage,

	String bank,
	String account,
	String introduce
){

}
