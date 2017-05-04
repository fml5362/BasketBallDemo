package com.hyphenate.chatuidemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.hyphenate.chatuidemo.R;
import com.hyphenate.chatuidemo.circle.CircleMainActivity;

import java.util.ArrayList;
import java.util.List;

public class FaxianFragment extends Fragment implements OnClickListener {

    private List<Fragment> fragmentList;
    private CircleMainActivity circleMainActivity;
    private QiuxunActivity qiuxunActivity;
    private ViewPager mViewPager;
    private List<String> titles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_oa_message_new, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) getView().findViewById(R.id.viewPager);

        mViewPager
                .setAdapter(new MyAdapter(getActivity().getSupportFragmentManager(), titles));

    }

    private void initData() {
        fragmentList = new ArrayList<Fragment>();
        titles = new ArrayList<String>();
        titles.add("aaaa");
        if (circleMainActivity == null) {
            circleMainActivity = new CircleMainActivity();
        }
        if (qiuxunActivity == null) {
            qiuxunActivity = new QiuxunActivity();
        }
        fragmentList.add(qiuxunActivity);
        fragmentList.add(circleMainActivity);
    }

    @Override
    public void onClick(View v) {

    }

    public class MyAdapter extends FragmentPagerAdapter {
        List<String> _titles;

        public MyAdapter(FragmentManager fm, List<String> titles) {
            super(fm);
            _titles = titles;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return _titles.get(position);
        }

        @Override
        public int getCount() {
            return _titles.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }
    }
}
