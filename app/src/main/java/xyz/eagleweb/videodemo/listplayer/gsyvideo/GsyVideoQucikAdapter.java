package xyz.eagleweb.videodemo.listplayer.gsyvideo;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import xyz.eagleweb.videodemo.listplayer.R;

/**
 * @author Shuai.Wong
 * 2019/2/14
 */
public class GsyVideoQucikAdapter extends BaseQuickAdapter<String, GsyVideoQucikAdapter.ViewHolder> {


    private Handler                         mHandler                = new Handler();
    private Set<String>                     preparReleaseKeySet     = new HashSet<>();
    private Map<MyMultiVideoView, Runnable> videoReleaseRunnableMap = new HashMap<>();

    public GsyVideoQucikAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder holder, String item) {
        holder.iv_cover.setVisibility(View.VISIBLE);
        //        if (holder.gsyVideoPlayer.isInPlayingState()) {
        //            holder.gsyVideoPlayer.releaseVideos();
        //            System.out.println("检测 复用的情况 而且滑动还太快了 就搞事情嘛 清理掉！" + holder.gsyVideoPlayer.getPlayTag());
        //        }
        holder.gsyVideoPlayer.setUpLazy(item, false, null, null, "这是title");
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
        holder.gsyVideoPlayer.setVideoAllCallBack(new MyGSYSampleCallBack(holder.iv_cover));
        int position = holder.getAdapterPosition();
        System.out.println("播放位置：" + position);
        holder.gsyVideoPlayer.setPlayTag(position + "_" + item);
        holder.gsyVideoPlayer.setPlayPosition(position);
        //是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
        holder.gsyVideoPlayer.setAutoFullWithSize(false);
        //音频焦点冲突时是否释放
        holder.gsyVideoPlayer.setReleaseWhenLossAudio(true);
        //全屏动画
        holder.gsyVideoPlayer.setShowFullAnimation(false);
        //小屏时不触摸滑动
        holder.gsyVideoPlayer.setIsTouchWiget(false);
        // 设置封面
        //        ImageView view = new ImageView(mContext);
        //        view.setScaleType(ImageView.ScaleType.FIT_XY);
        //        holder.gsyVideoPlayer.setThumbImageView(view);
        holder.gsyVideoPlayer.setThumbPlay(true);
        holder.gsyVideoPlayer.setShowPauseCover(false);
        holder.gsyVideoPlayer.setIsTouchWigetFull(false);
        holder.gsyVideoPlayer.setHideKey(true);
        holder.gsyVideoPlayer.setFullHideActionBar(true);
        holder.gsyVideoPlayer.setFullHideStatusBar(true);
        holder.gsyVideoPlayer.getStartButton().setVisibility(View.GONE);
        Glide.with(mContext)
                .load("http://img2.imgtn.bdimg.com/it/u=3126854174,2746990258&fm=26&gp=0.jpg")
                .into(holder.iv_cover);
        holder.gsyVideoPlayer.setLooping(true);
    }

    public Set<String> getPrepareReleasePlayerSet() {
        return preparReleaseKeySet;
    }

    class ViewHolder extends BaseViewHolder {
        private MyMultiVideoView gsyVideoPlayer;
        private ImageView        iv_cover;

        public ViewHolder(View view) {
            super(view);
            gsyVideoPlayer = view.findViewById(R.id.video);
            iv_cover = view.findViewById(R.id.iv_cover);
            //            if (new Random().nextInt() % 2 == 1) {
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1080));
            //            } else {
            //                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 780));
            //            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        System.out.println("检测 onAttachedToRecyclerView " + recyclerView.getVerticalScrollbarPosition());
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        System.out.println("检测 onDetachedFromRecyclerView " + recyclerView.getVerticalScrollbarPosition());
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        System.out.println("检测 onViewAttachedToWindow " + holder.getAdapterPosition());
        MyMultiVideoView gsyVideoPlayer = holder.gsyVideoPlayer;
        preparReleaseKeySet.remove(gsyVideoPlayer.getKey());
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        System.out.println("检测 onViewDetachedFromWindow " + holder.getAdapterPosition());
        holder.iv_cover.setVisibility(View.VISIBLE);
        MyMultiVideoView gsyVideoPlayer = holder.gsyVideoPlayer;
        //        if (gsyVideoPlayer.isInPlayingState()) {
        preparReleaseKeySet.add(gsyVideoPlayer.getKey());
        //            return;
        //        }
    }

    class ReleaseRunnable implements Runnable {
        private ViewHolder holder;

        public ReleaseRunnable(ViewHolder viewHolder) {
            holder = viewHolder;
        }

        @Override
        public void run() {
            MyMultiVideoView gsyVideoPlayer = holder.gsyVideoPlayer;
            videoReleaseRunnableMap.remove(gsyVideoPlayer);
            gsyVideoPlayer.releaseVideos();
            GSYVideoManager.instance().clearAllDefaultCache(mContext);
            //            System.gc();
            System.out.println("检测 清理缓存 释放资源！" + holder.getAdapterPosition() + " " + gsyVideoPlayer.getPlayTag());
        }
    }

    class MyGSYSampleCallBack extends GSYSampleCallBack {
        private View mIvCoverView;

        public MyGSYSampleCallBack(View view) {
            mIvCoverView = view;
        }

        @Override
        public void onPrepared(String url, Object... objects) {
            mIvCoverView.setVisibility(View.GONE);
        }
    }

}
