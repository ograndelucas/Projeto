package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import VO.Empresa;

public class EmpresaDao {

	private Connection connection;
	
	public EmpresaDao() {
		// TODO Auto-generated constructor stub
		connection = DBUtil.getConnection();
	}
	
	public Empresa GetEmpresaById(int idEmpresa) throws SQLException {
		PreparedStatement preparedStatement = connection
                .prepareStatement("select idEmpresa, nomeFantasia from Empresa where idEmpresa = ?");
		preparedStatement.setInt(1, idEmpresa);
		ResultSet rs = preparedStatement.executeQuery();
		Empresa emp = null;
		while(rs.next()) {
			emp = new Empresa(rs.getInt("idEmpresa"), rs.getString("nomeFantasia"));
		}
		return emp;
	}
	
	public void AddEmpresa(String nomeFantasia) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("insert into Empresa(nomeFantasia, dataCriacao) values(?,NOW())");
		preparedStatement.setString(1, nomeFantasia);
		preparedStatement.executeQuery();
	}
	
	public void updateEmpresa(String nomeFantasia, int idEmpresa) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("update Exame set nomeFantasia = ? where idEmpresa = ?");
		preparedStatement.setString(1, nomeFantasia);		
		preparedStatement.setInt(2, idEmpresa);
		preparedStatement.executeQuery();
		
	}
	
	public void ExcluirEmpresa(int idEmpresa) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("delete from Empresa where idEmpresa = ?");
		preparedStatement.setInt(1, idEmpresa);
		preparedStatement.executeQuery();
	}

}
