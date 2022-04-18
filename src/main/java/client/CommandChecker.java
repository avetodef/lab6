package client;

import common.console.ConsoleOutputer;
import common.exceptions.EmptyInputException;
import common.interaction.Response;
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

public class CommandChecker extends ACommands {
    ConsoleOutputer output = new ConsoleOutputer();

    public boolean ifExecuteScript(List<String> inp) {
        boolean flag = false;

        String nameOfScript = inp.get(1); //вот и нашлось счастье мое. он считает что аргс нулл

        if (ExecuteReader.checkNameOfFileInList(nameOfScript)) {

            ExecuteReader.listOfNamesOfScripts.add(nameOfScript);

            try {
                List<String> listOfCommands = Files.readAllLines(Paths.get(nameOfScript + ".txt").toAbsolutePath());

                for (String lineOfFile : listOfCommands
                ) {

                    String command = lineOfFile.trim();

                    if (command.isEmpty()) {
                        throw new EmptyInputException();
                    }
                    List<String> args = new ArrayList<>(Arrays.asList(command.split(" ")));

                    try {

                        if (CommandSaver.checkCommand(args))
                            return true;
                        else {
                            output.printPurple("в скрипте параша написана, переделывай");
                            flag = false;
                        }
                    } catch (RuntimeException e) {
                        output.printPurple("в скрипте параша написана, переделывай"
                        );
                        flag = false;
                    }
                }
            } catch (NoSuchFileException e) {
                output.printBlue("нет такого файла");
                flag = false;


            } catch (IOException e) {
                output.printRed("что то пошло не так...");
                e.printStackTrace();
                flag = false;
            }
            ExecuteReader.listOfNamesOfScripts.clear();
        } else {
            output.printPurple("рекурсия... интересно кто бы мог решить сделать нам рекурсию....");
            flag = false;
        }
        return flag;
    }

    @Override
    public Response execute(RouteDAO routeDAO) {
        return null;
    }
}
