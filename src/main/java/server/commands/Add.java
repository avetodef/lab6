package server.commands;

import common.console.Console;
import common.dao.RouteDAO;
import common.exceptions.ExitException;
import common.utils.Route;
import common.utils.RouteInfo;

import java.util.NoSuchElementException;

/**
 * Класс команды ADD, предназначенный для добавления элемента в коллекцию
 */
public class Add extends ACommands{
    Console console = new Console();

    public String execute(RouteDAO routeDAO) {
        try {
            RouteInfo info = console.info();
            Route route = new Route(info.name, info.x, info.y, info.fromX,
                    info.fromY, info.nameFrom, info.toX, info.toY, info.nameTo,
                    info.distance);
            routeDAO.create(route);
        }catch (NoSuchElementException e){throw new ExitException(e.getMessage());}
        catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        catch (RuntimeException e) {
            e.printStackTrace();
            return "невозможно добавить элемент в коллекцию" + System.lineSeparator();

        }
        return "элемент добавлен в коллекцию";
    }
}
