package server.commands;

import common.console.Console;
import common.dao.RouteDAO;
import common.utils.Route;
import common.utils.RouteInfo;

import static common.console.ConsoleOutputer.output;

/**
 * Класс команды UPDATE BY ID, предназначенный для обновления элемента по его id.
 *
 * @param
 */
public class UpdateById extends ACommands {
    Console console = new Console();


    public String execute(RouteDAO routeDAO) {

            int idFromConsole = Integer.parseInt(args.get(0));
            if (routeDAO.getAll().size() == 0) {
                System.out.println("коллекция пустая. нечего обновлять");
            } else {

                if (!checkId(idFromConsole, routeDAO))
                    System.out.println("элемента с таким id нет. ведите другой id");
                else {
                    try {
                        RouteInfo info = console.info();
                        routeDAO.update(idFromConsole, info);
                    }
                    catch (IndexOutOfBoundsException e){
                        System.out.println("брат забыл айди ввести походу");
                    }
                    catch (RuntimeException e) {
                        output("неверный ввод");
                    }
                    output("элемент коллекции обновлен");
                }

            }
        if (routeDAO.getAll().size() != 0){
            if (checkId(idFromConsole, routeDAO)){
                try {
                    RouteInfo info = console.info();
                    routeDAO.update(idFromConsole, info);
                }
                catch (RuntimeException e){
                    return ("неверный ввод");
                }
                return ("элемент коллекции обновлен");
            }
            else {
                return ("нет элемента с таким id. вызовите команду еще раз используя другой id");
            }
        }
        else return ("коллекция пустая. нечего обновлять");
    }
    private boolean checkId(int id, RouteDAO routeDAO){

        for (Route route : routeDAO.getAll()) {
            if (route.getId() == id) {
                return true;
            }
        }
        return false;
    }

}
