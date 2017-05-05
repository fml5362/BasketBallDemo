package com.hyphenate.chatuidemo.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chatuidemo.R;
import com.hyphenate.chatuidemo.db.QiusaixinxiDao;

import java.util.UUID;


/**
 * 发送话题和活动的页面
 *
 * @author Administrator
 */
public class Circle_Send_ThemeActivity extends BaseActivity implements
        OnClickListener {
    private static final int TAKE_PICTURE = 0x000001;
    private String path = "";
    private NoteEditText noteEditText_name;
    private NoteEditText noteEditText_about, noteEditText_time;
    private TextView theme_tv_name, theme_tv_time, theme_tv_desc;
    private String content_String, time_content_String, name_String;
    private StringBuffer stringBuffer = new StringBuffer();
    private String subString;
    private String flag, title;
    private TextView btn_right;
    private LinearLayout theme_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        // 隐藏当前界面的actionbar
//		getSupportActionBar().hide();
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_send_theme);
        flag = getIntent().getStringExtra("THEME");
        initView();
    }


    //发送创建话题的数据
    private void sendThemeData(String newflag) {
        ActiveDetail detail = new ActiveDetail();
        detail.setAll(noteEditText_about.getText().toString());
        detail.setName(noteEditText_name.getText().toString());
        detail.setTime(noteEditText_time.getText().toString());
        detail.setMyNameOrOther("自己");
        detail.setDeleteFlag("0");
        detail.setFlag(newflag);
        detail.setId(UUID.randomUUID().toString());
        QiusaixinxiDao qiusaixinxiDao = new QiusaixinxiDao(this);
        qiusaixinxiDao.saveMessage(detail);
        Toast.makeText(Circle_Send_ThemeActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void initView() {
        theme_time = (LinearLayout) findViewById(R.id.theme_time);
        btn_right = (TextView) findViewById(R.id.cbtn_right);
        btn_right.setText("发送");
        btn_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag.equals("THEME")) {
                    sendThemeData("l0");
                } else if (flag.equals("ACTIVITY")) {
                    sendThemeData("q0");
                }

            }
        });
        // 话题名子
        noteEditText_name = (NoteEditText) findViewById(R.id.noteEditText_name);
        // 话题简介
        noteEditText_about = (NoteEditText) findViewById(R.id.noteEditText_about);
        //活动的时间
        noteEditText_time = (NoteEditText) findViewById(R.id.noteEditText_time);
        if (!TextUtils.isEmpty(name_String)) {
            noteEditText_name.setText(name_String);
            Editable text = noteEditText_name.getText();
            Spannable spanText = text;
            Selection.setSelection(spanText, text.length());
        }
        if (!TextUtils.isEmpty(content_String)) {
            noteEditText_about.setText(content_String);
            Editable text = noteEditText_about.getText();
            Spannable spanText = text;
            Selection.setSelection(spanText, text.length());
        }
        if (!TextUtils.isEmpty(time_content_String)) {
            noteEditText_time.setText(time_content_String);
            Editable text = noteEditText_time.getText();
            Spannable spanText = text;
            Selection.setSelection(spanText, text.length());
        }
        // 名称
        theme_tv_name = (TextView) findViewById(R.id.theme_tv_name);
        theme_tv_time = (TextView) findViewById(R.id.theme_tv_time);
        theme_tv_desc = (TextView) findViewById(R.id.theme_tv_desc);
        if (!TextUtils.isEmpty(flag)) {
            // 从话题点击进来的
            if (flag.equals("THEME")) {
                theme_time.setVisibility(View.GONE);
                theme_tv_name.setText("球队名称：");
                theme_tv_desc.setText("球队简介：");
                // 从活动点击进来的
            } else if (flag.equals("ACTIVITY")) {
                theme_time.setVisibility(View.VISIBLE);
                theme_tv_name.setText("联赛名称：");
                theme_tv_time.setText("联赛时间及地点：");
                theme_tv_desc.setText("联赛介绍：");
            }
        }
    }


    private void saveTempToPref(String text) {
        SharedPreferences sp = getSharedPreferences(
                "basketball", MODE_PRIVATE);
        sp.edit().putString("basketball", text).commit();

    }


    @Override
    public void onClick(View v) {

    }
}
