package com.example.webhookdemo.model;

public class FinalQueryPayload {
    private String finalQuery;

    public FinalQueryPayload(String finalQuery) {
        this.finalQuery = finalQuery;
    }

    public String getFinalQuery(){
        return finalQuery;
    }

    public void setFinalQuery(String finalQuery){
        this.finalQuery = finalQuery;
    }
}
