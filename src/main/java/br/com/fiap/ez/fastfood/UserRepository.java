package br.com.fiap.ez.fastfood;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository {

	private final String dbUrl = "jdbc:postgresql://terraform-20241115020036064600000001.cr6w0cgk4uiy.us-east-1.rds.amazonaws.com:5432/postgres?currentSchema=ez_fastfood";
    private final String dbUser = "postgres";
    private final String dbPassword = "postgres";

    /**
     * Busca um usuário no banco de dados com base no CPF.
     * 
     * @param cpf CPF do usuário.
     * @return Objeto User contendo o CPF e senha do usuário, ou null se não encontrado.
     * @throws Exception Em caso de falha na conexão ou execução da query.
     */
    public User findByCpf(String cpf) throws Exception {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            // Consulta SQL para buscar o usuário pelo CPF
            String query = "SELECT cpf, password FROM EZ_FASTFOOD.CUSTOMER WHERE cpf = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, cpf);

            // Executa a consulta
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Cria um objeto User com os dados retornados
                return new User(
                    rs.getString("cpf"),
                    rs.getString("password")
                );
            }
        }
        return null; // Retorna null se o usuário não foi encontrado
    }
}
