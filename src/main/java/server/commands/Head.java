package server.commands;

import common.dao.RouteDAO;
import common.interaction.Response;
import common.interaction.Status;

/**
 * Класс команды HEAD, предназначенный для вывода первого элемента коллекции
 */
public class Head extends ACommands{

    public Response execute(RouteDAO routeDAO) {
        if (routeDAO.getAll().size() == 0) {
            response.setMsg("пусто...");
            response.setStatus(Status.COLLECTION_ERROR);
        } else {
            response.setMsg(routeDAO.printFirst());
            response.setStatus(Status.OK);
        }
        return response;
    }

}
