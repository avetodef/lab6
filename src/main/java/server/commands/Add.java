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
    {
        isAsker = true;
    }
    public String execute(RouteDAO routeDAO) {
        try {
            Route route = new Route(info.name, info.x, info.y, info.fromX,
                    info.fromY, info.nameFrom, info.toX, info.toY, info.nameTo,
                    info.distance);
            routeDAO.create(route);
        }catch (NoSuchElementException e){throw new ExitException(e.getMessage());}
        catch (NullPointerException e){
            e.printStackTrace();
        }
        catch (RuntimeException e) {
            e.printStackTrace();
            return "невозможно добавить элемент в коллекцию" + e.getMessage();

        }
        return "элемент добавлен в коллекцию";
        //:(
    }
}
