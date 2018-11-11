package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import VO.Exame;

public class ExameDao {

	private Connection connection;
	
	public ExameDao() {
		connection = DBUtil.getConnection();
	}
	
	public ArrayList<Exame> GetExames(int[] idEmpresas) throws SQLException{
		if(idEmpresas.length == 0) {
			return null;
		}
		String sql = "select * from Exame ";
		for(int i = 0;i<idEmpresas.length;i++) {
			if(i == 0) {
				sql = sql + "where idEmpresa = ? ";
			}
			else {
				sql = sql + "and idEmpresa = ?";
			}
			
		}
		PreparedStatement preparedStatement = connection
                .prepareStatement(sql);
		for(int i = 0; i<idEmpresas.length;i++) {
			preparedStatement.setInt((i+1), idEmpresas[i]);
		}
		ResultSet rs = preparedStatement.executeQuery();
		ArrayList<Exame> exList = new ArrayList<>();
		while(rs.next()) {
			Exame ex = new Exame(rs.getInt("idExame"), rs.getString("nomePaciente"), rs.getString("descricao"), rs.getInt("idEmpresa"), 
					rs.getDate("dataRealiazacao"));
			exList.add(ex);
		}
		return exList;
	}
	
	public Exame GetExameById(int idExame) throws SQLException {
		PreparedStatement preparedStatement = connection
                .prepareStatement("select * from Exame where idExame = ?");
		preparedStatement.setInt(1, idExame);
		ResultSet rs = preparedStatement.executeQuery();
		Exame ex = null;
		while(rs.next()) {
			ex = new Exame(rs.getInt("idExame"), rs.getString("nomePaciente"), rs.getString("descricao"), rs.getInt("idEmpresa"), 
					rs.getDate("dataRealiazacao"));
		}
		return ex;
	}
	
	public void AddExame(Exame ex) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("insert into Exame(nomePaciente, descricao, idEmpresa, dataRealizacao"
				+ "values(?,?,?,?)");
		preparedStatement.setString(1, ex.getNomePaciente());
		preparedStatement.setString(2, ex.getDescricao());
		preparedStatement.setInt(3, ex.getIdEmpresa());
		preparedStatement.setDate(4, (Date) ex.getDataRealiazacao());
		preparedStatement.executeQuery();
	}
	
	public void UpdateExame(Exame ex) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("update Exame set nomePaciente = ?, descricao = ?, idEmpresa = ?, "
				+ "dataRealizacao = ? where idExame = ?");
		preparedStatement.setString(1, ex.getNomePaciente());
		preparedStatement.setString(2, ex.getDescricao());
		preparedStatement.setInt(3, ex.getIdEmpresa());
		preparedStatement.setDate(4, (Date) ex.getDataRealiazacao());
		preparedStatement.setInt(5, ex.getIdExame());
		preparedStatement.executeQuery();
		
	}
	
	public void ExcluirExame(int idExame) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("delete from Exame where idExame = ?");
		preparedStatement.setInt(1, idExame);
		preparedStatement.executeQuery();
	}

}
