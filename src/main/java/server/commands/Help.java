package server.commands;

import common.dao.RouteDAO;
import common.interaction.Response;
import common.interaction.Status;


/**
 * Класс команды HELP, предназначенной для выведения списка команд и их возможностей
 */
public class Help extends ACommands {

    public Response execute(RouteDAO routeDAO) {
//        CommandSaver.commandDescription.forEach((key,value) -> responsePrinter.appendln(key + value));
       response.setMsg("help : вывести справку по доступным командам " + System.lineSeparator() +
               "info: вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.) "
               + System.lineSeparator() +
               "show: " + "вывести в стандартный поток вывода все элементы коллекции в строковом представлении "
               + System.lineSeparator() +
        "add {element} : " + "добавить новый элемент в коллекцию " + System.lineSeparator() +
               "update id {element} : " + "обновить значение элемента коллекции, id которого равен заданному " + System.lineSeparator() +
               "remove_by_id id :" + "удалить элемент из коллекции по его id "
               + System.lineSeparator() +

        "clear :  " + "очистить коллекцию "
               + System.lineSeparator() +
        "save: " + "сохранить колекцию в файл "
               + System.lineSeparator() +
        "execute_script file_name :" + "считать и исполнить скрипт из указанного файла."
               + System.lineSeparator() +
        " В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме."
               + System.lineSeparator() +
        "exit: " + "завершить программу (без сохранения в файл) "
               + System.lineSeparator() +
        "remove_first : " + "удалить первый элемент из коллекции "
               + System.lineSeparator() +
        "head : " + "вывести первый элемент коллекции "
               + System.lineSeparator() +
        "add_if_min {element} : " + "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции "
               + System.lineSeparator() +
        "print_unique_distance : " + "вывести уникальные значения поля distance всех элементов в коллекции "
               + System.lineSeparator() +
        "print_ascending_distance :  " + "вывести значения поля distance всех элементов в порядке возрастания "
               + System.lineSeparator() +
        "print_descending_distance :  вывести значения поля distance всех элементов в порядке убывания "
               + System.lineSeparator() +
        "rzhaka: новая попытка защиты, новый сюрприз специально только для alex_egosin!!!");
       response.setStatus(Status.OK);
       return response;
    }

}