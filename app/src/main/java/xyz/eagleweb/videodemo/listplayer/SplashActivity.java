package xyz.eagleweb.videodemo.listplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // 选用节操播放器做的。列表自动播放
        startActivity(new Intent(this, JcvdMainActivity.class));
        // 选用GSY播放器做的。列表自动播放
        //        startActivity(new Intent(this, GSYMainActivity.class));
        finish();
        // TODO: 2019/2/14 客观的说 GSY播放器比节操播放器要功能全面和强大一些。但是包体积也是大了很多！需要取舍！  JC不大 一百多K！ GSY最小也需要1.2M
        // TODO: 2019/2/14 可以利用节操播放器的UI + IjkPlayer的内核 能基本达到GSY的功能
    }
}
