package xyz.eagleweb.videodemo.listplayer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.cache.CacheFactory;
import com.shuyu.gsyvideoplayer.cache.ProxyCacheManager;
import com.shuyu.gsyvideoplayer.player.IjkPlayerManager;
import com.shuyu.gsyvideoplayer.player.PlayerFactory;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;

import java.util.ArrayList;

import xyz.eagleweb.videodemo.listplayer.gsyvideo.CustomManager;
import xyz.eagleweb.videodemo.listplayer.gsyvideo.GsyVideoQucikAdapter;
import xyz.eagleweb.videodemo.listplayer.gsyvideo.ScrollCalculatorHelper;

public class GSYMainActivity extends Activity {

    private RecyclerView           mRvList;
    private ScrollCalculatorHelper scrollCalculatorHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PlayerFactory.setPlayManager(IjkPlayerManager.class);//ijk模式
        CacheFactory.setCacheManager(ProxyCacheManager.class);//代理缓存模式，支持所有模式，不支持m3u8等
        //        //默认显示比例
        //        GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_DEFAULT);
        //
        //        //16:9
        //        GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_16_9);
        //
        //        //全屏裁减显示，为了显示正常 CoverImageView 建议使用FrameLayout作为父布局
        //        GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_FULL);

        //全屏拉伸显示，使用这个属性时，surface_container建议使用FrameLayout
        GSYVideoType.setShowType(GSYVideoType.SCREEN_MATCH_FULL);
        //
        //        //4:3
        //        GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_4_3);
        //
        ////        implementation 'com.google.android.exoplayer:exoplayer:2.7.1'
        //        Jzvd.setMediaInterface(new JZExoPlayer());  //exo

        initView();
        initGsyVideoData();
    }


    private void initGsyVideoData() {
        ArrayList<String> metaAppInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            //            metaAppInfos.add("http://www.jmzsjy.com/UploadFile/微课/地方风味小吃——宫廷香酥牛肉饼.mp4");
            //            metaAppInfos.add("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4");
            //            metaAppInfos.add("http://221.228.226.23/11/t/j/v/b/tjvbwspwhqdmgouolposcsfafpedmb/sh.yinyuetai.com/691201536EE4912BF7E4F1E2C67B8119.mp4");
            metaAppInfos.add("http://cdn.233xyx.com/upload/video/air.com.gamebrain.voi/voi.mp4");
            metaAppInfos.add("http://cdn.233xyx.com/upload/video/隐藏我的游戏/tanqiu.mp4");
            metaAppInfos.add("http://cdn.233xyx.com/upload/video/com.ab.tinygame.drawphysicsline/wulihuaixan.mp4");
            metaAppInfos.add("http://cdn.233xyx.com/upload/video/com.acidcousins.chilly/huxiaojixing.mp4");
            metaAppInfos.add("http://cdn.233xyx.com/upload/video/com.cis.cathospital.nyyb/chaotuoliyiyuan.mp4");
            metaAppInfos.add("http://cdn.233xyx.com/upload/video/com.ChillyRoom.DungeonShooter/yuanqiqishi.mp4");
            metaAppInfos.add("http://cdn.233xyx.com/upload/video/com.centurysoft.fruityrobo/guobaosanguo.mp4");
            metaAppInfos.add("http://cdn.233xyx.com/upload/video/com.bigduckgames.flow/ziyouzoudong.mp4");
            metaAppInfos.add("http://cdn.233xyx.com/upload/video/com.bdj.vortexDroid/RollyVortex.mp4");
            metaAppInfos.add("http://cdn.233xyx.com/upload/video/com.AlexNaronov.DaG/huageyouxi.mp4");
            metaAppInfos.add("http://cdn.233xyx.com/upload/video/com.acidcousins.fdunk/feixinglanqiu.mp4");
        }
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRvList.setLayoutManager(layoutManager);
        GsyVideoQucikAdapter qucikAdapter = new GsyVideoQucikAdapter(R.layout.item_gsy_video_list, metaAppInfos);
        mRvList.setAdapter(qucikAdapter);

        //限定范围为屏幕一半的上下偏移180
        int playTop = CommonUtil.getScreenHeight(this) / 2 - CommonUtil.dip2px(this, 180);
        int playBottom = CommonUtil.getScreenHeight(this) / 2 + CommonUtil.dip2px(this, 180);
        //自定播放帮助类
        scrollCalculatorHelper = new ScrollCalculatorHelper(R.id.video, playTop, playBottom);
        mRvList.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                scrollCalculatorHelper.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                System.out.println("有滑动啊：" + dy);
                gsyAutoPlay(recyclerView, layoutManager);
            }
        });
    }


    private void gsyAutoPlay(RecyclerView recyclerView, StaggeredGridLayoutManager layoutManager) {
        int[] into = new int[2];
        int[] into2 = new int[2];
        layoutManager.findFirstVisibleItemPositions(into);
        int firstVisibleItem = into[0];
        layoutManager.findLastVisibleItemPositions(into2);
        int lastVisibleItem = into2[0];

        //这是滑动自动播放的代码
        scrollCalculatorHelper.onScroll(recyclerView, firstVisibleItem, lastVisibleItem, lastVisibleItem - firstVisibleItem);
    }

    private void initView() {
        mRvList = (RecyclerView) findViewById(R.id.rv_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }
}
