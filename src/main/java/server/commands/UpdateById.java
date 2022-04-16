package server.commands;

import common.console.Console;
import common.dao.RouteDAO;
import common.interaction.Response;
import common.interaction.Status;
import common.utils.Route;
import common.utils.RouteInfo;

import static common.console.ConsoleOutputer.output;

/**
 * Класс команды UPDATE BY ID, предназначенный для обновления элемента по его id.
 *
 * @param
 */
public class UpdateById extends ACommands {
    {
        isAsker = true;
    }


    public Response execute(RouteDAO routeDAO) {

            int idFromConsole = Integer.parseInt(args.get(1));
            if (routeDAO.getAll().size() == 0) {
                response.setMsg("коллекция пустая. нечего обновлять");
                response.setStatus(Status.COLLECTION_ERROR);
            } else {

                if (!checkId(idFromConsole, routeDAO))
                {response.setMsg("элемента с таким id нет. ведите другой id");
                response.setStatus(Status.USER_EBLAN_ERROR);}
                else {
                    try {
                        routeDAO.update(idFromConsole, info);
                    }
                    catch (IndexOutOfBoundsException e){
                        response.setMsg("брат забыл айди ввести походу");
                        response.setStatus(Status.USER_EBLAN_ERROR);
                    }
                    catch (RuntimeException e) {
                        response.setMsg("чета проихошло...");
                        response.setStatus(Status.UNKNOWN_ERROR);
                    }
                    response.setMsg("элемент коллекции обновлен");
                    response.setStatus(Status.OK);
                }

            }
            return response;
//        if (routeDAO.getAll().size() != 0){
//            if (checkId(idFromConsole, routeDAO)){
//                try {
//                    RouteInfo info = console.info();
//                    routeDAO.update(idFromConsole, info);
//                }
//                catch (RuntimeException e){
//                    return ("неверный ввод");
//                }
//                return ("элемент коллекции обновлен");
//            }
//            else {
//                return ("нет элемента с таким id. вызовите команду еще раз используя другой id");
//            }
//        }
        //else return ("коллекция пустая. нечего обновлять");
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
