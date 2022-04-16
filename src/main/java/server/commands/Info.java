package server.commands;

import common.dao.RouteDAO;
import common.interaction.Response;
import common.interaction.Status;


/**
 * Класс команды INFO, предназначенный для вывода информации об элементах коллекции. Вывод осуществляется с помощью команды getDescription.
 */
public class Info extends ACommands {

    public Response execute(RouteDAO routeDAO) {

        response.setMsg(routeDAO.toString());
        response.setStatus(Status.OK);

        return response;
    }
}
