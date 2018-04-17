package com.personalstyler.client.testarelib;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.personalstyler.client.mylibrary.ModuleAdsManager2;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LinearLayout mContainer = findViewById(R.id.nAddd);
        mContainer.removeAllViews();

        ModuleAdsManager2 mManager = new ModuleAdsManager2(this,mContainer,"ca-app-pub-8562466601970101/5081303159",1);
        mContainer.addView(mManager.getAds());
    //   new TestA(this) ;
      // new TestWater(this);


       // new TestA(this);
        //new TestWater(this);

      // new TestBlue(this);
    }
}
