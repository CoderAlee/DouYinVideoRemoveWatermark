package org.alee.util.douyin.bgm;

import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;

import org.alee.dokodemo.door.annotation.EnumLoadModel;
import org.alee.dokodemo.door.annotation.LoadModel;
import org.alee.util.douyin.R;
import org.alee.util.douyin.base.BaseFragment;
import org.alee.util.douyin.util.path.PathUtil;
import org.alee.util.douyin.widget.player.MusicPlayer;
import org.alee.util.douyin.widget.player.PavedPlayer;

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
public final class BackgroundMusicFragment extends BaseFragment {
    private static final String MUSIC_CACHE_FILE = PathUtil.getInstance().assembleCustomPath(PathUtil.getInstance().getCachePath(), "OriginalMusic");
    private MusicPlayer mMusicPlayerRoot;
    private PavedPlayer mMusicPlayer;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_background_music;
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
        mMusicPlayerRoot = findView(R.id.root_music_player);
        mMusicPlayer = findView(R.id.view_player);
    }


    @Override
    protected void onLazySetViewsValue() {
        mMusicPlayerRoot.setAlbum("https://p3-dy-ipv6.byteimg.com/aweme/720x720/2e391000767d5393d6bf5.jpeg?from=4010531038");
        String url = "https://p9-dy.byteimg.com/obj/ies-music/1659501516314652.mp3";
        mMusicPlayer.setPlayTag(UUID.randomUUID().toString());
        mMusicPlayer.setPlayPosition(new Random().nextInt(100));
        mMusicPlayer.setUpLazy(url, true, new File(MUSIC_CACHE_FILE), null, null);
        mMusicPlayer.setLooping(true);
        mMusicPlayer.setReleaseWhenLossAudio(true);
        mMusicPlayer.setBottomShowProgressBarDrawable(new ColorDrawable(getResources().getColor(R.color.white)), new ColorDrawable(getResources().getColor(R.color.colorAccent)));
        mMusicPlayer.startPlayLogic();
    }
}
