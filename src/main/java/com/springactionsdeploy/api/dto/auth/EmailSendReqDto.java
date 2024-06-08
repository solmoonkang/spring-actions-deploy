package com.springactionsdeploy.api.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailSendReqDto(
	@Email
	@NotBlank(message = "[❎ ERROR] 이메일을 입력해주세요.")
	String email
) {
}
