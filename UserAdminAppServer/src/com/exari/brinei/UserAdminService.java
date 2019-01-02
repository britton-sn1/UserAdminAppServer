package com.exari.brinei;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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
	int nextId = 0;

	/**
	 * Default constructor.
	 */
	public UserAdminService() {
		loadModel();
		
	}

	private void loadModel() {
		users.clear();
		
		Arrays.asList( 
				new User("admin", 100), 
				new User("user1", 1101), 
				new User("eric", 101),
				new User("bert", 1211),
				new User("beth", 11), 
				new User("ralf", 1921), 
				new User("bob", 1501), 
				new User("alice", 15),
				new User("thomas",82)
			)
		.stream()
		.sorted((u1, u2) -> u1.getUserName().compareTo(u2.getUserName()))
		.forEach(u->addUser(u));
		
		
		
	}
	
	private void addUser(User u) {
		users.put(u.getId(), u);
		if(nextId < u.getId()) {
			nextId = u.getId()+1;
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
		String newUser = request.getParameter("add");
		if(newUser != null && newUser.trim().length() > 0) {
			addNewUser(newUser);
		}

		response.addHeader("content-type", "application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		users.values().stream().sorted((u1,u2)->u1.getUserName().compareTo(u2.getUserName())).forEach(u -> sb.append("{\"username\" : \"").append(u.getUserName()).append("\" , ")
				.append("\"id\" : \"").append(u.getId()).append("\"},"));
		
		if(sb.length() > 1) { 
			sb.replace(sb.length()-1, sb.length(), "");
		}
		sb.append("]");
		
		String json = sb.toString();
		
		response.getWriter().append(json);
		
	}

	private void addNewUser(String newUser) {
		
		nextId = nextId + 1;
		users.put(nextId, new User(newUser.toLowerCase(),nextId));
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
