package services;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

public class Accountcheck {
	
	public boolean nameCheck(String name) {
		if (name == null)
			return true;
		try {
			return name.getBytes("UTF-8").length >= 21;
		} catch (UnsupportedEncodingException e) {
			return true; // エラー時は保守的にエラーと扱う
		}
	}
	
	public boolean mailCheck(String mail) {
		if (mail == null)
			return true;
		try {
			if (mail.getBytes("UTF-8").length >= 101) {
				return true;
			}if (!isValidEmail(mail)) {
				return true;
			}
		} catch (UnsupportedEncodingException e) {
			return true;
		}
		return false;
	}
	private boolean isValidEmail(String email) {
		String regex = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
		return Pattern.matches(regex, email);
	}
	
	public boolean passwordCheck(String password) {
		if (password == null)
			return true;
		try {
			return password.getBytes("UTF-8").length >= 31;
		} catch (UnsupportedEncodingException e) {
			return true; // エラー時は保守的にエラーと扱う
		}
	}
		
	public boolean confirmpasswordCheck(String password, String confirmPassword) {
	    if (confirmPassword == null || confirmPassword.isEmpty()) {
	        return true; // 未入力 → エラー
	    }

	    if (!confirmPassword.equals(password)) {
	        return true; // 一致しない → エラー
	    }

	    return false; // 問題なし
	}
	
	public boolean isValidEmailFormat(String email) {
	    String regex = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
	    return Pattern.matches(regex, email);
	}

}
	
	
	

