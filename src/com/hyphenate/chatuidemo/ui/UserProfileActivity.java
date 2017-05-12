package com.hyphenate.chatuidemo.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chatuidemo.DemoHelper;
import com.hyphenate.chatuidemo.R;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.utils.EaseUserUtils;

import java.io.ByteArrayOutputStream;

public class UserProfileActivity extends BaseActivity implements OnClickListener {

    private static final int REQUESTCODE_PICK = 1;
    private static final int REQUESTCODE_CUTTING = 2;
    private ImageView headAvatar;
    private ImageView headPhotoUpdate;
    private ImageView iconRightArrow;
    private TextView tvNickName;
    private TextView onet0;
    private TextView onet1;
    private TextView onet2;
    private TextView onet3;
    private TextView onet4;
    private TextView onet5;
    private TextView onet6;
    private TextView onet7;
    private TextView onet8;
    private RelativeLayout one0;
    private RelativeLayout one1;
    private RelativeLayout one2;
    private RelativeLayout one3;
    private RelativeLayout one4;
    private RelativeLayout one5;
    private RelativeLayout one6;
    private RelativeLayout one7;
    private RelativeLayout one8;
    private TextView tvUsername;
    private ProgressDialog dialog;
    private RelativeLayout rlNickName;
    private PersonBean personBean;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.em_activity_user_profile);
        initView();
        initListener();
        getToPref();
    }

    private void initView() {
        headAvatar = (ImageView) findViewById(R.id.user_head_avatar);
        headPhotoUpdate = (ImageView) findViewById(R.id.user_head_headphoto_update);
        tvUsername = (TextView) findViewById(R.id.user_username);
        tvNickName = (TextView) findViewById(R.id.user_nickname);

        onet0 = (TextView) findViewById(R.id.onet0);
        onet1 = (TextView) findViewById(R.id.onet1);
        onet2 = (TextView) findViewById(R.id.onet2);
        onet3 = (TextView) findViewById(R.id.onet3);
        onet4 = (TextView) findViewById(R.id.onet4);
        onet5 = (TextView) findViewById(R.id.onet5);
        onet6 = (TextView) findViewById(R.id.onet6);
        onet7 = (TextView) findViewById(R.id.onet7);
        onet8 = (TextView) findViewById(R.id.onet8);
        onet0.setOnClickListener(this);
        onet1.setOnClickListener(this);
        onet2.setOnClickListener(this);
        onet3.setOnClickListener(this);
        onet4.setOnClickListener(this);
        onet5.setOnClickListener(this);
        onet6.setOnClickListener(this);
        onet7.setOnClickListener(this);
        onet8.setOnClickListener(this);
        one0 = (RelativeLayout) findViewById(R.id.one0);
        one1 = (RelativeLayout) findViewById(R.id.one1);
        one2 = (RelativeLayout) findViewById(R.id.one2);
        one3 = (RelativeLayout) findViewById(R.id.one3);
        one4 = (RelativeLayout) findViewById(R.id.one4);
        one5 = (RelativeLayout) findViewById(R.id.one5);
        one6 = (RelativeLayout) findViewById(R.id.one6);
        one7 = (RelativeLayout) findViewById(R.id.one7);
        one8 = (RelativeLayout) findViewById(R.id.one8);
        one0.setOnClickListener(this);
        one1.setOnClickListener(this);
        one2.setOnClickListener(this);
        one3.setOnClickListener(this);
        one4.setOnClickListener(this);
        one5.setOnClickListener(this);
        one6.setOnClickListener(this);
        one7.setOnClickListener(this);
        one8.setOnClickListener(this);

        rlNickName = (RelativeLayout) findViewById(R.id.rl_nickname);
        iconRightArrow = (ImageView) findViewById(R.id.ic_right_arrow);
    }

    private void initListener() {
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        boolean enableUpdate = intent.getBooleanExtra("setting", false);
        if (enableUpdate) {
            headPhotoUpdate.setVisibility(View.VISIBLE);
            iconRightArrow.setVisibility(View.VISIBLE);
            rlNickName.setOnClickListener(this);
            headAvatar.setOnClickListener(this);
        } else {
            headPhotoUpdate.setVisibility(View.GONE);
            iconRightArrow.setVisibility(View.INVISIBLE);
        }
        if (username != null) {
            if (username.equals(EMClient.getInstance().getCurrentUser())) {
                tvUsername.setText(EMClient.getInstance().getCurrentUser());
                EaseUserUtils.setUserNick(username, tvNickName);
                EaseUserUtils.setUserAvatar(this, username, headAvatar);
            } else {
                tvUsername.setText(username);
                EaseUserUtils.setUserNick(username, tvNickName);
                EaseUserUtils.setUserAvatar(this, username, headAvatar);
                asyncFetchUserInfo(username);
            }
        }
    }

    private void saveTempToPref(String text) {
        SharedPreferences sp = getSharedPreferences(
                "basketball", MODE_PRIVATE);
        sp.edit().putString("basketballPerson", text).commit();

    }

    private void getToPref() {
        SharedPreferences sp = getSharedPreferences(
                "basketball", MODE_PRIVATE);
        String text = sp.getString("basketballPerson", "kong");
        if (TextUtils.isEmpty(text) | "kong".equals(text)) {
            personBean = new PersonBean();
            personBean.setSex(onet0.getText().toString());
            personBean.setNianling(onet1.getText().toString());
            personBean.setShengao(onet2.getText().toString());
            personBean.setTizhong(onet3.getText().toString());
            personBean.setWeizhi(onet4.getText().toString());
            personBean.setChengshi(onet5.getText().toString());
            personBean.setQiuyihao(onet6.getText().toString());
            personBean.setQiuxiehao(onet7.getText().toString());
            personBean.setQiudui(onet8.getText().toString());
        } else {
            personBean = JSON.parseObject(text, PersonBean.class);
            onet0.setText(personBean.getSex());
            onet1.setText(personBean.getNianling());
            onet2.setText(personBean.getShengao());
            onet3.setText(personBean.getTizhong());
            onet4.setText(personBean.getWeizhi());
            onet5.setText(personBean.getChengshi());
            onet6.setText(personBean.getQiuyihao());
            onet7.setText(personBean.getQiuxiehao());
            onet8.setText(personBean.getQiudui());
        }
    }

    private void edittext(final TextView edit, final int num) {
        final EditText editText = new EditText(this);
        new Builder(this).setTitle("修改信息").setIcon(android.R.drawable.ic_dialog_info).setView(editText)
                .setPositiveButton(R.string.dl_ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nickString = editText.getText().toString();
                        if (TextUtils.isEmpty(nickString)) {
                            Toast.makeText(UserProfileActivity.this, "修改信息不能为空", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        updateText(edit, nickString);
                        switch (num) {
                            case 0:
                                personBean.setSex(nickString);
                                break;
                            case 1:
                                personBean.setNianling(nickString);
                                break;
                            case 2:
                                personBean.setShengao(nickString);
                                break;
                            case 3:
                                personBean.setTizhong(nickString);
                                break;
                            case 4:
                                personBean.setWeizhi(nickString);
                                break;
                            case 5:
                                personBean.setChengshi(nickString);
                                break;
                            case 6:
                                personBean.setQiuyihao(nickString);
                                break;
                            case 7:
                                personBean.setQiuxiehao(nickString);
                                break;
                            case 8:
                                personBean.setQiudui(nickString);
                                break;
                        }
                        saveTempToPref(JSON.toJSONString(personBean));
                    }
                }).setNegativeButton(R.string.dl_cancel, null).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_head_avatar:
                uploadHeadPhoto();
                break;
            case R.id.one0:
                edittext(onet0, 0);
                break;
            case R.id.one1:
                edittext(onet1, 1);
                break;
            case R.id.one2:
                edittext(onet2, 2);
                break;
            case R.id.one3:
                edittext(onet3, 3);
                break;
            case R.id.one4:
                edittext(onet4, 4);
                break;
            case R.id.one5:
                edittext(onet5, 5);
                break;
            case R.id.one6:
                edittext(onet6, 6);
                break;
            case R.id.one7:
                edittext(onet7, 7);
                break;
            case R.id.one8:
                edittext(onet8, 8);
                break;
            case R.id.rl_nickname:
                final EditText editText = new EditText(this);
                new AlertDialog.Builder(this).setTitle(R.string.setting_nickname).setIcon(android.R.drawable.ic_dialog_info).setView(editText)
                        .setPositiveButton(R.string.dl_ok, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String nickString = editText.getText().toString();
                                if (TextUtils.isEmpty(nickString)) {
                                    Toast.makeText(UserProfileActivity.this, getString(R.string.toast_nick_not_isnull), Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                updateRemoteNick(nickString);
                            }
                        }).setNegativeButton(R.string.dl_cancel, null).show();
                break;
            default:
                break;
        }

    }

    public void asyncFetchUserInfo(String username) {
        DemoHelper.getInstance().getUserProfileManager().asyncGetUserInfo(username, new EMValueCallBack<EaseUser>() {

            @Override
            public void onSuccess(EaseUser user) {
                if (user != null) {
                    DemoHelper.getInstance().saveContact(user);
                    if (isFinishing()) {
                        return;
                    }
                    tvNickName.setText(user.getNick());
                    if (!TextUtils.isEmpty(user.getAvatar())) {
                        Glide.with(UserProfileActivity.this).load(user.getAvatar()).placeholder(R.drawable.em_default_avatar).into(headAvatar);
                    } else {
                        Glide.with(UserProfileActivity.this).load(R.drawable.em_default_avatar).into(headAvatar);
                    }
                }
            }

            @Override
            public void onError(int error, String errorMsg) {
            }
        });
    }


    private void uploadHeadPhoto() {
        AlertDialog.Builder builder = new Builder(this);
        builder.setTitle(R.string.dl_title_upload_photo);
        builder.setItems(new String[]{getString(R.string.dl_msg_take_photo), getString(R.string.dl_msg_local_upload)},
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        switch (which) {
                            case 0:
                                Toast.makeText(UserProfileActivity.this, getString(R.string.toast_no_support),
                                        Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                                pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                                startActivityForResult(pickIntent, REQUESTCODE_PICK);
                                break;
                            default:
                                break;
                        }
                    }
                });
        builder.create().show();
    }

    private void updateText(final TextView textData, final String nickName) {
        textData.setText(nickName);
    }

    private void updateRemoteNick(final String nickName) {
        dialog = ProgressDialog.show(this, getString(R.string.dl_update_nick), getString(R.string.dl_waiting));
        new Thread(new Runnable() {

            @Override
            public void run() {
                boolean updatenick = DemoHelper.getInstance().getUserProfileManager().updateCurrentUserNickName(nickName);
                if (UserProfileActivity.this.isFinishing()) {
                    return;
                }
                if (!updatenick) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(UserProfileActivity.this, getString(R.string.toast_updatenick_fail), Toast.LENGTH_SHORT)
                                    .show();
                            dialog.dismiss();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                            Toast.makeText(UserProfileActivity.this, getString(R.string.toast_updatenick_success), Toast.LENGTH_SHORT)
                                    .show();
                            tvNickName.setText(nickName);
                        }
                    });
                }
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUESTCODE_PICK:
                if (data == null || data.getData() == null) {
                    return;
                }
                startPhotoZoom(data.getData());
                break;
            case REQUESTCODE_CUTTING:
                if (data != null) {
                    setPicToView(data);
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, REQUESTCODE_CUTTING);
    }

    /**
     * save the picture data
     *
     * @param picdata
     */
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(getResources(), photo);
            headAvatar.setImageDrawable(drawable);
            uploadUserAvatar(Bitmap2Bytes(photo));
        }

    }

    private void uploadUserAvatar(final byte[] data) {
        dialog = ProgressDialog.show(this, getString(R.string.dl_update_photo), getString(R.string.dl_waiting));
        new Thread(new Runnable() {

            @Override
            public void run() {
                final String avatarUrl = DemoHelper.getInstance().getUserProfileManager().uploadUserAvatar(data);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        if (avatarUrl != null) {
                            Toast.makeText(UserProfileActivity.this, getString(R.string.toast_updatephoto_success),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UserProfileActivity.this, getString(R.string.toast_updatephoto_fail),
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        }).start();

        dialog.show();
    }


    public byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}
