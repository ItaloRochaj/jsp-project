package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOLoginRepository {
	
	private Connection connection;
	
	 public DAOLoginRepository() {
		 connection = SingleConnectionBanco.getConnection();
		// TODO Auto-generated constructor stub
	}
	 
	 public boolean validarAutenticacao(ModelLogin modelLogin) throws SQLException {
		    
		    String sql = "SELECT * FROM model_login WHERE UPPER(login) = UPPER(?) AND UPPER(senha) = UPPER(?)";
		    PreparedStatement statement = connection.prepareStatement(sql);
		    statement.setString(1, modelLogin.getLogin());
		    statement.setString(2, modelLogin.getSenha());
		    
		    ResultSet resultSet = statement.executeQuery();
		    
		    if (resultSet.next()) {
		        return true; /*Autenticado*/
		    }
		    
		    return false; /*Não Autenticado*/
		}

}
