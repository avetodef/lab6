package server.commands;

import common.dao.RouteDAO;
import server.file.FileManager;

import java.io.IOException;

/**
 * Класс команды SAVE, предназначенный для сохранения элементов в коллекцию
 */
public class Save extends ACommands{
    FileManager writer = new FileManager();
    public String execute(RouteDAO routeDAO) {
            try {
                writer.save(routeDAO);
                return "ура сохранилось";
            } catch (RuntimeException | IOException e) {
                return ("не удалось сохранить коллекцию " + e.getMessage() + System.lineSeparator());
            }
        }
    }
