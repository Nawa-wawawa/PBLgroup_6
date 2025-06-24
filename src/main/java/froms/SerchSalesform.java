package froms;

import jakarta.servlet.http.HttpServletRequest;

public class SerchSalesform {
	public String start_date;
	public String end_date;
	public String staff;
	public String category;
	public String productName;
	public String remarks;

	public SerchSalesform(HttpServletRequest request) {
		this.start_date = request.getParameter("start_date");
		this.end_date = request.getParameter("end_date");
		this.staff = request.getParameter("staff");
		this.category = request.getParameter("category");
		this.productName = request.getParameter("product_name");
		this.remarks = request.getParameter("remarks");
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
