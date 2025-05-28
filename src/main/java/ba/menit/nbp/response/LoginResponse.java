package ba.menit.nbp.response;

import ba.menit.nbp.dtos.UserDto;
import ba.menit.nbp.entities.User;

public class LoginResponse {
    private String token;
    private long expiresIn;
    private UserDto user;

    // Getters and Setters

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}