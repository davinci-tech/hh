package com.huawei.health.suggestion.receiver;

import android.content.Context;
import android.content.Intent;
import com.huawei.health.messagecenter.model.IpushBase;
import com.huawei.health.suggestion.data.DataDownloadService;
import defpackage.arx;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class FitnessHistoryPushReceiver implements IpushBase {
    private static final String TAG = "Fitness_FitnessHistoryPushReceiver";

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public List<String> getPushType() {
        return Arrays.asList("4001");
    }

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public void processPushMsg(Context context, String str) {
        ReleaseLogUtil.e(TAG, "processPushMsg...");
        arx.b().startService(new Intent(arx.b(), (Class<?>) DataDownloadService.class));
    }
}
