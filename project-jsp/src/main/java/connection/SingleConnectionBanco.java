package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
    
    private static String banco = "jdbc:postgresql://localhost:5432/projects_java?autoReconnect=true";
    private static String user = "postgres";
    private static String senha = "rita@20";
    private static Connection connection = null;

    // Método de conexão - Inicializa a conexão se necessário
    public static Connection getConnection() {
        // Verifica se a conexão já foi estabelecida
        if (connection == null) {
            conectar(); // Inicia a conexão se não estiver criada
        }
        return connection; // Retorna a conexão
    }

    // Construtor, chamado para garantir que a conexão seja estabelecida na criação do objeto
    public SingleConnectionBanco() {
        conectar();  // Chama o método de conexão
    }

    // Método que faz a conexão com o banco de dados
    private static void conectar() {
        try {
            // Se a conexão ainda não foi criada
            if (connection == null) {
                // Carrega o driver do PostgreSQL
                Class.forName("org.postgresql.Driver");
                // Cria a conexão com o banco de dados
                connection = DriverManager.getConnection(banco, user, senha);
                connection.setAutoCommit(false); // Define auto-commit como false
            }
        } catch (Exception e) {
            e.printStackTrace(); // Exibe o erro
        }
    }
}
