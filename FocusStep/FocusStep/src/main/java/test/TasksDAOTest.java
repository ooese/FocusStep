package test;

import java.sql.Date;

import dao.AccountsDAO;
import model.Account;
import model.Login;
	
public class TasksDAOTest {
		public static void main(String[] args) {
		testFindTaskOK(); //タスクが見つかる場合のテスト
		testFindTaskNG(); //タスクが見つからない場合のテスト
		}
		public static void testFindTaskOK() {
			Login login = new Login("yusuke.minato@miyabilink.jp","0123456789");//正しいID・PWを入力
			AccountsDAO dao = new AccountsDAO();
			Account result = dao.findByLogin(login);

			if (result != null &&
				result.get
				result.get
				result.get
				result.get
				result.get
				result.get
				result.get
				result.get
				result.get
				result.get
				result.get
				result.get
				result.get
					System.out.println("testFindTaskOK:成功しました");
				} else {
					System.out.println("testFindTaskOK:失敗しました");
				}
			}
		
		public static void testFindTaskNG() {
			Login login = new Login("00000000@00000.00","0000000000");//間違ったID・PWを入力
			AccountsDAO dao = new AccountsDAO();
			Account result = dao.findByLogin(login);
			if (result == null) {
				System.out.println("testFindTaskNG:成功しました");
			} else {
				System.out.println("testFindTaskNG:失敗しました");
			}
		}
	}
		