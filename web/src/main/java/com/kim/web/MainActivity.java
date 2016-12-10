package com.kim.web;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private Button back;
    private Button go;
    private EditText uriStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        webView = (WebView) findViewById(R.id.web);
        back = (Button) findViewById(R.id.back);
        go = (Button) findViewById(R.id.go);
        uriStr = (EditText) findViewById(R.id.edit_url);

        webView.loadUrl("http://www.baidu.com");
        uriStr.setText("www.baidu.com");
        uriStr.clearFocus();

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                go.setText("GO");
            }
        });


        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("------", "do back");
                webView.goBack();
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = go.getText().toString();
                if (str.equals("GO")) {
                    String uriString = uriStr.getText().toString().trim();
                    if (!uriString.equals("")) {
                        Log.d("------", "do go");
                        if (uriString.startsWith("http://")) {
                            webView.loadUrl(uriString);
                        } else {
                            webView.loadUrl("http://" + uriString);
                        }
                        go.setText("STOP");
                    }
                } else if (str.equals("STOP")) {
                    Log.d("------", "do stop");
                    webView.stopLoading();
                    go.setText("GO");
                }
            }
        });
    }
}
