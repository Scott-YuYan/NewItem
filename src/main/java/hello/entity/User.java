package hello.entity;

import java.time.Instant;

public class User {
    private int id;
    private String username;
    private String bcrPassword;
    private String avatar;
    private Instant createdAt;
    private Instant modifyAt;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getModifyAt() {
        return modifyAt;
    }

    public String getBcrPassword() {
        return bcrPassword;
    }


    public User(int id, String username, String bcrPassword, String avatar, Instant createdAt, Instant modifyAt) {
        this.id = id;
        this.username = username;
        this.bcrPassword = bcrPassword;
        this.avatar = avatar;
        this.createdAt = createdAt;
        this.modifyAt = modifyAt;
    }
}
