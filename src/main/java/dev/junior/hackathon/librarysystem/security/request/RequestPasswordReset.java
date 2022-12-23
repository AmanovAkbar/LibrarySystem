package dev.junior.hackathon.librarysystem.security.request;

import jakarta.validation.constraints.NotNull;

public class RequestPasswordReset {
    @NotNull
    private String password;
    @NotNull
    private String secondPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecondPassword() {
        return secondPassword;
    }

    public void setSecondPassword(String secondPassword) {
        this.secondPassword = secondPassword;
    }
}
