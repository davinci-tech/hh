package com.huawei.hms.iap.entity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import com.huawei.hms.support.api.client.Result;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: classes7.dex */
public class ScanRedeemCodeResult extends Result {
    private static final String TAG = "ScanRedeemCodeResult";
    private Intent mIntent;

    public void startActivityForResult(Activity activity, int i) {
        if (activity == null) {
            HMSLog.e(TAG, "startActivityForResult but activity is null");
            return;
        }
        try {
            activity.startActivityForResult(this.mIntent, i);
        } catch (ActivityNotFoundException unused) {
            HMSLog.e(TAG, "startActivityForResult occur ActivityNotFoundException");
        }
    }

    public ScanRedeemCodeResult(Intent intent) {
        this.mIntent = intent;
    }
}
