package server.commands;

import server.dao.RouteDAO;
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
                if (!routeDAO.delete(id))
                    response.msg("нет элемента с таким id. введите команду заново с правильным id" ).
                            status(Status.USER_EBLAN_ERROR);
                 else
                     response.msg("ура удалилось").status(Status.OK);

            }
            catch (IndexOutOfBoundsException e){
                response.status(Status.USER_EBLAN_ERROR).msg("брат забыл айди ввести походу ");
            }
            catch (NumberFormatException e){
                response.msg("леее почему не int ввел братан").status(Status.USER_EBLAN_ERROR);

            }
            catch (RuntimeException e) {
                response.msg("непредвиденная ошибка в классе команды: " + e.getMessage())
                        .status(Status.UNKNOWN_ERROR);
            }

        }
        return response;
    }

}
