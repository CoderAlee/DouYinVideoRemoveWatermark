package org.alee.util.douyin;

import org.alee.dokodemo.door.annotation.Node;
import org.alee.dokodemo.door.core.DokodemoDoor;
import org.alee.util.douyin.base.BaseActivity;
import org.alee.util.douyin.fragment.MainFragment;

@Node(containerViewId = R.id.fragment_container, stickyStack = true)
public class MainActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getViews() {

    }

    @Override
    protected void setViewsValue() {
        DokodemoDoor.getNodeProxy(this).startFragment(new MainFragment());
    }

    @Override
    protected void setListeners() {

    }
}