package beans;

public class Account {
	private int id;
	private String name;
	private String mail;
	private String password;
	private byte authority;
	
	public Account(int id, String name, String mail, String password, byte authority) {
		this.id = id;
		this.name = name;
		this.mail = mail;
		this.password = password;
		this.authority = authority;
	}
	
	

	public Account(String mail, String password) {
		super();
		this.mail = mail;
		this.password = password;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(byte authority) {
		this.authority = authority;
	}
}
