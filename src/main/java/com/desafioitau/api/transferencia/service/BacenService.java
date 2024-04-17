package com.desafioitau.api.transferencia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.desafioitau.api.transferencia.dto.NotificacaoRequestDTO;
import com.desafioitau.api.transferencia.exception.TransferenciaException;

import reactor.util.retry.Retry;

@Service
public class BacenService {

	@Autowired
	private WebClient webClient;
	
	private static final Logger log = LoggerFactory.getLogger(BacenService.class);

	public void comunicaBacen(NotificacaoRequestDTO notificacaoRequestDTO) throws Throwable {

		try {
			log.info("method=comunicaBacen, step=starting, notificacaoRequestDTO={}", notificacaoRequestDTO);
			webClient.post().uri("http://wiremock:8080/notificacoes").bodyValue(notificacaoRequestDTO).retrieve()
					.bodyToMono(String.class).retryWhen(Retry.max(3)).block();
			log.info("method=comunicaBacen, step=finished, notificacaoRequestDTO={}", notificacaoRequestDTO);

		} catch (Throwable e) {

			if ("Retries exhausted: 3/3".contains(e.getMessage())) {
				throw new TransferenciaException("NÃO FOI POSSIVEL CONTACTAR O BACEN.");
//			} else {
				
			}
		}

	}
}
