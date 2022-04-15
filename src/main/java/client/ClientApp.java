package client;

import common.console.ConsoleOutputer;
import common.console.ConsoleReader;
import common.dao.RouteDAO;
import common.exceptions.EmptyInputException;
import common.exceptions.ExitException;
import common.json.JsonConverter;
import common.utils.IdGenerator;
import server.commands.CommandSaver;
import server.file.FileManager;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ClientApp {

     FileManager manager = new FileManager();
     RouteDAO dao = manager.read();
     ConsoleReader consoleReader = new ConsoleReader();
     ConsoleOutputer outputer = new ConsoleOutputer();
     Scanner fromKeyboard = new Scanner(System.in);
    ByteBuffer buffer = ByteBuffer.allocate(2048);
    //TODO обработать пицот миллионов поломок джокера по типу поменял порт, подключил два клиента, еще что-нибудь
    //TODO сделать чтобы передавались не строки а объекты класса реквест и респонз (сообщение егошина момент)
    int serverPort = 6666;
    protected  void mainClientLoop() {

        IdGenerator.reloadId(dao);
        List<String> input;
        String serverResponse;


    try {//todo как заставить байт буфер и потоки дружить....

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(true); //подключается если блокирующий канал... если неблокирующий то не подключается

        //todo нужнл все переделывать селекторами.
        //socketChannel.bind(new InetSocketAddress("localhost", serverPort));

        socketChannel.connect(new InetSocketAddress("localhost", serverPort));

        System.out.println(socketChannel.isConnected());

        while (true) {
            try {

                input = consoleReader.reader();

                List<String> toCheck = input;
                ifExit(input, dao);

                if (CommandSaver.checkCommand(toCheck)) {

                    buffer = StandardCharsets.UTF_8.encode(JsonConverter.serialize(input));
                    //System.out.println(buffer);
                    //System.out.println(socketChannel.write(buffer));
                    socketChannel.write(buffer);
                    System.out.println("capacity:" +  buffer.capacity());
                    System.out.println("limit: " + buffer.limit());

                    System.out.println("команда отправляется на сервер...");
                }
                else
                    throw new NullPointerException("Введённой вами команды не существует. Попробуйте ввести другую команду.");

                //todo передает все по 9 символов... а иногда не по 9 а по рандому. может выкинуть 27 симолов а может 3 символа
                //todo мне кажется что он выводит только символов какой у него лимит... а как сделать лимит больше я не знаю
                buffer.clear();

                //TimeUnit.MINUTES.sleep(1);

                socketChannel.read(buffer);
                buffer.flip();
                //buffer.limit(100);
//TODO рассказываю будующей себе. короче он считает за лимит и капасити то сколько букв в твоей команде. и еще он если не успевает вывести
                // предыдущий резльтат выполнения команды то он продолжает его выводить
// tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt

                System.out.println("capacity:" +  buffer.capacity());
                System.out.println("limit: " + buffer.limit());
                System.out.println(buffer.toString());
                serverResponse = StandardCharsets.UTF_8.decode(buffer).toString();
                outputer.printWhite("сообщение от сервера: "+serverResponse);


//                buffer.clear(); //ничего не записывается в буфер
//                int bRead = socketChannel.read(buffer);
//                System.out.println(bRead);
//                serverResponse = StandardCharsets.UTF_8.decode(buffer).toString(); //ну конечно же)

                //System.out.println(serverResponse); //печатает что сервер респонз пустой...


            }
            catch (NullPointerException e) {
                outputer.printRed("Введённой вами команды не существует. Попробуйте ввести другую команду.");
            }
            catch (EmptyInputException e) {
                outputer.printRed(e.getMessage());
            }
            catch (IndexOutOfBoundsException e) {
                outputer.printRed("брат забыл айди ввести походу");
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
        System.out.println("пытаюсь переподключиться...");
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
        outputer.printCyan("__________                                 \n" +
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

        //outputer.printCyan("\t\t\t\t\t\tNika and Sofia production\n");
        System.out.println("Для того чтобы начать введите команду. Чтобы увидеть список доступных команд введите help");
    }

    private  void ifExit(List<String> command, RouteDAO dao){

        if (command.contains("exit")){
            outputer.printPurple("  _______________                        |*\\_/*|________\n" +
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
}