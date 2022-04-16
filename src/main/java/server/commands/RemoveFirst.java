package server.commands;

import common.dao.RouteDAO;
import common.interaction.Response;
import common.interaction.Status;

/**
 * Класс команды REMOVE FIRST, предназначенный для удаления первого элемента из коллекции
 */
public class RemoveFirst extends ACommands{

    public Response execute(RouteDAO routeDAO) {
        if (routeDAO.getAll().size() == 0) {
            response.setMsg("коллекция пустая. нечего удалять");
            response.setStatus(Status.COLLECTION_ERROR);
        } else {
            routeDAO.removeFirst();
            //System.out.println(routeDAO.getSize());
            response.setMsg("первый элемент коллекции успешно удален");
            response.setStatus(Status.OK);
        }
        return response;
    }

}
