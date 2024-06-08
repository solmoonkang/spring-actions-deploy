package com.springactionsdeploy.global.error.exception;

import com.springactionsdeploy.global.error.model.ErrorCode;

public class BadRequestException extends DartException {
	public BadRequestException(ErrorCode errorCode) { super(errorCode); }
}
