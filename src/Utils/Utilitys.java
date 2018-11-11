package Utils;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONML;
import org.json.JSONObject;


public class Utilitys {

	public static void AddResponse(HttpServletResponse response, String json) throws IOException {
		response.setContentType("application/json"); 
		response.setCharacterEncoding("utf-8");
		response.getWriter().println(json);
	}
	
	public static StringBuffer GetJsonRequest(HttpServletRequest request) throws IOException {
		StringBuffer jb = new StringBuffer();
		String line = null;
		
		BufferedReader reader = request.getReader();
		while ((line = reader.readLine()) != null)
		    jb.append(line);
		return jb;
		
	}
	
	public static JSONObject CreateJsonResponseError(String message, int status) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("Status", status);
		json.put("Message", message);
		return json;
	}

}
