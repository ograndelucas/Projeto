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
	
	public Usuario GetUsuarioById(int idUsuario) throws SQLException {
		PreparedStatement preparedStatement = connection
                .prepareStatement("select idUsuario, email, isAdmin from Usuario where idUsuario = ?");
		preparedStatement.setInt(1, idUsuario);
		ResultSet rs = preparedStatement.executeQuery();
		Usuario user = null;
		while(rs.next()) {
			user = new Usuario(rs.getInt("idUsuario"), rs.getString("nome"), rs.getString("email"), rs.getBoolean("isAdmin"));
		}
		return user;
	}
	
	public void AddUsuario(Usuario user, String senha) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("insert into Usuario(nome, email, senha, dataCriacao,isAdmin) "
				+ "values(?,?,?,NOW(),?)");
		preparedStatement.setString(1, user.getNome());
		preparedStatement.setString(2, user.getEmail());
		preparedStatement.setString(3, senha);
		preparedStatement.setBoolean(4, user.getIsAdmin());
		preparedStatement.executeQuery();
	}
	
	public void updateUsuario(Usuario user) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("update Usuario set nome = ?, email = ?, dataAlteracao = NOW(),"
				+ " isAdmin = ? where idUsuario = ?");
		preparedStatement.setString(1, user.getNome());		
		preparedStatement.setString(2, user.getEmail());
		preparedStatement.setBoolean(3, user.getIsAdmin());
		preparedStatement.setInt(4, user.getId());
		preparedStatement.executeQuery();
		
	}
	
	public void ExcluirUsuario(int idUsuario) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("delete from Usuario where idUsuario = ?");
		preparedStatement.setInt(1, idUsuario);
		preparedStatement.executeQuery();
	}
	
	public void TrocaSenhaUsuario(String senha, int idUsuario) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("update Usuario set senha = ? where idUsuario = ?");
		preparedStatement.setString(1, senha);
		preparedStatement.setInt(2, idUsuario);
		preparedStatement.executeQuery();
	}

}
