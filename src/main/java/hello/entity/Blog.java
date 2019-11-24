package hello.entity;

import java.time.Instant;

public class Blog{
    private Integer id;
    private String title;
    private String description;
    private String content;
    private User user;
    private Instant created_at;
    private Instant updated_at;



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

    public Instant getCreated_at() {
        return created_at;
    }

    public Instant getUpdated_at() {
        return updated_at;
    }
}
