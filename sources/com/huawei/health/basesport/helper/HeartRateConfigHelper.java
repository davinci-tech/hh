package com.huawei.health.basesport.helper;

import com.huawei.health.basesport.helper.HeartRateConfigHelper;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import defpackage.eme;
import defpackage.fgd;
import defpackage.jdx;
import defpackage.koq;
import health.compact.a.Services;
import health.compact.a.util.LogUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class HeartRateConfigHelper {

    /* renamed from: a, reason: collision with root package name */
    private HeartZoneConf f2194a;
    private int b = 0;
    private OnConfigHelperListener c;

    public interface OnConfigHelperListener {
        void onInitComplete();
    }

    public HeartRateConfigHelper(final int i, OnConfigHelperListener onConfigHelperListener) {
        this.c = onConfigHelperListener;
        jdx.b(new Runnable() { // from class: caf
            @Override // java.lang.Runnable
            public final void run() {
                HeartRateConfigHelper.this.b(i);
            }
        });
    }

    public /* synthetic */ void b(int i) {
        int heartPostureType = eme.b().getHeartPostureType(i);
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
        if (userProfileMgrApi == null) {
            LogUtil.c("HeartRateConfigHelper", "init : userProfileMgrApi is null.");
            return;
        }
        HeartZoneConf e = fgd.e(heartPostureType, userProfileMgrApi.getLocalUserInfo().getAgeOrDefaultValue());
        this.f2194a = e;
        if (e != null) {
            this.b = e.getClassifyMethod();
        }
        OnConfigHelperListener onConfigHelperListener = this.c;
        if (onConfigHelperListener != null) {
            onConfigHelperListener.onInitComplete();
        }
    }

    public int a(int i) {
        HeartZoneConf heartZoneConf = this.f2194a;
        if (heartZoneConf != null) {
            return heartZoneConf.findRateRegion(i, this.b);
        }
        return -1;
    }

    public String d(int i) {
        int a2 = a(i);
        List<String> c = fgd.c(this.b);
        return koq.d(c, a2) ? c.get(a2) : "";
    }

    public HeartZoneConf a() {
        return this.f2194a;
    }
}
