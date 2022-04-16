package server.commands;

import common.dao.RouteDAO;
import common.exceptions.EmptyInputException;
import common.interaction.Response;
import common.interaction.Status;
import server.file.FileManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;

/**
 * Класс команды EXECUTE SCRIPT, предназначенный для чтения и исполнения скрипта из файла
 */
public class ExecuteScript extends ACommands{
    //TODO эта параша нихуя не работает и я боюсь сюда лезть. @ника чини. /беззлобно
    FileManager manager = new FileManager();
    RouteDAO dao = manager.read();

    public Response execute(RouteDAO routeDAO) {

        String nameOfScript = args.get(1);
        if (ExecuteReader.checkNameOfFileInList(nameOfScript)) {
            ExecuteReader.listOfNamesOfScripts.add(nameOfScript);
            try {
                List<String> listOfCommands = Files.readAllLines(Paths.get(nameOfScript + ".txt").toAbsolutePath());
                for (String lineOfFile : listOfCommands
                ) {
                    ACommands commands;
                    String command = lineOfFile.trim();

                    if (command.isEmpty()) {
                        throw new EmptyInputException();
                    }
                    List<String> args = new ArrayList<>(Arrays.asList(command.split(" ")));
                    try {
                        commands = CommandSaver.getCommand(args);
                        commands.execute(dao);
                    } catch (RuntimeException e) {
                        response.setMsg("ты норм? в скрипте параша написана, переделывай");
                        response.setStatus(Status.USER_EBLAN_ERROR);
                    }
                }
            }
            catch (NoSuchFileException e){
                response.setMsg("файл не найден");
                response.setStatus(Status.FILE_ERROR);
            }
            catch (IOException e) {
                response.setMsg("Все пошло по пизде, чекай мать: ");
                response.setStatus(Status.UNKNOWN_ERROR);

            }
            ExecuteReader.listOfNamesOfScripts.clear();
        } else {
            response.setMsg("пу пу пу.... обнаружена рекурсия");
            response.setStatus(Status.USER_EBLAN_ERROR);
        }
        response.setMsg("что-то не так произошло....");
        response.setStatus(Status.UNKNOWN_ERROR);

        return response;
    }

}

