package common.interaction;

import java.io.Serializable;

public class Response implements Serializable {
    public String msg;
    public Status status;

    //            #1
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    //            #2

    public Response msg(String msg) {
        this.msg = msg;
        return this;
    }

    public Response status(Status status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "Response{" +
                "msg='" + msg + '\'' +
                ", status=" + status +
                '}';
    }
}
