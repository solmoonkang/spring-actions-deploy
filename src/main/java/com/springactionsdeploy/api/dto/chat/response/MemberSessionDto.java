package com.springactionsdeploy.api.dto.chat.response;

public record MemberSessionDto(
	String nickname,
	String sessionId,
	String destination
) {
}
