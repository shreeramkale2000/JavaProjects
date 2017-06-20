package org.ritvik.quick.myquick;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@SuppressWarnings("deprecation")
public class JSONUtilities {

    private static JsonFactory factory = new JsonFactory();
    private static ObjectMapper objmapper = new ObjectMapper();

    //Converting Objects To JSON
    public static String toJson(Object pojo) throws IOException {
        StringWriter swriter = new StringWriter();
        JsonGenerator gen = factory.createJsonGenerator(swriter);
        gen.useDefaultPrettyPrinter();
        
        objmapper.writeValue(gen, pojo);
        gen.close();
        return swriter.toString();
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