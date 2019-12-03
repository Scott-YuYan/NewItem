package hello.entity;

import java.time.Instant;

public class Blog{
    private Integer id;
    private String title;
    private String description;
    private String content;
    private User user;
    private Instant createAt;
    private Instant modifyAt;



    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public Instant getModifyAt() {
        return modifyAt;
    }
}
