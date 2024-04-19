package com.desafioitau.api.transferencia.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;
import com.desafioitau.api.transferencia.dto.NotificacaoRequestDTO;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
public class BacenServiceTest {

	@Mock
	private WebClient webClient;

	@InjectMocks
	private BacenService bacenService;

	@Test
	public void comunicaBacenTest() throws Throwable {

		NotificacaoRequestDTO notificacaoRequestDTO = new NotificacaoRequestDTO();
		WebClient.RequestBodyUriSpec requestBodyUriSpec = Mockito.mock(WebClient.RequestBodyUriSpec.class);
		WebClient.RequestHeadersSpec requestHeadersSpec = Mockito.mock(WebClient.RequestHeadersSpec.class);
		WebClient.ResponseSpec responseSpec = Mockito.mock(WebClient.ResponseSpec.class);
		Mono mono = Mockito.mock(Mono.class);
		Mockito.when(webClient.post()).thenReturn(requestBodyUriSpec);
		Mockito.when(requestBodyUriSpec.uri(Mockito.any(String.class))).thenReturn(requestBodyUriSpec);
		Mockito.when(requestBodyUriSpec.bodyValue(Mockito.any(NotificacaoRequestDTO.class)))
				.thenReturn(requestHeadersSpec);

		Mockito.when(requestHeadersSpec.header(Mockito.any(String.class), Mockito.any()))
				.thenReturn(requestHeadersSpec);

		Mockito.when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);

		Mockito.when(responseSpec.bodyToMono(Mockito.any(Class.class))).thenReturn(mono);

		Mockito.when(mono.block()).thenReturn("");

		bacenService.comunicaBacen(notificacaoRequestDTO);

	}

}