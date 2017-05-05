/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hyphenate.chatuidemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chatuidemo.R;

/**
 * settings screen
 */
@SuppressWarnings({"FieldCanBeLocal"})
public class MyFragment extends Fragment implements OnClickListener {
    private RelativeLayout message_rl, mytiezi_rl, mydetails_rl, mysetting_rl;

    public MyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        message_rl = (RelativeLayout) getView().findViewById(R.id.message_rl);
        mytiezi_rl = (RelativeLayout) getView().findViewById(R.id.mytiezi_rl);
        mydetails_rl = (RelativeLayout) getView().findViewById(R.id.mydetails_rl);
        mysetting_rl = (RelativeLayout) getView().findViewById(R.id.mysetting_rl);
        message_rl.setVisibility(View.GONE);
        message_rl.setOnClickListener(this);
        mytiezi_rl.setOnClickListener(this);
        mydetails_rl.setOnClickListener(this);
        mysetting_rl.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message_rl:
                startActivity(new Intent(getActivity(), ContactActivity.class));
                break;
            case R.id.mytiezi_rl:
                startActivity(new Intent(getActivity(), Circle_Theme_Activity.class).putExtra("flag", true)
                );
                break;
            case R.id.mysetting_rl:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.mydetails_rl:
                startActivity(new Intent(getActivity(), UserProfileActivity.class).putExtra("setting", true)
                        .putExtra("username", EMClient.getInstance().getCurrentUser()));
                break;
        }
    }
}
