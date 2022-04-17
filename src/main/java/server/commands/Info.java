package server.commands;

import server.dao.RouteDAO;
import common.interaction.Response;
import common.interaction.Status;


/**
 * Класс команды INFO, предназначенный для вывода информации об элементах коллекции. Вывод осуществляется с помощью команды getDescription.
 */
public class Info extends ACommands {

    public Response execute(RouteDAO routeDAO) {

        response.msg(routeDAO.toString()).status(Status.OK);

        return response;
    }
}
