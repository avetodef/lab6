package client;

import common.console.Console;
import common.console.ConsoleOutputer;
import common.console.ConsoleReader;
import common.dao.RouteDAO;
import common.exceptions.EmptyInputException;
import common.exceptions.ExitException;
import common.interaction.Request;
import common.interaction.Response;
import common.json.JsonConverter;
import common.utils.IdGenerator;
import common.utils.RouteInfo;
import server.commands.ACommands;
import server.commands.CommandSaver;
import server.file.FileManager;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class ClientApp {

     FileManager manager = new FileManager();
     RouteDAO dao = manager.read();
     ConsoleReader consoleReader = new ConsoleReader();
     ConsoleOutputer output = new ConsoleOutputer();
     Scanner fromKeyboard = new Scanner(System.in);
     ByteBuffer buffer = ByteBuffer.allocate(40000);
     Console console =  new Console();

    //TODO обработать пицот миллионов поломок джокера по типу поменял порт, подключил два клиента, еще что-нибудь
    //TODO сделать чтобы передавались не строки а объекты класса реквест и респонз (сообщение егошина момент)
    int serverPort = 6666;

    protected  void mainClientLoop() {

        IdGenerator.reloadId(dao);
        List<String> input;
        String serverResponse;
        Request request;

        try {

        SocketChannel socketChannel = SocketChannel.open();
        //TODO оно не работает если поставить false...
        socketChannel.configureBlocking(true); //подключается если блокирующий канал... если неблокирующий то не подключается

        //todo нужнл все переделывать селекторами.

        socketChannel.connect(new InetSocketAddress("localhost", serverPort));

        while (true) {
            try {

                input = consoleReader.reader();
                request = new Request(input, null);
                ifExit(input, dao);
                if (CommandSaver.checkCommand(input)) {
                    ACommands command = CommandSaver.getCommand(input);

                    //socketChannel.write(StandardCharsets.UTF_8.encode(JsonConverter.serialize(input)));
                    if (command.isAsker()){
                        RouteInfo info = console.info();
                        request.setInfo(info);
                        //socketChannel.write(StandardCharsets.UTF_8.encode(JsonConverter.serRouteInfo(info)));
                    }
                    socketChannel.write(StandardCharsets.UTF_8.encode(JsonConverter.ser(request)));

                    System.out.println("команда отправляется на сервер...");
                } else
                    throw new NullPointerException("Введённой вами команды не существует. Попробуйте ввести другую команду.");

                socketChannel.read(buffer);
                buffer.flip();

                serverResponse = StandardCharsets.UTF_8.decode(buffer).toString().substring(2);
                Response response = JsonConverter.desResponse(serverResponse);
                printPrettyResponse(response);

                buffer.clear();


            }
            catch (NullPointerException e) {
                output.printRed("Введённой вами команды не существует. Попробуйте ввести другую команду.");
            }
            catch (EmptyInputException e) {
                output.printRed(e.getMessage());
            }
            catch (IndexOutOfBoundsException e) {
                output.printRed("брат забыл айди ввести походу");
            }
            catch (NoSuchElementException e) {
                throw new ExitException("пока-пока");
            }

        }
    }
    catch (UnknownHostException e) {
        System.err.println("неизвестный хост. порешай там в коде что нибудь ок?");
        System.exit(0); //TODO тут тоже падает. ну короче везде тут он падает и умирает
    }
    catch (IOException exception) {
        System.err.println("Сервер пока недоступен. Закончить работу клиента? (напишите {yes} или {no})?");
        String answer;
        while (!(answer = fromKeyboard.nextLine()).equals("no")) {
            switch (answer) {
                case "":
                    break;
                case "yes":
                    System.exit(0);
                    break;
                default:
                    System.out.println("скажи пожалуйста.... yes или no");
            }
        }
        System.out.println("жди...если еще раз выкинется что сервер недоступен то еще подожди. а если не выкинется значит все хорошо");
    }
    }

    protected void runClient() {

        while (true){
            try{
                mainClientLoop();
            }
            catch (ExitException e){
                System.out.println(e.getMessage());
                break;
            }
            catch (RuntimeException e){
                System.out.println("ошибка.....: ");
                e.printStackTrace();
            }
        }

    }

    protected void greetings() {
        //outputer.printCyan("\t\t\t\t\t▒██░░░─░▄█▀▄─░▐█▀▄──░▄█▀▄─     ▒█▀▀ \n\t\t\t\t\t▒██░░░░▐█▄▄▐█░▐█▀▀▄░▐█▄▄▐█     ▒▀▀▄ \n\t\t\t\t\t▒██▄▄█░▐█─░▐█░▐█▄▄▀░▐█─░▐█     ▒▄▄▀ ");
        output.printCyan("          __________                                 \n" +
                "         .'----------`.                              \n" +
                "         | .--------. |                             \n" +
                "         | | LABA 6 | |       ___________              \n" +
                "         | |########| |      /___________\\             \n" +
                ".--------| `--------' |------|    --=--  |-------------.\n" +
                "|        `----,-.-----'      |SUIR MOMENT|             | \n" +
                "|       ______|_|_______     |___________|             | \n" +
                "|      /  NIKA AND SOFIA \\                             | \n" +
                "|     /    PRODUCTION     \\                            | \n" +
                "|     ^^^^^^^^^^^^^^^^^^^^                            | \n" +
                "+-----------------------------------------------------+\n" +
                "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ ");

        System.out.println("Для того чтобы начать введите команду. Чтобы увидеть список доступных команд введите help");
    }

    private  void ifExit(List<String> command, RouteDAO dao){

        if (command.contains("exit")){
            output.printPurple("   _______________                        |*\\_/*|________\n" +
                    "  |  ___________  |     .-.     .-.      ||_/-\\_|______  |\n" +
                    "  | |           | |    .****. .****.     | |           | |\n" +
                    "  | |   0   0   | |    .*****.*****.     | |   0   0   | |\n" +
                    "  | |     -     | |     .*********.      | |     -     | |\n" +
                    "  | |   \\___/   | |      .*******.       | |   \\___/   | |\n" +
                    "  | |___CLIENT___| |       .*****.        | |___SERVER_| |\n" +
                    "  |_____|\\_/|_____|        .***.         |_______________|\n" +
                    "    _|__|/ \\|_|_.............*.............._|________|_\n" +
                    "   / ********** \\          ПОКА             / ********** \\\n" +
                    " /  ************  \\                      /  ************  \\\n" +
                    "--------------------                    --------------------");

            Exit.execute(dao);
        }
    }

    private void printPrettyResponse(Response r){
        switch (r.status){
            case OK -> output.printGreen(r.msg);
            case FILE_ERROR -> output.printBlue(r.msg);
            case UNKNOWN_ERROR -> output.printRed(r.msg);
            case COLLECTION_ERROR -> output.printYellow(r.msg);
            case USER_EBLAN_ERROR -> output.printPurple(r.msg);
        }
    }
}