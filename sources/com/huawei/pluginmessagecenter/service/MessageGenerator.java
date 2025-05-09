package com.huawei.pluginmessagecenter.service;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.mrc;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class MessageGenerator {
    private static final int INIT_LIST_SIZE = 3;
    private static final Byte[] LOCK = new Byte[1];
    private static final String TAG = "MessageGenerator";
    private static volatile MessageGenerator sInstance;
    private Context mContext;

    private MessageGenerator(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static MessageGenerator getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new MessageGenerator(context);
                }
            }
        }
        return sInstance;
    }

    public boolean generateMessage(MessageObject messageObject) {
        if (messageObject == null) {
            LogUtil.a(TAG, "Message object is null.");
            return false;
        }
        LogUtil.c(TAG, "generateMessage messageObjects = ", messageObject);
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(messageObject);
        return mrc.e(this.mContext).b(arrayList);
    }

    public String requestMessageId(String str, String str2) {
        LogUtil.a(TAG, "Enter requestMessageId | module = ", str, " type = ", str2);
        return !isValidParam(str, str2) ? "" : mrc.e(this.mContext).a(str, str2);
    }

    private boolean isValidParam(String str, String str2) {
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) ? false : true;
    }
}
