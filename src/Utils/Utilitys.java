package Utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;


public class Utilitys {

	public static void AddResponse(HttpServletResponse response, String json) throws IOException {
		response.setContentType("application/json"); 
		response.setCharacterEncoding("utf-8");
		response.getWriter().println(json);
	}

}
