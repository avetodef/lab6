package server.commands;

import common.dao.RouteDAO;

/**
 * Класс команды REMOVE FIRST, предназначенный для удаления первого элемента из коллекции
 */
public class RemoveFirst extends ACommands{

    public String execute(RouteDAO routeDAO) {
        if (routeDAO.getAll().size() == 0) {
            return ("коллекция пустая. нечего удалять");
        } else {
            routeDAO.removeFirst();
            //System.out.println(routeDAO.getSize());
            return ("первый элемент коллекции успешно удален");
        }
    }

}
