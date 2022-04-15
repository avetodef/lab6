package server.commands;

import common.dao.RouteDAO;
import server.file.FileManager;

import java.io.IOException;

/**
 * Класс команды SAVE, предназначенный для сохранения элементов в коллекцию
 */
public class Save {

    public static String execute(RouteDAO routeDAO) {
        FileManager writer = new FileManager();
            try {
                writer.save(routeDAO);
                return "ура сохранилось";
            } catch (RuntimeException | IOException e) {
                return ("не удалось сохранить коллекцию " + e.getMessage() );
            }
        }
    }
