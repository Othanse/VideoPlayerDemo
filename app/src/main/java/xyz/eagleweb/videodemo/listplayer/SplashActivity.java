package xyz.eagleweb.videodemo.listplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startActivity(new Intent(this, JcvdMainActivity.class));
        //        startActivity(new Intent(this, GSYMainActivity.class));
        finish();
    }
}
