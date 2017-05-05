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
package com.hyphenate.chatuidemo.db;

import android.content.ContentValues;
import android.content.Context;

import com.hyphenate.chatuidemo.ui.ActiveDetail;

import java.util.List;

public class QiusaixinxiDao {
    public static final String TABLE_NAME = "qiusai";
    static final String NAME = "name";
    static final String TIME = "time";
    static final String ID = "id";
    static final String ALLTEXT = "alltext";
    static final String PERSON = "person";
    static final String DELETEFLAG = "deleteflag";
    static final String FLAG = "flag";
    static final String MYNAMEOROTHER = "myNameOrOther";


    public QiusaixinxiDao(Context context) {
    }

    /**
     * save message
     *
     * @param message
     * @return return cursor of the message
     */
    public Integer saveMessage(ActiveDetail message) {
        return DemoDBManager.getInstance().saveQiusaiMessage(message);
    }

    /**
     * update message
     *
     * @param msgId
     * @param values
     */
    public void updateMessage(int msgId, ContentValues values) {
        DemoDBManager.getInstance().updateQiusaiMessage(msgId, values);
    }

    /**
     * get messges
     *
     * @return
     */
    public List<ActiveDetail> getMessagesList(boolean name) {
        return DemoDBManager.getInstance().getQiusaiMessagesList(name);
    }

    public void deleteMessage(String from) {
        DemoDBManager.getInstance().deleteQiusaiMessage(from);
    }

}
