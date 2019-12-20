package com.example.ismatkhanam.on_button_click;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

public class Tabs extends Activity implements View.OnClickListener {

    TabHost th;
    TextView showResult;
    long start,stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);
        th=(TabHost) findViewById(R.id.tabHost);

        Button newTab=(Button) findViewById(R.id.bAddTab);
        Button bStart=(Button) findViewById(R.id.bStartWatch);
        Button bStop=(Button) findViewById(R.id.bStopWatch);

        showResult=(TextView)findViewById(R.id.tvShowResult);
        bStart.setOnClickListener(this);
        bStop.setOnClickListener(this);

        newTab.setOnClickListener(this);

        th.setup();
        TabHost.TabSpec spec=th.newTabSpec("tag1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("StopWatch");
        th.addTab(spec);
        spec=th.newTabSpec("tag2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Tab 2");
        th.addTab(spec);
        spec=th.newTabSpec("tag3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Add a Tab");
        th.addTab(spec);
        //start=0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bAddTab:
                TabHost.TabSpec our_spec=th.newTabSpec("tag1");
                our_spec.setContent(new TabHost.TabContentFactory(){
                    public View createTabContent(String tag){
                        TextView text=new TextView(Tabs.this);
                        text.setText("You've created a new tab!!!");
                        return (text);
                    }
                });
                our_spec.setIndicator("New");
                th.addTab(our_spec);
                break;

            case R.id.bStartWatch:
                start=System.currentTimeMillis();
                break;

            case R.id.bStopWatch:
                stop=System.currentTimeMillis();
                if(start != 0){
                    long result=stop-start;

                    int millis=(int)result;
                    int second=(int) result/1000;
                    int minute=second/60;
                    millis=millis % 100;
                    second=second % 60;

                    //showResult.setText(Long.toString(result));
                    showResult.setText(String.format("%d:%02d:%02d",minute,second,millis));
                }
                break;
        }
    }
}
