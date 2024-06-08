package com.springactionsdeploy.global.error.exception;

import com.springactionsdeploy.global.error.model.ErrorCode;

public class NotFoundException extends DartException {
	public NotFoundException(ErrorCode errorCode) { super(errorCode);}
}
