package xyz.eagleweb.videodemo.listplayer.gsyvideo;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuyu.gsyvideoplayer.video.ListGSYVideoPlayer;

import java.util.List;
import java.util.Random;

import xyz.eagleweb.videodemo.listplayer.R;

/**
 * @author Shuai.Wong
 * 2019/2/14
 */
public class GsyVideoQucikAdapter extends BaseQuickAdapter<String, GsyVideoQucikAdapter.ViewHolder> {


    public GsyVideoQucikAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder holder, String item) {
        holder.gsyVideoPlayer.setUpLazy(item, true, null, null, "这是title");
        //增加title
        holder.gsyVideoPlayer.getTitleTextView().setVisibility(View.GONE);
        //设置返回键
        holder.gsyVideoPlayer.getBackButton().setVisibility(View.GONE);
        //设置全屏按键功能
        holder.gsyVideoPlayer.getFullscreenButton().setVisibility(View.GONE);
        //        holder.gsyVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                holder.gsyVideoPlayer.startWindowFullscreen(mContext, false, true);
        //            }
        //        });
        //防止错位设置
        int position = holder.getAdapterPosition();
        System.out.println("播放位置：" + position);
        holder.gsyVideoPlayer.setPlayTag(position + "_" + item);
        holder.gsyVideoPlayer.setPlayPosition(position);
        //是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
        holder.gsyVideoPlayer.setAutoFullWithSize(false);
        //音频焦点冲突时是否释放
        holder.gsyVideoPlayer.setReleaseWhenLossAudio(false);
        //全屏动画
        holder.gsyVideoPlayer.setShowFullAnimation(true);
        //小屏时不触摸滑动
        holder.gsyVideoPlayer.setIsTouchWiget(false);
        // 设置封面
        ImageView view = new ImageView(mContext);
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.gsyVideoPlayer.setThumbImageView(view);
        holder.gsyVideoPlayer.setThumbPlay(true);
        Glide.with(mContext)
                .load("http://img2.imgtn.bdimg.com/it/u=3126854174,2746990258&fm=26&gp=0.jpg")
                .into(view);
    }

    class ViewHolder extends BaseViewHolder {
        private ListGSYVideoPlayer gsyVideoPlayer;

        public ViewHolder(View view) {
            super(view);
            gsyVideoPlayer = view.findViewById(R.id.video);
            if (new Random().nextInt() % 2 == 1) {
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1080));
            } else {
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 780));
            }
        }
    }
}
