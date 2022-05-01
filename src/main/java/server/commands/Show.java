package server.commands;

import server.dao.RouteDAO;
import common.interaction.Response;
import common.interaction.Status;

/**
 * Класс команды SHOW, предназначенный для вывода коллекции на консоль
 */
public class Show extends ACommands {

    @Override
    public Response execute(RouteDAO routeDAO) {
        if (routeDAO.getAll().size() == 0) {
            response.msg("пусто").status(Status.COLLECTION_ERROR);

        }
        else
        response.status(Status.OK).msg(routeDAO.getCollection());

        return response;
    }
}