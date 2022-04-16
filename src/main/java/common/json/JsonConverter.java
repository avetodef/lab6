package common.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.interaction.Request;
import common.interaction.Response;


public class JsonConverter {


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
