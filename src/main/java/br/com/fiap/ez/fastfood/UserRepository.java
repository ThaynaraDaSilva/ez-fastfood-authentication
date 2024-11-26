package br.com.fiap.ez.fastfood;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository {

	// Configurações de conexão via variáveis de ambiente
	private final String dbUrl = System.getenv("DB_URL");
	private final String dbUser = System.getenv("DB_USER");
	private final String dbPassword = System.getenv("DB_PASSWORD");
//JWT_SECRET_KEY: chave-secreta

	public UserRepository() {
		if (dbUrl == null || dbUser == null || dbPassword == null) {
			throw new IllegalArgumentException("Database environment variables are not properly configured.");
		}
	}

	/**
	 * Busca um usuário no banco de dados com base no CPF.
	 *
	 * @param cpf CPF do usuário.
	 * @return Objeto User contendo o CPF e senha do usuário, ou null se não
	 *         encontrado.
	 * @throws Exception Em caso de falha na conexão ou execução da query.
	 */
	public User findByCpf(String cpf) throws Exception {
		String query = "SELECT cpf, password FROM ez_fastfood.customer WHERE cpf = ?";

		// Utilizando try-with-resources para todos os recursos
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
				PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, cpf);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					// Retorna o objeto User com os dados encontrados
					return new User(rs.getString("cpf"), rs.getString("password"));
				}
			}
		} catch (Exception e) {
			// Log do erro
			System.err.println("Error querying user by CPF: " + e.getMessage());
			throw e;
		}

		return null; // Retorna null se o usuário não foi encontrado
	}
}
