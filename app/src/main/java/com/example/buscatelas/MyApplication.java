package com.example.buscatelas;

import android.app.Application;

import com.example.buscatelas.models.Client;
import com.example.buscatelas.models.Request;
import com.example.buscatelas.models.ServiceProvider;

public class MyApplication  extends Application {
    private Client currentClient;
    private ServiceProvider currentProvider;
    private Request currentRequest;

    public Client getCurrentClient() {
        return currentClient;
    }

    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
    }

    public ServiceProvider getCurrentProvider() {
        return currentProvider;
    }

    public void setCurrentProvider(ServiceProvider currentProvider) {
        this.currentProvider = currentProvider;
    }

    public Request getCurrentRequest() {
        return currentRequest;
    }

    public void setCurrentRequest(Request currentRequest) {
        this.currentRequest = currentRequest;
    }
}
