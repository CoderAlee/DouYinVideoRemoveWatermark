package org.alee.util.douyin.util.path;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/2/25
 * @description: 路径定义
 *
 *********************************************************/
interface IPath {
    /**
     * sdk路径
     *
     * @return 路径
     */
    String getSDRootPath();

    /**
     * 根路径
     *
     * @return 路径
     */
    String getRootPath();

    /**
     * 缓存路径
     *
     * @return 路径
     */
    String getCachePath();

    /**
     * 系统路径
     *
     * @return 路径
     */
    String getSystemPath();

    /**
     * 日志路径
     *
     * @return 路径
     */
    String getLogPath();

    /**
     * 下载路径
     *
     * @return 路径
     */
    String getDownPath();

    /**
     * 图片缓存路径
     *
     * @return 路径
     */
    String getImageCachePath();

    /**
     * 缩略图缓存路径
     *
     * @return 路径
     */
    String getThumbImageCachePath();

    /**
     * 轮播图片缓存路径
     *
     * @return 路径
     */
    String getBannerCachePath();

    /**
     * 网络请求缓存路径
     *
     * @return 路径
     */
    String getNetworkRequestCachePath();

    /**
     * 组装自定义路径
     * @param path 每一级的路径
     * @return 路径
     */
    String assembleCustomPath(String... path);
}
