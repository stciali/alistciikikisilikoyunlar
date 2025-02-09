package com.rijndael.kraloyun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.onesignal.OneSignal;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private FirebaseAnalytics fbAnalytics;


    private String reklamGecisID= "ca-app-pub-9586837522472927/6342880091"; //"ca-app-pub-3940256099942544/1033173712"; //"ca-app-pub-8399443771294551/4622224107";

    public static List<FlowerData> mFlowerList;
    public static FlowerData mFlowerData;


    public static InterstitialAd mInterstitialAd;
    int x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().hide();

       //OneSignal.setLogLevel(OneSignal.LOG_LEVEL.DEBUG, OneSignal.LOG_LEVEL.DEBUG);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

      final FirebaseDatabaseLoad sc = new FirebaseDatabaseLoad();
      sc.mContext = this;
            sc.initFirebase();
          //  sc.resimYukle();


        fbAnalytics = FirebaseAnalytics.getInstance(this);


        MobileAds.initialize(this);


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(reklamGecisID);

        gecisLoad();


        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.d("ads","reklam yuklendi bea...");
                gecisYukle();
            }

        });

        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {

                mFlowerList = new ArrayList<>();

                for(int x =0; x < FirebaseDatabaseLoad.say1; x++){

                    mFlowerData = new FlowerData(FirebaseDatabaseLoad.isimler[x], FirebaseDatabaseLoad.resimler[x],
                            getDrawable(R.drawable.rose));
                    mFlowerList.add(mFlowerData);

                }

                Intent i = new Intent(MainActivity.this,GameSelector.class);
                startActivity(i);
            }
        };
        handler.postDelayed(r, 3500);



    }


    public static void gecisLoad()
    {
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }


    public static void gecisYukle(){

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }

    }


}
