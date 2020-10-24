package org.alee.util.douyin.base;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import org.alee.compinent.remover.annotation.RepeatedClick;
import org.alee.dokodemo.door.annotation.Node;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/9/28
 * @description: xxxx
 *
 *********************************************************/
@Node
public abstract class BaseFragment extends BaseNodeFragment implements View.OnClickListener {
    /**
     * 根布局
     */
    protected View mView = null;
    /**
     * Context 对象
     */
    protected Context mContext = null;
    /**
     * 所有注册的view
     */
    private SparseArray<View> mViews;

    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViews = new SparseArray<>();
        initData();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        getViews();
        setListeners();
        setViewsValue();
        return mView;
    }

    /**
     * 设置布局文件
     *
     * @return 布局资源id
     */
    protected abstract int getLayoutId();

    /**
     * 关联控件
     */
    protected abstract void getViews();

    /**
     * 关联控件点击事件
     */
    protected abstract void setListeners();

    /**
     * 关联控件值
     */
    protected abstract void setViewsValue();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    @RepeatedClick
    @Override
    public final void onClick(View view) {
        processClick(view);
    }

    /**
     * 事件回调 点击事件回调
     *
     * @param v view
     */
    protected void processClick(View v) {

    }

    /**
     * 根据id查找绑定view
     *
     * @param viewId 控件id
     * @param <E>    类型
     * @return view
     */
    public <E extends View> E findView(int viewId) {
        if (mView != null) {
            E view = (E) mViews.get(viewId);
            if (view == null) {
                view = mView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return view;
        }
        return null;
    }

    /**
     * 绑定事件
     *
     * @param view view
     * @param <E>  类型
     */
    public <E extends View> void setClick(E view) {
        view.setOnClickListener(this);
    }
}
