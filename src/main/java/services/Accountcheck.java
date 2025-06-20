package services;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import beans.getAccountRequest;

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
			}
			if (!isValidEmail(mail)) {
				return true;
			}
		} catch (UnsupportedEncodingException e) {
			return true;
		}
		return false;
	}
	public boolean mailSerchCheck(String mail) {
		if (mail == null)
			return true;
		try {
			if (mail.getBytes("UTF-8").length >= 101) {
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

	public Map<String, String> useCheck(getAccountRequest form) {

		Accountcheck checker = new Accountcheck();
		Map<String, String> fieldErrors = new HashMap<>();

		// ■氏名
		if (form.name == null || form.name.trim().isEmpty()) {
			fieldErrors.put("name", "氏名を入力してください。");
		} else if (!form.name.matches("^[\\p{L} 　\\-\\ー]+$")) { // 例：日本語・英字・スペース・ハイフンだけ許可
			fieldErrors.put("name", "氏名の形式が正しくありません。");
		} else if (checker.nameCheck(form.name)) {
			fieldErrors.put("name", "氏名は20文字以内で入力してください。");
		}

		// ■メールアドレス
		if (form.mail == null || form.mail.trim().isEmpty()) {
			fieldErrors.put("mail", "メールアドレスを入力してください。");
		} else if (!checker.isValidEmailFormat(form.mail)) {
			fieldErrors.put("mail", "メールアドレスの形式が正しくありません。");
		} else if (checker.mailCheck(form.mail)) {
			fieldErrors.put("mail", "メールアドレスは100文字以内で入力してください。");
		}

		// ■パスワード
		if (form.password == null || form.password.trim().isEmpty()) {
			fieldErrors.put("password", "パスワードを入力してください。");
		} else if (checker.passwordCheck(form.password)) {
			fieldErrors.put("password", "パスワードは30文字以内で入力してください。");
		}

		// ■パスワード（確認）
		if (form.confirmPassword == null || form.confirmPassword.trim().isEmpty()) {
			fieldErrors.put("confirmPassword", "確認用パスワードを入力してください。");
		} else if (!form.confirmPassword.equals(form.password)) {
			fieldErrors.put("confirmPassword", "パスワードとパスワード（確認）の入力内容が異なっています。");
		}

		return fieldErrors;
	}

}
