package br.com.fiap.ez.fastfood;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

public class TokenJWTService {

	private final Algorithm jwtAlgorithm;
    private final long expirationTimeInMillis;

    /**
     * Construtor do TokenJWTService.
     * 
     * @param secretKey A chave secreta do JWT.
     * @param expirationTime Tempo de expiração do token.
     */
    public TokenJWTService(@Value("${jwt.secret.key}") String secretKey,
                           @Value("${jwt.token.expiration}") long expirationTime) {
        this.jwtAlgorithm = Algorithm.HMAC256(secretKey);
        this.expirationTimeInMillis = expirationTime;
    }

    /**
     * Gera um token JWT para o usuário autenticado.
     *
     * @param user O objeto User contendo o CPF do usuário.
     * @return O token JWT gerado.
     */
    public String generateToken(User user) {
        return JWT.create()
                .withSubject(user.getCpf()) // Define o CPF como o "subject"
                .withClaim("cpf", user.getCpf()) // Adiciona o CPF como claim
                .withIssuedAt(new Date()) // Data de emissão
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTimeInMillis)) // Expiração
                .sign(jwtAlgorithm); // Assina o token
    }

    /**
     * Valida um token JWT recebido.
     *
     * @param token O token JWT a ser validado.
     * @return true se o token for válido, false caso contrário.
     */
    public boolean validateToken(String token) {
        try {
            JWT.require(jwtAlgorithm)
                .build()
                .verify(token); // Verifica a assinatura e a validade do token
            return true;
        } catch (Exception e) {
            // Token inválido ou expirado
            return false;
        }
    }
}
