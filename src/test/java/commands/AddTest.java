package commands;

import common.dao.RouteDAO;
import org.junit.jupiter.api.Test;
import common.utils.Route;

import java.time.ZonedDateTime;
import java.util.ArrayDeque;
import java.util.Deque;

class AddTest {

    @Test
    void execute() {
        Add addElement = new Add();
        RouteDAO dao = new RouteDAO();
        ZonedDateTime date = ZonedDateTime.now();
        Deque<Route> collection = new ArrayDeque<>();
        int size = 0;

        Route element = new Route("first", 1, 1.0, 1, (long) 1.0, "1", 1, 1, "1", 2);
        //RouteInfo info = new RouteInfo(1, "first", 1, 1.0, date, 1, (long) 1.0, "1", 1, 1, "1", 2);

        //dao.create(element);
        addElement.execute(dao);



    }
}