package com.hyphenate.chatuidemo.ui;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chatuidemo.R;
import com.hyphenate.chatuidemo.circle.utils.CommonUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 活动的详情页
 *
 * @author DY
 */
public class Activity_Active_Detail extends BaseActivity implements
        OnClickListener {
    private List<String> mThemeLists;
    private LinearLayout ll_sign;
    private LinearLayout ll_comment, editTextBodyLl;
    private TextView tv_person_start, tv_active_text;

    private ListView lv_comments;
    private EditText circleEt;
    private ImageView sendIv;
    private TextView tv_active_time, tv_title, tv_oa_sender;
    // Activity最外层的Layout视图
    private ActiveDetail data;
    private boolean flag;
    private MyAdapter myAdapter;

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        // 隐藏当前界面的actionBar
        setContentView(R.layout.activity_active_detai);
        mThemeLists = new ArrayList<>();
        initData();
        initView();

    }

    private void initData() {
        data = (ActiveDetail) getIntent().getSerializableExtra("data");
        flag = getIntent().getBooleanExtra("flag", true);
    }

    private void initView() {

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_person_start = (TextView) findViewById(R.id.tv_person_start);
        tv_active_time = (TextView) findViewById(R.id.tv_active_time);
        lv_comments = (ListView) findViewById(R.id.lv_comments);
        sendIv = (ImageView) findViewById(R.id.sendIv);
        tv_active_text = (TextView) findViewById(R.id.tv_active_text);
        tv_oa_sender = (TextView) findViewById(R.id.tv_oa_sender);
        ll_sign = (LinearLayout) findViewById(R.id.ll_sign);
        ll_comment = (LinearLayout) findViewById(R.id.ll_comment);
        editTextBodyLl = (LinearLayout) findViewById(R.id.editTextBodyLl);
        circleEt = (EditText) findViewById(R.id.circleEt);
        if (data.getFlag().equals("l0")) {
            tv_title.setText("联赛的标题 : " + data.getName());
            tv_active_time.setText("联赛的时间和地点 : " + data.getTime());
            tv_person_start.setText("活动的发起人 : " + data.getMyNameOrOther());
            tv_active_text.setText("联赛的介绍 : " + data.getAll());
        } else {
            tv_title.setText("球队的名称 : " + data.getName());
            tv_person_start.setText("球队的发起人 : " + data.getMyNameOrOther());
            tv_active_text.setText("联赛的介绍 : " + data.getAll());
        }

        // 接收人
        final List<String> toIds = new ArrayList<>();
        Collections.addAll(toIds, "张三", "李四", "王五", "李云兴", "王磊");
        if (toIds != null) {
            for (String name : toIds) {
                setNickNameClickable(name, tv_oa_sender);
            }
        }
        ll_sign.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toIds.contains("自己")) {
                    Toast.makeText(Activity_Active_Detail.this, "取消报名", Toast.LENGTH_SHORT).show();
                    toIds.remove("自己");
                    tv_oa_sender.setText("报名人员 ： ");
                    for (String name : toIds) {
                        setNickNameClickable(name, tv_oa_sender);
                    }
                } else {
                    toIds.add("自己");
                    tv_oa_sender.setText("报名人员 ： ");
                    for (String name : toIds) {
                        setNickNameClickable(name, tv_oa_sender);
                    }
                    Toast.makeText(Activity_Active_Detail.this, "报名成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ll_comment.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEditTextBodyVisible(View.VISIBLE);
            }
        });
        sendIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEditTextBodyVisible(View.GONE);

                if (TextUtils.isEmpty(circleEt.getText().toString())) {
                    Toast.makeText(Activity_Active_Detail.this, "评论不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                circleEt.setText("");
                mThemeLists.add("自己 : "+circleEt.getText().toString());
                myAdapter.notifyDataSetChanged();
            }
        });
        myAdapter = new MyAdapter();
        lv_comments.setAdapter(myAdapter);
    }

    public void updateEditTextBodyVisible(int visibility) {
        editTextBodyLl.setVisibility(visibility);


        if (View.VISIBLE == visibility) {
            circleEt.requestFocus();
            //弹出键盘
            CommonUtils.showSoftInput(circleEt.getContext(), circleEt);

        } else if (View.GONE == visibility) {
            //隐藏键盘
            CommonUtils.hideSoftInput(circleEt.getContext(), circleEt);
        }
    }

    private void setNickNameClickable(String name, TextView textView) {
        SpannableString spannableString = new SpannableString(name + "  ");
        spannableString.setSpan(null, 0, name.length() - 1,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.append(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_title:// 评论发送按钮
                break;
            case R.id.ll_sign:// 报名
                break;
            case R.id.ll_comment:// 评论
                break;
        }

    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mThemeLists.size();
        }

        @Override
        public String getItem(int position) {
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
                convertView = View.inflate(Activity_Active_Detail.this,
                        R.layout.item_lv_text, null);
                holder = new Circle_Theme_Activity.ViewHolder(convertView);
            } else {
                holder = (Circle_Theme_Activity.ViewHolder) convertView.getTag();
            }
            // 设置数据
            String themeList = mThemeLists.get(position);
            if (holder.tv_theme_title != null) {
                holder.tv_theme_title.setText(themeList);
            }
            return convertView;
        }

    }

    static class ViewHolder {

        public TextView tv_theme_title;

        public ViewHolder(View convertView) {
            super();
            // TODO Auto-generated constructor stub
            tv_theme_title = (TextView) convertView
                    .findViewById(R.id.tv_theme_title);
            convertView.setTag(this);
        }

    }
}
