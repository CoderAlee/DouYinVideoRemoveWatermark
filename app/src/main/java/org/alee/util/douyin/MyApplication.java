package org.alee.util.douyin;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.multidex.MultiDex;

import org.alee.dokodemo.door.core.DokodemoDoor;
import org.alee.util.douyin.util.DokodemoDoorLogger;
import org.alee.util.douyin.util.path.PathUtil;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/9/28
 * @description: xxxx
 *
 *********************************************************/
public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        PathUtil.getInstance().init(this);
        DokodemoDoor.setLogger(new DokodemoDoorLogger());
        DokodemoDoor.setUseDebugModel(true);
        registerActivityLifecycleCallbacks(new PlayerLifecycle());
    }
}
