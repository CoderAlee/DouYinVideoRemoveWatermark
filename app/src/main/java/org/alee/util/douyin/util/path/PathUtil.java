package org.alee.util.douyin.util.path;

import android.content.Context;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/2/25
 * @description: 代理类
 *
 *********************************************************/
public final class PathUtil implements IPath {
    /**
     * 被代理类
     */
    private IPath mPathImpl;

    private PathUtil() {

    }

    /**
     * 获取单例对象
     *
     * @return {@link PathUtil}
     */
    public static PathUtil getInstance() {
        return PathUtilHolder.INSTANCE;
    }

    /**
     * 初始化
     *
     * @param pContext 上下文
     */
    public void init(Context pContext) {
        mPathImpl = new PathImpl(pContext);
    }

    @Override
    public String getSDRootPath() {
        if (null == mPathImpl) {
            return "";
        }
        return mPathImpl.getSDRootPath();
    }

    @Override
    public String getRootPath() {
        if (null == mPathImpl) {
            return "";
        }
        return mPathImpl.getRootPath();
    }

    @Override
    public String getCachePath() {
        if (null == mPathImpl) {
            return "";
        }
        return mPathImpl.getCachePath();
    }

    @Override
    public String getSystemPath() {
        if (null == mPathImpl) {
            return "";
        }
        return mPathImpl.getSystemPath();
    }

    @Override
    public String getLogPath() {
        if (null == mPathImpl) {
            return "";
        }
        return mPathImpl.getLogPath();
    }

    @Override
    public String getDownPath() {
        if (null == mPathImpl) {
            return "";
        }
        return mPathImpl.getDownPath();
    }

    @Override
    public String getImageCachePath() {
        if (null == mPathImpl) {
            return "";
        }
        return mPathImpl.getImageCachePath();
    }

    @Override
    public String getThumbImageCachePath() {
        if (null == mPathImpl) {
            return "";
        }
        return mPathImpl.getThumbImageCachePath();
    }

    @Override
    public String getBannerCachePath() {
        if (null == mPathImpl) {
            return "";
        }
        return mPathImpl.getBannerCachePath();
    }

    @Override
    public String getNetworkRequestCachePath() {
        if (null == mPathImpl) {
            return "";
        }
        return mPathImpl.getNetworkRequestCachePath();
    }

    @Override
    public String assembleCustomPath(String... path) {
        return mPathImpl.assembleCustomPath(path);
    }

    /**
     * 静态内部类持有外部对象实现单利方式
     */
    private static class PathUtilHolder {
        private static PathUtil INSTANCE = new PathUtil();
    }
}
