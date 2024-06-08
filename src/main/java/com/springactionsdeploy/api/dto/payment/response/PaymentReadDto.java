package com.springactionsdeploy.api.dto.payment.response;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record PaymentReadDto(
	Long paymentId,
	int amount,
	LocalDateTime approvedAt,
	String order,
	String galleryName
) {
}
