package com.desafioitau.api.transferencia.service;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.desafioitau.api.transferencia.component.ConsultaClienteComponent;
import com.desafioitau.api.transferencia.component.ContasComponent;
import com.desafioitau.api.transferencia.dto.TransferenciaRequestDTO;
import com.desafioitau.api.transferencia.dto.TransferenciaRequestDTO.Conta;
import com.desafioitau.api.transferencia.exception.TransferenciaException;

@RunWith(SpringRunner.class)
public class TransferenciaServiceTest {

    @Mock
    private ConsultaClienteComponent consultaClienteComponent;

    @Mock
    private ContasComponent contasComponent;

    @InjectMocks
    private TransferenciaService transferenciaService;

    @Test
    public void testRealizaTransferencia() throws TransferenciaException {
        TransferenciaRequestDTO transferenciaRequestDTO = new TransferenciaRequestDTO();
        Conta conta = new Conta();
        
        transferenciaRequestDTO.setIdCliente("123456");
        conta.setIdOrigem("789012");
        conta.setIdDestino("345678");
        transferenciaRequestDTO.setConta(conta);
        transferenciaRequestDTO.setValor(100.0);

        transferenciaService.realizaTransferencia(transferenciaRequestDTO);

    }
}
