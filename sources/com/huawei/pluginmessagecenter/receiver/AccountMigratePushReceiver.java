package com.huawei.pluginmessagecenter.receiver;

import android.content.Context;
import com.huawei.health.messagecenter.model.IpushBase;
import com.huawei.health.messagecenter.model.IpushTokenCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes6.dex */
public class AccountMigratePushReceiver implements IpushBase, IpushTokenCallback {
    private static final String TAG = "AccountMigratePushReceiver";

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public List<String> getPushType() {
        return Arrays.asList("5");
    }

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public void processPushMsg(Context context, String str) {
        if (str == null || "".equals(str) || str.length() < 1) {
            LogUtil.b(TAG, "processPushMsg Error PushMsg is Empty");
            return;
        }
        LogUtil.c(TAG, "account migrate: processPushMsg():msg=", str);
        SharedPreferenceManager.e(context, Integer.toString(10015), "is_cloud_push_receiver", "is_cloud_push_receiver_true", new StorageParams(0));
    }

    @Override // com.huawei.health.messagecenter.model.IpushTokenCallback
    public void pushTokenHandle(Context context, String str) {
        LogUtil.c(TAG, "token = ", str);
    }
}
