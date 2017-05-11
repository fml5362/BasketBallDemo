package com.hyphenate.chatuidemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chatuidemo.R;
import com.hyphenate.chatuidemo.db.QiusaixinxiDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 纳通圈中的话题列表/活动列表界面
 *
 * @author DY
 */
public class Circle_Theme_Activity extends BaseActivity {

    private ListView mListView;
    private MyAdapter mAdapter;
    private List<ActiveDetail> mThemeLists;
    private boolean type;
    private QiusaixinxiDao qiusaixinxiDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_theme);
        mThemeLists = new ArrayList<ActiveDetail>();
        qiusaixinxiDao = new QiusaixinxiDao(this);
        if (getIntent() != null) {
            type = getIntent().getBooleanExtra("flag", true);
        }

        requestData(type);
        initView();
    }

    private void initView() {
        // TODO Auto-generated method stub
        mListView = (ListView) findViewById(R.id.lv_theme);
        mAdapter = new MyAdapter();
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (type) {
//					// 话题
                    Intent intent = new Intent(Circle_Theme_Activity.this,
                            Activity_Active_Detail.class);
                    intent.putExtra("flag", type);
                    intent.putExtra("data", mThemeLists.get(arg2));
                    startActivity(intent);
                } else if (type) {
                    Intent intent = new Intent(Circle_Theme_Activity.this,
                            Activity_Active_Detail.class);
                    intent.putExtra("flag", type);
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
        if (mThemeLists!=null&&mThemeLists.size()==0){
            Toast.makeText(Circle_Theme_Activity.this,"暂无信息，请创建联赛或球队",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestData(false);
        mAdapter.notifyDataSetChanged();
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
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(Circle_Theme_Activity.this,
                        R.layout.item_lv_circle_theme, null);
                holder = new ViewHolder(convertView);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            // 设置数据
            ActiveDetail themeList = mThemeLists.get(position);
            if (holder.tv_theme_title != null) {
                if (!TextUtils.isEmpty(themeList.getName())) {
                    holder.tv_theme_title.setText(themeList.getName());
                }
            }
            if (holder.tv_theme_time != null) {
                if (!TextUtils.isEmpty(themeList.getTime())) {
                    holder.tv_theme_time.setText(themeList.getTime());
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
