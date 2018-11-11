package Controller;

import java.io.BufferedReader;
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
 * Servlet implementation class Login
 */
@WebServlet(description = "Conectar no sistema", urlPatterns = { "/login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Boolean erro = false;
		String message = "";
		int status = 0;
		Usuario user = null;
		StringBuffer jb = new StringBuffer();
		String line = null;
		
		try {
			BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		    	jb.append(line);
		} catch (Exception e) { 
			erro = true;
			status = 400;
			message = e.getMessage();
		}
		if(!erro) {
			try {
				JSONObject jsonObject =  HTTP.toJSONObject(jb.toString());
				UsuarioDao dao = new UsuarioDao();
				user = dao.ConectaUsuario(jsonObject.getString("email"), jsonObject.getString("senha"));
				status = 201;
			} catch (JSONException e) {
			    // crash and burn
			    message = "Error parsing JSON request string";
			    status = 400;
			    erro = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = e.getMessage();
				status = 404;
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

}
