package common.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonConverter {

    public static String serialize(List<String> commands) {
        String serialized = null;
        try {
            serialized = new ObjectMapper().writeValueAsString(commands);
        } catch (JsonProcessingException e) {
            System.out.println("беды с сериализацией: " + e.getMessage() + " " + e.getCause());
        }
        return serialized;
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

}
