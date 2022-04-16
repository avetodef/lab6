package server.commands;

import common.dao.RouteDAO;
import common.interaction.Response;
import common.interaction.Status;
import common.utils.Route;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс команды PRINT UNIQUE DISTANCE, предназначенный для вывода значения уникального поля distance
 */
public class PrintUniqueDistance extends ACommands{

    static Set<Integer> distanceSet = new HashSet<>();

    public Response execute(RouteDAO routeDAO) {
        if (routeDAO.getAll().size() == 0) {
            response.setMsg("коллекция пустая. нечего выводить");
            response.setStatus(Status.COLLECTION_ERROR);
        } else {
            routeDAO.getAll().stream().forEach(r -> distanceSet.add(r.getDistance()));
            //System.out.println("уникальные значения поля distance: " + distanceSet.toString());
            response.setMsg("уникальные значения поля distance: " + distanceSet.toString());
            response.setStatus(Status.OK);
        }

        return response;
    }

}
