package common.interaction;

public class Response {
    public String msg;
    public Status status;
//TODO если захочется чтобы егошин спросил про паттерен строитель то переписать все на вариант #2

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
   // response.msg("diqwjfdq).status(OK); или в другом порядке
}
