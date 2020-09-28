package org.alee.provider.net.douyin;

import org.alee.provider.net.douyin.bean.BeanVideoInfo;

import retrofit2.http.GET;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/9/28
 * @description: xxxx
 *
 *********************************************************/
interface DouYinApi {
    /**
     * get请求
     *
     * @return 结果
     */
    @GET("web/api/v2/aweme/iteminfo/")
    Observable<BeanVideoInfo> getRecommendList(String videoId);
}
