package srgns.com.mywebview;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    WebView myWeb;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide();//Actionbar gizleme

        myWeb=new WebView(this);

        if (savedInstanceState!=null)
        {
            myWeb.restoreState(savedInstanceState);
        }
        else {
            myWeb=findViewById(R.id.webView);
            myWeb.getSettings().setJavaScriptEnabled(true);
            myWeb.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
            myWeb.getSettings().setBuiltInZoomControls(true);
            myWeb.setWebViewClient(new WebViewClient());
            myWeb.setWebChromeClient(new WebChromeClient());
            myWeb.loadUrl("http://hub.arantu.com/");


        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        myWeb.saveState(outState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (myWeb.canGoBack()){
            myWeb.goBack();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (myWeb.canGoBack()) {
                        myWeb.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}
