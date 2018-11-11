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
	
	

}
