package server;

import common.console.ConsoleOutputer;
import common.dao.RouteDAO;
import common.exceptions.EmptyInputException;
import common.exceptions.ExitException;
import common.interaction.Request;
import common.interaction.Response;
import common.interaction.Status;
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
        Response serverResponse;
        Response errorResponse = new Response();
        errorResponse.setStatus(Status.SERVER_ERROR);
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

                    String requestJson;
                    StringBuilder builder = new StringBuilder();
                    int byteRead;

                    while ((byteRead = socketInputStream.read()) != -1) {
                        if (byteRead == 0) break;
                        builder.append((char) byteRead);
                    }

                    System.out.println("builder: " + builder);

                    requestJson = builder.toString();

                    Request request = JsonConverter.des(requestJson);
                    System.out.println(requestJson);
                    command = ACommands.getCommand(request);

//                    builder = new StringBuilder();
//                    command = CommandSaver.getCommand((Objects.requireNonNull(JsonConverter.deserialize(requestJson))));
//
//                    if (command.isAsker()){
//                        String routeInfo;
//                        int byteInfo;
//
//                        while ((byteInfo = socketInputStream.read())!= -1){
//                            if (byteInfo == 0) break;
//                            builder.append( (char) byteInfo );
//                        }
//
//                        routeInfo = builder.toString();
//                        command.setInfo(JsonConverter.desToRouteInfo(routeInfo));
//                        System.out.println(routeInfo);
//                    }

                    serverResponse = command.execute(dao);
                    dataOutputStream.writeUTF(JsonConverter.serResponse(serverResponse));

                    Save.execute(dao);

                } catch (NullPointerException e) {
                    errorResponse.setMsg("Введённой вами команды не существует. Попробуйте ввести другую команду." + e.getLocalizedMessage()
                            + e.getCause());
                    dataOutputStream.writeUTF(JsonConverter.serResponse(errorResponse));
                } catch (NoSuchElementException e) {
                    throw new ExitException("пока............");
                }
                catch (BindException e) {
                    e.printStackTrace();
                }
                catch (UTFDataFormatException e){
                    errorResponse.setMsg("зачем прогу ломаешь");
                    dataOutputStream.writeUTF(JsonConverter.serResponse(errorResponse));

                }
                //TODO выкидывает бесконечный поток исключений если соединение преравно :)))
                catch (IOException exception) {
                    System.err.println("Клиент пока недоступен...такое случается.");
                    exception.printStackTrace();
                    //жди......
                    break;


                }

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
