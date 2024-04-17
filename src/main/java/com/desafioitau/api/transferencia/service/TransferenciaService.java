package com.desafioitau.api.transferencia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafioitau.api.transferencia.component.ConsultaClienteComponent;
import com.desafioitau.api.transferencia.component.ContasComponent;
import com.desafioitau.api.transferencia.dto.TransferenciaRequestDTO;
import com.desafioitau.api.transferencia.exception.TransferenciaException;

@Service
public class TransferenciaService {
	
	@Autowired
	private ConsultaClienteComponent consultaClienteComponent;
	
	@Autowired
	private ContasComponent contasComponent;

	public void realizaTransferencia(TransferenciaRequestDTO transferenciaRequestDTO) throws TransferenciaException {
		
		consultaClienteComponent.consultaCliente(transferenciaRequestDTO.getIdCliente());
		contasComponent.consultaContaAtiva(transferenciaRequestDTO.getConta().getIdOrigem(), true, transferenciaRequestDTO.getValor());
		contasComponent.consultaContaAtiva(transferenciaRequestDTO.getConta().getIdDestino(), false, 0);
		
	}
}
