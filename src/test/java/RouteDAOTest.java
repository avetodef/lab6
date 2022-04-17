import server.dao.RouteDAO;
import common.utils.Route;
import common.utils.RouteInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;

class RouteDAOTest {

    Deque<Route> collection = new ArrayDeque<>();

    RouteDAO dao = new RouteDAO(collection);

    Route firstElement = new Route("first", 1, 1.0, 1, (long) 1.0, "1", 1, 1, "1", 2);
    Route secondElement = new Route("second", 2, 2.0, 2, (long) 2.0, "2", 2, 2, "2", 2);

    //тестируем метод add
    @Test
    void collectionSizeShouldBeUpByOne(){
        int sizeBefore = collection.size();
        dao.create(firstElement);
        int sizeAfter = collection.size();
        int assertedSize = sizeBefore + 1;

        Assertions.assertEquals(assertedSize, sizeAfter);
    }
    //тестируем метод clear
    @Test
    void collectionShouldBeClear(){
        collection.add(firstElement);
        collection.add(secondElement);

        dao.clear();

        Assertions.assertEquals(0, collection.size());
    }
    //removeFirst
    @Test
    void shouldBeNoFirstElement(){
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
    public void elementShouldBeUpdated(){
        collection.add(firstElement);
        collection.add(secondElement);
        ZonedDateTime zdt = ZonedDateTime.now();

        RouteInfo newRoute = new RouteInfo(2,"new", 12.0, 12.0, zdt, 12.0, (long) 12.0,"from", 12, 12, "to", 2 );
        Route assertedRoute = new Route("new", 12.0, 12.0, 12.0, (long) 12.0,"from", 12, 12, "to", 2);
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
    public void maxIdShouldBe2(){

        collection.add(firstElement);
        collection.add(secondElement);
        List<Integer> ids = new ArrayList<>();

        for (Route route : collection){
            ids.add(route.getId());
        }
        int maxId = -1;
        for (Integer id : ids) {
            if (id > maxId)
                maxId = id;
        }

        Assertions.assertEquals(maxId, dao.getMaxId());

    }
}