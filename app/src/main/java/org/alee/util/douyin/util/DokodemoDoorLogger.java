package org.alee.util.douyin.util;

import android.util.Log;

import org.alee.dokodemo.door.template.ILogger;
import org.alee.util.douyin.BuildConfig;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/9/28
 * @description: xxxx
 *
 *********************************************************/
public final class DokodemoDoorLogger implements ILogger {
    @Override
    public void logEnable(boolean b) {

    }

    @Override
    public void debug(String s, String s1) {
        Log.d(BuildConfig.LOG_TAG,"Tag:"+s+"      "+s1);
    }

    @Override
    public void info(String s, String s1) {
        Log.i(BuildConfig.LOG_TAG,"Tag:"+s+"      "+s1);
    }

    @Override
    public void verbose(String s, String s1) {
        Log.v(BuildConfig.LOG_TAG,"Tag:"+s+"      "+s1);
    }

    @Override
    public void warning(String s, String s1) {
        Log.w(BuildConfig.LOG_TAG,"Tag:"+s+"      "+s1);
    }

    @Override
    public void error(String s, String s1) {
        Log.e(BuildConfig.LOG_TAG,"Tag:"+s+"      "+s1);
    }
}
