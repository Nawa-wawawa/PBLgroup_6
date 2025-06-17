package beans;

public class accounts {

	int account_id;
	String name;
	String mail;
	String password;
	byte authority;

	public accounts(int account_id, String name, String mail, String password, byte authority) {
		super();
		this.account_id = account_id;
		this.name = name;
		this.mail = mail;
		this.password = password;
		this.authority = authority;
	}




	public accounts(int account_id, String name, byte authority) {
		super();
		this.account_id = account_id;
		this.name = name;
		this.authority = authority;
	}




	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
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

	public byte getAuthority() {

		return authority;
	}

	public void setAuthority(byte authority) {
		this.authority = authority;
	}
}
