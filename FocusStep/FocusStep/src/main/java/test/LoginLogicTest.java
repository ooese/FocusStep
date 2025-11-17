package test;

import model.Login;
import model.LoginLogic;

public class LoginLogicTest {
	public static void main(String[] args) {
		testExecuteOK(); //ログイン成功のテスト
		testExecuteNG(); //ログイン失敗のテスト
	}
	public static void testExecuteOK() {
		Login login = new Login("yusuke.minato@miyabilink.jp","0123456789"); //正しいID・PWを入力
		LoginLogic bo = new LoginLogic();
		boolean result = bo.execute(login);
		if(result) {
			System.out.println("testExecuteOK:成功しました");
		}else {
			System.out.println("testExecuteOK:失敗しました");
		}
	}
	public static void testExecuteNG() {
		Login login = new Login("yusuke.minato@miyabilink.jp","0000000000"); //間違ったID・PWを入力
		LoginLogic bo = new LoginLogic();
		boolean result = bo.execute(login);
		if(!result) {
			System.out.println("testExecuteNG:成功しました");
		}else {
			System.out.println("testExecuteNG:失敗しました");
		}
	}
}
