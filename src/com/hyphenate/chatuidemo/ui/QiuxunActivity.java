package com.hyphenate.chatuidemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hyphenate.chatuidemo.R;
import com.hyphenate.chatuidemo.db.QiusaixinxiDao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yiw
 * @ClassName: MainActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2015-12-28 下午4:21:18
 */
public class QiuxunActivity extends Fragment  {


    private ListView mListView;
    private MyAdapter mAdapter;
    private List<ActiveDetail> mThemeLists;
    private boolean type;
    private QiusaixinxiDao qiusaixinxiDao;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_circle_theme, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mThemeLists = new ArrayList<ActiveDetail>();
        qiusaixinxiDao = new QiusaixinxiDao(getActivity());
            type = false;
        requestData(type);
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        requestData(false);
    }

    private void initView() {
        // TODO Auto-generated method stub
        mListView = (ListView) getView().findViewById(R.id.lv_theme);
        mAdapter =new MyAdapter();
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if ( mThemeLists.get(arg2).getDeleteFlag().equals("0")) {
//					// 话题
                    Intent intent = new Intent(getActivity(),
                            Activity_Active_Detail.class);
                    intent.putExtra("flag", true);
                    intent.putExtra("data", mThemeLists.get(arg2));
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(),
                            Activity_Active_Detail.class);
                    intent.putExtra("flag", false);
                    intent.putExtra("data", mThemeLists.get(arg2));
                    startActivity(intent);
                }
            }
        });
    }

    private void requestData(final boolean b) {
        if (b) {
            mThemeLists = qiusaixinxiDao.getMessagesList(true);
        } else {
            mThemeLists = qiusaixinxiDao.getMessagesList(false);

        }
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mThemeLists.size();
        }

        @Override
        public ActiveDetail getItem(int position) {
            // TODO Auto-generated method stub
            return mThemeLists.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            Circle_Theme_Activity.ViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(getActivity(),
                        R.layout.item_lv_circle_theme, null);
                holder = new Circle_Theme_Activity.ViewHolder(convertView);
            } else {
                holder = (Circle_Theme_Activity.ViewHolder) convertView.getTag();
            }
            // 设置数据
            ActiveDetail themeList = mThemeLists.get(position);
            if (holder.tv_theme_title != null) {
                if (!TextUtils.isEmpty(themeList.getName())) {
                    holder.tv_theme_title.setText(themeList.getName());
                }
            }
            if (holder.tv_theme_time != null) {
                if (!TextUtils.isEmpty(themeList.getStarttime())) {
                    holder.tv_theme_time.setText(themeList.getStarttime());
                }
            }
            if (holder.tv_dept != null) {
                if (!TextUtils.isEmpty(themeList.getMyNameOrOther())) {
                    holder.tv_dept.setText(themeList.getMyNameOrOther());
                }
            }
            if (themeList.getFlag().equals("l0")){
                holder.im_theme_pic.setImageResource(R.drawable.liansai);
            }else{
                holder.im_theme_pic.setImageResource(R.drawable.qiuxun);
            }
            return convertView;
        }

    }

    static class ViewHolder {

        public ImageView im_theme_pic;
        public TextView tv_theme_title;
        public TextView tv_dept;
        public TextView tv_theme_time;

        public ViewHolder(View convertView) {
            super();
            // TODO Auto-generated constructor stub
            im_theme_pic = (ImageView) convertView
                    .findViewById(R.id.im_theme_pic);
            tv_theme_title = (TextView) convertView
                    .findViewById(R.id.tv_theme_title);
            tv_dept = (TextView) convertView.findViewById(R.id.tv_dept);
            tv_theme_time = (TextView) convertView
                    .findViewById(R.id.tv_theme_time);
            convertView.setTag(this);
        }

    }

}
