package com.example.ismatkhanam.on_button_click;

public class XMLDataCollected {
    int temp=0;
    String city=null;

    public void setCity(String c){
        city = c;
    }

    public void setTemp(int t){
        temp = t;
    }

    public String data_to_string(){
        return "In "+ city + " the Current Temperature in F is " + temp + " degrees";
    }
}
