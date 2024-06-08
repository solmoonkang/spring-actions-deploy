package com.springactionsdeploy.global.error.exception;

import com.springactionsdeploy.global.error.model.ErrorCode;

public class UnauthorizedException extends DartException {
	public UnauthorizedException(ErrorCode errorCode) { super(errorCode);}
}
