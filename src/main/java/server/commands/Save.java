package server.commands;

import server.dao.RouteDAO;
import common.interaction.Response;
import common.interaction.Status;
import server.file.FileManager;

import java.io.IOException;

/**
 * Класс команды SAVE, предназначенный для сохранения элементов в коллекцию
 */
public class Save {

    public static Response execute(RouteDAO routeDAO) {
        Response response = new Response();
        FileManager writer = new FileManager();
            try {
                writer.save(routeDAO);
                response.status(Status.OK).msg("ура сохранилось");

            } catch (RuntimeException | IOException e) {
                response.msg("не удалось сохранить коллекцию " + e.getMessage())
                        .status(Status.COLLECTION_ERROR);
            }

            return response;
        }
    }
