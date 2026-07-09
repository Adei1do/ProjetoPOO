package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {
    
    // Configuração de acesso ao MySQL do seu container Docker
    private static final String URL = "jdbc:mysql://localhost:3306/db_escola?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String SENHA = "root-db-escola"; // A senha que definiu no Docker

    public static Connection getConexao() {
        try {
            // Regista o driver do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver do MySQL não encontrado na pasta lib! " + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco no Docker! Verifique se o container está ativo. " + e.getMessage());
            return null;
        }
    }
}
