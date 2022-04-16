package common.exceptions;

/**
 *сключение для выхода из основного цикла программы
 */
public final class ExitException extends RuntimeException {
    public ExitException(String msg) {
        super(msg);
    }
}