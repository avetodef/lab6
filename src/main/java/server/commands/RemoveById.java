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
                int id = Integer.parseInt(args.get(1));
                if (routeDAO.delete(id)) {
                    return "нет элемента с таким id. введите команду заново с правильным id" ;
                } else {
                    return "элемент успешно удален";

                }
            }
            catch (IndexOutOfBoundsException e){
                return ("брат забыл айди ввести походу " + Integer.parseInt(args.get(1)) + " " + e.getMessage());
            }
            catch (RuntimeException e) {
                return ("непредвиденная ошибка в классе команды: " + e.getMessage());
            }

        }
    }
}
