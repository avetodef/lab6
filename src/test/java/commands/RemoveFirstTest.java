package commands;

import common.dao.RouteDAO;
import org.junit.jupiter.api.Assertions;
import common.utils.Route;
import server.commands.RemoveFirst;

import java.util.ArrayDeque;
import java.util.Deque;


class RemoveFirstTest {

    @org.junit.jupiter.api.Test

    void execute() {
        RemoveFirst removeFirst = new RemoveFirst();
        RouteDAO dao = new RouteDAO();
        Deque<Route> collection = new ArrayDeque<>();
        Route firstElement = new Route("first", 1, 1.0, 1, (long) 1.0, "1", 1, 1, "1", 2);
        Route secondElement = new Route("second", 2, 2.0, 2, (long) 2.0, "2", 2, 2, "2", 2);

        collection.add(firstElement);
        collection.add(secondElement);

        int size = collection.size();

        removeFirst.execute(dao);

        int assertedSize = collection.size();

        Assertions.assertEquals(size ,assertedSize);

    }
}