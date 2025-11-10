package model;

import java.io.Serializable;
import java.time.LocalDate;

public class UserAccount implements Serializable {
    private String userId;
    private String password;
    private String nameSei;
    private String nameMei;
    private String nameSeiKana;
    private String nameMeiKana;
    private LocalDate birth;
    private String phone;
    private String mail;

    public UserAccount(String userId, String password, String nameSei, String nameMei,
                       String nameSeiKana, String nameMeiKana, LocalDate birth,
                       String phone, String mail) {
        this.userId = userId;
        this.password = password;
        this.nameSei = nameSei;
        this.nameMei = nameMei;
        this.nameSeiKana = nameSeiKana;
        this.nameMeiKana = nameMeiKana;
        this.birth = birth;
        this.phone = phone;
        this.mail = mail;
    }

    // Getter & Setter
    public String getUserId() { return userId; }
    public String getPassword() { return password; }
    public String getNameSei() { return nameSei; }
    public String getNameMei() { return nameMei; }
    public String getNameSeiKana() { return nameSeiKana; }
    public String getNameMeiKana() { return nameMeiKana; }
    public LocalDate getBirth() { return birth; }
    public String getPhone() { return phone; }
    public String getMail() { return mail; }

    public void setPassword(String password) { this.password = password; }
}
