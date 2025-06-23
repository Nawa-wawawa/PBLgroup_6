package beans;

import java.sql.Date;

import froms.SerchSalesform;

public class salescondition {

	private Date start_date;
	private Date end_date;
	private int account_id;
	private int category_id;
	private String trade_name;
	private String note;

	public salescondition(Date start_date, Date end_date, int account_id, int category_id, String trade_name,
			String note) {
		super();
		this.start_date = start_date;
		this.end_date = end_date;
		this.account_id = account_id;
		this.category_id = category_id;
		this.trade_name = trade_name;
		this.note = note;
	}

	public salescondition(SerchSalesform form) {
		if (form.start_date != null && !form.start_date.isEmpty()) {
			this.start_date = Date.valueOf(form.start_date);
		}
		if (form.end_date != null && !form.end_date.isEmpty()) {
			this.end_date = Date.valueOf(form.end_date);
		}

		this.account_id = Integer.parseInt(form.staff);
		this.category_id = Integer.parseInt(form.category);
		this.trade_name = form.productName;
		this.note = form.remarks;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getTrade_name() {
		return trade_name;
	}

	public void setTrade_name(String trade_name) {
		this.trade_name = trade_name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
