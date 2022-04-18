package server;

import common.console.ConsoleOutputer;
import server.dao.RouteDAO;
import common.exceptions.ExitException;
import common.interaction.Request;
import common.interaction.Response;
import common.interaction.Status;
import common.json.JsonConverter;
import common.utils.IdGenerator;
import server.commands.ACommands;
import server.commands.Save;
import server.file.FileManager;

import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class ServerApp {
    Scanner fromKeyboard = new Scanner(System.in);
    FileManager manager = new FileManager();
    RouteDAO dao = manager.read();
    ConsoleOutputer output = new ConsoleOutputer();

    protected void mainServerLoop() throws IOException {

        IdGenerator.reloadId(dao);
        ACommands command;
        Response serverResponse;
        Response errorResponse = new Response();
        errorResponse.setStatus(Status.SERVER_ERROR);

        try {

            int port = 6666;
            output.printPurple("Ожидаю подключение клиента");
            ServerSocket serverSocket = new ServerSocket(port, 1);

            while (true) {

                try {

                    Socket socket = serverSocket.accept();
                    InputStream socketInputStream = socket.getInputStream();
                    OutputStream socketOutputStream = socket.getOutputStream();

                    DataOutputStream dataOutputStream = new DataOutputStream(socketOutputStream);

                    output.printPurple("Клиент подключился");


                    while (true) {

                        try {
                            output.printWhite("готов принимать запросы от клиента");
                            String requestJson;
                            StringBuilder builder = new StringBuilder();
                            int byteRead;
                            while ((byteRead = socketInputStream.read()) != -1) { //тут падает
                                if (byteRead == 0) break;
                                builder.append((char) byteRead);
                            }
                            requestJson = builder.toString();

                            Request request = JsonConverter.des(requestJson);

                            command = ACommands.getCommand(request);

                            serverResponse = command.execute(dao);
                            dataOutputStream.writeUTF(JsonConverter.serResponse(serverResponse));

                            Save.execute(dao);

                        } catch (NullPointerException e) {
                            errorResponse.setMsg("Введённой вами команды не существует. Попробуйте ввести другую команду." + e.getLocalizedMessage()
                                    + e.getCause());
                            dataOutputStream.writeUTF(JsonConverter.serResponse(errorResponse));

                        } catch (NoSuchElementException e) {
                            throw new ExitException("пока............");

                        } catch (BindException e) {
                            e.printStackTrace();

                        } catch (UTFDataFormatException e) {
                            errorResponse.setMsg("зачем прогу ломаешь, зачем добавляешь 500+ элементов ");
                            dataOutputStream.writeUTF(JsonConverter.serResponse(errorResponse));
                        }
                    }
                } catch (SocketException e) {
                    System.err.println("клиент упал. подожди немного. закончить работу клиента? yes или no?");

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
                    System.err.println("ну подожди еще значит");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
