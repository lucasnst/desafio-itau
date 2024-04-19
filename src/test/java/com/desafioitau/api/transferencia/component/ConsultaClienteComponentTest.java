package com.desafioitau.api.transferencia.component;

import static org.mockito.Mockito.when;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;
import com.desafioitau.api.transferencia.dto.ClienteResponseDTO;
import com.desafioitau.api.transferencia.exception.TransferenciaException;
import com.fasterxml.jackson.core.JsonProcessingException;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
public class ConsultaClienteComponentTest {

	@Mock
	private WebClient webClient;

	@InjectMocks
	private ConsultaClienteComponent consultaClienteComponent;

	@Test

	public void callValidateArqc() throws TransferenciaException, JsonProcessingException {

		ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();
		WebClient.RequestHeadersUriSpec requestHeaderUriSpec = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
		WebClient.RequestHeadersSpec requestHeadersSpec = Mockito.mock(WebClient.RequestHeadersSpec.class);
		WebClient.ResponseSpec responseSpec = Mockito.mock(WebClient.ResponseSpec.class);
		when(webClient.get()).thenReturn(requestHeaderUriSpec);
		when(requestHeaderUriSpec.uri(Mockito.anyString(), Mockito.anyString())).thenReturn(requestHeadersSpec);
		when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
		when(responseSpec.bodyToMono(ClienteResponseDTO.class)).thenReturn(Mono.just(clienteResponseDTO));
		consultaClienteComponent.consultaCliente("2ceb26e9-7b5c-417e-bf75-ffaa66e3a76f");

	}

}