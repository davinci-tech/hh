package com.huawei.hianalytics;

import android.app.ActivityManager;
import android.content.pm.ActivityInfo;
import com.hihonor.android.app.ActivityManagerEx;
import java.util.List;

/* loaded from: classes4.dex */
public class f0 implements i0 {

    /* renamed from: a, reason: collision with root package name */
    public d0 f3852a;
    public d0 b;

    @Override // com.huawei.hianalytics.i0
    public h0 b() {
        if (this.f3852a == null) {
            this.f3852a = new d0(1);
        }
        return this.f3852a;
    }

    @Override // com.huawei.hianalytics.i0
    public List<ActivityManager.RunningTaskInfo> a(int i) {
        return ActivityManagerEx.getTasks(i);
    }

    @Override // com.huawei.hianalytics.i0
    /* renamed from: a, reason: collision with other method in class */
    public h0 mo549a() {
        if (this.b == null) {
            this.b = new d0(2);
        }
        return this.b;
    }

    @Override // com.huawei.hianalytics.i0
    public ActivityInfo a() {
        return ActivityManagerEx.getLastResumedActivity();
    }
}
