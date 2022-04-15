package server.commands;

import common.dao.RouteDAO;


/**
 * Класс команды INFO, предназначенный для вывода информации об элементах коллекции. Вывод осуществляется с помощью команды getDescription.
 */
public class Info extends ACommands {

    public String execute(RouteDAO routeDAO) {

            return (routeDAO.toString());

    }
}
