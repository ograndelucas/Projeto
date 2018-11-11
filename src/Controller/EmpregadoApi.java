package Controller;

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

import DAO.EmpregadoDao;
import DAO.UsuarioDao;
import Utils.Utilitys;
import VO.Usuario;

/**
 * Servlet implementation class Empregado
 */
@WebServlet(description = "Insert, select or update usuario in Empresa", urlPatterns = { "/empregado" })
public class EmpregadoApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpregadoApi() {
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
		int[] idArray = null;
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

				EmpregadoDao dao = new EmpregadoDao();
				idArray = dao.GetIdEmpresas(jsonObject.getInt("idUsuario"));
				status = 200;
			} catch (SQLException | JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				erro = true;
				status = 404;
				message = e.getMessage();
			}
		}
		
		try {
			JSONObject resp = new JSONObject();
			response.setStatus(status);
			if(erro) {
				resp.put("Status", status);
				resp.put("Message", message);
				Utilitys.AddResponse(response, resp.toString());
			}
			else {
				
				
				JSONArray array = new JSONArray();
				for(int i = 0; i<idArray.length;i++) {
					array.put(idArray[i]);
				}
				resp.put("IdsEmpresa", array);
				Utilitys.AddResponse(response, resp.toString());
			}			
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				response.getWriter().println("Ocorreu algum erro: "+e1.getMessage());
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int status = 0;
		String message = "";
		Boolean erro = false;		
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
				EmpregadoDao dao = new EmpregadoDao();
				dao.AddUsuarioInEmpresa(jsonObject.getInt("idUsuario"), jsonObject.getInt("idEmpresa"));
				message = "Usuario Cadastrado Com Sucesso!!!";
				status = 201;
			} catch (SQLException | JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				erro = true;
				status = 404;
				message = e.getMessage();
			}
		}
		
		try {
			JSONObject resp = new JSONObject();
			response.setStatus(status);
			
			resp.put("Status", status);
			resp.put("Message", message);
			Utilitys.AddResponse(response, resp.toString());
				
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				response.getWriter().println("Ocorreu algum erro: "+e1.getMessage());
			}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int status = 0;
		String message = "";
		Boolean erro = false;
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
				EmpregadoDao dao = new EmpregadoDao();
				dao.DemitirUsuarioDaEmpresa(jsonObject.getInt("idEmpresa"),jsonObject.getInt("idUsuario"));
				message = "Demitido Com Sucesso!!!";
				status = 200;
			} catch (SQLException | JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				erro = true;
				status = 204;
				message = e.getMessage();
			}
		}
		try {
			JSONObject resp = new JSONObject();
			response.setStatus(status);
			
			resp.put("Status", status);
			resp.put("Message", message);
			Utilitys.AddResponse(response, resp.toString());
				
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				response.getWriter().println("Ocorreu algum erro: "+e1.getMessage());
			}
	}

}
