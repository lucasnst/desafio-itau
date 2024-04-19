package com.desafioitau.api.transferencia.enumaration;

public enum TransferenciaEnumaration {

	BASE_URL("http://wiremock:8080"),
	CLIENTE_NAO_EXISTE("CLIENTE NÃO EXISTE"),
	CONTA_ORIGEM_INATIVA("CONTA ORIGEM INATIVA"),
	CONTA_DESTINO_INATIVA("CONTA DESTINO INATIVA"),
	SALDO_INSUFICIENTE("SALDO INSUFICIENTE"),
	LIMITE_DIARIO("VOCÊ EXCEDEU O LIMITE DIARIO"),
	ERRO_INESPERADO("OCORREU UM ERRO INESPERADO");

	private final String id;

	public String getId() {
		return id;
	}

	TransferenciaEnumaration(String id) {
		this.id = id;
	}
}
