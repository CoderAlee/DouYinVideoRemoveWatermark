package org.alee.util.douyin.util.path;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/2/25
 * @description: 路径实现类
 *
 *********************************************************/
final class PathImpl implements IPath {
    /**
     * 缓存目录名称.
     */
    private static final String PATH_CACHE = "cache";
    /**
     * 系统目录名称.
     */
    private static final String PATH_SYSTEM = "system";
    /**
     * 日志目录名称.
     */
    private static final String PATH_LOGS = "logs";
    /**
     * 下载目录名称.
     */
    private static final String PATH_DOWNLOAD = "download";
    /**
     * 图片缓存目录名称.
     */
    private static final String PATH_CACHE_IMAGE = "image";
    /**
     * 缩略图缓存目录名称.
     */
    private static final String PATH_CACHE_THUMB_IMAGE = "thumb_image";
    /**
     * 轮播图片缓存目录名称.
     */
    private static final String PATH_CACHE_IMAGE_BANNER = "banner";
    /**
     * 网络请求缓存路径名称.
     */
    private static final String PATH_CACHE_NETWORK_REQUEST = "network_request";
    /**
     * 上下文对象.
     */
    private Context mContext = null;
    /**
     * SD卡根路径.
     */
    private String mSDRootPath = "";
    /**
     * 应用跟路径.
     */
    private String mRootPath = "";
    /**
     * 缓存路径.
     */
    private String mCachePath = "";
    /**
     * 日志路径.
     */
    private String mLogsPath = "";
    /**
     * 应用系统路径.
     */
    private String mSystemPath = "";
    /**
     * 下载路径.
     */
    private String mDownLoadPath = "";

    /**
     * 图片缓存路径.
     */
    private String mImageCachePath = "";

    /**
     * 缩略图缓存路径.
     */
    private String mThumbImageCachePath = "";

    /**
     * 轮播图片缓存路径.
     */
    private String mBannerImageCachePath = "";

    /**
     * 网络请求缓存路径.
     */
    private String mNetworkRequestCachePath = "";

    /**
     * @param pContext 上下文对象
     */
    PathImpl(Context pContext) {
        this.mContext = pContext;
    }

    @Override
    public String getSDRootPath() {
        if (!TextUtils.isEmpty(mSDRootPath)) {
            return mSDRootPath;
        }
        if (mContext == null) {
            return "";
        }
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (!sdCardExist) {
            mSDRootPath = mContext.getFilesDir().getAbsolutePath();
            return mSDRootPath;
        }
        mSDRootPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + File.separator;
        return mSDRootPath;
    }

    @Override
    public String getRootPath() {
        if (!TextUtils.isEmpty(mRootPath)) {
            return mRootPath;
        }
        if (mContext == null) {
            return "";
        }
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (!sdCardExist) {
            mRootPath = mContext.getFilesDir().getAbsolutePath();
            return mRootPath;
        }
        String packageName = mContext.getPackageName();
        mRootPath = assembleAndCreatePath(Environment.getExternalStorageDirectory().getAbsolutePath(), "." + packageName);
        return mRootPath;
    }

    @Override
    public String getCachePath() {
        if (!TextUtils.isEmpty(mCachePath)) {
            return mCachePath;
        }
        mCachePath = assembleAndCreatePath(getRootPath(), PATH_CACHE);
        return mCachePath;
    }

    @Override
    public String getSystemPath() {
        if (!TextUtils.isEmpty(mSystemPath)) {
            return mSystemPath;
        }
        mSystemPath = assembleAndCreatePath(getRootPath(), PATH_SYSTEM);
        return mSystemPath;
    }

    @Override
    public String getLogPath() {
        if (!TextUtils.isEmpty(mLogsPath)) {
            return mLogsPath;
        }
        mLogsPath = assembleAndCreatePath(getRootPath(), PATH_LOGS);
        return mLogsPath;
    }

    @Override
    public String getDownPath() {
        if (!TextUtils.isEmpty(mDownLoadPath)) {
            return mDownLoadPath;
        }
        mDownLoadPath = assembleAndCreatePath(getRootPath(), PATH_DOWNLOAD);
        return mDownLoadPath;
    }

    @Override
    public String getImageCachePath() {
        if (!TextUtils.isEmpty(mImageCachePath)) {
            return mImageCachePath;
        }
        mImageCachePath = assembleAndCreatePath(getCachePath(), PATH_CACHE_IMAGE);
        return mImageCachePath;
    }

    @Override
    public String getThumbImageCachePath() {
        if (!TextUtils.isEmpty(mThumbImageCachePath)) {
            return mThumbImageCachePath;
        }
        mThumbImageCachePath = assembleAndCreatePath(getCachePath(), PATH_CACHE_THUMB_IMAGE);
        return mThumbImageCachePath;
    }

    @Override
    public String getBannerCachePath() {
        if (!TextUtils.isEmpty(mBannerImageCachePath)) {
            return mBannerImageCachePath;
        }
        mBannerImageCachePath = assembleAndCreatePath(getImageCachePath(), PATH_CACHE_IMAGE_BANNER);
        return mBannerImageCachePath;
    }

    @Override
    public String getNetworkRequestCachePath() {
        if (!TextUtils.isEmpty(mNetworkRequestCachePath)) {
            return mNetworkRequestCachePath;
        }
        mNetworkRequestCachePath = assembleAndCreatePath(getCachePath(), PATH_CACHE_NETWORK_REQUEST);
        return mNetworkRequestCachePath;
    }

    @Override
    public String assembleCustomPath(String... path) {
        StringBuilder buffer = new StringBuilder("");
        if (null == path || 0 >= path.length) {
            return buffer.toString();
        }
        for (String str : path) {
            if (TextUtils.isEmpty(str)) {
                continue;
            }
            buffer.append(str);
            if (TextUtils.equals(File.separator, str.substring(str.length() - 1))) {
                continue;
            }
            buffer.append(File.separator);
        }
        return buffer.toString();
    }

    private String assembleAndCreatePath(String... path) {
        String integralPath = assembleCustomPath(path);
        createPath(integralPath);
        return integralPath;
    }

    /**
     * @param path 路径
     */
    private void createPath(String path) {
        if (TextUtils.isEmpty(path)) {
            return;
        }
        File file = new File(path);
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }
}
