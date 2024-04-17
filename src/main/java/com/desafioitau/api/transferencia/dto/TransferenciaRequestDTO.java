package com.desafioitau.api.transferencia.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferenciaRequestDTO {
	
	@NotBlank
    private String idCliente;
	@NotBlank
    private double valor;
	//@NotBlank
    private Conta conta;

    @Getter
    @Setter
    public static class Conta {
    	@NotBlank
        private String idOrigem;
    	@NotBlank
        private String idDestino;
    }
}
