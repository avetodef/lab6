package server.commands;

import common.dao.RouteDAO;
import common.utils.RouteInfo;

import java.util.List;

public abstract class ACommands {

    public List<String> args;

    public void addArgs(List<String> args) {
        this.args = args;
    }
    public String execute(RouteDAO routeDAO){ return " "; }
    protected boolean isAsker;
    protected RouteInfo info;

    public void setInfo(RouteInfo info) {
        this.info = info;
    }

    public boolean isAsker() {
        return isAsker;
    }
}
