package server.commands;


import server.dao.RouteDAO;
import common.exceptions.ExitException;
import common.interaction.Response;
import common.interaction.Status;
import common.utils.Route;


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
            response.msg("ошибка..." + e.getMessage()).status(Status.COLLECTION_ERROR);
        }
        catch (RuntimeException e) {
            e.printStackTrace();
            response.msg("невозможно добавить элемент в коллекцию" + e.getMessage()).status(Status.COLLECTION_ERROR);

        }
        response.msg("элемент добавлен в коллекцию").status(Status.OK);
        //:(
        return response;
    }
}
