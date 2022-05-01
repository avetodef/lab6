package server.commands;

import server.dao.RouteDAO;
import common.interaction.Response;
import common.interaction.Status;

/**
 * Класс команды HEAD, предназначенный для вывода первого элемента коллекции
 */
public class Head extends ACommands{

    public Response execute(RouteDAO routeDAO) {
        if (routeDAO.getAll().size() == 0) {

            response.msg("пусто...").status(Status.COLLECTION_ERROR);
        } else {

            response.msg(routeDAO.printFirst()).status(Status.OK);
        }

        return response;
    }

}
