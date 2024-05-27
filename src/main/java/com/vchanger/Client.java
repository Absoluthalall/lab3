package com.vchanger;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

public class Client {
    private String fileName;
    public Client(String fileName){
        this.fileName=fileName;
    }
    public HashMap<String, Reactor> start() throws IOException, ParseException, ParserConfigurationException, SAXException {
        JsonHandler jsonHandler = new JsonHandler();
        XmlHandler xmlHandler = new XmlHandler();
        YamlHandler yamlHandler = new YamlHandler();
        yamlHandler.setNextHandler(xmlHandler);
        xmlHandler.setNextHandler(jsonHandler);
        HashMap<String, Reactor> reactors = yamlHandler.repeat(this.fileName);
        return reactors;
    }
}
