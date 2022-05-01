package common.exceptions;

public class InvalidReceivedException extends Throwable {
    public InvalidReceivedException(){
        super("Данные повреждены");
    }
}
