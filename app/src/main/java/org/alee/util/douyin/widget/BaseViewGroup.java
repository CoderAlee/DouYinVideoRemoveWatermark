package org.alee.util.douyin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;


/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/3/25
 * @description: xxxx
 *
 *********************************************************/
public abstract class BaseViewGroup extends LinearLayout {
    /**
     * {@link Context}
     */
    protected Context mContext;
    /**
     * 根布局
     */
    private View mRootView;
    /**
     * 所有注册的view
     */
    private SparseArray<View> mViewCache;

    public BaseViewGroup(Context context) {
        super(context);
        init(context, this);
    }

    /**
     * 初始化
     *
     * @param context 上下文
     * @param parent  父布局
     */
    private void init(Context context, ViewGroup parent) {
        mContext = context;
        initData();
        mRootView = LayoutInflater.from(context).inflate(getLayoutId(), parent, true);
        mViewCache = new SparseArray<>();
        getView();
        setListener();
        setViewValues();
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 获取布局id
     *
     * @return 布局id
     */
    protected abstract int getLayoutId();

    /**
     * 关联控件
     */
    protected abstract void getView();

    /**
     * 设置事件监听器
     */
    protected abstract void setListener();

    /**
     * 设置view属性
     */
    protected abstract void setViewValues();

    public BaseViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, this);
    }

    public BaseViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, this);
    }

    /**
     * 根据id查找绑定view
     *
     * @param viewId 控件id
     * @param <E>    类型
     * @return view
     */
    public <E extends View> E findView(int viewId) {
        if (mRootView != null) {
            E view = (E) mViewCache.get(viewId);
            if (view == null) {
                view = mRootView.findViewById(viewId);
                mViewCache.put(viewId, view);
            }
            return view;
        }
        return null;
    }

    @Override
    public View getRootView() {
        return mRootView;
    }

}
