package com.desafioitau.api.transferencia.component;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import com.desafioitau.api.transferencia.dto.ClienteResponseDTO;
import com.desafioitau.api.transferencia.dto.ContaResponseDTO;
import com.desafioitau.api.transferencia.exception.TransferenciaException;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class ContasComponentTest {

	@Mock
	private WebClient webClient;

	@InjectMocks
	private ContasComponent contasComponent;

	@Test
	public void testConsultaContaAtivaContaAtiva() throws TransferenciaException {

		ContaResponseDTO contaResponseDTO = new ContaResponseDTO();
		contaResponseDTO.setAtivo(Boolean.TRUE);
		contaResponseDTO.setSaldo(1000);
		contaResponseDTO.setLimiteDiario(2000);

		WebClient.RequestHeadersUriSpec requestHeaderUriSpec = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
		WebClient.RequestHeadersSpec requestHeadersSpec = Mockito.mock(WebClient.RequestHeadersSpec.class);
		WebClient.ResponseSpec responseSpec = Mockito.mock(WebClient.ResponseSpec.class);

		when(webClient.get()).thenReturn(requestHeaderUriSpec);
		when(requestHeaderUriSpec.uri(Mockito.anyString(), Mockito.anyString())).thenReturn(requestHeadersSpec);
		when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
		when(responseSpec.bodyToMono(ContaResponseDTO.class)).thenReturn(Mono.just(contaResponseDTO));

		String idConta = "123456";
		Boolean origem = true;
		double valor = 100.0;
		Boolean resultado = contasComponent.consultaContaAtiva(idConta, origem, valor);

		assertTrue(resultado);

	}

	@Test
	public void testValidaSaldoSaldoSuficiente() throws TransferenciaException {

		double saldo = 200.0;
		double valorTransferencia = 100.0;

		Boolean resultado = contasComponent.validaSaldo(saldo, valorTransferencia);

		assertTrue(resultado);

	}

	@Test(expected = TransferenciaException.class)
	public void testValidaSaldoSaldoInsuficiente() throws TransferenciaException {

		double saldo = 50.0;
		double valorTransferencia = 100.0;

		contasComponent.validaSaldo(saldo, valorTransferencia);

	}

	@Test
	public void testValidaLimiteDiarioDentroLimite() throws TransferenciaException {

		double limite = 500.0;
		double valorTransferencia = 100.0;

		Boolean resultado = contasComponent.validaLimiteDiario(limite, valorTransferencia);

		assertFalse(resultado);

	}

	@Test(expected = TransferenciaException.class)
	public void testValidaLimiteDiarioExcedeLimite() throws TransferenciaException {

		double limite = 100.0;
		double valorTransferencia = 200.0;

		contasComponent.validaLimiteDiario(limite, valorTransferencia);

	}

}