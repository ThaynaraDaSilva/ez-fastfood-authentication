package br.com.fiap.ez.fastfood;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationHandler implements RequestHandler<Map<String, String>, Map<String, String>> {

	private final UserRepository userRepository;
    private final TokenJWTService tokenJWTService;

    public AuthenticationHandler() {
        // Inicializa manualmente as dependências
        this.userRepository = new UserRepository(); // Substitua pelo seu repositório real
     // Obtém valores das variáveis de ambiente
        String secretKey = System.getenv("JWT_SECRET_KEY");
        long expirationTime = Long.parseLong(System.getenv("JWT_TOKEN_EXPIRATION"));

        this.tokenJWTService = new TokenJWTService(secretKey, expirationTime); 
    }

    @Override
    public Map<String, String> handleRequest(Map<String, String> event, Context context) {
        String cpf = event.get("cpf");
        String password = event.get("password");

        Map<String, String> response = new HashMap<>();
        try {
            // Busca o usuário no banco
            User user = userRepository.findByCpf(cpf);
            if (user == null) {
                throw new IllegalArgumentException("User not found");
            }

            // Valida a senha
            if (!user.getPassword().equals(password)) {
                throw new IllegalArgumentException("Invalid credentials");
            }

            // Gera o token JWT
            String token = tokenJWTService.generateToken(user);

            // Resposta de sucesso
            response.put("token", token);
            response.put("message", "Authentication successful");
        } catch (IllegalArgumentException e) {
            // Erros esperados (usuário não encontrado ou credenciais inválidas)
            response.put("error", e.getMessage());
        } catch (Exception e) {
            // Erros inesperados
            context.getLogger().log("Error: " + e.getMessage());
            response.put("error", "Internal server error");
        }

        return response;
    }

}
