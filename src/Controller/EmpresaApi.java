package Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import DAO.EmpresaDao;
import DAO.ExameDao;
import Utils.Utilitys;
import VO.Empresa;

/**
 * Servlet implementation class EmpresaApi
 */
@WebServlet(description = "Selecionar, atualizar, apagar ou inserir uma empresa", urlPatterns = { "/empresa" })
public class EmpresaApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpresaApi() {
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
		Empresa emp = new Empresa();
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

				EmpresaDao dao = new EmpresaDao();
				emp = dao.GetEmpresaById(jsonObject.getInt("idEmpresa"));
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
				resp.put("Empresa", emp);
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

				EmpresaDao dao = new EmpresaDao();
				dao.AddEmpresa(jsonObject.getString("nomeFantasia"));
				status = 201;
				message = "Inserido com Sucesso!!!";
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
				EmpresaDao dao = new EmpresaDao();
				dao.updateEmpresa(jsonObject.getString("nomeFantasia"), jsonObject.getInt("idEmpresa"));
				status = 200;
				message = "Atualizado com Sucesso!!!";
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
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				
				ExameDao dao = new ExameDao();
				dao.ExcluirExame(jsonObject.getInt("idEmpresa"));
				message = "Excluido Com Sucesso!!!";
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
