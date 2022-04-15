package server.commands;

import common.dao.RouteDAO;

/**
 * Класс команды REMOVE BY ID, предназначенный для удаления элемента по его id
 *
 * @param
 */
public class RemoveById extends ACommands {

    public String execute(RouteDAO routeDAO) {
        if (routeDAO.getAll().size() == 0) {
            return ("коллекция пустая. нечего удалять");
        } else {
            try {
                //TODO переделать часть где берут аргументы :)))))))) то же самле с апдейт бай айди
                int id = Integer.parseInt(args.get(0));
                if (!routeDAO.removeById(id)) {
                    return ("нет элемента с таким id. введите команду заново с правильным id");
                } else {
                    return ("элемент успешно удален");

                }
            }
            catch (IndexOutOfBoundsException e){
                return ("брат забыл айди ввести походу");
            }
            catch (RuntimeException e) {
                return ("непредвиденная ошибка в классе команды: " + e.getMessage());
            }

        }
    }
}
