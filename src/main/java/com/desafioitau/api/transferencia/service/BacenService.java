package com.desafioitau.api.transferencia.service;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.desafioitau.api.transferencia.dto.NotificacaoRequestDTO;
import com.desafioitau.api.transferencia.enumaration.TransferenciaEnumaration;
import com.desafioitau.api.transferencia.exception.TransferenciaException;

import reactor.util.retry.Retry;

@Service
public class BacenService {

	@Autowired
	private WebClient webClient;
	
	private static final Logger log = LoggerFactory.getLogger(BacenService.class);

	public void comunicaBacen(NotificacaoRequestDTO notificacaoRequestDTO) throws Throwable {

		try {
			webClient.post().uri(TransferenciaEnumaration.BASE_URL.getId() + "/notificacoes").bodyValue(notificacaoRequestDTO).retrieve()
					.bodyToMono(String.class).retryWhen(Retry.max(3)).timeout(Duration.ofSeconds(1)).block();

		} catch (Throwable e) {

			if ("Retries exhausted: 3/3".contains(e.getMessage())) {
				throw new TransferenciaException("NÃO FOI POSSIVEL CONTACTAR O BACEN.");
			}
		}

	}
}
