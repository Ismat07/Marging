package com.example.ismatkhanam.on_button_click;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import java.net.URL;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class WeatherXMLParsing extends Activity implements View.OnClickListener {

    static final String base_url="http://www.google.com/ig/api?weather=";
    TextView tv;
    EditText city,state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.weather);
        Button b=(Button) findViewById(R.id.b_weather);
        tv= (TextView)findViewById(R.id.tv_weather);
        city=(EditText)findViewById(R.id.et_city);
        state=(EditText)findViewById(R.id.et_state);

        b.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String c=city.getText().toString();
        String s=state.getText().toString();

        StringBuilder url=new StringBuilder(base_url);
        url.append(c + "," + s);
        String full_url=url.toString();
        try{
            URL website = new URL(full_url);

            // getting xmlreader to parse data
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();
            HandlingXMLStuff doing_work = new HandlingXMLStuff();
            xr.setContentHandler(doing_work);
            xr.parse(new InputSource(website.openStream()));
            String information = doing_work.getInformation();
            tv.setText(information);
        }catch (Exception e){
            tv.setText("error!!!");
        }
    }
}
