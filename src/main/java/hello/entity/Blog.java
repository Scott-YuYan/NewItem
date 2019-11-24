package hello.entity;

import java.time.Instant;

public class Blog {
    private Integer id;
    private String title;
    private String description;
    private String content;
    private User user;
    private Instant created_at;
    private Instant updated_at;

}
