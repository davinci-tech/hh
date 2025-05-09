package com.huawei.ui.thirdpartservice.syncdata.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.thirdpartservice.runtasticapi.RuntasticProviderApi;
import defpackage.siq;

/* loaded from: classes7.dex */
public class SyncBroadcastReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("com.huawei.plugin.account.login".equals(intent.getAction())) {
            LogUtil.a("SyncBroadcastReceiver", "SyncThirdReceiver ACTION_LOGIN_SUCCESSFUL");
            siq.a().e();
        } else if (RuntasticProviderApi.ACTION_RUNTASTIC_SYNC_DATA.equals(intent.getAction())) {
            LogUtil.a("SyncBroadcastReceiver", "SyncThirdReceiver", RuntasticProviderApi.ACTION_RUNTASTIC_SYNC_DATA);
            siq.a().e();
        } else if ("com.huawei.sync_activity_to_third_platform".equals(intent.getAction())) {
            LogUtil.a("SyncBroadcastReceiver", "SyncThirdReceiver Manual triggering");
            siq.a().e();
        } else {
            LogUtil.h("SyncBroadcastReceiver", "SyncThirdReceiver unknown action:", intent.getAction());
        }
    }
}
