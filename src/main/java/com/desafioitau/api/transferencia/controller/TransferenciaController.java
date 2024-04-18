package com.desafioitau.api.transferencia.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.desafioitau.api.transferencia.component.TransferenciaToNotificacoesComponent;
import com.desafioitau.api.transferencia.dto.NotificacaoRequestDTO;
import com.desafioitau.api.transferencia.dto.TransferenciaRequestDTO;
import com.desafioitau.api.transferencia.dto.TransferenciaResponseDTO;
import com.desafioitau.api.transferencia.service.BacenService;
import com.desafioitau.api.transferencia.service.TransferenciaService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class TransferenciaController {

	@Autowired
	private TransferenciaService transferenciaService;

	@Autowired
	private BacenService bacenService;

	@Autowired
	private TransferenciaToNotificacoesComponent transferenciaToNotificacoesComponent;

	@PostMapping("/transferencia")
	@RateLimiter(name = "rateLimiterApi")
	public ResponseEntity<TransferenciaResponseDTO> efetuarTransferencia(
			@RequestBody TransferenciaRequestDTO transferenciaRequestDTO) throws Throwable {
		TransferenciaResponseDTO transferenciaResponseDTO = new TransferenciaResponseDTO();
		NotificacaoRequestDTO notificacaoRequestDTO = new NotificacaoRequestDTO();

		transferenciaService.realizaTransferencia(transferenciaRequestDTO);

		notificacaoRequestDTO = transferenciaToNotificacoesComponent.transformToNotificacao(transferenciaRequestDTO);

		bacenService.comunicaBacen(notificacaoRequestDTO);

		UUID uuid = UUID.randomUUID();

		transferenciaResponseDTO.setIdTransferencia(uuid);

		return ResponseEntity.ok().body(transferenciaResponseDTO);

	}
}
