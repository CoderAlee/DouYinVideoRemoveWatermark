package org.alee.util.douyin;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.shuyu.gsyvideoplayer.GSYVideoManager;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/10/23
 * @description: xxxx
 *
 *********************************************************/
class PlayerLifecycle implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        GSYVideoManager.onResume();
    }

    @Override
    public void onActivityPaused(Activity activity) {
        GSYVideoManager.onPause();
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        GSYVideoManager.releaseAllVideos();
    }
}
