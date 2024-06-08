package com.springactionsdeploy.api.dto.auth;

import lombok.Builder;

@Builder
public record TokenResDto(
	String accessToken
) {
}
