package org.alee.util.douyin.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.qmuiteam.qmui.alpha.QMUIAlphaTextView;

import org.alee.dokodemo.door.annotation.Node;
import org.alee.util.douyin.R;
import org.alee.util.douyin.base.BaseFragment;
import org.alee.util.douyin.main.adapter.ViewPagerAdapter;
import org.alee.util.douyin.preview.PreviewFragment;

import java.util.Objects;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/9/28
 * @description: xxxx
 *
 *********************************************************/
public final class MainFragment extends BaseFragment {
    /**
     * 标题
     */
    private QMUIAlphaTextView mTitleTv;
    /**
     * {@link DrawerLayout}
     */
    private DrawerLayout mDrawerLayout;
    /**
     * {@link ViewPager2}
     */
    private ViewPager2 mContainerLayout;
    /**
     * {@link FloatingActionButton}
     */
    private FloatingActionButton mMoreBtn;
    /**
     * {@link NavigationView}
     */
    private NavigationView mNavigationView;
    /**
     * {@link Toolbar}
     */
    private Toolbar mToolbar;
    /**
     * {@link TabLayout}
     */
    private TabLayout mLabelLayout;

    private ViewPagerAdapter mPagerAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void getViews() {
        mTitleTv = findView(R.id.tv_link);
        mDrawerLayout = findView(R.id.content_root);
        mContainerLayout = findView(R.id.vp_container);
        mMoreBtn = findView(R.id.fbtn_more);
        mNavigationView = findView(R.id.nv_menu);
        mToolbar = findView(R.id.tool_bar);
        mLabelLayout = findView(R.id.tl_root);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void setViewsValue() {
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(mToolbar);
        bindDrawer();
        disableNavigationViewScrollbars(mNavigationView);
        mContainerLayout.setAdapter(mPagerAdapter);
        mPagerAdapter.addItem("预览", new PreviewFragment());
        mPagerAdapter.addItem("去水印", new PreviewFragment());
        mPagerAdapter.addItem("剥离BGM", new PreviewFragment());
        mPagerAdapter.addItem("文案", new PreviewFragment());
        new TabLayoutMediator(mLabelLayout, mContainerLayout, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(mPagerAdapter.getTitle(position)).setIcon(getResources().getDrawable(R.mipmap.icon_launcher));
            }
        }).attach();
        mPagerAdapter.notifyDataSetChanged();
        mTitleTv.setText("123213123123123123123");

    }

    /**
     * 去除NavigationView的滚动条
     * 滚动条不在NavigationView中，而是在他的child—NavigationMenuView中
     *
     * @param navigationView view
     */
    private void disableNavigationViewScrollbars(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }

    private void bindDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), mDrawerLayout, mToolbar, R.string.main_fragment_open, R.string.main_fragment_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected void initData() {
        mPagerAdapter = new ViewPagerAdapter(this);
    }
}
