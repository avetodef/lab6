package server.commands;

import common.console.Console;
import common.dao.RouteDAO;
import common.exceptions.ExitException;
import common.interaction.Response;
import common.interaction.Status;
import common.utils.Route;
import common.utils.RouteInfo;

import java.util.NoSuchElementException;

/**
 * Класс команды ADD, предназначенный для добавления элемента в коллекцию
 */
public class Add extends ACommands{

    {
        isAsker = true;
    }
    public Response execute(RouteDAO routeDAO) {
        try {
            Route route = new Route(info.name, info.x, info.y, info.fromX,
                    info.fromY, info.nameFrom, info.toX, info.toY, info.nameTo,
                    info.distance);
            routeDAO.create(route);
        }catch (NoSuchElementException e){throw new ExitException(e.getMessage());}

        catch (NullPointerException e){
            response.setMsg("ошибка..." + e.getMessage());
            response.setStatus(Status.COLLECTION_ERROR);
        }
        catch (RuntimeException e) {
            e.printStackTrace();
            response.setMsg("невозможно добавить элемент в коллекцию" + e.getMessage());
            response.setStatus(Status.COLLECTION_ERROR);

        }
        response.setMsg("элемент добавлен в коллекцию");
        response.setStatus(Status.OK);
        //:(
        return response;
    }
}
