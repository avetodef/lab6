package server.commands;

import common.dao.RouteDAO;
import common.exceptions.EmptyInputException;
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
    //TODO почему не работет апдейт бай айди.
    FileManager manager = new FileManager();
    RouteDAO dao = manager.read();

    public String execute(RouteDAO routeDAO) {

        String nameOfScript = args.get(0);
        if (ExecuteReader.checkNameOfFileInList(nameOfScript)) {
            ExecuteReader.listOfNamesOfScripts.add(nameOfScript);
            try {

                List<String> listOfCommands = Files.readAllLines(Paths.get(nameOfScript + ".txt").toAbsolutePath());
                for (String lineOfFile : listOfCommands
                ) {
                    ACommands commands;
                    String command = lineOfFile.trim();
                    Map<String, String> ids = new HashMap<>();

                    if (command.isEmpty()) {
                        throw new EmptyInputException();
                    }
                    List<String> args = new ArrayList<>(Arrays.asList(command.split(" ")));
                    try {
                        commands = CommandSaver.getCommand(args);
                        commands.execute(dao);
                    } catch (RuntimeException e) {
                        return ("ты норм? в скрипте параша написана, переделывай");
                    }
                }
            }
            catch (NoSuchFileException e){
                return ("файл не найден");
            }
            catch (IOException e) {
                e.printStackTrace();
                return ("Все пошло по пизде, чекай мать: " + System.lineSeparator());

            }
            ExecuteReader.listOfNamesOfScripts.clear();
        } else {
            return ("пу пу пу.... обнаружена рекурсия");
        }
        return "что-то не так произошло....";
    }

}

