package org.ritvik.quick.myquick;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JSONUtilities {

    private static ObjectMapper objmapper = new ObjectMapper();

    //Converting Objects To JSON
    public static String toJson(Object pojo) throws IOException {
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
        objmapper.writeValue(baos, pojo);
        return baos.toString();
    }

    //Converting JSON To pojo
    public static <T> Object fromJson(String jsonAsString, Class<T> pojoClass) throws JsonParseException, JsonMappingException, IOException {
        return objmapper.readValue(jsonAsString, pojoClass);
    }
    
    //Converting JSON To Tree Node
    public static ObjectNode fromJson(String resJson) throws IOException{
        return (ObjectNode) objmapper.readTree(resJson);
    }
    
}