package com.springactionsdeploy.api.dto.payment.response;

import java.time.LocalDateTime;

public record PaymentApproveDto(
	String item_code,
	LocalDateTime approved_at
) {
}
