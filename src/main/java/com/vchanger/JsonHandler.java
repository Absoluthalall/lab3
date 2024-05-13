package com.vchanger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class JsonHandler extends Handler {

    @Override
    public boolean checkType(String fileName) {
        String type = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (type.equals("json")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public HashMap<String, Reactor> parse(String fileName) {
        HashMap<String, Reactor> hashMap = new HashMap<>(0);
        try {
            Object obj = new JSONParser().parse(new FileReader(fileName));
            JSONObject jo = (JSONObject) obj;
            for (Object key : jo.keySet()) {
                String clas = (String) key;
                JSONObject reactorData = (JSONObject) jo.get(clas);

                Reactor reactor = new Reactor(
                        reactorData.get("class").toString(),
                        ((Number) reactorData.get("burnup")).doubleValue(),
                        ((Number) reactorData.get("kpd")).doubleValue(),
                        ((Number) reactorData.get("enrichment")).doubleValue(),
                        ((Number) reactorData.get("termal_capacity")).doubleValue(),
                        ((Number) reactorData.get("electrical_capacity")).doubleValue(),
                        ((Number) reactorData.get("life_time")).doubleValue(),
                        ((Number) reactorData.get("first_load")).doubleValue(),
                        "json"
                );
                hashMap.put(clas, reactor);
            }
        } catch(IOException | ParseException e){
            e.printStackTrace();
        }
            return hashMap;
        }
}
