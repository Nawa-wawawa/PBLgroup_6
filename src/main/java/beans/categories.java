package beans;

public class categories {
	
	private int category_id;
	private String category_name;
	private int active_flg;
	
	public categories(int category_id, String category_name, int active_flg) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
		this.active_flg = active_flg;
	}
	public categories(String category_name, int active_flg) {
		super();
		this.category_name = category_name;
		this.active_flg = active_flg;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public int getActive_flg() {
		return active_flg;
	}
	public void setActive_flg(int active_flg) {
		this.active_flg = active_flg;
	}
	
}
