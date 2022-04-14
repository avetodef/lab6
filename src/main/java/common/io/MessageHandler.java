package common.io;
import common.utils.Route;

/**
 * нтерфейс для вывода на консоль
 */
public interface MessageHandler {
    static void output(String msg) {}
    static void output(Route route){}
}