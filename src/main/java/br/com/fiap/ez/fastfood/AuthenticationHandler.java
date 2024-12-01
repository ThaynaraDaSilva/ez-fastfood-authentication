package br.com.fiap.ez.fastfood;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationHandler implements RequestHandler<Map<String, Object>, Map<String, String>> {


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
    public Map<String, String> handleRequest(Map<String, Object> event, Context context) {
        Map<String, String> response = new HashMap<>();
        try {
            // Extract the raw JSON body
            String body = (String) event.get("body");
            if (body == null || body.isEmpty()) {
                throw new IllegalArgumentException("Request body is missing");
            }

            // Parse the JSON body into a Map
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> bodyMap = objectMapper.readValue(body, Map.class);

            // Extract CPF and password
            String cpf = bodyMap.get("cpf");
            String password = bodyMap.get("password");

            if (cpf == null || password == null) {
                throw new IllegalArgumentException("CPF or password is missing");
            }

            // Validate user credentials
            User user = userRepository.findByCpf(cpf);
            if (user == null || !user.getPassword().equals(password)) {
                throw new IllegalArgumentException("Invalid credentials");
            }

            // Generate JWT token
            String token = tokenJWTService.generateToken(user);

            // Build a successful response
            response.put("message", "Authentication successful");
            response.put("token", token);
        } catch (IllegalArgumentException e) {
            // Handle expected errors (e.g., invalid input or authentication failure)
            context.getLogger().log("Error: " + e.getMessage());
            response.put("error", e.getMessage());
        } catch (Exception e) {
            // Handle unexpected errors
            context.getLogger().log("Internal server error: " + e.getMessage());
            response.put("error", "Internal server error");
        }

        return response;
    }

}
