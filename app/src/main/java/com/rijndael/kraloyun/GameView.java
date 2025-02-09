package com.rijndael.kraloyun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GameView extends AppCompatActivity {


    private WebView webView;
    private CustomWebViewClient webViewClient;
    String  title;

    boolean reklamGoster=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);

        getSupportActionBar().hide();
        Bundle b = getIntent().getExtras();
        String gameURL = b.getString("gameURL");
        int oyunmod = b.getInt("gameMod");


        if(oyunmod == 1) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }


        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {

                if(reklamGoster)
                {
                    MainActivity.gecisLoad();
                    reklamGoster = false;
                }

            }
        };
        handler.postDelayed(r, 1900);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        webViewClient = new CustomWebViewClient();
        webView = (WebView) findViewById(R.id.web);
       // webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);

        String MyUA = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";

       // webView.getSettings().setUserAgentString(MyUA);
        webView.setWebViewClient(webViewClient);






        webView.setWebChromeClient(new WebChromeClient() {




        });
        webView.loadUrl(gameURL);//"https://html5.gamedistribution.com/a55c9cc9c21e4fc683c8c6857f3d0c75/");

    }


    @Override
     public void onBackPressed(){
        super.onBackPressed();
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle(getString(R.string.app_name));
            alertDialogBuilder.setMessage(getString(R.string.soru));
            alertDialogBuilder.setNegativeButton(getString(R.string.btn1),null);
            alertDialogBuilder.setPositiveButton(getString(R.string.btn2),new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onBackPressed();
                    reklamGoster = true;
                    finish();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class CustomWebViewClient extends WebViewClient {



        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            Log.d("ads",url);

            if(url.contains("adservice") || url.contains("googleads") || url.contains("ads")){
                Log.d("ads","yakalandi!!!!!!!");


                return AdBlocker.createEmptyResource();
            }

            return super.shouldInterceptRequest(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
         //   String javaScript = "javascript:(function() { document.getElementById('google_image_div').remove();})()";
          //  webView.loadUrl(javaScript);

        }


    }

}
