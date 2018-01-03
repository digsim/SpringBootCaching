package org.digsim.homelinux.caching.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author AdNovum Informatik AG
 * Created on 03/01/18.
 */
@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	void handleBadRequests(HttpServletResponse response) throws IOException {
		//response.sendError(HttpStatus.BAD_REQUEST.value(), "Please try again and with a non empty string as 'name'");
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}
}
