package hello.entity;

public class Result {
    private String status;
    private String msg;
    private Boolean isLogin;
    private User user;

    public static Result getFailResult(String msg){
        return new Result("fail",msg,false);
    }

    public static Result getOkResult(String msg,User user){
        return new Result("ok",msg,true,user);
    }
    private Result(String status, String msg, Boolean isLogin) {
        this.status = status;
        this.msg = msg;
        this.isLogin = isLogin;
    }

    private Result(String status, String msg, Boolean isLogin, User user) {
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
