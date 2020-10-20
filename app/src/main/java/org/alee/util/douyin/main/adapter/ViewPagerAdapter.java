package org.alee.util.douyin.main.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.alee.util.douyin.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/10/20
 * @description: xxxx
 *
 *********************************************************/
public final class ViewPagerAdapter extends FragmentStateAdapter {
    private final Map<String, BaseFragment> mData;
    private final List<String> mTitleList;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        mData = new HashMap<>();
        mTitleList = new ArrayList<>();
    }

    public ViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
        mData = new HashMap<>();
        mTitleList = new ArrayList<>();
    }

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        mData = new HashMap<>();
        mTitleList = new ArrayList<>();
    }


    public void addItem(String title, BaseFragment fragment) {
        mTitleList.remove(title);
        mTitleList.add(title);
        mData.put(title, fragment);
    }

    public String getTitle(int position){
        return  mTitleList.get(position);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mData.get(getTitle(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
