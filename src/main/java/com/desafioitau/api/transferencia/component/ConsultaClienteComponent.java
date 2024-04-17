package com.desafioitau.api.transferencia.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.desafioitau.api.transferencia.dto.ClienteResponseDTO;
import com.desafioitau.api.transferencia.exception.TransferenciaException;

import io.micrometer.observation.annotation.Observed;

@Component
@Observed
public class ConsultaClienteComponent {

	@Autowired
	private WebClient webClient;

	public void consultaCliente(String idCliente) throws TransferenciaException {

		try {
			webClient.get().uri("http://wiremock:8080/clientes/{idCliente}", idCliente).retrieve()
					.bodyToMono(ClienteResponseDTO.class).block();
		} catch (WebClientResponseException e) {

			if (HttpStatus.NOT_FOUND.equals(e.getStatusCode())) {
				throw new TransferenciaException("CLIENTE NÃO EXISTE.");
			}
		}

	}
}
