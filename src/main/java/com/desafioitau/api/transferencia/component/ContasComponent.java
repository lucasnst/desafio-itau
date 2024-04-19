package com.desafioitau.api.transferencia.component;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.desafioitau.api.transferencia.dto.ContaResponseDTO;
import com.desafioitau.api.transferencia.enumaration.TransferenciaEnumaration;
import com.desafioitau.api.transferencia.exception.TransferenciaException;

import reactor.util.retry.Retry;

@Component
public class ContasComponent {

	@Autowired
	private WebClient webClient;
	
	private static final Logger log = LoggerFactory.getLogger(ContasComponent.class);

	public Boolean consultaContaAtiva(String idConta, Boolean origem, double valor) throws TransferenciaException {
		
		
		log.info("method=consultaContaAtiva, step=starting, idConta={}, origem={}, valor={}", idConta, origem, valor);
		ContaResponseDTO contaResponseDTO = webClient.get().uri(TransferenciaEnumaration.BASE_URL.getId() + "/contas/{idConta}", idConta)
				.retrieve().bodyToMono(ContaResponseDTO.class).timeout(Duration.ofSeconds(2)).retryWhen(Retry.max(3)).block();
		
		log.info("method=consultaContaAtiva, step=finished, idConta={}, origem={}, valor={}", idConta, origem, valor, contaResponseDTO);

		if (!contaResponseDTO.isAtivo()) {
			if(origem)
				throw new TransferenciaException(TransferenciaEnumaration.CONTA_ORIGEM_INATIVA.getId());
			else
				throw new TransferenciaException(TransferenciaEnumaration.CONTA_DESTINO_INATIVA.getId());
		}
		if (origem) {
			this.validaSaldo(contaResponseDTO.getSaldo(), valor);
		}

		this.validaLimiteDiario(contaResponseDTO.getLimiteDiario(), valor);
		
		return Boolean.TRUE;
	}

	public Boolean validaSaldo(double saldo, double valorTransferencia) throws TransferenciaException {

		if (saldo < valorTransferencia) {
			throw new TransferenciaException(TransferenciaEnumaration.SALDO_INSUFICIENTE.getId());
		}

		return Boolean.TRUE;
	}
	
	public Boolean validaLimiteDiario(double limite, double valorTransferencia) throws TransferenciaException {
		
		if (limite < valorTransferencia) {
			throw new TransferenciaException(TransferenciaEnumaration.LIMITE_DIARIO.getId());
		}
		
		return Boolean.FALSE;
	}
}
