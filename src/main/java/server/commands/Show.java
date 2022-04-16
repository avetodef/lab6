package server.commands;

import common.dao.RouteDAO;
import common.interaction.Response;
import common.interaction.Status;

/**
 * Класс команды SHOW, предназначенный для вывода коллекции на консоль
 */
public class Show extends ACommands {

    @Override
    public Response execute(RouteDAO routeDAO) {
        if (routeDAO.getAll().size() == 0) {
            response.setMsg("коллекция пустая");
            response.setStatus(Status.COLLECTION_ERROR);
        }
        else
        {response.setMsg(routeDAO.getCollection());
        response.setStatus(Status.OK);}

        return response;
    }
}