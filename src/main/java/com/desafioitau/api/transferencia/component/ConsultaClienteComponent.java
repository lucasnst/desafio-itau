package com.desafioitau.api.transferencia.component;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.desafioitau.api.transferencia.dto.ClienteResponseDTO;
import com.desafioitau.api.transferencia.enumaration.TransferenciaEnumaration;
import com.desafioitau.api.transferencia.exception.TransferenciaException;

import io.micrometer.observation.annotation.Observed;
import reactor.util.retry.Retry;

@Component
public class ConsultaClienteComponent {

	@Autowired
	private WebClient webClient;

	public void consultaCliente(String idCliente) throws TransferenciaException {

		try {
			webClient.get().uri(TransferenciaEnumaration.BASE_URL.getId() + "/clientes/{idCliente}", idCliente).retrieve()
					.bodyToMono(ClienteResponseDTO.class).timeout(Duration.ofSeconds(2)).retryWhen(Retry.max(3)).block();
		} catch (WebClientResponseException e) {

			if (HttpStatus.NOT_FOUND.equals(e.getStatusCode())) {
				throw new TransferenciaException(TransferenciaEnumaration.CLIENTE_NAO_EXISTE.getId());
			}
		}

	}
}
