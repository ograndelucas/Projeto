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

import DAO.EmpresaDao;
import Utils.Utilitys;
import VO.Empresa;

/**
 * Servlet implementation class EmpresaLista
 */
@WebServlet(description = "Listar todas as empresas", urlPatterns = { "/empresa/lista" })
public class EmpresaLista extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpresaLista() {
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
		ArrayList<Empresa> empList = new ArrayList<>();
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
				empList = dao.GetAllEmpresa();
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
				JSONArray list = new JSONArray();
				for(int i = 0;i<empList.size();i++) {
					list.put(empList.get(i));
				}
				resp.put("Lista", list);
				Utilitys.AddResponse(response, resp.toString());
			}			
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				response.getWriter().println("Ocorreu algum erro: "+e1.getMessage());
			}

	}

}
