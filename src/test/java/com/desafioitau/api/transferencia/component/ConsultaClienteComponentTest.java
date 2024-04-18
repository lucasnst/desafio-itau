package com.desafioitau.api.transferencia.component;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;

import com.desafioitau.api.transferencia.exception.TransferenciaException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsultaClienteComponentTest {

	@Autowired
	private WebClient webClient;

	@Autowired
	private ConsultaClienteComponent consultaClienteComponent;

	@Test
	public void testConsultaClienteClienteExiste() throws TransferenciaException {
		String idCliente = "2ceb26e9-7b5c-417e-bf75-ffaa66e3a76f";

		consultaClienteComponent.consultaCliente(idCliente);

	}

	@Test(expected = TransferenciaException.class)
	public void testConsultaClienteClienteNaoExiste() throws TransferenciaException {
		String idCliente = "789012";

		consultaClienteComponent.consultaCliente(idCliente);
	}

}