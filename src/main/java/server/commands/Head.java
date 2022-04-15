package server.commands;

import common.dao.RouteDAO;

/**
 * Класс команды HEAD, предназначенный для вывода первого элемента коллекции
 */
public class Head extends ACommands{

    public String execute(RouteDAO routeDAO) {
        if (routeDAO.getAll().size() == 0) {
            System.out.println("пусто...");
        } else {
            return (routeDAO.printFirst());
        }
        return " ";
    }

}
