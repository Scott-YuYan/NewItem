package hello.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.Instant;

public class User {
    private int id;
    @JsonIgnore
    private int blogId;
    private String username;
    @JsonIgnore
    private String bcrPassword;
    private String avatar;
    private Instant createdAt;
    private Instant modifyAt;

    public int getBlogId() {
        return blogId;
    }

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

    public User(String username, String bcrPassword) {
        this.username = username;
        this.bcrPassword = bcrPassword;
    }

    public User(int id, int blogId, String username, String bcrPassword, String avatar, Instant createdAt, Instant modifyAt) {
        this.id = id;
        this.blogId = blogId;
        this.username = username;
        this.bcrPassword = bcrPassword;
        this.avatar = avatar;
        this.createdAt = createdAt;
        this.modifyAt = modifyAt;
    }
}
