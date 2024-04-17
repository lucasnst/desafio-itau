package com.desafioitau.api.transferencia;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import io.netty.channel.ChannelOption;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebConfig {

	@Bean
	public WebClient create() {

		HttpClient httpClient = HttpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000)
				.option(ChannelOption.SO_TIMEOUT, 2000).responseTimeout(Duration.ofMillis(2000));

		return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).build();
	}

}