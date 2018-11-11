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
 * Servlet implementation class UsuarioTrocaSenha
 */
@WebServlet(description = "requisição apra troca de senha", urlPatterns = { "/usuario/trocasenha" })
public class UsuarioTrocaSenha extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioTrocaSenha() {
        super();
        // TODO Auto-generated constructor stub
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
				UsuarioDao dao = new UsuarioDao();
				dao.TrocaSenhaUsuario(jsonObject.getString("senha"), jsonObject.getInt("idUsuario"));
				message = "Solicitação de Mudança de Senha REalizado Com Sucesso!!!";
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
