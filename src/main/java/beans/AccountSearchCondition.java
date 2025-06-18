package beans;

public class AccountSearchCondition {
	

    private String name;
    private String mail;
    private byte authority;

    public AccountSearchCondition(String name, String mail, byte authority) {
        this.name = name;
        this.mail = mail;
        this.authority = authority;
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

	public byte getAuthority() {
		return authority;
	}

	public void setAuthority(byte authority) {
		this.authority = authority;
	}

}
