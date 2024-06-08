package com.springactionsdeploy.global.error.exception;

import com.springactionsdeploy.global.error.model.ErrorCode;

public class ServerErrorException extends DartException {
	public ServerErrorException(ErrorCode errorCode) { super(errorCode); }
}
