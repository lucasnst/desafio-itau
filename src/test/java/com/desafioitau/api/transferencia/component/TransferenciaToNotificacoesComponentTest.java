package com.desafioitau.api.transferencia.component;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.desafioitau.api.transferencia.dto.NotificacaoRequestDTO;
import com.desafioitau.api.transferencia.dto.TransferenciaRequestDTO;
import com.desafioitau.api.transferencia.dto.TransferenciaRequestDTO.Conta;

public class TransferenciaToNotificacoesComponentTest {

    @Test
    public void testTransformToNotificacao() {
        TransferenciaRequestDTO transferenciaRequestDTO = new TransferenciaRequestDTO();
        
        Conta conta = new Conta();
        
        conta.setIdOrigem("123");
        conta.setIdDestino("456");
        transferenciaRequestDTO.setConta(conta);
        transferenciaRequestDTO.setValor(100.0);

        TransferenciaToNotificacoesComponent component = new TransferenciaToNotificacoesComponent();

        NotificacaoRequestDTO notificacaoRequestDTO = component.transformToNotificacao(transferenciaRequestDTO);

        assertNotNull(notificacaoRequestDTO);
    }

}