package com.desafioitau.api.transferencia.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferenciaException extends Exception{

	private String message;

	public TransferenciaException(String message) {
		super();
		this.message = message;
	}
}
