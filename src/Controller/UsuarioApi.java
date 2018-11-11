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

import DAO.UsuarioDao;
import Utils.Utilitys;
import VO.Usuario;

/**
 * Servlet implementation class UsuarioApi
 */
@WebServlet(description = "Adiciona, atualiza, busca ou apaga um usuario", urlPatterns = { "/usuario" })
public class UsuarioApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioApi() {
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
		Usuario user = new Usuario();
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

				UsuarioDao dao = new UsuarioDao();
				user = dao.GetUsuarioById(jsonObject.getInt("idUsuario"));
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
				resp.put("Usuario", user);
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
				Usuario user = new Usuario(jsonObject.getString("nome"), jsonObject.getString("email"), jsonObject.getString("isAdmin"));
				UsuarioDao dao = new UsuarioDao();
				dao.AddUsuario(user, jsonObject.getString("senha"));
				message = "Inserido Com Sucesso!!!";
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
				Usuario user = new Usuario(jsonObject.getInt("idUsuario"), jsonObject.getString("nome"), jsonObject.getString("email"), 
						jsonObject.getBoolean("isAdmin"));
				UsuarioDao dao = new UsuarioDao();
				dao.updateUsuario(user);
				message = "Atualizado Com Sucesso!!!";
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
				
				UsuarioDao dao = new UsuarioDao();
				dao.ExcluirUsuario(jsonObject.getInt("idUsuario"));
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
