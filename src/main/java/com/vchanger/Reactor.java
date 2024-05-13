package com.vchanger;

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
}
