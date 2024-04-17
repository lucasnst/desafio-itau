package com.desafioitau.api.transferencia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.desafioitau.api.transferencia.dto.TransferenciaResponseDTO;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<String> geralException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(TransferenciaException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<TransferenciaResponseDTO> handleBookNotFound(TransferenciaException ex) {
    	TransferenciaResponseDTO transferenciaResponseDTO = new TransferenciaResponseDTO();
    	transferenciaResponseDTO.setMessage(ex.getMessage());
        return new ResponseEntity<>(transferenciaResponseDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
