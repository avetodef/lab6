package server.commands;


import common.dao.RouteDAO;
import common.utils.Route;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Класс команды PRINT ASCENDING DISTANCE, предназначенный для вывода значений поля distance в порядке возрастания
 */
public class PrintAscendingDistance extends ACommands{


    public String execute(RouteDAO routeDAO) {

        StringBuilder builder = new StringBuilder();
        routeDAO.getAll().stream()
                .sorted((r1, r2) -> r2.getDistance() - r1.getDistance())
                .forEach(r->builder.append(r.getDistance()).append(" "));


        if (routeDAO.getAll().size() == 0)
            return "коллекция пустая. нечего выводить";
        else
            return "значения поля distance всех элементов в порядке возрастания: " + builder;
    }
}
