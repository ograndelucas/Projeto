package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpregadoDao {

	private Connection connection;
	
	public EmpregadoDao() {
		// TODO Auto-generated constructor stub
		connection = DBUtil.getConnection();
	}
	
	public int[] GetIdEmpresas(int idUsuario) throws SQLException {
		PreparedStatement preparedStatement = connection
                .prepareStatement("select idEmpresa from Empregado where  idUsuario = ? and dataDemissao is null");
		preparedStatement.setInt(1, idUsuario);
		ResultSet rs = preparedStatement.executeQuery();
		int[] arrayId = new int[rs.getRow()];
		int cont = 0;
		while(rs.next()) {
			arrayId[cont] = rs.getInt("idEmpresa");
			cont++;
		}
		return arrayId;
	}
	
	public void AddUsuarioInEmpresa(int idUsuario, int idEmpresa) throws SQLException {
		if(ThisIsUsuarioIsInThisEmpresa(idUsuario, idEmpresa)) {
			UpdateRegistroUsuarioInEmpresa(idEmpresa, idUsuario);
		}
		PreparedStatement preparedStatement = connection.prepareStatement("insert into Empregado(idEmpresa, idUsuario, dataAdmissao) "
				+ "values(?, ?,NOW())");
		preparedStatement.setInt(1, idEmpresa);
		preparedStatement.setInt(2, idUsuario);
		preparedStatement.executeQuery();
	}
	
	private void UpdateRegistroUsuarioInEmpresa(int idEmpresa, int idUsuario) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("update Empregado set dataAdmissao = NOW(), dataDemissao is null "
				+ "where idEmpresa = ? and idUsuario = ?");
		preparedStatement.setInt(1, idEmpresa);
		preparedStatement.setInt(2, idUsuario);
		preparedStatement.executeQuery();
	}
	
	private Boolean ThisIsUsuarioIsInThisEmpresa(int idUsuario, int idEmpresa) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("select * from Empregado where idEmpresa = ? and idUsuario = ?");
		preparedStatement.setInt(1, idEmpresa);
		preparedStatement.setInt(2, idUsuario);
		ResultSet rs = preparedStatement.executeQuery();
		return rs.getRow() >0;
	}
	
	public void DemitirUsuarioDaEmpresa(int idEmpresa, int idUsuario) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("Update Empregado set DataDemissao = NOW() where idEmpresa = ?"
				+ " and idUsuario = ?");
		preparedStatement.setInt(1, idEmpresa);
		preparedStatement.setInt(2, idUsuario);
		preparedStatement.executeQuery();
	}
	
	public int[] GetAllFuncionariosByEmpresa(int idEmpresa) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("select idUsuario from Empregado where idEmpresa = ?");
		preparedStatement.setInt(1, idEmpresa);
		ResultSet rs = preparedStatement.executeQuery();
		int[] idArray = new int[rs.getRow()];
		int cont = 0;
		while(rs.next()) {
			idArray[cont] = rs.getInt("idUsuario");
		}
		return idArray;
	}

}
