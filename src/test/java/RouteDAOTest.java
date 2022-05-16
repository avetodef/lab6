import common.utils.Route;
import common.utils.RouteInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import server.dao.RouteDAO;

import java.time.ZonedDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RouteDAOTest {

    Deque<Route> collection = new ArrayDeque<>();

    RouteDAO dao = new RouteDAO(collection);

    Route firstElement = new Route(1, "first", 1, 1.0, 1, (long) 1.0, "1", 1, 1, "1", 2);
    Route secondElement = new Route(2, "second", 2, 2.0, 2, (long) 2.0, "2", 2, 2, "2", 2);

    //тестируем метод add
    @Test
    void collectionSizeShouldBeUpByOne() {
        int sizeBefore = collection.size();
        dao.create(firstElement);
        int sizeAfter = collection.size();
        int assertedSize = sizeBefore + 1;

        Assertions.assertEquals(assertedSize, sizeAfter);
    }

    //removeFirst
    @Test
    void shouldBeNoFirstElement() {
        collection.add(firstElement);
        collection.add(secondElement);

        dao.removeFirst();

        Assertions.assertNull(dao.get(1));
    }

    //тестируем метод delete(int id)
    @Test
    void shouldBeNoElementWithId2() {
        collection.add(firstElement);
        collection.add(secondElement);

        int id = 2;

        dao.delete(id);

        Route routeWithId2 = dao.get(id);

        assertNull(routeWithId2);

    }

    //тестируем метод update(int id)

    @Test
//    //TODO переделать этот тестик + еще как минимум 4 штуки добавить
    public void elementShouldBeUpdated() {
        collection.add(firstElement);
        collection.add(secondElement);

        ArrayList<String> data = new ArrayList<>();
        data.add("2");
        data.add("new");
        data.add("12.0");
        data.add("12.0");
        data.add(ZonedDateTime.now().toString());
        data.add("12");
        data.add("12");
        data.add("from");
        data.add("12");
        data.add("12");
        data.add("to");
        data.add("2");
        RouteInfo newRoute = new RouteInfo(data);
        Route assertedRoute = new Route(2, "new", 12.0, 12.0, 12.0, (long) 12.0, "from", 12, 12, "to", 2);

        dao.update(2, newRoute);

        Assertions.assertEquals(secondElement.getName(), assertedRoute.getName());

        Assertions.assertEquals(secondElement.getCoordinates().getCoorX(), assertedRoute.getCoordinates().getCoorX());
        Assertions.assertEquals(secondElement.getCoordinates().getCoorY(), assertedRoute.getCoordinates().getCoorY());

        Assertions.assertEquals(secondElement.getFrom().getFromX(), assertedRoute.getFrom().getFromX());
        Assertions.assertEquals(secondElement.getFrom().getFromY(), assertedRoute.getFrom().getFromY());
        Assertions.assertEquals(secondElement.getFrom().getName(), assertedRoute.getFrom().getName());

        Assertions.assertEquals(secondElement.getTo().getToX(), assertedRoute.getTo().getToX());
        Assertions.assertEquals(secondElement.getTo().getToY(), assertedRoute.getTo().getToY());
        Assertions.assertEquals(secondElement.getTo().getName(), assertedRoute.getTo().getName());

        Assertions.assertEquals(secondElement.getDistance(), assertedRoute.getDistance());
    }

    //тестируем getMaxID
    @Test
    @DisplayName("Проверка метода получения максимального id элемента")
    public void maxIdShouldBe2() {

        collection.add(firstElement);
        collection.add(secondElement);
        List<Integer> ids = new ArrayList<>();

        for (Route route : collection) {
            ids.add(route.getId());
        }
        int maxId = -1;
        for (Integer id : ids) {
            if (id > maxId)
                maxId = id;
        }

        Assertions.assertEquals(maxId, dao.getMaxId());

    }


    private RouteDAO routeDAO;

    @BeforeEach
    public void setup() {
        System.out.println("Instantiating RouteDAO");
        routeDAO = new RouteDAO();
    }

    @Test
    @DisplayName("Проверка метода добавления нового элемента в коллекцию")
    void add() {
        //Route route = new Route(1, "name", 2, 4.0, 5.0, (long) 6.99, "name_from", 5, 5, "name_to", 7, null);
        //routeDAO.create(route);
        collection.add(firstElement);
        assertFalse(dao.getCollection().isEmpty());
        assertEquals(1, dao.getAll().size());
    }

    /*@Test
    @DisplayName("Проверка метода очистки коллекции")
    void clear() {
        Route route1 = new Route(1,"name",2, 2.0,2,(long) 2.0,"name_from",2,2,"name_to",2,null);
        Route route2 = new Route(1,"name",2, 2.0,2,(long) 2.0,"name_from",2,2,"name_to",2,null);
    routeDAO.create(route1);
    routeDAO.create(route2);
    routeDAO.
    }
}*/
}