package com.vchanger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class XmlHandler extends Handler{
    @Override
    public boolean checkType(String fileName) {
        String type = fileName.substring(fileName.lastIndexOf(".") + 1);
        if(type.equals("xml")){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public HashMap<String, Reactor> parse(String fileName) {
        HashMap<String, Reactor> hashMap = new HashMap<>();
        try {
            File file = new File(fileName);
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
            NodeList reactorElements = document.getElementsByTagName("root").item(0).getChildNodes();
            for (int i = 0; i < reactorElements.getLength(); i++) {
                Node reactorElement = reactorElements.item(i);
                if (reactorElement.getNodeType() == Node.ELEMENT_NODE) {
                    Element reactorNode = (Element) reactorElement;
                    String key = reactorNode.getNodeName();
                    String clas = reactorNode.getElementsByTagName("class").item(0).getTextContent();
                    double burnup = Double.valueOf(reactorNode.getElementsByTagName("burnup").item(0).getTextContent());
                    double kpd = Double.valueOf(reactorNode.getElementsByTagName("kpd").item(0).getTextContent());
                    double enrichment = Double.valueOf(reactorNode.getElementsByTagName("enrichment").item(0).getTextContent());
                    double termal_capacity = Double.valueOf(reactorNode.getElementsByTagName("termal_capacity").item(0).getTextContent());
                    double electrical_capacity = Double.valueOf(reactorNode.getElementsByTagName("electrical_capacity").item(0).getTextContent());
                    double life_time = Double.valueOf(reactorNode.getElementsByTagName("life_time").item(0).getTextContent());
                    double first_load = Double.valueOf(reactorNode.getElementsByTagName("first_load").item(0).getTextContent());
                    Reactor reactor = new Reactor(clas, burnup, kpd, enrichment, termal_capacity, electrical_capacity, life_time, first_load, "xml");
                    hashMap.put(key, reactor);
                }
            }
        }catch (ParserConfigurationException | IOException | SAXException e){
            e.printStackTrace();
        }
        return hashMap;
    }
}
