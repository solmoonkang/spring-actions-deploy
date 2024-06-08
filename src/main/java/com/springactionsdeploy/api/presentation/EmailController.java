package com.springactionsdeploy.api.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springactionsdeploy.api.application.auth.EmailService;
import com.springactionsdeploy.api.dto.auth.EmailSendReqDto;
import com.springactionsdeploy.api.dto.auth.EmailVerificationReqDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/email")
public class EmailController {

	private final EmailService emailService;

	@PostMapping("/send")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> sendEmail(@RequestBody EmailSendReqDto emailSendReqDto) {
		emailService.sendEmail(emailSendReqDto.email());
		return ResponseEntity.ok("OK");
	}

	@PostMapping("/verify")
	public ResponseEntity<String> verifyCode(@RequestBody EmailVerificationReqDto emailVerificationReqDto) {
		emailService.verifyCode(emailVerificationReqDto.email(), emailVerificationReqDto.code());
		return ResponseEntity.ok("OK");
	}
}
