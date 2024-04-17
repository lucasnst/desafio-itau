package com.desafioitau.api.transferencia.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Setter
public class TransferenciaResponseDTO {

	@JsonInclude(Include.NON_NULL)
	private UUID idTransferencia;

	@JsonInclude(Include.NON_NULL)
	private String message;
}
