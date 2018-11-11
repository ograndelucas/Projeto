package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonArray;

import DAO.EmpregadoDao;
import DAO.ExameDao;
import Utils.Utilitys;
import VO.Exame;

/**
 * Servlet implementation class ExameLista
 */
@WebServlet(description = "Lista os exames de acordo com as empresas do usuario", urlPatterns = { "/exame/Lista" })
public class ExameLista extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExameLista() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int status = 0;
		String message = "";
		Boolean erro = false;
		ArrayList<Exame> exList = new ArrayList<>();
		StringBuffer jb = new StringBuffer();
		
		try {
			jb = Utilitys.GetJsonRequest(request);
		} catch (Exception e) { 
			erro = true;
			status = 400;
			message = e.getMessage();
		}
		if(!erro) {
			try {
				JSONObject jsonObject =  HTTP.toJSONObject(jb.toString());
				EmpregadoDao empDao = new EmpregadoDao();
				int[] idEmpresas = empDao.GetIdEmpresas(jsonObject.getInt("idUsuario"));
				ExameDao exDao = new ExameDao();
				exList = exDao.GetExames(idEmpresas);
				status = 200;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				status = 400;
				message = e.getMessage();
				erro = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				status = 404;
				message = e.getMessage();
				erro = true;
			}
		}
		 
			try {
				response.setStatus(status);
				JSONObject json = new JSONObject();
				if(erro) {
					json = Utilitys.CreateJsonResponseError(message, status);
					Utilitys.AddResponse(response, json.toString());
				}
				else {
					JSONArray lista = new JSONArray();
					for(int i = 0; i<exList.size();i++) {
						lista.put(exList.get(i));
					}
					json.put("lista", lista);
					Utilitys.AddResponse(response, json.toString());					
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.getWriter().println("Ocorreu algum erro: "+e.getMessage());
			}
		
	}

}
