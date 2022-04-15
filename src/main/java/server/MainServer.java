package server;

import common.dao.RouteDAO;

import java.io.IOException;
import java.net.Socket;

public class MainServer {

    public static void main(String[] ar) throws IOException {
        ServerApp server = new ServerApp();
        server.mainServerLoop();
//        RequestHandler handler = new RequestHandler(new Socket(), new RouteDAO());
//        handler.run();
    }
}
