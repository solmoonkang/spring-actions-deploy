package com.springactionsdeploy.global.error.exception;

import com.springactionsdeploy.global.error.model.ErrorCode;

public class ForbiddenException extends DartException {
	public ForbiddenException(ErrorCode errorCode) { super(errorCode); }
}
