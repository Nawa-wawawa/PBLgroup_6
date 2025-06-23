package beans;

import jakarta.servlet.http.HttpServletRequest;

public class getAccountRequest {

	public String name;
	public String mail;
	public String password;
	public String confirmPassword;
	public String[] roles;

	public getAccountRequest(HttpServletRequest request) {
		this.name = request.getParameter("name");
		this.mail = request.getParameter("mail");
		this.password = request.getParameter("password");
		this.confirmPassword = request.getParameter("confirmPassword");
		this.roles = request.getParameterValues("role");
	}
}
