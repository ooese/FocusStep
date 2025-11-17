package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.Login;

public class AccountsDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/desktop/H2/FocusStep";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	public Account findByLogin(Login login) {
		Account account = null;
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			//SELECT文を準備
			String sql = "SELECT USER_ID,USER_ID_MAIL,PASS,NAME_SEI,NAME_MEI,NAME_SEI_KANA,NAME_MEI_KANA,BIRTH FROM USER_ACCOUNTS WHERE USER_ID_MAIL = ? AND PASS = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, login.getLoginId());
			pStmt.setString(2, login.getPass());

			//SELECT文を実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				//ユーザーが存在したらデータを取得そのユーザーを表すAccountインスタンスを生成
				int userId = rs.getInt("USER_ID");
				String pass = rs.getString("PASS");
				String mail = rs.getString("USER_ID_MAIL");
				String loginId = mail;  //メールアドレスをログインIDとして使う
				String nameSei = rs.getString("NAME_SEI");
				String nameMei = rs.getString("NAME_MEI");
				String nameSeiKana = rs.getString("NAME_SEI_KANA");
				String nameMeiKana = rs.getString("NAME_MEI_KANA");
				Date birth = rs.getDate("BIRTH");
				account = new Account(userId, pass,mail, loginId, nameSei, nameMei, nameSeiKana, nameMeiKana, birth);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return account;
	}
}