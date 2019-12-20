package com.example.ismatkhanam.on_button_click;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

public class Get_Method_ex {

    public String get_internet_data() throws Exception{
        BufferedReader br=null;
        String data=null;
        try {
            HttpClient client = new DefaultHttpClient();
            URI website=new URI("http://www.thenewbostan.com");
            HttpGet request=new HttpGet();
            request.setURI(website);
            HttpResponse response=client.execute(request);
            br=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb=new StringBuffer("");
            String l="";
            String nl=System.getProperty("line.separator");
            while ((l = br.readLine()) != null){
                sb.append(l + nl);
            }
            br.close();
            data=sb.toString();
            return data;
        }
        finally {
            if(br != null){
                try{
                    br.close();
                    return data;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }
}
