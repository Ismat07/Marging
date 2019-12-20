package com.example.ismatkhanam.on_button_click;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {

    String[] classes={ "MainActivity", "TextPlay", "Email", "Camera",
            "Data", "GFX", "GFXSurface", "SoundStuff", "Slider", "Tabs",
            "SimpleBrowser", "Flipper", "SharedPrefs", "InternalData",
            "ExternalData", "SQLiteExample", "Accelerate", "HttpExample",
            "WeatherXMLParsing", "PointlessWidget", "WidgetConfig",
            "GLExample", "GLCubeEx", "Voice", "TextVoice", "StatusBar",
            "SeekBarVolume"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //it is used to make this screen as a fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String pos=classes[position];
        try {
            Class our_class = Class.forName("com.example.ismatkhanam.on_button_click." +pos);
            Intent i=new Intent(Menu.this,our_class);
            startActivity(i);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater blow_up=getMenuInflater();
        blow_up.inflate(R.menu.cool_menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.about_us:
                Intent i=new Intent("com.example.ismatkhanam.on_button_click.ABOUT");
                startActivity(i);
                break;
            case R.id.preferences:
                Intent a=new Intent("com.example.ismatkhanam.on_button_click.PREFS");
                startActivity(a);
                break;
            case R.id.exit:
                finish();
                break;
        }
        return false;
    }
}
