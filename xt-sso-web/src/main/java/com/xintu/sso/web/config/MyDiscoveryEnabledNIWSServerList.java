package com.xintu.sso.web.config;

import java.util.List;

import com.netflix.niws.loadbalancer.DiscoveryEnabledNIWSServerList;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;

public class MyDiscoveryEnabledNIWSServerList extends DiscoveryEnabledNIWSServerList {

    public List<DiscoveryEnabledServer> getInitialListOfServers() {
        System.out.println("MyDiscoveryEnabledNIWSServerList getInitialListOfServers  ... ");
        return super.getInitialListOfServers();
    }

    @Override
    public List<DiscoveryEnabledServer> getUpdatedListOfServers(){
        System.out.println("MyDiscoveryEnabledNIWSServerList getUpdatedListOfServers  ... ");
        return super.getUpdatedListOfServers();
    }
}