package client;

import common.console.Console;
import common.console.ConsoleOutputer;
import common.console.ConsoleReader;
import common.exceptions.CustomException;
import common.exceptions.EmptyInputException;
import common.exceptions.ExitException;
import common.interaction.Request;
import common.interaction.Response;
import common.json.JsonConverter;
import common.utils.RouteInfo;
import server.commands.ACommands;
import server.commands.CommandSaver;
import server.dao.RouteDAO;
import server.file.FileManager;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;


public class ClientApp {


    ConsoleReader consoleReader = new ConsoleReader();
    ConsoleOutputer output = new ConsoleOutputer();
    Scanner fromKeyboard = new Scanner(System.in);
    ByteBuffer buffer = ByteBuffer.allocate(40000);
    Console console = new Console();
    CommandChecker commandChecker = new CommandChecker();
    int serverPort = 6666;

    protected void mainClientLoop() {


        List<String> input;
        String serverResponse;
        Request request;

        try {
            Selector selector = Selector.open();
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);

            socketChannel.connect(new InetSocketAddress("localhost", serverPort));
            socketChannel.register(selector, SelectionKey.OP_CONNECT);

            while (true) {

                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    SocketChannel client = (SocketChannel) key.channel();
                    {
                        if (key.isConnectable()) {


                            if (client.isConnectionPending()) {
                                try {
                                    client.finishConnect();
                                    output.printWhite("готов к работе с сервером");
                                } catch (IOException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            client.register(selector, SelectionKey.OP_WRITE);
                            continue;
                        }

                        if (key.isWritable()) {

                            try {

                                input = consoleReader.reader();
                                request = new Request(input, null);
                                ASCIIArt.ifExit(input, output);

                                if (input.contains("execute_script")) {
                                    if (commandChecker.ifExecuteScript(input)) {
                                        readAndSend(input, request, socketChannel);
                                    } else break;
                                } else {
                                    readAndSend(input, request, socketChannel);
                                }

                            } catch (NumberFormatException e) {
                                System.out.println("int введи");
                            } catch (NullPointerException e) {
                                output.printRed("Введённой вами команды не существует. Попробуйте ввести другую команду.");
                            } catch (EmptyInputException e) {
                                output.printRed(e.getMessage());
                                continue;
                            } catch (IndexOutOfBoundsException e) {
                                output.printRed("брат забыл айди ввести походу");
                                continue;
                            } catch (IOException e) {
                                System.out.println("writable problems: " + e.getMessage());
                            }
                            client.register(selector, SelectionKey.OP_READ);
                            continue;
                        }

                        if (key.isReadable()) {

                            try {
                                socketChannel.read(buffer);
                                buffer.flip();

                                serverResponse = StandardCharsets.UTF_8.decode(buffer).toString().substring(2);

                                Response response = JsonConverter.desResponse(serverResponse);
                                printPrettyResponse(response);

                                buffer.clear();

                            } catch (RuntimeException e) {
                                System.out.println("readable problems: " + e.getMessage());
                                e.printStackTrace();
                            }
                            client.register(selector, SelectionKey.OP_WRITE);

                        }
                    }

                }

            }

        } catch (UnknownHostException e) {
            System.err.println("неизвестный хост. порешай там в коде что нибудь ок?");
            System.exit(1);
        } catch (IOException exception) {
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
            System.out.println("жди...");
        }
    }

    private void readAndSend(List<String> input, Request request, SocketChannel socketChannel) throws IOException {
        boolean flag = true;
        if (CommandSaver.checkCommand(input)) {

            ACommands command = CommandSaver.getCommand(input);
            if (command.isIdAsker()) {
                if (input.size() != 2 || Integer.parseInt(input.get(1)) < 0 || input.get(1).contains(".") || input.get(1).contains(",")) {
                    System.err.println("введи нормальный айди");
                    flag = false;

                }
            }
            if (flag) {
                if (command.isAsker()) {
                    RouteInfo info = console.info();
                    request.setInfo(info);
                }

                socketChannel.write(StandardCharsets.UTF_8.encode(JsonConverter.ser(request)));
                System.out.println("sending to the server...");
            } else throw new CustomException("ну значит не отправлю на сервер твою команду. заново вводи");

        } else
            throw new NullPointerException("Введённой вами команды не существует. Попробуйте ввести другую команду.");

    }

    protected void runClient() {

        while (true) {
            try {
                mainClientLoop();
            } catch (ExitException e) {
                System.err.println(e.getMessage());
                break;
            } catch (RuntimeException e) {
                System.err.println("ошибка.....: ");
                e.printStackTrace();
            }
        }

    }


    private void printPrettyResponse(Response r) {
        switch (r.status) {
            case OK -> output.printNormal(r.msg);
            case FILE_ERROR -> output.printBlue(r.msg);
            case UNKNOWN_ERROR, SERVER_ERROR -> output.printRed(r.msg);
            case COLLECTION_ERROR -> output.printYellow(r.msg);
            case USER_EBLAN_ERROR -> output.printPurple(r.msg);
        }
    }


}