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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chatuidemo.R;
import com.hyphenate.chatuidemo.db.QiusaixinxiDao;

import java.util.Random;
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
    private NoteEditText noteEditText_outtime;
    private NoteEditText noteEditText_about, noteEditText_time;
    private TextView theme_tv_name, theme_tv_time, theme_tv_desc;
    private String content_String, time_content_String, name_String;
    private StringBuffer stringBuffer = new StringBuffer();
    private String subString;
    private String flag, title;
    private TextView btn_right;
    private TextView tv_outtime;
    private LinearLayout theme_time;
    private Spinner spinner_main_edu;
    private ArrayAdapter<String> adapter = null;
    private String data;

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
        if (newflag.equals("q0")) {
            detail.setAll(noteEditText_about.getText().toString());
        } else {
            if (TextUtils.isEmpty(data)) {
                detail.setAll("5V5");
            } else {
                detail.setAll(data);
            }
            detail.setAddress(noteEditText_time.getText().toString());
            detail.setStarttime(noteEditText_about.getText().toString());
            detail.setEndtime(noteEditText_outtime.getText().toString());
        }
        detail.setName(noteEditText_name.getText().toString());
        int aa = testRandom2();
        if (aa == 0) {
            detail.setMyNameOrOther("自己");
            detail.setDeleteFlag("0");
        } else if (aa == 1) {
            detail.setMyNameOrOther("张三");
            detail.setDeleteFlag("1");
        } else if (aa == 2) {
            detail.setMyNameOrOther("李四");
            detail.setDeleteFlag("2");
        }
        detail.setFlag(newflag);
        detail.setId(UUID.randomUUID().toString());
        QiusaixinxiDao qiusaixinxiDao = new QiusaixinxiDao(this);
        qiusaixinxiDao.saveMessage(detail);
        Toast.makeText(Circle_Send_ThemeActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    //在一定范围内生成随机数.
    //比如此处要求在[0 - n)内生成随机数.
    //注意:包含0不包含n
    int a;

    private int testRandom2() {
        Random random = new Random();

        for (int i = 0; i < 1; i++) {
            System.out.println("random.nextInt()=" + random.nextInt(3));
            a = random.nextInt(2);
        }
        return a;
    }

    private void initView() {
        theme_time = (LinearLayout) findViewById(R.id.theme_time);
        btn_right = (TextView) findViewById(R.id.cbtn_right);
        btn_right.setText("发送");
        btn_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                testRandom2();
                if (flag.equals("THEME")) {
                    sendThemeData("q0");
                } else if (flag.equals("ACTIVITY")) {
                    sendThemeData("l0");
                }

            }
        });
        noteEditText_name = (NoteEditText) findViewById(R.id.noteEditText_name);
        noteEditText_about = (NoteEditText) findViewById(R.id.noteEditText_about);
        noteEditText_time = (NoteEditText) findViewById(R.id.noteEditText_time);
        noteEditText_outtime = (NoteEditText) findViewById(R.id.noteEditText_outtime);
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
        tv_outtime = (TextView) findViewById(R.id.tv_outtime);
        // 设置数据源
        String[] strArr = new String[]{"5V5", "4V4", "3V3", "单挑赛", "三分大赛", "技巧大赛", "其他"};

        spinner_main_edu = (Spinner) findViewById(R.id.spinner_main_edu);
        adapter = new ArrayAdapter<String>(Circle_Send_ThemeActivity.this,
                android.R.layout.simple_list_item_single_choice, strArr);
// 给控件设置适配器
        spinner_main_edu.setAdapter(adapter);
        spinner_main_edu
                .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id) {
                        data = parent.getItemAtPosition(position)
                                .toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });


        if (!TextUtils.isEmpty(flag)) {
            // 从话题点击进来的
            if (flag.equals("THEME")) {
                theme_time.setVisibility(View.GONE);
                tv_outtime.setVisibility(View.GONE);
                spinner_main_edu.setVisibility(View.GONE);
                theme_tv_name.setText("请输入球队名称：");
                theme_tv_desc.setText("请输入球队简介：");
                // 从活动点击进来的
            } else if (flag.equals("ACTIVITY")) {
                theme_time.setVisibility(View.VISIBLE);
                theme_tv_name.setText("请输入联赛名称：");
                theme_tv_time.setText("请输入联赛地点：");
                theme_tv_desc.setText("请选择开始时间：");
                tv_outtime.setText("请选择结束时间：");
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
