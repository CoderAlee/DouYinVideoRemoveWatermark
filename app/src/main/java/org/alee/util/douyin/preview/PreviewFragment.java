package org.alee.util.douyin.preview;


import android.graphics.drawable.ColorDrawable;
import android.util.Log;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
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
public final class PreviewFragment extends BaseFragment {
    private static final String TAG = PreviewFragment.class.getSimpleName();
    private static final String VIDEO_CACHE_FILE = PathUtil.getInstance().assembleCustomPath(PathUtil.getInstance().getCachePath(), "OriginalVideo");
    private PavedPlayer mVideoPlayer;
    private volatile int mCurrentPosition;

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
        mVideoPlayer.setVideoAllCallBack(new VideoAllCallBack() {
            @Override
            public void onStartPrepared(String url, Object... objects) {
                Log.i(TAG, "开始加载");
            }

            @Override
            public void onPrepared(String url, Object... objects) {
                Log.i(TAG, "加载成功");
            }

            @Override
            public void onClickStartIcon(String url, Object... objects) {

            }

            @Override
            public void onClickStartError(String url, Object... objects) {

            }

            @Override
            public void onClickStop(String url, Object... objects) {

            }

            @Override
            public void onClickStopFullscreen(String url, Object... objects) {

            }

            @Override
            public void onClickResume(String url, Object... objects) {

            }

            @Override
            public void onClickResumeFullscreen(String url, Object... objects) {

            }

            @Override
            public void onClickSeekbar(String url, Object... objects) {

            }

            @Override
            public void onClickSeekbarFullscreen(String url, Object... objects) {

            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
                Log.i(TAG, "播放完成");
            }

            @Override
            public void onEnterFullscreen(String url, Object... objects) {

            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {

            }

            @Override
            public void onQuitSmallWidget(String url, Object... objects) {

            }

            @Override
            public void onEnterSmallWidget(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekVolume(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekPosition(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekLight(String url, Object... objects) {

            }

            @Override
            public void onPlayError(String url, Object... objects) {
                Log.i(TAG, "播放错误");
            }

            @Override
            public void onClickStartThumb(String url, Object... objects) {

            }

            @Override
            public void onClickBlank(String url, Object... objects) {

            }

            @Override
            public void onClickBlankFullscreen(String url, Object... objects) {

            }
        });
        mVideoPlayer.setGSYVideoProgressListener(new GSYVideoProgressListener() {
            @Override
            public void onProgress(int progress, int secProgress, int currentPosition, int duration) {
                Log.i(TAG, "当前播放进度:" + progress);
                Log.i(TAG, "当前内存缓冲进度:" + secProgress);
                Log.i(TAG, "当前播放位置:" + currentPosition);
                Log.i(TAG, "总时长:" + duration);
                mCurrentPosition = currentPosition;
            }
        });
        Log.i(TAG, "" + this);
    }

    @Override
    protected void onLazySetViewsValue() {
        String url = "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200f020000btobfg51n62nna6iqphg&ratio=720p&line=0";
        mVideoPlayer.setPlayTag(UUID.randomUUID().toString());
        mVideoPlayer.setPlayPosition(new Random().nextInt(100));
        mVideoPlayer.setUpLazy(url, true, new File(VIDEO_CACHE_FILE), null, null);
        mVideoPlayer.setLooping(true);
        mVideoPlayer.setReleaseWhenLossAudio(true);
        mVideoPlayer.setBottomShowProgressBarDrawable(new ColorDrawable(getResources().getColor(R.color.white)), new ColorDrawable(getResources().getColor(R.color.colorAccent)));
        mVideoPlayer.startPlayLogic();
    }

    @Override
    public void onResume() {
        super.onResume();
        GSYVideoManager.instance().setPlayPosition(mVideoPlayer.getPlayPosition());
        GSYVideoManager.instance().setPlayTag(mVideoPlayer.getPlayTag());
        mVideoPlayer.onVideoResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        GSYVideoManager.instance().setPlayPosition(mVideoPlayer.getPlayPosition());
        GSYVideoManager.instance().setPlayTag(mVideoPlayer.getPlayTag());
        mVideoPlayer.onVideoPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mVideoPlayer.release();
    }
}
