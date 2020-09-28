package org.alee.provider.net.douyin;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/9/28
 * @description: xxxx
 *
 *********************************************************/
public interface IDouYinRequest {

    /**
     * 获取推荐列表
     *
     * @param listener {@link IRequestListener}
     */
    void getVideoInfo(IRequestListener listener);
}
