package com.desafioitau.api.transferencia.enumaration;

public enum TransferenciaEnumaration {

	BASE_URL("http://localhost:9191");

	private final String id;

	public String getId() {
		return id;
	}

	TransferenciaEnumaration(String id) {
		this.id = id;
	}
}
