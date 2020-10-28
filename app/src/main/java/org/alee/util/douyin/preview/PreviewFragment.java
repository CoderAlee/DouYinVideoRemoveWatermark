package org.alee.util.douyin.preview;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;

import com.shuyu.gsyvideoplayer.listener.GSYVideoProgressListener;
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack;

import org.alee.dokodemo.door.annotation.EnumLoadModel;
import org.alee.dokodemo.door.annotation.LoadModel;
import org.alee.util.douyin.R;
import org.alee.util.douyin.base.BaseFragment;
import org.alee.util.douyin.util.path.PathUtil;
import org.alee.util.douyin.widget.player.PavedPlayer;

import java.io.File;
import java.util.Random;
import java.util.UUID;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/10/20
 * @description: xxxx
 *
 *********************************************************/
@LoadModel(loadModel = EnumLoadModel.LAZY_LOAD)
public  class PreviewFragment extends BaseFragment {
    private static final String TAG = PreviewFragment.class.getSimpleName();
    protected static final String VIDEO_CACHE_FILE = PathUtil.getInstance().assembleCustomPath(PathUtil.getInstance().getCachePath(), "OriginalVideo");
    protected PavedPlayer mVideoPlayer;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_preview;
    }

    @Override
    protected void getViews() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void setViewsValue() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onLazyGetViews() {
        mVideoPlayer = findView(R.id.view_player);
    }

    @Override
    protected void onLazySetListeners() {

    }

    @Override
    protected void onLazySetViewsValue() {
        String url = "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=v0200f200000bpaga132ap9clet9psjg&ratio=720p&line=0";
        mVideoPlayer.setPlayTag(UUID.randomUUID().toString());
        mVideoPlayer.setPlayPosition(new Random().nextInt(100));
        mVideoPlayer.setUpLazy(url, true, new File(VIDEO_CACHE_FILE), null, null);
        mVideoPlayer.setLooping(true);
        mVideoPlayer.setReleaseWhenLossAudio(true);
        mVideoPlayer.setBottomShowProgressBarDrawable(new ColorDrawable(getResources().getColor(R.color.white)), new ColorDrawable(getResources().getColor(R.color.colorAccent)));
        mVideoPlayer.startPlayLogic();
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mVideoPlayer.release();
    }
}
