package server;

import common.dao.RouteDAO;
import common.interaction.Response;
import server.commands.ACommands;

public class ServerResponse extends Response {

    protected String gotACommand(String command){
        String output = " ";
        output = "получена команда: " + command;
        return output;
    }

    protected String commandResponse(ACommands commands, RouteDAO dao){
        String output = " ";
        output = commands.execute(dao);
        return output;
    }
    String msg;
//TODO я не знаю как писать эту парашу если честно
    public ServerResponse(String msg) {
        this.msg = msg;
    }

}
