package hello.entity;

public abstract class Result<T> {
    private String status;
    private String msg;
    T data;

    protected Result(String status, String msg) {
        this(status, msg, null);
    }


    protected Result(String status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
