package server.commands;


import server.dao.RouteDAO;
import common.interaction.Response;
import common.interaction.Status;

/**
 * Класс команды PRINT ASCENDING DISTANCE, предназначенный для вывода значений поля distance в порядке возрастания
 */
public class PrintAscendingDistance extends ACommands{


    public Response execute(RouteDAO routeDAO) {

        StringBuilder builder = new StringBuilder();
        routeDAO.getAll().stream()
                .sorted((r1, r2) -> r2.getDistance() - r1.getDistance())
                .forEach(r->builder.append(r.getDistance()).append(" "));


        if (routeDAO.getAll().size() == 0)
            response.status(Status.COLLECTION_ERROR).msg("коллекция пустая. нечего выводить");
        else
            response.msg("значения поля distance всех элементов в порядке возрастания: " + builder).status(Status.OK);

        return response;
    }
}
