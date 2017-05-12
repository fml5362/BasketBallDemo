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

import com.hyphenate.chatuidemo.R;

/**
 * settings screen
 */
@SuppressWarnings({"FieldCanBeLocal"})
public class SaishiFragment extends Fragment implements OnClickListener {
    private RelativeLayout qiudui_rl, dongtai_rl,liansai_rl;

    public SaishiFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_saishi, container, false);
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
        qiudui_rl = (RelativeLayout) getView().findViewById(R.id.qiudui_rl);
        liansai_rl = (RelativeLayout) getView().findViewById(R.id.liansai_rl);
        dongtai_rl = (RelativeLayout) getView().findViewById(R.id.dongtai_rl);
        qiudui_rl.setOnClickListener(this);
        liansai_rl.setOnClickListener(this);
        dongtai_rl.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qiudui_rl:
                startActivity(new Intent(getActivity(), Circle_Send_ThemeActivity.class).putExtra("THEME", "THEME"));
                break;
            case R.id.liansai_rl:
                startActivity(new Intent(getActivity(), Circle_Send_ThemeActivity.class).putExtra("THEME", "ACTIVITY"));
                break;
            case R.id.dongtai_rl:
                startActivity(new Intent(getActivity(), Circle_SendActivity.class));
                break;
        }
    }
}
