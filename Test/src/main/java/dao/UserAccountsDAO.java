package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.UserAccount;

public class UserAccountsDAO {
    private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/desktop/H2/FocusStep";
    private final String DB_USER = "sa";
    private final String DB_PASS = "";

    // ✅ 次のUSER_IDを採番する（例: 000001 → 000002）
    public String generateNextUserId() {
        String nextId = "000001";
        String sql = "SELECT MAX(USER_ID) FROM USER_ACCOUNTS";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next() && rs.getString(1) != null) {
                int current = Integer.parseInt(rs.getString(1));
                nextId = String.format("%06d", current + 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextId;
    }

    // ✅ 初期パスワード（生年月日をYYYYMMDD形式に）
    private String generateInitialPassword(LocalDate birth) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");
        return birth.format(fmt);
    }

    // ✅ 新規ユーザー登録
    public boolean insert(UserAccount user) {
        String sql = "INSERT INTO USER_ACCOUNTS (USER_ID, PASSWORD, NAME_SEI, NAME_MEI, NAME_SEI_KANA, NAME_MEI_KANA, BIRTH, PHONE, MAIL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // パスワード初期化
            String password = generateInitialPassword(user.getBirth());
            user.setPassword(password);

            ps.setString(1, user.getUserId());
            ps.setString(2, password);
            ps.setString(3, user.getNameSei());
            ps.setString(4, user.getNameMei());
            ps.setString(5, user.getNameSeiKana());
            ps.setString(6, user.getNameMeiKana());
            ps.setDate(7, Date.valueOf(user.getBirth()));
            ps.setString(8, user.getPhone());
            ps.setString(9, user.getMail());

            int result = ps.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
