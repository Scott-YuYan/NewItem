package hello.entity;

public class LoginResult extends Result<User> {
    Boolean isLogin;

    public static LoginResult getSuccessResult(String msg, User user) {
        return new LoginResult("ok", msg, user);
    }

    public static LoginResult getFailResult(String msg) {
        return new LoginResult("fail", msg, null);
    }

    public static LoginResult getIsLoginStatus(User user) {
        return new LoginResult("ok", "", true, user);
    }

    public static LoginResult getIsNotLoginStatus() {
        return new LoginResult("ok", "", false,null);
    }


    private LoginResult(String status, String msg, User user) {
        super(status, msg, user);
    }

    private LoginResult(String status, String msg, Boolean isLogin, User user) {
        super(status, msg, user);
        this.isLogin = isLogin;
    }


    public Boolean getLogin() {
        return isLogin;
    }
}
