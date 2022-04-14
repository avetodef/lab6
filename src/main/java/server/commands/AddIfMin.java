package server.commands;

import common.console.Console;
import common.dao.RouteDAO;
import common.utils.Route;
import common.utils.RouteInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс команды ADD IF MIN, предназначенный для добавления элементов в коллекцию, если он является наименьшим
 */
public class AddIfMin extends ACommands{

    List<Integer> distanceList = new ArrayList<>();
    Console console = new Console();

    public String execute(RouteDAO routeDAO) {
        distanceList = getDistanceList(routeDAO);
            try {
                if (!checkDistanceList(distanceList)) {
                    RouteInfo info = console.info();

                    if (info.distance > distanceList.get(0)) {
                        System.out.println("у нового элемента значение поля distance больше минимального");
                        System.out.println("вызовите команду снова с другим значением поля distance");

                    } else {
                        Route route = new Route(info.name, info.x, info.y, info.fromX,
                                info.fromY, info.nameFrom, info.toX, info.toY, info.nameTo,
                                info.distance);
                        routeDAO.create(route);
                        distanceList.clear();
                        return ("новый элемент добавлен в коллекцию");

                    }
                }
                else {
                    return ("в коллекции уже есть элемент с минимальным возможным значением");
                }
            }
            catch (RuntimeException e){
                return ("невозможно добавить элемент в коллекцию: " + e.getMessage());
            }
        return "а что тут писать я не знаю";
    }

    private List<Integer> getDistanceList(RouteDAO dao){
        for (Route route : dao.getAll()){
            distanceList.add(route.getDistance());
        }
        Collections.sort(distanceList);
        return distanceList;
    }

    private boolean checkDistanceList(List<Integer> distanceList){
        return !distanceList.contains(2);
    }
}
