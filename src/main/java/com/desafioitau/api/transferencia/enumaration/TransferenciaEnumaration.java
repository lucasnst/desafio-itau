package com.desafioitau.api.transferencia.enumaration;

public enum TransferenciaEnumaration {

	BASE_URL("http://wiremock:8080");

	private final String id;

	public String getId() {
		return id;
	}

	TransferenciaEnumaration(String id) {
		this.id = id;
	}
}
