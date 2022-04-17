package server.commands;

import server.dao.RouteDAO;
import common.interaction.Response;
import common.interaction.Status;
import common.utils.Route;

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
            if (routeDAO.getAll().size() == 0)
                response.msg("пусто. нечего обновлять").status(Status.COLLECTION_ERROR);

             else {

                if (!checkId(idFromConsole, routeDAO))
                response.status(Status.USER_EBLAN_ERROR).msg("элемента с таким id нет. ведите другой id");

                else {
                    try {
                        routeDAO.update(idFromConsole, info);
                    }
                    catch (IndexOutOfBoundsException e){
                        response.msg("брат забыл айди ввести походу").status(Status.USER_EBLAN_ERROR);
                    }
                    catch (NumberFormatException e){
                        response.msg("леее почему не int ввел братан").status(Status.USER_EBLAN_ERROR);

                    }
                    catch (RuntimeException e) {
                        response.msg("чета проихошло..." + e.getMessage()).status(Status.UNKNOWN_ERROR);

                    }
                    response.msg("элемент коллекции обновлен").status(Status.OK);

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
