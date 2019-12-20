package com.example.ismatkhanam.on_button_click;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class SimpleBrowser extends Activity implements View.OnClickListener {

    EditText url;
    WebView our_browser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simplebrowser);

        our_browser=(WebView)findViewById(R.id.wv_browser);

        our_browser.getSettings().setJavaScriptEnabled(true);
        our_browser.getSettings().setLoadWithOverviewMode(true);
        our_browser.getSettings().setUseWideViewPort(true);

        our_browser.setWebViewClient(new ourViewClient());
        try {
            our_browser.loadUrl("http://www.thenewboston.com");
        }catch (Exception e){
            e.printStackTrace();
        }

        Button go=(Button)findViewById(R.id.b_go);
        Button back=(Button)findViewById(R.id.b_back);
        Button refresh=(Button)findViewById(R.id.b_refresh);
        Button forward=(Button)findViewById(R.id.b_forward);
        Button clear_history=(Button)findViewById(R.id.b_history);
        url=(EditText)findViewById(R.id.et_URL);

        go.setOnClickListener(this);
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);
        forward.setOnClickListener(this);
        clear_history.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_go:
                String web=url.getText().toString();
                our_browser.loadUrl(web);

                //hidding the keyboard after using the edittext
                InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(url.getWindowToken(),0);
                break;

            case R.id.b_back:
                if(our_browser.canGoBack())
                    our_browser.goBack();
                break;

            case R.id.b_forward:
                if(our_browser.canGoForward())
                    our_browser.goForward();
                break;

            case R.id.b_refresh:
                our_browser.reload();
                break;

            case R.id.b_history:
                our_browser.clearHistory();
                break;
        }
    }
}
