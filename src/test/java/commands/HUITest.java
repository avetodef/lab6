package commands;

import common.utils.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import server.dao.RouteDAO;

import java.util.ArrayDeque;
import java.util.Deque;

public class HUITest {
    private RouteDAO routeDAO;
    private

    @BeforeEach
    void setup(){
        RouteDAO routeDAO = new RouteDAO();
    }

    @Test
    @DisplayName("add")
    void add(){
        Route route = new Route(1, "first", 1, 1.0, 1, (long) 1.0, "1", 1, 1, "1", 2);
        routeDAO.create(route);
        assertFalse(routeDAO.getAll().isEmpty());
    }

    @Test
    @DisplayName("clear")
    void clear(){
        Route route1 = new Route(1, "first", 1, 1.0, 1, (long) 1.0, "1", 1, 1, "1", 2);
        Route route2 = new Route(1, "first", 1, 1.0, 1, (long) 1.0, "1", 1, 1, "1", 2);
        routeDAO.create(route1);
        routeDAO.create(route2);
        routeDAO.clear();
        assertTrue(routeDAO.getAll().isEmpty());
    }


}
