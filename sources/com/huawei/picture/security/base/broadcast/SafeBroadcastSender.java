package com.huawei.picture.security.base.broadcast;

import android.content.Context;
import android.content.Intent;

/* loaded from: classes9.dex */
public class SafeBroadcastSender {

    static abstract class BaseSender {
        Context context;
        Intent intent;

        BaseSender(Intent intent, Context context) {
            this.intent = intent;
            this.context = context;
        }
    }
}
