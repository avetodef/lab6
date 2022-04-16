package common.interaction;

import common.utils.RouteInfo;
import java.io.Serializable;
import java.util.List;


public class Request implements Serializable {
    public List<String> args;
    public RouteInfo info;

    public Request(List<String> args, RouteInfo info) {
        this.args = args;
        this.info = info;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }

    public RouteInfo getInfo() {
        return info;
    }

    public void setInfo(RouteInfo info) {
        this.info = info;
    }

    public Request() {
    }
}
