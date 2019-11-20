package hello.entity;

import java.time.Instant;

public class User {
    private int id;
    private String username;
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

    public User(int id, String username, String avatar, Instant createdAt, Instant modifyAt) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
        this.createdAt = createdAt;
        this.modifyAt = modifyAt;
    }


}
