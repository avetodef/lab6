package server.commands;

import common.dao.RouteDAO;

/**
 * Класс команды SHOW, предназначенный для вывода коллекции на консоль
 */
public class Show extends ACommands {

    @Override
    public String execute(RouteDAO routeDAO) {
        if (routeDAO.getAll().size() == 0) {
            return ("коллекция пустая");
        }
        else
            return (routeDAO.getCollection());

    }
}