package server.commands;

import common.dao.RouteDAO;
import common.utils.Route;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс команды PRINT DESCENDING DISTANCE, предназначенный для вывода значений поля distance в порядке убывания
 */
public class PrintDescendingDistance extends ACommands {

    static List<Integer> distanceList = new ArrayList<>();

    public String execute(RouteDAO routeDAO) {

        for (Route route : routeDAO.getAll()) {
            distanceList.add(route.getDistance());
        }
        if (routeDAO.getAll().size() == 0) {
            return ("коллекция пустая. нечего выводить");
        } else {

            Collections.sort(distanceList);
            Collections.reverse(distanceList);
            String output = distanceList.toString();
            distanceList.clear();
            return "значения поля distance всех элементов в порядке убывания: " + output;

        }
    }

}
