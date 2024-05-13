package com.vchanger;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

public abstract class Handler {
    Handler nextHandler;
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }
    public abstract boolean checkType(String fileName);
    public abstract HashMap<String, Reactor> parse(String fileName) throws IOException, ParseException, ParserConfigurationException, SAXException;
    public HashMap<String, Reactor> repeat(String fileName) throws IOException, ParseException, ParserConfigurationException, SAXException {
        if (checkType(fileName) == true){
            return parse(fileName);
        }
        else{
            return nextHandler.repeat(fileName);
        }

    }
}
