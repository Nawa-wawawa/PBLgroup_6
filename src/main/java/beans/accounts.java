package beans;

public class accounts {

	private int account_id;
	private String name;
	private String mail;
	private String password;
	private byte authority;
	

	public accounts(int id, String name, String mail, String password, byte authority) {
		this.account_id = id;

		this.name = name;
		this.mail = mail;
		this.password = password;
		this.authority = authority;
	}
	
	

	public accounts(String mail, String password) {
		super();
		this.mail = mail;
		this.password = password;
	}


	public int getId() {
		return account_id;
	}

	public void setId(int id) {
		this.account_id = id;

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

	//これを入れると動かない
	
//	ステートメントパラメータをバインドできません
//	  Can't convert value '3' into bit string
//	  Can't convert value '3' into bit string
//	    For input string: "3" under radix 2
//	    For input string: "3" under radix 2
//	
}
