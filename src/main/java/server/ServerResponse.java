package server;

import common.dao.RouteDAO;
import common.interaction.Response;
import server.commands.ACommands;

public class ServerResponse extends Response {

    protected String commandResponse(ACommands commands, RouteDAO dao){
        String output = "";
        output = commands.execute(dao);
        return output;
    }

}
