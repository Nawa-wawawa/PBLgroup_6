package services;

import java.io.UnsupportedEncodingException;

public class Salescheck {

	//商品名入力時、101バイト以上入力時はエラー。						

	public boolean productCheck(String name) {

		if (name == null)
			return true;
		try {
			return name.getBytes("UTF-8").length > 100;
		} catch (UnsupportedEncodingException e) {
			return true; // エラー時は保守的にエラーと扱う
		}
	}

	//単価を入力時、10桁以上入力時はエラー。

	public boolean priceCheck(int n) {
		if (n == 0)
			return true;
		return n > 1000000000;
	}

	//個数を入力時、10桁以上入力時はエラー。

	public boolean quantityCheck(int n) {
		if (n == 0)
			return true;
		return n > 1000000000;
	}

	//備考を入力時、400バイト以上入力時はエラー
	public boolean remarksCheck(String remark) {
		if (remark == null)
			return true;
		try {
			return remark.getBytes("UTF-8").length > 400;
		} catch (UnsupportedEncodingException e) {
			return true; // エラー時は保守的にエラーと扱う
		}
	}

}
