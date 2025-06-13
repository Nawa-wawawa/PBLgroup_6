package beans;

public class Account {
	private int account_id;
	private String name;
	private String mail;
	private String password;
	private byte authority;
	
	public Account(int account_id, String name, String mail, String password, byte authority) {
		this.account_id = account_id;
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
		return account_id;
	}

	public void setId(int account_id) {
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

	ステートメントパラメータをバインドできません
	  Can't convert value '3' into bit string
	  Can't convert value '3' into bit string
	    For input string: "3" under radix 2
	    For input string: "3" under radix 2
	
}
