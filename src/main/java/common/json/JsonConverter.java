package common.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.interaction.Request;
import common.interaction.Response;
import common.utils.RouteInfo;

import java.util.List;

public class JsonConverter {

    public static String serialize(List<String> commands) {
        String serialized = null;
        try {
            serialized = new ObjectMapper().writeValueAsString(commands);
        } catch (JsonProcessingException e) {
            System.out.println("беды с сериализацией: " + e.getMessage() + " " + e.getCause());
        }
        return serialized + "\0";
    }

    public static List<String> deserialize(String s){

        List<String> deserialized;
        try{
            deserialized = new ObjectMapper().readValue(s, List.class);
            return deserialized;
        } catch (JsonProcessingException e) {
            System.out.println("проблемы десериализации: " + e.getMessage());
        }
        return null;
    }


    public static String serRouteInfo(RouteInfo info){
        String output = " ";
        try{
            output = new ObjectMapper().writeValueAsString(info);
        }
        catch (JsonProcessingException e) {
            System.out.println("беды с сериализацией: " + e.getMessage() + " " + e.getCause());
        }
        return output + "\0";
    }

    public static RouteInfo desToRouteInfo(String s){
        RouteInfo ouput = null;
        try{
            ouput = new ObjectMapper().readValue(s, RouteInfo.class);
        } catch (JsonProcessingException e) {
            System.out.println("проблемы десериализации: " + e.getMessage());
        }
        return ouput;
    }

    public static String ser(Request request){
        String output = "";
        try{
            output = new ObjectMapper().writeValueAsString(request);
        } catch (JsonProcessingException e) {
            System.out.println("беды с сериализацией реквеста " + e.getMessage());
        }

        return output + "\0";
    }

    public static Request des(String s){
        Request output = null;

        try {
            output = new ObjectMapper().readValue(s, Request.class);
        } catch (JsonProcessingException e) {
            System.out.println("краказябра хи хи ха ха чин чань чунь (десер реквеста) " + e.getMessage());
        }
        return output;
    }

    public static String serResponse(Response r){
        String ouput = " ";
        try {
            ouput = new ObjectMapper().writeValueAsString(r);
        } catch (JsonProcessingException e) {
            System.out.println("nwqipfjopqwfhiq");
        }
        return ouput;
    }

    public static Response desResponse(String s){
        Response output = null;

        try {
            output = new ObjectMapper().readValue(s, Response.class);
        } catch (JsonProcessingException e) {
            System.out.println("краказябра хи хи ха ха чин чань чунь (десер реквеста) " + e.getMessage());
        }
        return output;
    }
}
