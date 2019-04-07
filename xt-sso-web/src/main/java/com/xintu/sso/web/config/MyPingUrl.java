package com.xintu.sso.web.config;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;

public class  MyPingUrl implements IPing {
    private IPing pingUrl;

    public MyPingUrl(IPing ping){
        this.pingUrl = ping;
    }

    @Override
    public boolean isAlive(Server server) {
        boolean isAlive = pingUrl.isAlive(server);
        System.out.println("MyPingUrl  " + server.getHostPort() + " isAlive = " + isAlive + "; info=" + server.toString());
        return isAlive;
    }
}