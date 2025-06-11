package beans;

import java.sql.Date;

public class sales {
	private int sale_id;
	private Date sale_date;
	private int account_id;
	private int category_id;
	private String trade_name;
	private int unit_price;
	private int sale_number;
	private String note;

	public sales(int sale_id, Date sale_date, int account_id, int category_id, String trade_name, int unit_price,
			int sale_number,
			String note) {
		super();
		this.sale_id = sale_id;
		this.sale_date = sale_date;
		this.account_id = account_id;
		this.category_id = category_id;
		this.trade_name = trade_name;
		this.unit_price = unit_price;
		this.sale_number = sale_number;
		this.note = note;
	}

	public sales(Date sale_date, int account_id, int category_id, String trade_name, int unit_price,
			int sale_number, String note) {
		super();
		this.sale_date = sale_date;
		this.account_id = account_id;
		this.category_id = category_id;
		this.trade_name = trade_name;
		this.unit_price = unit_price;
		this.sale_number = sale_number;
		this.note = note;
	}

	public int getSale_id() {
		return sale_id;
	}

	public void setSale_id(int sale_id) {
		this.sale_id = sale_id;
	}

	public Date getSale_date() {
		return sale_date;
	}

	public void setSale_date(Date sale_date) {
		this.sale_date = sale_date;
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

	public int getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(int unit_price) {
		this.unit_price = unit_price;
	}

	public int getSale_number() {
		return sale_number;
	}

	public void setSale_number(int sale_number) {
		this.sale_number = sale_number;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
