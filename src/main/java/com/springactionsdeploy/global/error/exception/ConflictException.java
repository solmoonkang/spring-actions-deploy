package com.springactionsdeploy.global.error.exception;

import com.springactionsdeploy.global.error.model.ErrorCode;

public class ConflictException extends DartException {
	public ConflictException(ErrorCode errorCode) { super(errorCode); }
}
