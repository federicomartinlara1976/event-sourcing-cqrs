package net.bounceme.chronos.eventsourcingcqrs.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.eventsourcingcqrs.exceptions.NotFoundException;

@RestControllerAdvice
@Slf4j
class GlobalControllerExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Map<String, String>> handleNotFoundException(NotFoundException e) {
		String msg = e.getMessage();
		log.error("ERROR: {}", msg);

		Map<String, String> errors = new HashMap<>();
		errors.put("mensaje", msg);

		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(errors);
	}
}
