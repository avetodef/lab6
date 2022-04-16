package server.commands;


import common.dao.RouteDAO;
import common.interaction.Response;
import common.interaction.Status;
import common.utils.Route;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Класс команды PRINT ASCENDING DISTANCE, предназначенный для вывода значений поля distance в порядке возрастания
 */
public class PrintAscendingDistance extends ACommands{


    public Response execute(RouteDAO routeDAO) {

        StringBuilder builder = new StringBuilder();
        routeDAO.getAll().stream()
                .sorted((r1, r2) -> r2.getDistance() - r1.getDistance())
                .forEach(r->builder.append(r.getDistance()).append(" "));


        if (routeDAO.getAll().size() == 0){
            response.setMsg("коллекция пустая. нечего выводить");
            response.setStatus(Status.COLLECTION_ERROR);}
        else{
            response.setMsg("значения поля distance всех элементов в порядке возрастания: " + builder);
            response.setStatus(Status.OK);
        }
        return response;
    }
}
