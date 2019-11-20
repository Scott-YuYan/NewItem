package hello.entity;

public class Result {
    private String status;
    private String msg;
    private User user;

    public Result(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Result(String status, String msg, User user) {
        this.status = status;
        this.msg = msg;
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public User getUser() {
        return user;
    }
}
