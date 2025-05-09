package com.huawei.hianalytics;

import android.app.ActivityManager;
import android.content.pm.ActivityInfo;
import com.huawei.android.app.ActivityManagerEx;
import java.util.List;

/* loaded from: classes4.dex */
public class g0 implements i0 {

    /* renamed from: a, reason: collision with root package name */
    public e0 f3872a;
    public e0 b;

    @Override // com.huawei.hianalytics.i0
    public h0 b() {
        if (this.f3872a == null) {
            this.f3872a = new e0(1);
        }
        return this.f3872a;
    }

    @Override // com.huawei.hianalytics.i0
    public List<ActivityManager.RunningTaskInfo> a(int i) {
        return ActivityManagerEx.getTasks(i);
    }

    @Override // com.huawei.hianalytics.i0
    /* renamed from: a */
    public h0 mo549a() {
        if (this.b == null) {
            this.b = new e0(2);
        }
        return this.b;
    }

    @Override // com.huawei.hianalytics.i0
    public ActivityInfo a() {
        return ActivityManagerEx.getLastResumedActivity();
    }
}
