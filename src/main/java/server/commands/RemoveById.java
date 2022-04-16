package server.commands;

import common.dao.RouteDAO;
import common.interaction.Response;
import common.interaction.Status;

/**
 * Класс команды REMOVE BY ID, предназначенный для удаления элемента по его id
 *
 * @param
 */
public class RemoveById extends ACommands {

    public Response execute(RouteDAO routeDAO) {
        if (routeDAO.getAll().size() == 0) {
            response.setMsg("коллекция пустая. нечего удалять");
            response.setStatus(Status.COLLECTION_ERROR);
        } else {
            try {
                int id = Integer.parseInt(args.get(1));
                if (routeDAO.delete(id)) {
                    response.setMsg("нет элемента с таким id. введите команду заново с правильным id" );
                    response.setStatus(Status.USER_EBLAN_ERROR);
                } else {
                    response.setMsg("элемент успешно удален");
                    response.setStatus(Status.OK);

                }
            }
            catch (IndexOutOfBoundsException e){
                response.setMsg("брат забыл айди ввести походу ");
                response.setStatus(Status.USER_EBLAN_ERROR);
            }
            catch (RuntimeException e) {
                response.setMsg("непредвиденная ошибка в классе команды: " + e.getMessage());
                response.setStatus(Status.UNKNOWN_ERROR);
            }

        }
        return response;
    }

}
