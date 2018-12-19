package com.exari.brinei;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserAdminService
 */
@WebServlet("/UserAdminService")
public class UserAdminService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Map<Integer, User> users = new LinkedHashMap<>();

	/**
	 * Default constructor.
	 */
	public UserAdminService() {
		loadModel();
		
	}

	private void loadModel() {
		User[] usersArray = { new User("admin", 100), new User("user1", 1101), new User("eric", 101),
				new User("bert", 1211), new User("ralf", 1921), new User("bob", 1501), new User("baldrik", 15),
				new User("thomas",82) };
		
		Arrays.sort(usersArray, ((u1, u2) -> ((u1.getId() - u2.getId()))));
		
		for(User user : usersArray) {
			users.put(user.getId(), user);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sId = request.getParameter("id"); 
		if(sId != null && sId.trim().length() > 0) {
			doDelete(Integer.valueOf(sId));
		}
		String reset= request.getParameter("reset");
		if("true".equals(reset)){
			loadModel();
		}

		response.addHeader("content-type", "application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		users.values().stream().forEach(u -> sb.append("{\"username\" : \"").append(u.getUserName()).append("\" , ")
				.append("\"id\" : \"").append(u.getId()).append("\"},"));
		
		if(sb.length() > 1) { 
			sb.replace(sb.length()-1, sb.length(), "");
		}
		sb.append("]");
		
		String json = sb.toString();
		
		response.getWriter().append(json);
		
	}

	private void doDelete(Integer id) {
		users.remove(id);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
