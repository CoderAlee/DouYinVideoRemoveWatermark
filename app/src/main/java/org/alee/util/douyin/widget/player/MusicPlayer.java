package org.alee.util.douyin.widget.player;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import org.alee.util.douyin.R;
import org.alee.util.douyin.widget.BaseViewGroup;
import org.alee.util.douyin.widget.DimPleView;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/10/26
 * @description: xxxx
 *
 *********************************************************/
public class MusicPlayer extends BaseViewGroup {
    private ImageView mAlbumIv;
    private DimPleView mDustView;

    public MusicPlayer(Context context) {
        super(context);
    }

    public MusicPlayer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MusicPlayer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_music_player;
    }

    @Override
    protected void getView() {
        mAlbumIv = findView(R.id.iv_album);
        mDustView = findView(R.id.dpv_dust);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void setViewValues() {
        mAlbumIv.getLayoutParams().height = 268 * 2 - 20;
        mAlbumIv.getLayoutParams().width = 268 * 2 - 20;
        start();
    }

    public void setAlbum(String url) {
        Glide.with(this).load(url).apply(RequestOptions.bitmapTransform(new CircleCrop())).addListener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        }).into(mAlbumIv);
    }

    private void start() {
        RotateAnimation rotate = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        LinearInterpolator lin = new LinearInterpolator();
        rotate.setInterpolator(lin);
        rotate.setDuration(10 * 1000);
        rotate.setRepeatCount(-1);
        rotate.setFillAfter(true);
        rotate.setStartOffset(0);
        mAlbumIv.setAnimation(rotate);
    }

}
