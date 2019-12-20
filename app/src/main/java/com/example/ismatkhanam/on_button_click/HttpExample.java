package com.example.ismatkhanam.on_button_click;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class HttpExample extends Activity {

    TextView http_stuff;
    HttpClient client;
    JSONObject json;

    final static String url="http://api.twitter.com/1/statuses/user_timeline.json?screen_name=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_ex);
        http_stuff=(TextView) findViewById(R.id.tv_http);

       /* Get_Method_ex test = new Get_Method_ex();
        String returned;
        try {
            returned = test.get_internet_data();
            http_stuff.setText(returned);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        client = new DefaultHttpClient();

        // this line is used to get info from json file
        new Read().execute("text");
    }

    public JSONObject last_tweet(String username) throws ClientProtocolException,IOException,JSONException{
        StringBuilder url_sb=new StringBuilder(url);
        url_sb.append(username);

        HttpGet get=new HttpGet(url_sb.toString());
        HttpResponse r=client.execute(get);
        int status = r.getStatusLine().getStatusCode();
        if(status == 200){
            HttpEntity e = r.getEntity();
            String data = EntityUtils.toString(e);
            JSONArray time_line = new JSONArray(data);
            JSONObject last=time_line.getJSONObject(0);
            return last;
        }else {
            Toast.makeText(HttpExample.this, "error", Toast.LENGTH_SHORT);
            return null;
        }
    }

    public class Read extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... params) {
            try {
                json = last_tweet("thenewboston");
                return json.getString(params[0]);
            }catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            http_stuff.setText(s);

        }
    }
}
