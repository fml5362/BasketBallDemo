package com.hyphenate.chatuidemo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;

import com.hyphenate.chatuidemo.R;

import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends BaseActivity implements OnClickListener {

    private List<Fragment> fragmentList;
    private SettingsFragment settingsFragment;
    private ViewPager mViewPager;
    private List<String> titles;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_oa_message_new);
        initData();
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        mViewPager
                .setAdapter(new MyAdapter(getSupportFragmentManager(), titles));

    }

    private void initData() {
        fragmentList = new ArrayList<Fragment>();
        titles = new ArrayList<String>();
        titles.add("设置");
        if (settingsFragment == null) {
            settingsFragment = new SettingsFragment();
        }
        fragmentList.add(settingsFragment);
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
