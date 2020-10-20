package org.alee.util.douyin.base;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/10/21
 * @description: xxxx
 *
 *********************************************************/
abstract class BaseNodeFragment extends Fragment {
    /**
     * 成功
     */
    protected static final int FRAGMENT_RESULT_CODE_SUCCESS = 0x1010;
    /**
     * 取消
     */
    protected static final int FRAGMENT_RESULT_CODE_CANCEL = 0x1020;

    /**
     * 获取容器Id
     *
     * @return 容器id
     */
    public Integer getContainerViewId() {
        return 0;
    }

    /**
     * 处理退栈事件
     *
     * @return 是否拦截退栈事件向上层传递
     */
    public Boolean onNodeBackPressed() {
        return false;
    }

    /**
     * 接收回调
     *
     * @param requestCode 请求code
     * @param resultCode  返回code
     * @param args        参数
     */
    public void onFragmentResult(int requestCode, int resultCode, Bundle args) {

    }

    /**
     * 是否拦截退栈事件向下传递
     *
     * @return 结果
     */
    public Boolean onInterceptBackPressed() {
        return false;
    }

    /**
     * 懒加载回调
     *
     * @param bundle {@link Bundle}
     */
    public void onLazyLoadViewCreated(Bundle bundle) {
        onLazyInitData();
        onLazyGetViews();
        onLazySetListeners();
        onLazySetViewsValue();
    }

    protected void onLazyInitData() {

    }

    /**
     * 关联控件
     */
    protected void onLazyGetViews() {

    }

    /**
     * 关联控件点击事件
     */
    protected void onLazySetListeners() {

    }

    /**
     * 关联控件值
     */
    protected void onLazySetViewsValue() {

    }

    /**
     * 获取动画
     *
     * @return 动画
     */
    public int[] getNodeAnimations() {
        return new int[]{0, 0, 0, 0};
    }

    /**
     * 获取Fragment tag
     *
     * @return 结果
     */
    public String getFragmentTag() {
        return "";
    }
}
