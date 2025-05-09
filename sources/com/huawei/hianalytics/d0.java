package com.huawei.hianalytics;

import android.os.Bundle;
import com.hihonor.android.app.ActivityManagerEx;
import com.hihonor.android.app.IHwActivityNotifierEx;
import com.huawei.hianalytics.core.common.EnvUtils;
import com.huawei.hianalytics.core.log.HiLog;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public class d0 extends IHwActivityNotifierEx implements h0 {

    /* renamed from: a, reason: collision with root package name */
    public final int f3846a;

    /* renamed from: a, reason: collision with other field name */
    public final CopyOnWriteArrayList<j0> f25a = new CopyOnWriteArrayList<>();

    public void call(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        int i = 0;
        if (j.m570c(EnvUtils.getAppContext())) {
            while (i < this.f25a.size()) {
                this.f25a.get(i).a(bundle);
                i++;
            }
        } else {
            HiLog.d("HnAM", "call, screen is off");
            while (i < this.f25a.size()) {
                this.f25a.get(i).a();
                i++;
            }
        }
    }

    @Override // com.huawei.hianalytics.h0
    public void c() {
        ActivityManagerEx.unregisterHwActivityNotifier(this);
    }

    @Override // com.huawei.hianalytics.h0
    public void b() {
        String str;
        int i = this.f3846a;
        if (i == 1) {
            str = "appSwitch";
        } else if (i != 2) {
            return;
        } else {
            str = "activityLifeState";
        }
        ActivityManagerEx.registerHwActivityNotifier(this, str);
    }

    @Override // com.huawei.hianalytics.h0
    public void a(j0 j0Var) {
        this.f25a.add(j0Var);
    }

    @Override // com.huawei.hianalytics.h0
    public void a() {
        this.f25a.clear();
    }

    public d0(int i) {
        this.f3846a = i;
    }
}
