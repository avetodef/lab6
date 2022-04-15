package common.console;

import common.utils.Route;

public interface MessageHandler {
    static void output(String msg) {}
    static void output(Route route){}
}
