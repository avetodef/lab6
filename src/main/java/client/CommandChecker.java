package client;

import common.exceptions.EmptyInputException;
import common.interaction.Response;
import common.interaction.Status;
import server.commands.ACommands;
import server.commands.CommandSaver;
import server.commands.ExecuteReader;
import server.dao.RouteDAO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandChecker extends ACommands{
//TODO как я это вижу. у тебя тут практически то же самое что и в самом классе скрипта, но только у тебя булеан и если что-то не так то просто
    //выводится сообщение что файла нет итд.
    // а вот если у тебя в скрипте написано нормально что-то то тогда возвращается правда и собственно клиент может отсылать команду на сервер
    public boolean ifExecuteScript( ) {

        String nameOfScript = args.get(1); //ok

        if (ExecuteReader.checkNameOfFileInList(nameOfScript)) {

            ExecuteReader.listOfNamesOfScripts.add(nameOfScript);

            try {
                List<String> listOfCommands = Files.readAllLines(Paths.get(nameOfScript + ".txt").toAbsolutePath());

                for (String lineOfFile : listOfCommands
                ) {

                    String command = lineOfFile.trim();
                    System.out.println("command");
                    if (command.isEmpty()) {
                        throw new EmptyInputException();
                    }
                    List<String> args = new ArrayList<>(Arrays.asList(command.split(" ")));

                    try {

                        if (CommandSaver.checkCommand(args))
                            return true;
                    } catch (RuntimeException e) {
                        System.out.println("в скрипте параша написана, переделывай" //TODO по аналогии с этим сделай когда ловятся остальные исключения или рекурсия
                        );
                        return false;
                    }
                }
            } catch (NoSuchFileException e) {

            } catch (IOException e) {

            }
            ExecuteReader.listOfNamesOfScripts.clear();
        } else {
            response.msg("пу пу пу.... обнаружена рекурсия").status(Status.USER_EBLAN_ERROR);
        }

    }

    @Override
    public Response execute(RouteDAO routeDAO) {
        return null;
    }
}
