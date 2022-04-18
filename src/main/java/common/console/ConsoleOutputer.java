package common.console;


import common.utils.Route;

public class ConsoleOutputer implements MessageHandler {

    public static void output(String msg) {System.out.println(msg); }
    public static void output(Route r){
        System.out.println(r.toString());} //надо посмотреть, мб стоит еще переопределить тустринг

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public  void printRed(String msg){
        System.out.println(ANSI_RED + msg + ANSI_RESET);
    }

    public void printGreen(String msg){
        System.out.println(ANSI_GREEN + msg + ANSI_RESET);
    }

    public   void printYellow(String msg){
        System.out.println(ANSI_YELLOW + msg + ANSI_RESET);
    }

    public  void printBlue(String msg){
        System.out.println(ANSI_BLUE + msg + ANSI_RESET);
    }

    public   void printPurple(String msg){
        System.out.println(ANSI_PURPLE + msg + ANSI_RESET);
    }

    public   void printCyan(String msg){
        System.out.println(ANSI_CYAN + msg + ANSI_RESET);
    }

    public void printWhite(String msg){
        System.out.println(ANSI_WHITE + msg + ANSI_RESET);
    }

    public void printNormal(String msg){
        System.out.println(msg);
    }

}



