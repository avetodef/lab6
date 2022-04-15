package server.commands;

import common.dao.RouteDAO;

import java.util.List;

public abstract class ACommands {

    public List<String> args;

    public void addArgs(List<String> args) {
        this.args = args;
    }
    public String execute(RouteDAO routeDAO){ return " "; }


}
