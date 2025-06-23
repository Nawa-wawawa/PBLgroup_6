package froms;

import jakarta.servlet.http.HttpServletRequest;

public class InsertSaleform {
	public String saleDate;
	public String staff;
	public String category;
	public String productName;
	public String unitPrice;
	public String quantity;
	public String remarks;

	public InsertSaleform(HttpServletRequest request) {

		this.saleDate = request.getParameter("sale_date");
		this.staff = request.getParameter("staff");
		this.category = request.getParameter("category");
		this.productName = request.getParameter("product_name");
		this.unitPrice = request.getParameter("unit_price");
		this.quantity = request.getParameter("quantity");
		this.remarks = request.getParameter("remarks");

	}
}
