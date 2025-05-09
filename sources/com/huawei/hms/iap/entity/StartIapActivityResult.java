package com.huawei.hms.iap.entity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import com.huawei.hms.support.api.client.Result;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: classes9.dex */
public class StartIapActivityResult extends Result {
    private Intent mIntent;

    public void startActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        try {
            activity.startActivity(this.mIntent);
        } catch (ActivityNotFoundException unused) {
            HMSLog.e("StartIapActivityResult", "startActivity ActivityNotFoundException");
        }
    }

    public StartIapActivityResult(Intent intent) {
        this.mIntent = intent;
    }
}
