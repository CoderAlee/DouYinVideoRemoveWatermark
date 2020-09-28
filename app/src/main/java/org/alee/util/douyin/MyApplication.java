package org.alee.util.douyin;

import android.app.Application;

import org.alee.dokodemo.door.core.DokodemoDoor;
import org.alee.util.douyin.util.DokodemoDoorLogger;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/9/28
 * @description: xxxx
 *
 *********************************************************/
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DokodemoDoor.setLogger(new DokodemoDoorLogger());
        DokodemoDoor.setUseDebugModel(true);
    }
}
