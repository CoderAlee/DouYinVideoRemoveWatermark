package org.alee.util.douyin.preview;

import android.widget.TextView;

import org.alee.dokodemo.door.annotation.EnumLoadModel;
import org.alee.dokodemo.door.annotation.LoadModel;
import org.alee.util.douyin.R;
import org.alee.util.douyin.base.BaseFragment;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/10/20
 * @description: xxxx
 *
 *********************************************************/
@LoadModel(loadModel = EnumLoadModel.LAZY_LOAD)
public final class PreviewFragment extends BaseFragment {
    private TextView mTestTv;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_preview;
    }

    @Override
    protected void getViews() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void setViewsValue() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onLazyGetViews() {
        mTestTv = findView(R.id.tv_test);
    }

    @Override
    protected void onLazySetViewsValue() {
        mTestTv.setText("456");
    }
}
