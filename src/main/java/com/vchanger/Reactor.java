package com.vchanger;

import javax.swing.tree.DefaultMutableTreeNode;

public class Reactor {
    String clas;
    double burnup;
    double kpd;
    double enrichment;
    double terminalCapacity;
    double electricalCapacity;
    double lifeTime;
    double firstLoad;
    String  type;



    public Reactor(String clas, double burnup, double kpd, double enrichment, double terminalCapacity,
                   double electricalCapacity,  double lifeTime,double firstLoad, String fileType) {
        this.clas = clas;
        this.burnup = burnup;
        this.kpd = kpd;
        this.enrichment=enrichment;
        this.terminalCapacity = terminalCapacity;
        this.electricalCapacity = electricalCapacity;
        this.lifeTime = lifeTime;
        this.firstLoad = firstLoad;
        this.type=fileType;
    }
    public DefaultMutableTreeNode getNode(DefaultMutableTreeNode reactorNode){
        reactorNode.add(new DefaultMutableTreeNode("class: " + this.clas));
        reactorNode.add(new DefaultMutableTreeNode("burnap: " + this.burnup));
        reactorNode.add(new DefaultMutableTreeNode("kpd: " + this.kpd));
        reactorNode.add(new DefaultMutableTreeNode("enrichment: " + this.enrichment));
        reactorNode.add(new DefaultMutableTreeNode("terminalCapacity: " + this.terminalCapacity));
        reactorNode.add(new DefaultMutableTreeNode("electricalCapacity: " + this.electricalCapacity));
        reactorNode.add(new DefaultMutableTreeNode("lifeTime: " + this.lifeTime));
        reactorNode.add(new DefaultMutableTreeNode("firstLoad: " + this.firstLoad));
        reactorNode.add(new DefaultMutableTreeNode("type: " + this.type));
        return reactorNode;
    }
}
