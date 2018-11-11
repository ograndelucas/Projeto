package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import VO.Usuario;


public class UsuarioDao {

	private Connection connection;
	
	public UsuarioDao() {
		// TODO Auto-generated constructor stub
		connection = DBUtil.getConnection();
	}
	
	public Usuario ConectaUsuario(String email, String senha) throws SQLException {
		PreparedStatement preparedStatement = connection
                .prepareStatement("select idUsuario, nome,email,isAdmin from Usuario where email = ? and senha = ?");
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, senha);
		ResultSet rs = preparedStatement.executeQuery();
		Usuario user = null;
		while(rs.next()) {
			user = new Usuario(rs.getInt("idUsuario"), rs.getString("nome"), rs.getString("email"), rs.getBoolean("isAdmin"));
		}
		return user;
	}

}
