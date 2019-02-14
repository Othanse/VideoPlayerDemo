package xyz.eagleweb.videodemo.listplayer.jcvd;

import android.content.Context;
import android.util.AttributeSet;

import cn.jzvd.JzvdStd;

/**
 * @author Shuai.Wong
 * 2019/2/14
 */
public class MyJcvdPlayer extends JzvdStd {
    public MyJcvdPlayer(Context context) {
        super(context);
        customInit();
    }
    public MyJcvdPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        customInit();
    }


    private void customInit() {
//        thumbImageView
    }

}
