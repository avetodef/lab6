package server.commands;

import server.dao.RouteDAO;
import common.interaction.Response;
import common.interaction.Status;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс команды PRINT UNIQUE DISTANCE, предназначенный для вывода значения уникального поля distance
 */
public class PrintUniqueDistance extends ACommands{

    static Set<Integer> distanceSet = new HashSet<>();

    public Response execute(RouteDAO routeDAO) {
        if (routeDAO.getAll().size() == 0)
            response.msg("пусто").status(Status.COLLECTION_ERROR);
         else {
            routeDAO.getAll().stream().forEach(r -> distanceSet.add(r.getDistance()));

            response.msg("уникальные значения поля distance: " + distanceSet.toString()).
                    status(Status.OK);

        }

        return response;
    }

}
