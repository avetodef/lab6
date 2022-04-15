package server.commands;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**f
 * Класс, предназначенный для добавления классов команд
 */
public class CommandSaver {
    public static final Map<String, ACommands> commandsMap = new LinkedHashMap<>();
    static {
        commandsMap.put("help", new Help());
        commandsMap.put("info", new Info());
        commandsMap.put("show", new Show());
        commandsMap.put("add", new Add());
        commandsMap.put("update_by_id", new UpdateById());
        commandsMap.put("remove_by_id", new RemoveById());
        commandsMap.put("clear", new Clear());
        //commandsMap.put("save", new Save());
        commandsMap.put("execute_script", new ExecuteScript());
        //commandsMap.put("exit", new Exit()); //done
        commandsMap.put("remove_first", new RemoveFirst());
        commandsMap.put("head", new Head());
        commandsMap.put("add_if_min", new AddIfMin());
        commandsMap.put("print_unique_distance", new PrintUniqueDistance());
        commandsMap.put("print_ascending_distance", new PrintAscendingDistance());
        commandsMap.put("print_descending_distance", new PrintDescendingDistance());
        commandsMap.put("rzhaka", new Rzhaka());
        commandsMap.put("tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt", new Test());

    }

    public static final Map<String, String> commandDescription = new LinkedHashMap<>();
    static {
        commandDescription.put("help: ", "вывести справку по доступным командам");
        commandDescription.put("info: ", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.) \")");
        commandDescription.put("show: " , "вывести в стандартный поток вывода все элементы коллекции в строковом представлении ");
        commandDescription.put("add: " , "добавить новый элемент в коллекцию ");
        commandDescription.put("update id {element} : " ,"обновить значение элемента коллекции, id которого равен заданному ");
        commandDescription.put("remove_by_id id :" , "удалить элемент из коллекции по его id ");
        commandDescription.put("clear :  " , "очистить коллекцию ");
        commandDescription.put("save: " , "сохранить колекцию в файл ");
        commandDescription.put("execute_script file_name :" , "считать и исполнить скрипт из указанного файла.");
        //commandDescription.put("exit: " , "завершить работу клиента");
        commandDescription.put("remove_first : " , "удалить первый элемент из коллекции ");
        commandDescription.put("head : " , "вывести первый элемент коллекции ");
        commandDescription.put("add_if_min {element} : " , "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции ");
        commandDescription.put("print_unique_distance : " , "вывести уникальные значения поля distance всех элементов в коллекции ");
        commandDescription.put("print_field_ascending_distance :  " , "вывести значения поля distance всех элементов в порядке возрастания ");
        commandDescription.put("print_field_descending_distance :",  "вывести значения поля distance всех элементов в порядке убывания ");
        commandDescription.put("rzhaka: ","и вот мы снова втроем, в прекрасной аудитории 306. давайте все помолимся что сегодня мы защитимся!!");
    }
    /**
     * Добавление на консоль команд
     *
     * @return command
     */

    public static ACommands getCommand(List<String> input) {

        ACommands command = commandsMap.get(input.get(0));
        input.remove(0);
        command.addArgs(input);

        return command;
    }

    public static boolean checkCommand(List<String> s){

        ACommands command = commandsMap.get(s.get(0));

        return command != null;
    }
}

