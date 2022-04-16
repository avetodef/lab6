package client;

import common.dao.RouteDAO;
import common.exceptions.ExitException;
import server.commands.ACommands;
import server.commands.Save;

import java.util.NoSuchElementException;

/**
 * Класс команды EXIT, предназначенный для выхода из выполнения программы
 */
public class Exit  {

    public static String execute() {

        //System.out.println("пока.");
            try {
                //Save.execute(routeDAO);
                System.exit(0);
                return ("завершение работы клиента...");

            } catch (NoSuchElementException e) {
                throw new ExitException("пока..............");
            }
    }
}
