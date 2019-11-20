package hello.entity;

public class Result {
    private String status;
    private String msg;
    private Boolean isLogin;
    private User user;

    public Result(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Result(String status, Boolean isLogin) {
        this.status = status;
        this.isLogin = isLogin;
    }

    public Result(String status, String msg, User user) {
        this.status = status;
        this.msg = msg;
        this.user = user;
    }

    public Result(String status, String msg, Boolean isLogin, User user) {
        this.status = status;
        this.msg = msg;
        this.isLogin = isLogin;
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
