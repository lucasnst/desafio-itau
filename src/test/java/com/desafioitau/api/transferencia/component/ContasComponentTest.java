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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.desafioitau.api.transferencia.dto.ContaResponseDTO;
import com.desafioitau.api.transferencia.exception.TransferenciaException;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class ContasComponentTest {

    @Mock
    private WebClient webClient;

    @InjectMocks
    private ContasComponent contasComponent;

//    @Before
//    public void setup() {
//        when(webClient.get()).thenReturn(mock(WebClient.RequestHeadersUriSpec.class));
//    }
//
//    @Test
//    public void testConsultaContaAtivaContaAtiva() throws TransferenciaException {
//    	
//    	when(webClientMock.get())
//        .thenReturn(requestHeadersUriSpecMock);
//        String idConta = "123456";
//        Boolean origem = true;
//        double valor = 100.0;
//        ContaResponseDTO contaResponseDTO = new ContaResponseDTO();
//        contaResponseDTO.setAtivo(true);
//        contaResponseDTO.setSaldo(200.0);
//        contaResponseDTO.setLimiteDiario(500.0);
//        when(webClient.get().uri(anyString(), anyString()).retrieve()).thenReturn(mock(ResponseSpec.class));
//        when(webClient.get().uri(anyString(), anyString()).retrieve().bodyToMono(ContaResponseDTO.class).block()).thenReturn(contaResponseDTO);
//
//        Boolean resultado = contasComponent.consultaContaAtiva(idConta, origem, valor);
//
//        assertTrue(resultado);
//    }

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