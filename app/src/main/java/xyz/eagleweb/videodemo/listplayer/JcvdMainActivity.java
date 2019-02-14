package xyz.eagleweb.videodemo.listplayer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.shuyu.gsyvideoplayer.utils.CommonUtil;

import java.util.ArrayList;

import cn.jzvd.Jzvd;
import xyz.eagleweb.videodemo.listplayer.jcvd.JCPlayerQucikAdapter;
import xyz.eagleweb.videodemo.listplayer.jcvd.JCVDScrollCalculatorHelper;
import xyz.eagleweb.videodemo.listplayer.jcvd.JZMediaIjkplayer;

public class JcvdMainActivity extends Activity {

    private RecyclerView               mRvList;
    private JCVDScrollCalculatorHelper jcvdScrollCalculatorHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Jzvd.setMediaInterface(new JZMediaIjkplayer());
        Jzvd.setVideoImageDisplayType(Jzvd.VIDEO_IMAGE_DISPLAY_TYPE_FILL_PARENT);

        initView();
        initJCVDVideoData();
    }

    private void initJCVDVideoData() {
        ArrayList<String> metaAppInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            metaAppInfos.add("http://www.jmzsjy.com/UploadFile/微课/地方风味小吃——宫廷香酥牛肉饼.mp4");
            metaAppInfos.add("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4");
            metaAppInfos.add("http://221.228.226.23/11/t/j/v/b/tjvbwspwhqdmgouolposcsfafpedmb/sh.yinyuetai.com/691201536EE4912BF7E4F1E2C67B8119.mp4");
        }
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRvList.setLayoutManager(layoutManager);
        JCPlayerQucikAdapter qucikAdapter = new JCPlayerQucikAdapter(R.layout.item_jcvd_video_list, metaAppInfos);
        mRvList.setAdapter(qucikAdapter);

        //限定范围为屏幕一半的上下偏移180
        int playTop = CommonUtil.getScreenHeight(this) / 2 - CommonUtil.dip2px(this, 180);
        int playBottom = CommonUtil.getScreenHeight(this) / 2 + CommonUtil.dip2px(this, 180);
        //自定播放帮助类
        jcvdScrollCalculatorHelper = new JCVDScrollCalculatorHelper(R.id.video, playTop, playBottom);
        mRvList.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                jcvdScrollCalculatorHelper.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                System.out.println("有滑动啊：" + dy);
                jcvdAutoPlay(recyclerView, layoutManager);
            }
        });
    }

    private void jcvdAutoPlay(RecyclerView recyclerView, StaggeredGridLayoutManager layoutManager) {
        int[] into = new int[2];
        int[] into2 = new int[2];
        layoutManager.findFirstVisibleItemPositions(into);
        int firstVisibleItem = into[0];
        layoutManager.findLastVisibleItemPositions(into2);
        int lastVisibleItem = into2[0];

        //这是滑动自动播放的代码
        jcvdScrollCalculatorHelper.onScroll(recyclerView, firstVisibleItem, lastVisibleItem, lastVisibleItem - firstVisibleItem);
    }

    private void initView() {
        mRvList = (RecyclerView) findViewById(R.id.rv_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
