package common.interaction;

import common.utils.Route;

import java.io.File;
import java.io.Serializable;
import java.util.Deque;

public class Request implements Serializable {
    public String command;
    public File file;
    public Deque<Route> collection;

    public Request(String command){
        this.command = command;
    }

}
