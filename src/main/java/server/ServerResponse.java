package server;

import common.dao.RouteDAO;
import server.commands.ACommands;

public class ServerResponse {

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
}
