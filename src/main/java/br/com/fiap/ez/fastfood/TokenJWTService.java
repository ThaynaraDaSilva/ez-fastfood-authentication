package br.com.fiap.ez.fastfood;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class TokenJWTService {

	private final Algorithm jwtAlgorithm;
    private final long expirationTimeInMillis;

    /**
     * Construtor do TokenJWTService.
     *
     * @param secretKey      A chave secreta do JWT.
     * @param expirationTime Tempo de expiração do token em milissegundos.
     */
    public TokenJWTService(String secretKey, long expirationTime) {
        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalArgumentException("A chave secreta não pode ser nula ou vazia.");
        }
        if (expirationTime <= 0) {
            throw new IllegalArgumentException("O tempo de expiração deve ser maior que zero.");
        }

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
        if (user == null) {
            throw new IllegalArgumentException("O objeto User não pode ser nulo.");
        }
        if (user.getCpf() == null || user.getCpf().isEmpty()) {
            throw new IllegalArgumentException("O CPF do usuário não pode ser nulo ou vazio.");
        }

        try {
            return JWT.create()
                    .withSubject(user.getCpf()) // Define o CPF como o "subject"
                    .withClaim("cpf", user.getCpf()) // Adiciona o CPF como claim
                    .withIssuedAt(new Date()) // Data de emissão
                    .withExpiresAt(new Date(System.currentTimeMillis() + expirationTimeInMillis)) // Expiração
                    .sign(jwtAlgorithm); // Assina o token
        } catch (Exception e) {
            throw new RuntimeException("Falha ao gerar o token JWT: " + e.getMessage(), e);
        }
    }

    /**
     * Valida um token JWT recebido.
     *
     * @param token O token JWT a ser validado.
     * @return true se o token for válido, false caso contrário.
     */
    public boolean validateToken(String token) {
        if (token == null || token.isEmpty()) {
            System.err.println("O token não pode ser nulo ou vazio.");
            return false;
        }

        try {
            JWT.require(jwtAlgorithm)
                    .build()
                    .verify(token); // Verifica a assinatura e a validade do token
            return true;
        } catch (Exception e) {
            // Token inválido ou expirado
            System.err.println("Falha na validação do token: " + e.getMessage());
            return false;
        }
    }
}
