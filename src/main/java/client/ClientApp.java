package client;

import common.console.Console;
import common.console.ConsoleOutputer;
import common.console.ConsoleReader;
import common.exceptions.EmptyInputException;
import common.exceptions.ExitException;
import common.interaction.Request;
import common.interaction.Response;
import common.json.JsonConverter;
import common.utils.RouteInfo;
import server.commands.ACommands;
import server.commands.CommandSaver;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class ClientApp {


    ConsoleReader consoleReader = new ConsoleReader();
    ConsoleOutputer output = new ConsoleOutputer();
    Scanner fromKeyboard = new Scanner(System.in);
    ByteBuffer buffer = ByteBuffer.allocate(40000);
    Console console = new Console();

    //TODO обработать пицот миллионов поломок джокера по типу поменял порт, подключил два клиента, еще что-нибудь
    int serverPort = 6666;

    protected void mainClientLoop() {


        List<String> input;
        String serverResponse;
        Request request;

        try {
            Selector selector = Selector.open();
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            //TODO после первой команды перестает работать почему то...

            socketChannel.connect(new InetSocketAddress("localhost", serverPort));
            socketChannel.register(selector, SelectionKey.OP_CONNECT);

            while (true) {

                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    SocketChannel client = (SocketChannel) key.channel();
                    //try
                    {
                        if (key.isConnectable()) {
                            System.out.println("connect");

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
                            System.out.println("write");
                            try {

                                input = consoleReader.reader();
                                request = new Request(input, null);
                                ASCIIArt.ifExit(input, output);

                                readAndSend(input, request, socketChannel);


                            } catch (NullPointerException e) {
                                output.printRed("Введённой вами команды не существует. Попробуйте ввести другую команду.");
                            } catch (EmptyInputException e) {
                                output.printRed(e.getMessage());
                            } catch (IndexOutOfBoundsException e) {
                                output.printRed("брат забыл айди ввести походу");
                            } catch (IOException e) {
                                System.out.println("writable problems: " + e.getMessage());
                            }
                            client.register(selector, SelectionKey.OP_READ);
                            continue;
                        }

                        if (key.isReadable()) {
                            System.out.println("read");
                            try {
                                socketChannel.read(buffer);
                                buffer.flip();

                                serverResponse = StandardCharsets.UTF_8.decode(buffer).toString().substring(2);

                                Response response = JsonConverter.desResponse(serverResponse);
                                printPrettyResponse(response);

                                buffer.clear();

                            } catch (RuntimeException e) {
                                System.out.println("readable problems: " + e.getMessage());
                            }
                            client.register(selector, SelectionKey.OP_WRITE);

                        }
                    }

                }

            }

        }
        catch (UnknownHostException e) {
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

        if (CommandSaver.checkCommand(input)) {

            ACommands command = CommandSaver.getCommand(input);
            if (command.isAsker()) {
                RouteInfo info = console.info();
                request.setInfo(info);
            }
            if (command.isIdAsker()){
                if (input.size() != 2) throw new IndexOutOfBoundsException();
            }
            socketChannel.write(StandardCharsets.UTF_8.encode(JsonConverter.ser(request)));
            System.out.println("sending to the server...");


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