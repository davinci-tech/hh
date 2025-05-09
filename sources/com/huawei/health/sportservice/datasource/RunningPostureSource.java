package com.huawei.health.sportservice.datasource;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ffs;
import java.math.BigDecimal;

@SportComponentType(classify = 3, name = ComponentName.RUNNING_POSTURE_SOURCE)
/* loaded from: classes8.dex */
public class RunningPostureSource extends BaseSource implements SportLifecycle, IBaseResponseCallback {
    private static final String TAG = "SportService_RunningPostureSource";
    private ffs mRunningPosture;

    private float getFloatValue(float f) {
        if (f == 0.0f) {
            return -1.0f;
        }
        return f;
    }

    private int getIntegerValue(int i, float f) {
        return i == 0 ? (int) f : i;
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        this.mRunningPosture = new ffs();
        BaseSportManager.getInstance().setParas(SportParamsType.DEVICE_CALLBACK_AW70, this);
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void updateSourceData() {
        if (this.mRunningPosture == null) {
            LogUtil.a(TAG, "updateSourceData runningPosture == null");
        } else {
            BaseSportManager.getInstance().updateSourceData(getLogTag(), "RUNNING_POSTURE_DATA", this.mRunningPosture);
        }
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void generateData(Object obj) {
        if (obj instanceof ffs) {
            ffs ffsVar = new ffs();
            this.mRunningPosture = ffsVar;
            updateRunningPosture((ffs) obj, ffsVar);
            updateSourceData();
        }
    }

    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
    /* renamed from: onResponse */
    public void d(int i, Object obj) {
        if (i == 0) {
            generateData(obj);
        }
    }

    private void updateRunningPosture(ffs ffsVar, ffs ffsVar2) {
        LogUtil.a(TAG, "updateRunningPosture", ffsVar.toString());
        if (ffsVar2 == null) {
            LogUtil.a(TAG, "updateRunningPosture runningPosture == null");
            return;
        }
        if (ffsVar.o() != -1.0f) {
            ffsVar2.i(getIntegerValue(ffsVar.o()));
        }
        if (ffsVar.n() != -1.0f) {
            ffsVar2.b(getFloatValue(ffsVar.n()));
        }
        if (ffsVar.l() != -1.0f) {
            ffsVar2.g(getIntegerValue(ffsVar.l()));
        }
        if (ffsVar.t() != -1.0f) {
            ffsVar2.e(getFloatValue(ffsVar.t()));
        }
        if (ffsVar.r() != -1.0f) {
            ffsVar2.c(getFloatValue(ffsVar.r()));
        }
        if (ffsVar.m() != -1.0f) {
            ffsVar2.a(getFloatValue(ffsVar.m()));
        }
        if (ffsVar.f() != -1.0f) {
            ffsVar2.d(getFloatValue(ffsVar.f()));
        }
        updateFootParams(ffsVar, ffsVar2);
        LogUtil.a(TAG, "updateRunningPosture done", ffsVar2.toString());
    }

    private void updateFootParams(ffs ffsVar, ffs ffsVar2) {
        if (ffsVar.a() != -101) {
            ffsVar2.c(getIntegerValue(ffsVar.a(), -101.0f));
        }
        if (ffsVar.d() != -1) {
            ffsVar2.b(getIntegerValue(ffsVar.d()));
        }
        if (ffsVar.g() != -1) {
            ffsVar2.e(ffsVar.g());
        }
        if (ffsVar.b() != -1) {
            ffsVar2.a(getIntegerValue(ffsVar.b()));
        }
        if (ffsVar.e() != -1) {
            ffsVar2.d(getIntegerValue(ffsVar.e()));
        }
        if (ffsVar.c() != -1) {
            ffsVar2.e(getIntegerValue(ffsVar.c()));
        }
        if (ffsVar.i() != -1) {
            ffsVar2.h(getIntegerValue(ffsVar.i()));
        }
        if (ffsVar.h() != -1) {
            ffsVar2.f(getIntegerValue(ffsVar.h()));
        }
        if (ffsVar.k() != -1) {
            ffsVar2.j(getIntegerValue(ffsVar.k()));
        }
        refreshVerticalRatio(ffsVar2);
    }

    private void refreshVerticalRatio(ffs ffsVar) {
        int k = ffsVar.k();
        float t = ffsVar.t();
        if (k <= 0 || t <= 0.0f) {
            return;
        }
        ffsVar.c(getFloatValue(new BigDecimal((t / k) * 100.0f).setScale(1, 1).floatValue()));
    }

    private int getIntegerValue(int i) {
        return getIntegerValue(i, -1.0f);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStopSport() {
        this.mRunningPosture = null;
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected String getLogTag() {
        return TAG;
    }
}
