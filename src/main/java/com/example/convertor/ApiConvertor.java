package com.example.convertor;

import org.json.JSONObject;
import org.json.XML;

public class ApiConvertor {

    public static String xmlToJson(String xmlText){
        JSONObject json = XML.toJSONObject(xmlText);
        return json.toString(4);
    }

    public static String jsonToXml(String jsonText){
        JSONObject obj = new JSONObject(jsonText);
        return XML.toString(obj);
    }
}

