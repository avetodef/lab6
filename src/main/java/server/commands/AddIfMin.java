package server.commands;

import common.console.Console;
import common.dao.RouteDAO;
import common.interaction.Response;
import common.interaction.Status;
import common.utils.Route;
import common.utils.RouteInfo;

import java.util.*;

/**
 * Класс команды ADD IF MIN, предназначенный для добавления элементов в коллекцию, если он является наименьшим
 */
public class AddIfMin extends ACommands{
    Console console = new Console();

    public Response execute(RouteDAO routeDAO) {
        Optional<Route> minRoute = routeDAO.getAll().stream().min(Comparator.comparingInt(Route::getDistance));

        Integer minDistance = minRoute.isPresent() ? minRoute.get().getDistance() : Integer.MAX_VALUE;
        if (minDistance != 2) {
            try {
                RouteInfo info = console.info();

                if (info.distance < minDistance) {
                    Route route = new Route(info.name, info.x, info.y, info.fromX,
                            info.fromY, info.nameFrom, info.toX, info.toY, info.nameTo,
                            info.distance);
                    routeDAO.create(route);
                } else {
                    response.setMsg("\"у нового элемента поле distance больше чем у минимального. вызовите команду заново с валидным полем distance\"");
                    response.setStatus(Status.USER_EBLAN_ERROR);
                }
            } catch (RuntimeException e) {
                response.setMsg("невозможно добавить элемент в коллекцию: " + e.getMessage());
                response.setStatus(Status.COLLECTION_ERROR);
            }
        }
        else
            response.setMsg("в коллекции уже лежит элемент с минимальным возможным значением поля distance");
            response.setStatus(Status.COLLECTION_ERROR);
            return response;
    }

}






//public class AddIfMin extends ACommands{
//
//    List<Integer> distanceList = new ArrayList<>();
//    Console console = new Console();
//
//    public String execute(RouteDAO routeDAO) {
//        distanceList = getDistanceList(routeDAO);
//        Optional<Route> minRoute = routeDAO.getAll().stream().min(Comparator.comparingInt(Route::getDistance));
//
//        Integer minDistance = minRoute.isPresent() ? minRoute.get().getDistance() : Integer.MAX_VALUE;
//
//        try {
//            if (!checkDistanceList(distanceList)) {
//                RouteInfo info = console.info();
//
//                if (info.distance > distanceList.get(0)) {
//                    System.out.println("у нового элемента значение поля distance больше минимального");
//                    System.out.println("вызовите команду снова с другим значением поля distance");
//
//                } else {
//                    Route route = new Route(info.name, info.x, info.y, info.fromX,
//                            info.fromY, info.nameFrom, info.toX, info.toY, info.nameTo,
//                            info.distance);
//                    routeDAO.create(route);
//                    distanceList.clear();
//                    return ("новый элемент добавлен в коллекцию");
//
//                }
//            }
//            else {
//                return ("в коллекции уже есть элемент с минимальным возможным значением");
//            }
//        }
//        catch (RuntimeException e){
//            return ("невозможно добавить элемент в коллекцию: " + e.getMessage());
//        }
//        return "а что тут писать я не знаю";
//    }
//
//    private List<Integer> getDistanceList(RouteDAO dao){
//        for (Route route : dao.getAll()){
//            distanceList.add(route.getDistance());
//        }
//        Collections.sort(distanceList);
//        return distanceList;
//    }
//
//    private boolean checkDistanceList(List<Integer> distanceList){
//        return !distanceList.contains(2);
//    }
//}
