package org.alee.provider;

import java.net.HttpURLConnection;
import java.net.URL;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/9/28
 * @description: xxxx
 *
 *********************************************************/
public final class DouYinUrlHelper {
    private static final String HTTP = "http";
    private static final String HTTPS = "https";
    private static final String REAL_URL_START = "https://www.iesdouyin.com/share/video/";
    private static final String REAL_URL_END = "/?";

    private DouYinUrlHelper() {

    }

    /**
     * 获取单例对象
     *
     * @return {@link DouYinUrlHelper}
     */
    public static DouYinUrlHelper getInstance() {
        return DouYinUrlHelperHolder.INSTANCE;
    }

    public String resolveTheTitle(String input) throws InvalidContentException {
        if (null == input || 0 == input.length()) {
            throw new InvalidContentException();
        }
        int end = input.contains(HTTPS) ? input.indexOf(HTTPS) : input.indexOf(HTTP);
        if (0 > end) {
            return "抖音视频";
        }
        String url = input.substring(0, end);
        return url;
    }

    public String resolveTheUrl(String input) throws Throwable {
        if (null == input || 0 == input.length()) {
            throw new InvalidContentException();
        }
        int start = input.contains(HTTPS) ? input.indexOf(HTTPS) : input.indexOf(HTTP);
        if (0 > start) {
            throw new InvalidContentException();
        }
        int end = input.lastIndexOf("/");
        String url = input.substring(start, end);
        String realUrl = redirectUrl(url);
        String videoId = getVideoId(realUrl);
        return realUrl;
    }

    private String redirectUrl(String url) throws Throwable {
        URL serverUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
        conn.setRequestMethod("GET");
        conn.setInstanceFollowRedirects(false);
        conn.setRequestProperty("User-agent", "ua");
        conn.connect();
        String location = conn.getHeaderField("Location");
        return location;
    }

    private String getVideoId(String url) {
        return url.substring(url.indexOf(REAL_URL_START), url.lastIndexOf(REAL_URL_END));
    }

    /**
     * 静态内部类持有外部对象实现单利方式
     */
    private static class DouYinUrlHelperHolder {
        private static DouYinUrlHelper INSTANCE = new DouYinUrlHelper();
    }

}
