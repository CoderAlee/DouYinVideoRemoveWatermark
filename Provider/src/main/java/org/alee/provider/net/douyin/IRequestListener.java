package org.alee.provider.net.douyin;


import org.alee.provider.net.douyin.bean.BeanDouYin;

/**********************************************************
 *
 * @author: MingYu.Liu
 * @date: 2020/2/19
 * @description: 请求结果监听
 *
 *********************************************************/
public interface IRequestListener {
    /**
     * 成功的回调
     *
     * @param data       数据
     */
    void onSuccess(BeanDouYin data);

    /**
     * 状态吗错误的回调
     *
     * @param errorCode    错误码
     * @param errorMessage 错误信息
     */
    void onCodeError(String errorCode, String errorMessage);

    /**
     * 请求失败的回调
     *
     * @param e              异常信息
     * @param isNetWorkError 是否由于网络原因导致
     */
    void onFailure(Throwable e, boolean isNetWorkError);

    /**
     * 开始请求
     *
     * @param requestId 请求id
     */
    void onRequestStart(String requestId);

    /**
     * 结束请求
     */
    void onRequestEnd();
}
