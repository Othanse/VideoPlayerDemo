package xyz.eagleweb.videodemo.listplayer.gsyvideo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Shuai.Wong
 * 2019/2/14
 */
public class MyMultiVideoView extends MultiSampleVideo {

    public MyMultiVideoView(Context context, Boolean fullFlag) {
        super(context, fullFlag);
        customInit();
    }

    public MyMultiVideoView(Context context) {
        super(context);
        customInit();
    }

    public MyMultiVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        customInit();
    }

    private void customInit() {

//        mIsTouchWiget = false;
//        setLooping(true);
    }

    @Override
    protected void setViewShowState(View view, int visibility) {
        super.setViewShowState(view, visibility);
        if (view == null) {
            return;
        }
        if (mBottomProgressBar == null || view != mBottomProgressBar) {
            view.setVisibility(GONE);
        }
        //        if (view == mStartButton) {
        //            mStartButton.setVisibility(GONE);
        //            mDialogSeekTime.setVisibility(GONE);
        //            mDialogProgressBar.setVisibility(GONE);
        //            mDialogTotalTime.setVisibility(GONE);
        //            mLoadingProgressBar.setVisibility(GONE);
        //        }
    }

    @Override
    protected void onLossAudio() {
        //        super.onLossAudio();
    }

    @Override
    protected void onLossTransientAudio() {
        //        super.onLossTransientAudio();
    }

    @Override
    protected void onLossTransientCanDuck() {
        //        super.onLossTransientCanDuck();
    }

    @Override
    protected void onGankAudio() {
        //        super.onGankAudio();
    }

    //    @Override
    //    public void setReleaseWhenLossAudio(boolean releaseWhenLossAudio) {
    //        super.setReleaseWhenLossAudio(releaseWhenLossAudio);
    //    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
