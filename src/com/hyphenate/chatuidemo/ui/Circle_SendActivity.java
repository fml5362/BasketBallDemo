package com.hyphenate.chatuidemo.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chatuidemo.R;
import com.hyphenate.chatuidemo.circle.bean.CircleItem;
import com.hyphenate.chatuidemo.db.CircleDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;



/**
 * 发送dongtai
 *
 * @author Administrator
 */
public class Circle_SendActivity extends BaseActivity {
    private EditText noteEditText_outtime;
    private TextView btn_right;
    public static final SimpleDateFormat str_4_long = new SimpleDateFormat("MM月dd日");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        // 隐藏当前界面的actionbar
//		getSupportActionBar().hide();
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_send);
        initView();
    }

    public static String getYearThreeTime(long time) {
        return str_4_long.format(new Date(time));
    }

    //发送创建话题的数据
    private void sendThemeData() {
        CircleItem item = new CircleItem();
        item.setContent(noteEditText_outtime.getText().toString());
        item.setCreateTime(getYearThreeTime(System.currentTimeMillis()));
        item.setId(UUID.randomUUID().toString());
        CircleDao qiusaixinxiDao = new CircleDao(this);
        qiusaixinxiDao.saveMessage(item);
        Toast.makeText(Circle_SendActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
        finish();
    }


    private void initView() {
        btn_right = (TextView) findViewById(R.id.cbtn_right);
        btn_right.setText("发送");
        btn_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sendThemeData();
            }
        });
        noteEditText_outtime = (EditText) findViewById(R.id.noteEditText_outtime);
    }


}
