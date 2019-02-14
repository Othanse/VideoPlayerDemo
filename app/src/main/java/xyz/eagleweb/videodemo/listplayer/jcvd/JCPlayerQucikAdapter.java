package xyz.eagleweb.videodemo.listplayer.jcvd;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;
import java.util.Random;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import xyz.eagleweb.videodemo.listplayer.R;

/**
 * @author Shuai.Wong
 * 2019/2/14
 */
public class JCPlayerQucikAdapter extends BaseQuickAdapter<String, JCPlayerQucikAdapter.ViewHolder> {


    public JCPlayerQucikAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder helper, String item) {
        helper.mJzvdStd.setUp(item, "", Jzvd.SCREEN_WINDOW_LIST);

        helper.mJzvdStd.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext)
                .load("http://img2.imgtn.bdimg.com/it/u=3126854174,2746990258&fm=26&gp=0.jpg")
                .into(helper.mJzvdStd.thumbImageView);
    }

    class ViewHolder extends BaseViewHolder {
        private JzvdStd mJzvdStd;

        public ViewHolder(View view) {
            super(view);
            //            mJzvdStd = view.findViewById(R.id.video);
            mJzvdStd = view.findViewById(R.id.video);
            if (new Random().nextInt() % 2 == 1) {
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1080));
            } else {
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 780));
            }
        }
    }
}
