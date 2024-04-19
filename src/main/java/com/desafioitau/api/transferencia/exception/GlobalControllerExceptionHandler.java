package com.desafioitau.api.transferencia.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.desafioitau.api.transferencia.dto.TransferenciaResponseDTO;
import com.desafioitau.api.transferencia.enumaration.TransferenciaEnumaration;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> geralException(Exception ex) {
    	
    	log.error(ex.getMessage());
        return new ResponseEntity<>(TransferenciaEnumaration.ERRO_INESPERADO.getId(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(TransferenciaException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<TransferenciaResponseDTO> handleBookNotFound(TransferenciaException ex) {
    	TransferenciaResponseDTO transferenciaResponseDTO = new TransferenciaResponseDTO();
    	transferenciaResponseDTO.setMessage(ex.getMessage());
        return new ResponseEntity<>(transferenciaResponseDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @ExceptionHandler({ RequestNotPermitted.class })
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public void handleRequestNotPermitted() {
    }
}
