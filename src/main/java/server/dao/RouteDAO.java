package server.dao;


import common.utils.Route;
import common.utils.RouteInfo;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

public class RouteDAO implements DAO {

    private  Deque<Route> collection = new ArrayDeque<>();

    public String initDate = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy : HH.mm.ss"));


    public void create(Route route) {
        collection.add(route);
    }

    /**
     * Метод, который позволяет обновить элемент коллекции по его id
     *
     * @param id        - id элемента, который пользователь хочет обновить
     * @param routeInfo - характеристики, свойственные элементы коллекции
     */
    public boolean update(int id, RouteInfo routeInfo) {
        for (Route route : collection) {
            if (route.getId() == id) {
                route.update(routeInfo);
                return true;
            }
        }
        return false;
    }

    public boolean delete(int id) {
        return collection.removeIf(route -> route.getId() == id);
    }

    public Route get(int id) {
        for (Route route : collection) {
            if (route.getId() == id) {
                return route;
            }
        }
        return null;
    }

    public Deque<Route> getAll() {
        return new ArrayDeque<>(collection);
    }

    public void clear() {
        collection.clear();
    }

    public boolean removeById(int id){
        Optional<Route> route = collection.stream()
                .filter(r->r.getId()==id)
                .findFirst();
        if (route.isPresent()){
            collection.remove(route.get());
            return true;
        }
        return false;
    }


    public String getDescription() {

        StringBuilder out = new StringBuilder();
        for (Route route : collection)
            out.append(route.getDescription()).append(System.lineSeparator());

        return out.toString();
    }
//TODO если получиться что убрать Optional<Route> из туСтринга и написать стримом
    public String printFirst() {
        return (collection.getFirst().toString());
    }

    /**
     * Метод, который позволяет удалить первый элемнт коллекции
     */
    public void removeFirst() {
        collection.removeIf(route -> route.equals(collection.getFirst()));
    }

    /**
     * Метод, который позволяет вывести всю коллекцию
     *
     * @return collection
     */
    public String getCollection() {
        StringBuilder out = new StringBuilder(" ");
        for (Route route : collection)
            out.append(route.toString()).append(System.lineSeparator());

//        if(collection.size() == 0){
//            out.append("пусто");
//        }
        return (out.toString());
    }

    @Override
    public String toString() {
        return "Route{" + "type: " + collection.getClass().getSimpleName() + "," + "size: " + collection.size() + "," +
                "initDate: " + initDate + '\'' +
                '}';
    }

    public int getMaxId() {
        if (collection.isEmpty()) return 0;
        return collection.getLast().getId();
    }

    public int getSize() {
        return collection.size();
    }


    public RouteDAO(Deque<Route> collection) {
        this.collection = collection;
    }
    public RouteDAO(){}

    public boolean exit;
    public void trueExit(){
        exit = true;
    }
}
