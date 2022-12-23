package dev.junior.hackathon.librarysystem.model;


import jakarta.persistence.*;


import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "password_token")
public class PasswordToken {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "token")
    private String token;
    @Column(name = "expiry_date")
    private Date expiryDate;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @Column(name = "email")
    private String email;

    public PasswordToken(){}

    public PasswordToken(String token, User user){
        this.token = token;
        setExpiryDate(24);
        this.user = user;
        this.email = user.getEmail();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }
    public boolean isExpired(){
        return new Date().after(this.expiryDate);
    }
    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setExpiryDate(int hours){
        Calendar now = Calendar.getInstance();
        now.add(Calendar.HOUR, hours);
        this.expiryDate = now.getTime();
    }
    public void setUser(User user) {
        this.user = user;
    }
}
