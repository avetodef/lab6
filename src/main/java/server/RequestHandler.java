package server;

import common.dao.RouteDAO;
import common.interaction.Request;
import common.utils.Route;
import server.commands.Help;
import server.commands.Info;
import server.commands.Show;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Deque;

public class RequestHandler {

    private Socket socket;
    private RouteDAO dao;
    private boolean exit = false;

    RequestHandler(Socket socket, RouteDAO dao){
        this.dao = dao;
        this.socket = socket;
        dao.trueExit();
    }

    public void run(){
        try(ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())){
            Request request;
            while (!exit){
                try{
                    request = (Request) ois.readObject();
                }
                catch (ClassNotFoundException e){
                    System.out.println(e.getMessage());
                    request = new Request(" ");
                }
                String command = request.command;
                File file = request.file;
                Deque<Route> collection = request.collection;

                try{
                    switch (command){
                        case ("help"):
                            oos.writeObject(help.execute(dao));
                            break;
                        case "info":
                            oos.writeObject(info.execute(dao));
                        case "show":
                            oos.writeObject(show.execute(dao));
                    }
                }
                catch (IOException e){
                    System.out.println( e.getMessage());
                }
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    Help help = new Help();
    Info info = new Info();
    Show show = new Show();
}
