package client;

import common.console.ConsoleOutputer;
import common.console.ConsoleReader;
import common.dao.RouteDAO;
import common.exceptions.EmptyInputException;
import common.exceptions.ExitException;
import common.json.JsonConverter;
import common.utils.IdGenerator;
import server.commands.CommandSaver;
import server.commands.Save;
import server.file.FileManager;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientApp {

    static FileManager manager = new FileManager();
    static RouteDAO dao = manager.read();
    static ConsoleReader consoleReader = new ConsoleReader();
    static ConsoleOutputer outputer = new ConsoleOutputer();

    //TODO обработать пицот миллионов поломок джокера по типу поменял порт, подключил два клиента, еще что-нибудь
    //TODO и мне кажется что это все какой то бред и вообще не то что нужно
    //TODO сделать чтобы передавались не строки а объекты класса реквест и респонз (сообщение егошина момент)
    //TODO обработать временную недоступность сервера

    static String address = "localhost";

    protected static void mainClientLoop() {

        IdGenerator.reloadId(dao);
        List<String> input;
        String serverResponse;

        try {
            int serverPort = 6666;
            Socket socket = new Socket(address, serverPort);

            InputStream socketInputStream = socket.getInputStream();
            OutputStream socketOutputStream = socket.getOutputStream();

            DataInputStream in = new DataInputStream(socketInputStream);
            DataOutputStream out = new DataOutputStream(socketOutputStream);
            while (true) {
                try {

                    input = consoleReader.reader();
                    List<String> toCheck = input;
//                    if (input.contains("exit")) System.exit(0);

                    ifExit(input, dao);
                    if (CommandSaver.checkCommand(toCheck)) {
                        System.out.println("команда отправляется на сервер...");
                        out.writeUTF(JsonConverter.serialize(input));
                    } else
                        throw new NullPointerException("Введённой вами команды не существует. Попробуйте ввести другую команду.");

                    serverResponse = in.readUTF();
                    outputer.printWhite(serverResponse);
                    serverResponse = in.readUTF();
                    System.out.println(serverResponse);

                } catch (NullPointerException e) {
                    outputer.printRed("Введённой вами команды не существует. Попробуйте ввести другую команду.");
                } catch (EmptyInputException e) {
                    outputer.printRed(e.getMessage());
                } catch (IndexOutOfBoundsException e) {
                    outputer.printRed("брат забыл айди ввести походу");
                }
                catch (NoSuchElementException e) {
                    throw new ExitException("пока............");}
            }
        }
        catch (UnknownHostException e){
            System.out.println("неизвестный хост!");
            System.exit(0); //TODO тут тоже падает. ну короче везде тут он падает и умирает
        }
        catch(IllegalArgumentException e){
            System.out.println("имена портов клиента и сервера не совпадают: " + e.getMessage());
        }
        catch (NoSuchElementException e) {
            throw new ExitException("пока-пока");}
        catch (IOException e){ //TODO тут он тоже головой падает и выводит бесконечный поток исключений ))
            //System.out.println("ошибка на уровне mainClientLoop: " + e.getMessage());
        e.printStackTrace();
            System.exit(0);

        }

    }

    protected static void runClient() {

        while (true){
            try{
                mainClientLoop();
            }
            catch (ExitException e){
                System.out.println(e.getMessage());
                break;
            }
            catch (RuntimeException e){
                System.out.println("ошибка.....: " + e.getMessage());
            }
        }

    }

    protected static void greetings() {
        outputer.printCyan("\t\t\t\t\t▒██░░░─░▄█▀▄─░▐█▀▄──░▄█▀▄─     ▒█▀▀ \n" +
                "\t\t\t\t\t▒██░░░░▐█▄▄▐█░▐█▀▀▄░▐█▄▄▐█     ▒▀▀▄ \n" +
                "\t\t\t\t\t▒██▄▄█░▐█─░▐█░▐█▄▄▀░▐█─░▐█     ▒▄▄▀ ");
        outputer.printCyan("\t\t\t\t\t\tNika and Sofia production\n");
        System.out.println("Для того чтобы начать введите команду. Чтобы увидеть список доступных команд введите help");
    }

    private static void ifExit(List<String> command, RouteDAO dao){
        Save save = new Save();
        if (command.contains("exit")){
            save.execute(dao);
            System.exit(0);
        }
    }
}