package server.commands;

import common.dao.RouteDAO;
import common.interaction.Response;
import common.interaction.Status;
import server.file.FileManager;

import java.io.IOException;
import java.sql.PreparedStatement;

/**
 * Класс команды SAVE, предназначенный для сохранения элементов в коллекцию
 */
public class Save {

    public static Response execute(RouteDAO routeDAO) {
        Response response = new Response();
        FileManager writer = new FileManager();
            try {
                writer.save(routeDAO);
                response.setMsg("ура сохранилось");
                response.setStatus(Status.OK);
            } catch (RuntimeException | IOException e) {
                response.setMsg("не удалось сохранить коллекцию " + e.getMessage());
                response.setStatus(Status.COLLECTION_ERROR);
            }

            return response;
        }
    }
