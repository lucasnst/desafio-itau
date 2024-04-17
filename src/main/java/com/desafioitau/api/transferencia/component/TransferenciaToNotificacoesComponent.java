package com.desafioitau.api.transferencia.component;

import org.springframework.stereotype.Component;

import com.desafioitau.api.transferencia.dto.NotificacaoRequestDTO;
import com.desafioitau.api.transferencia.dto.TransferenciaRequestDTO;
import com.desafioitau.api.transferencia.dto.NotificacaoRequestDTO.Conta;

@Component
public class TransferenciaToNotificacoesComponent {

	public NotificacaoRequestDTO transformToNotificacao(TransferenciaRequestDTO transferenciaRequestDTO) {
		NotificacaoRequestDTO notificacaoRequestDTO = new NotificacaoRequestDTO();
		Conta conta = new Conta();
		conta.setIdDestino(transferenciaRequestDTO.getConta().getIdDestino());
		conta.setIdOrigem(transferenciaRequestDTO.getConta().getIdDestino());
		notificacaoRequestDTO.setConta(conta);
		notificacaoRequestDTO.setValor(transferenciaRequestDTO.getValor());

		return notificacaoRequestDTO;
	}
}
