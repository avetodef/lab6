package server;

import common.console.ConsoleOutputer;
import common.dao.RouteDAO;
import common.exceptions.EmptyInputException;
import common.exceptions.ExitException;
import common.json.JsonConverter;
import common.utils.IdGenerator;
import server.commands.ACommands;
import server.commands.CommandSaver;
import server.file.FileManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ServerApp {

    static FileManager manager = new FileManager();
    static RouteDAO dao = manager.read();
    static ConsoleOutputer outputer = new ConsoleOutputer();

    protected static void mainServerLoop(){

        IdGenerator.reloadId(dao);
        ACommands command;
        ServerResponse serverResponse = new ServerResponse();
        try {
            int port = 6666;
            outputer.printPurple("Ожидаю подключение клиента");
            ServerSocket ss = new ServerSocket(port);
            Socket socket = ss.accept();
            // TODO сделать чтобы сервер ждал пока клиент не подключится даже если клиент упадет

            outputer.printPurple("Клиент подключился");
            while (true) {

                // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
                InputStream socketInputStream = socket.getInputStream();
                OutputStream socketOutputStream = socket.getOutputStream();

                // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
                DataInputStream in = new DataInputStream(socketInputStream);
                DataOutputStream out = new DataOutputStream(socketOutputStream);
                try {

                    String commandFromClient;
                    commandFromClient = in.readUTF();

                    out.writeUTF(serverResponse.gotACommand(commandFromClient));
                    command = CommandSaver.getCommand((Objects.requireNonNull(JsonConverter.deserialize(commandFromClient))));
                    command.execute(dao);

                    out.writeUTF(serverResponse.commandResponse(command, dao));

                } catch (NullPointerException e) {
                    System.out.println("Введённой вами команды не существует. Попробуйте ввести другую команду." + e.getLocalizedMessage()
                    + e.getCause());
                } catch (NoSuchElementException e) {
                    throw new ExitException("пока............");
                } catch (EmptyInputException e) {
                    outputer.printRed("ошибка на сервере: " + e.getLocalizedMessage());
                } catch (IndexOutOfBoundsException e) {
                    outputer.printRed("брат забыл айди ввести походу");
                } catch (BindException e) {
                    System.out.println(e.getLocalizedMessage());
                    //TODO выкидывает бесконечный поток исключений если соединение преравно :)))
                } catch (SocketException e) {
                    outputer.printRed("соединение с клиентом прервано " + e.getLocalizedMessage());
                    break;
                }
            }
        }
        catch(IllegalArgumentException e){
            System.out.println("имена портов клиента и сервера не совпадают: " + e.getMessage());

        }
        catch(Exception x) {
            System.out.println("ошибка ServerApp: ") ;
        x.printStackTrace();}
    }


}
