package com.desafioitau.api.transferencia.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.desafioitau.api.transferencia.dto.ContaResponseDTO;
import com.desafioitau.api.transferencia.exception.TransferenciaException;
import com.desafioitau.api.transferencia.service.BacenService;

@Component
public class ContasComponent {

	@Autowired
	private WebClient webClient;
	
	private static final Logger log = LoggerFactory.getLogger(ContasComponent.class);

	public Boolean consultaContaAtiva(String idConta, Boolean origem, double valor) throws TransferenciaException {
		
		
		log.info("method=consultaContaAtiva, step=starting, idConta={}, origem={}, valor={}", idConta, origem, valor);
		ContaResponseDTO contaResponseDTO = webClient.get().uri("http://wiremock:8080/contas/{idConta}", idConta)
				.retrieve().bodyToMono(ContaResponseDTO.class).block();
		
		log.info("method=consultaContaAtiva, step=finished, idConta={}, origem={}, valor={}", idConta, origem, valor, contaResponseDTO);

		if (!contaResponseDTO.isAtivo()) {
			if(origem)
				throw new TransferenciaException("CONTA ORIGEM INATIVA.");
			else
				throw new TransferenciaException("CONTA DESTINO INATIVA.");
		}
		if (origem) {
			this.validaSaldo(contaResponseDTO.getSaldo(), valor);
		}

		this.validaLimiteDiario(contaResponseDTO.getLimiteDiario(), valor);
		
		return Boolean.TRUE;
	}

	public Boolean validaSaldo(double saldo, double valorTransferencia) throws TransferenciaException {

		if (saldo < valorTransferencia) {
			throw new TransferenciaException("SALDO INSUFICIENTE.");
		}

		return Boolean.TRUE;
	}
	
	public Boolean validaLimiteDiario(double limite, double valorTransferencia) throws TransferenciaException {
		
		if (limite < valorTransferencia) {
			throw new TransferenciaException("VOCÊ EXCEDEU O LIMITE DIARIO");
		}
		
		return Boolean.FALSE;
	}
}
