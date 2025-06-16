package services;

import java.io.UnsupportedEncodingException;

public class AccountSearchCheck {

		//名前入力時、21バイト以上入力時はエラー。						

		public boolean nameCheck(String name) {

			if (name == null)
				return true;
			try {
				return name.getBytes("UTF-8").length > 20;
			} catch (UnsupportedEncodingException e) {
				return true; // エラー時は保守的にエラーと扱う
			}
		}

		//メールを入力時、100バイト以上入力時はエラー
		public boolean mailCheck(String remark) {
			if (remark == null)
				return true;
			try {
				return remark.getBytes("UTF-8").length > 99;
			} catch (UnsupportedEncodingException e) {
				return true; // エラー時は保守的にエラーと扱う
			}
		}
		

	}
