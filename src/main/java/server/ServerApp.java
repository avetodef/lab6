package server;

import common.console.ConsoleOutputer;
import common.dao.RouteDAO;
import common.exceptions.EmptyInputException;
import common.exceptions.ExitException;
import common.json.JsonConverter;
import common.utils.IdGenerator;
import server.commands.ACommands;
import server.commands.CommandSaver;
import server.commands.Save;
import server.file.FileManager;
import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ServerApp {

    FileManager manager = new FileManager();
    RouteDAO dao = manager.read();
    ConsoleOutputer outputer = new ConsoleOutputer();

    protected void mainServerLoop() throws IOException {

        IdGenerator.reloadId(dao);
        ACommands command;
        ServerResponse serverResponse = new ServerResponse();

        try {
            int port = 6666;
            outputer.printPurple("Ожидаю подключение клиента");
            ServerSocket serverSocket = new ServerSocket(port);

            // TODO сделать чтобы сервер ждал пока клиент не подключится даже если клиент упадет

            Socket socket = serverSocket.accept();
            InputStream socketInputStream = socket.getInputStream();
            OutputStream socketOutputStream = socket.getOutputStream();

            DataOutputStream dataOutputStream = new DataOutputStream(socketOutputStream);

            System.out.println(socket.isConnected()); //true
            outputer.printPurple("Клиент подключился");


//            Selector selector = Selector.open();
//            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
//            InetSocketAddress hostAddress = new InetSocketAddress("localhost", port);
//            serverSocketChannel.bind(hostAddress);

            while (true) {

                try {

                    String commandFromClient;
                    StringBuilder builder = new StringBuilder();
                    int byteRead;

                    while((byteRead = socketInputStream.read())!=-1) {
                        if (byteRead == 0) break;
                        builder.append((char) byteRead);
                    }

                    System.out.println("builder: "+builder);

                    commandFromClient = builder.toString();
                    builder = new StringBuilder();
                    command = CommandSaver.getCommand((Objects.requireNonNull(JsonConverter.deserialize(commandFromClient))));
                    //это полная поебота
                    if (command.isAsker()){
                        String routeInfo;
                        int byteInfo;

                        while ((byteInfo = socketInputStream.read())!= -1){
                            if (byteInfo == 0) break;
                            builder.append( (char) byteInfo );
                        }

                        routeInfo = builder.toString();
                        command.setInfo(JsonConverter.desToRouteInfo(routeInfo));
                        System.out.println(routeInfo);
                    }
                    command.execute(dao);

                    dataOutputStream.writeUTF(serverResponse.commandResponse(command, dao));

                    Save.execute(dao);

                } catch (NullPointerException e) {
                    System.out.println("Введённой вами команды не существует. Попробуйте ввести другую команду." + e.getLocalizedMessage()
                            + e.getCause());
                } catch (NoSuchElementException e) {
                    throw new ExitException("пока............");
                } catch (EmptyInputException e) {
                    outputer.printRed("ошибка на сервере: " + e.getLocalizedMessage());
                } catch (IndexOutOfBoundsException e) {
                    outputer.printRed("брат забыл айди ввести походу" + e.getMessage());
                    e.printStackTrace();
                } catch (BindException e) {
                    System.out.println(e.getLocalizedMessage());
                }
                //TODO выкидывает бесконечный поток исключений если соединение преравно :)))
                catch (IOException exception) {
                    System.err.println("Клиент пока недоступен...такое случается.");
                    //жди......
                    break;


                }
//                catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
    //TODO этот чекер не работает...... вместе с ним ломается и клиент и сервер и вообще все падает
    private boolean checkConnection(Socket socket){
        //TODO надо как-то написать проверятель есть ли подсоединение или нет....
        //па ра ша
        return false;
    }
}
