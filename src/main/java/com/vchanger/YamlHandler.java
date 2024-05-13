package com.vchanger;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class YamlHandler extends Handler{
    @Override
    public boolean checkType(String fileName) {
        String type = fileName.substring(fileName.lastIndexOf(".") + 1);
        if(type.equals("yaml")){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public HashMap<String, Reactor> parse(String fileName) {
        Yaml yaml = new Yaml();
        HashMap<String, Reactor> hashMap = new HashMap<>();
        try {
            FileInputStream fis = new FileInputStream(fileName);
            Map<String, Map<String, Object>> data = yaml.load(fis);
            for (String reactorName : data.keySet()) {
                Map<String, Object> reactorData = data.get(reactorName);
                Reactor reactor = new Reactor((String) reactorData.get("class"),
                        ((Number) reactorData.get("burnup")).doubleValue(),
                        ((Number) reactorData.get("kpd")).doubleValue(),
                        ((Number) reactorData.get("enrichment")).doubleValue(),
                        ((Number) reactorData.get("termal_capacity")).doubleValue(),
                        ((Number) reactorData.get("electrical_capacity")).doubleValue(),
                        ((Number) reactorData.get("life_time")).doubleValue(),
                        ((Number) reactorData.get("first_load")).doubleValue(),
                        "yaml");
                hashMap.put(reactorName, reactor);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return hashMap;
    }
}
