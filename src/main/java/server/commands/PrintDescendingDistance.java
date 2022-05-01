package server.commands;

import server.dao.RouteDAO;
import common.interaction.Response;
import common.interaction.Status;
import common.utils.Route;


import java.util.Comparator;

/**
 * Класс команды PRINT DESCENDING DISTANCE, предназначенный для вывода значений поля distance в порядке убывания
 */
public class PrintDescendingDistance extends ACommands {


    public Response execute(RouteDAO routeDAO) {

        StringBuilder builder = new StringBuilder();
        routeDAO.getAll().stream()
                .sorted(Comparator.comparingInt(Route::getDistance))
                .forEach(r->builder.append(r.getDistance()).append(" "));


        if (routeDAO.getAll().size() == 0)

            response.msg("пусто").status(Status.COLLECTION_ERROR);

        else

            response.msg("значения поля distance всех элементов в порядке возрастания: " + builder).status(Status.OK);


        return response;
    }

}
