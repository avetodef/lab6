package server;

import common.dao.RouteDAO;
import common.json.JsonConverter;
import common.utils.RouteInfo;

import java.io.IOException;
import java.net.Socket;

public class MainServer {

    public static void main(String[] ar) throws IOException {
        ServerApp server = new ServerApp();
        server.mainServerLoop();

    }
}
