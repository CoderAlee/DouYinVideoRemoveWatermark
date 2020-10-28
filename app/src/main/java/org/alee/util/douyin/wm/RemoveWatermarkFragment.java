package org.alee.util.douyin.wm;

import android.graphics.drawable.ColorDrawable;

import org.alee.dokodemo.door.annotation.EnumLoadModel;
import org.alee.dokodemo.door.annotation.LoadModel;
import org.alee.util.douyin.R;
import org.alee.util.douyin.base.BaseFragment;
import org.alee.util.douyin.preview.PreviewFragment;

import java.io.File;
import java.util.Random;
import java.util.UUID;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/10/26
 * @description: xxxx
 *
 *********************************************************/
@LoadModel(loadModel = EnumLoadModel.LAZY_LOAD)
public class RemoveWatermarkFragment extends PreviewFragment {
    @Override
    protected void onLazySetViewsValue() {
        String url = "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200f200000bpaga132ap9clet9psjg&ratio=720p&line=0";
        mVideoPlayer.setPlayTag(UUID.randomUUID().toString());
        mVideoPlayer.setPlayPosition(new Random().nextInt(100));
        mVideoPlayer.setUpLazy(url, true, new File(VIDEO_CACHE_FILE), null, null);
        mVideoPlayer.setLooping(true);
        mVideoPlayer.setReleaseWhenLossAudio(true);
        mVideoPlayer.setBottomShowProgressBarDrawable(new ColorDrawable(getResources().getColor(R.color.white)), new ColorDrawable(getResources().getColor(R.color.colorAccent)));
        mVideoPlayer.startPlayLogic();
    }
}
