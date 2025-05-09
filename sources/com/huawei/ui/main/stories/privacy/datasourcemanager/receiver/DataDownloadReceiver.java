package com.huawei.ui.main.stories.privacy.datasourcemanager.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.privacy.datasourcemanager.listener.DataDownloadListener;

/* loaded from: classes7.dex */
public class DataDownloadReceiver extends BroadcastReceiver {
    private DataDownloadListener c;

    public DataDownloadReceiver(DataDownloadListener dataDownloadListener) {
        this.c = dataDownloadListener;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            LogUtil.b("DataDownloadReceiver", "action is null or is empty");
            return;
        }
        if (!"com.huawei.hihealth.action_sync".equals(action)) {
            LogUtil.b("DataDownloadReceiver", "action is not action_sync");
            return;
        }
        try {
            this.c.dataDownloadListener(intent.getIntExtra("com.huawei.hihealth.action_sync_status", -1));
        } catch (Exception unused) {
            LogUtil.b("DataDownloadReceiver", "getDataFromIntent Exception");
        }
    }
}
