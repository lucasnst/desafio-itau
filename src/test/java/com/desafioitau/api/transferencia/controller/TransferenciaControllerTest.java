package com.desafioitau.api.transferencia.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.desafioitau.api.transferencia.component.TransferenciaToNotificacoesComponent;
import com.desafioitau.api.transferencia.dto.NotificacaoRequestDTO;
import com.desafioitau.api.transferencia.dto.TransferenciaRequestDTO;
import com.desafioitau.api.transferencia.dto.TransferenciaResponseDTO;
import com.desafioitau.api.transferencia.service.BacenService;
import com.desafioitau.api.transferencia.service.TransferenciaService;

@RunWith(SpringRunner.class)
public class TransferenciaControllerTest {

    @Mock
    private TransferenciaService transferenciaService;

    @Mock
    private BacenService bacenService;

    @Mock
    private TransferenciaToNotificacoesComponent transferenciaToNotificacoesComponent;

    @InjectMocks
    private TransferenciaController transferenciaController;

    @Test
    public void testEfetuarTransferencia() throws Throwable {
        TransferenciaRequestDTO requestDTO = new TransferenciaRequestDTO();
        doNothing().when(transferenciaService).realizaTransferencia(requestDTO);
        when(transferenciaToNotificacoesComponent.transformToNotificacao(requestDTO))
                .thenReturn(new NotificacaoRequestDTO());
        doNothing().when(bacenService).comunicaBacen(any(NotificacaoRequestDTO.class));

        ResponseEntity<TransferenciaResponseDTO> response = transferenciaController.efetuarTransferencia(requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getIdTransferencia());
    }

}