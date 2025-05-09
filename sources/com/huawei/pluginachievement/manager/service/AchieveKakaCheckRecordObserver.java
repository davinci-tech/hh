package com.huawei.pluginachievement.manager.service;

import android.content.Context;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.impl.AchieveObserver;
import com.huawei.pluginachievement.manager.model.KakaCheckinRecord;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import defpackage.koq;
import defpackage.mct;
import defpackage.meh;
import defpackage.mle;
import defpackage.nsj;
import java.util.List;

/* loaded from: classes6.dex */
public class AchieveKakaCheckRecordObserver implements AchieveObserver {
    private IBaseResponseCallback d;
    private Context e;

    public AchieveKakaCheckRecordObserver(Context context) {
        this.e = context;
    }

    public void d(IBaseResponseCallback iBaseResponseCallback) {
        this.d = iBaseResponseCallback;
    }

    @Override // com.huawei.pluginachievement.impl.AchieveObserver
    public void onDataChanged(int i, UserAchieveWrapper userAchieveWrapper) {
        if (userAchieveWrapper == null) {
            LogUtil.h("PLGACHIEVE_AchieveKakaCheckRecordObserver", "AchieveKakaCheckRecordObserver wrapper is null");
            return;
        }
        int contentType = userAchieveWrapper.getContentType();
        if (i == -1 && contentType == 20) {
            LogUtil.h("PLGACHIEVE_AchieveKakaCheckRecordObserver", "HttpErrCode or wrapper is error");
            a();
        } else if (contentType == 20) {
            LogUtil.a("PLGACHIEVE_AchieveKakaCheckRecordObserver", "AchieveKakaCheckRecordObserver|onDataChanged contentType = ", Integer.valueOf(contentType));
            boolean a2 = a(this.e, userAchieveWrapper.getKakaCheckinRecords());
            IBaseResponseCallback iBaseResponseCallback = this.d;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(0, Boolean.valueOf(a2));
            }
            a();
        }
    }

    private void a() {
        LogUtil.a("PLGACHIEVE_AchieveKakaCheckRecordObserver", "enter releaseManager");
        meh.c(BaseApplication.getContext()).c(this);
    }

    private boolean a(Context context, List<KakaCheckinRecord> list) {
        int conDays;
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_AchieveKakaCheckRecordObserver", "check record is empty");
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = false;
        for (int i = 0; i < list.size(); i++) {
            KakaCheckinRecord kakaCheckinRecord = list.get(i);
            if (nsj.e(currentTimeMillis, 0) == mle.e(kakaCheckinRecord.getRecordDay()) && (conDays = kakaCheckinRecord.getConDays()) > 0) {
                LogUtil.a("PLGACHIEVE_AchieveKakaCheckRecordObserver", "CheckedIn:date = ", kakaCheckinRecord.getRecordDay());
                mct.b(this.e, "kakaLastCheckInTime", kakaCheckinRecord.getRecordDay());
                mct.b(context, "kakaCheckedDays", String.valueOf(conDays));
                z = true;
            }
        }
        return z;
    }
}
