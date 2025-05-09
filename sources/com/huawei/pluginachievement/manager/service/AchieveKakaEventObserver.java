package com.huawei.pluginachievement.manager.service;

import android.content.Context;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.impl.AchieveObserver;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import defpackage.mcz;
import defpackage.mde;
import defpackage.mdf;
import defpackage.meh;
import defpackage.mer;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class AchieveKakaEventObserver implements AchieveObserver {
    private Context d;

    public AchieveKakaEventObserver(Context context) {
        this.d = context;
    }

    @Override // com.huawei.pluginachievement.impl.AchieveObserver
    public void onDataChanged(int i, UserAchieveWrapper userAchieveWrapper) {
        if (i == -1 || userAchieveWrapper == null) {
            LogUtil.a("PLGACHIEVE_AchieveKakaEventObserver", "HttpErrCode or userAchieveWrapper is error");
            return;
        }
        int contentType = userAchieveWrapper.getContentType();
        LogUtil.a("PLGACHIEVE_AchieveKakaEventObserver", "AchieveKakaEventObserver|onDataChanged contentType = ", Integer.valueOf(contentType));
        if (contentType == 12) {
            final mde acquireKakaUpdateReturnBody = userAchieveWrapper.acquireKakaUpdateReturnBody();
            LogUtil.a("PLGACHIEVE_AchieveKakaEventObserver", "onDataChange UPDATE_TASK_STATUS resultCode =", userAchieveWrapper.getResultCode());
            if (acquireKakaUpdateReturnBody == null || !userAchieveWrapper.getResultCode().equals("10017")) {
                return;
            }
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.pluginachievement.manager.service.AchieveKakaEventObserver.4
                @Override // java.lang.Runnable
                public void run() {
                    AchieveKakaEventObserver.this.a(acquireKakaUpdateReturnBody.e());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("taskId", str);
        mcz d = meh.c(this.d.getApplicationContext()).d(12, hashMap);
        if (d instanceof mdf) {
            mdf mdfVar = (mdf) d;
            if (mdfVar.ag() == 30008) {
                mdfVar.h(0);
                mer.b(this.d).d(mdfVar, 0);
            }
        }
    }
}
