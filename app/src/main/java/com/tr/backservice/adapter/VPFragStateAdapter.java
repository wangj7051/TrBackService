package com.tr.backservice.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tr.backservice.fragment.BaseAppV4Fragment;

import java.util.List;

/**
 * 页卡适配器
 *
 * @author Jun.Wang
 */
public class VPFragStateAdapter extends FragmentStatePagerAdapter {

    /**
     * Fragment List
     */
    private List<? extends BaseAppV4Fragment> mListFms;

    /**
     * Refresh Flag
     */
    private boolean mIsRefreshFlag = false;

    public VPFragStateAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setListFrags(List<? extends BaseAppV4Fragment> listFms) {
        this.mListFms = listFms;
    }

    public void setRfreshFlag(boolean isRefresh) {
        this.mIsRefreshFlag = isRefresh;
    }

    public void refreshPages(boolean isRefresh) {
        setRfreshFlag(isRefresh);
        notifyDataSetChanged();
    }

    public void refresh(List<BaseAppV4Fragment> listFms) {
        setListFrags(listFms);
        notifyDataSetChanged();
    }

    public void refresh(List<BaseAppV4Fragment> listFms, boolean isRefresh) {
        setRfreshFlag(isRefresh);
        setListFrags(listFms);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mListFms == null) {
            return 0;
        }

        return mListFms.size();
    }

    @Override
    public Fragment getItem(int position) {
        if (mListFms == null || mListFms.size() == 0) {
            return null;
        }

        return mListFms.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        if (mIsRefreshFlag) {
            return POSITION_NONE;
        }

        return super.getItemPosition(object);
    }
}
