package br.com.fiap.ez.fastfood;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt") // Mapeia as propriedades que começam com 'jwt'

public class JwtProperties {

	private String secretKey;
	private long tokenExpiration;

	// Getters e Setters
	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public long getTokenExpiration() {
		return tokenExpiration;
	}

	public void setTokenExpiration(long tokenExpiration) {
		this.tokenExpiration = tokenExpiration;
	}

}
