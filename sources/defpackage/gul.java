package defpackage;

import com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter;
import com.huawei.healthcloud.plugintrack.manager.inteface.IStepRateUpdater;
import com.huawei.healthcloud.plugintrack.model.IRealStepCallback;
import com.huawei.hwfoundationmodel.trackmodel.StepRateData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.LogAnonymous;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes4.dex */
public class gul {
    private StepRateData b;
    private StepRateData e;
    private PluginSportTrackAdapter j;
    private IStepRateUpdater o;
    private List<StepRateData> f = new CopyOnWriteArrayList();
    private List<StepRateData> k = new CopyOnWriteArrayList();
    private List<StepRateData> i = new CopyOnWriteArrayList();
    private List<StepRateData> l = new CopyOnWriteArrayList();
    private boolean d = false;
    private CopyOnWriteArrayList<Integer> g = new CopyOnWriteArrayList<>();

    /* renamed from: a, reason: collision with root package name */
    private boolean f12943a = false;
    private int m = 0;
    private int h = 0;
    private gvs c = new gvs();

    private int e(int i, int i2, long j) {
        if (j <= 0) {
            return 0;
        }
        float f = i / (j / 60000.0f);
        return f > ((float) i2) ? i2 : (int) f;
    }

    public gul(PluginSportTrackAdapter pluginSportTrackAdapter, IStepRateUpdater iStepRateUpdater) {
        this.j = null;
        this.o = null;
        if (pluginSportTrackAdapter == null || iStepRateUpdater == null) {
            LogUtil.h("Track_StepRateUtils", "StepRateUtils adapter or updater is null");
        } else {
            this.j = pluginSportTrackAdapter;
            this.o = iStepRateUpdater;
        }
    }

    public void b(PluginSportTrackAdapter pluginSportTrackAdapter) {
        if (pluginSportTrackAdapter == null) {
            LogUtil.h("Track_StepRateUtils", "setAdapter adapter is null");
        } else {
            this.j = pluginSportTrackAdapter;
        }
    }

    public void c(List<StepRateData> list) {
        this.f.clear();
        this.k.clear();
        if (list == null) {
            LogUtil.b("Track_StepRateUtils", "stepRateList is null");
        } else {
            this.f.addAll(list);
        }
    }

    public void e(List<StepRateData> list) {
        this.f.clear();
        this.k.clear();
        if (list == null) {
            LogUtil.b("Track_StepRateUtils", "stepRateList is null");
        } else {
            this.f.addAll(list);
            this.k.addAll(list);
        }
    }

    public void a() {
        this.g.clear();
        this.h = 0;
    }

    public int d(int i, int i2) {
        j(i);
        if (p()) {
            return e(i2);
        }
        return 0;
    }

    public int h() {
        return this.g.size();
    }

    public int a(int i) {
        if (h() < i + 1) {
            return 0;
        }
        return this.g.get(i).intValue();
    }

    private void j(int i) {
        if (p()) {
            this.g.remove(0);
        }
        this.g.add(Integer.valueOf(i));
    }

    private boolean p() {
        return this.g.size() >= 12;
    }

    private int e(int i) {
        Integer num = this.g.get(0);
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = this.g;
        if (num == copyOnWriteArrayList.get(copyOnWriteArrayList.size() - 1)) {
            this.f12943a = true;
            this.m = this.g.get(0).intValue();
            this.h = 0;
            return 0;
        }
        if (!this.f12943a) {
            CopyOnWriteArrayList<Integer> copyOnWriteArrayList2 = this.g;
            int intValue = (copyOnWriteArrayList2.get(copyOnWriteArrayList2.size() - 1).intValue() - this.g.get(0).intValue()) * i;
            if (intValue <= 250) {
                this.h = intValue;
            } else {
                this.h = 0;
            }
        } else if (this.g.get(0).intValue() != this.m) {
            this.f12943a = false;
        }
        return this.h;
    }

    public void b(int i) {
        f(i);
    }

    public void o() {
        d(true);
        s();
    }

    public void k() {
        d(false);
    }

    public void l() {
        r();
        q();
    }

    public void e(boolean z) {
        this.d = z;
    }

    public void d(int i) {
        this.j.setStepType(i);
    }

    private void f(int i) {
        PluginSportTrackAdapter pluginSportTrackAdapter = this.j;
        if (pluginSportTrackAdapter == null) {
            LogUtil.b("Track_StepRateUtils", "registerRealStepListener mPluginTrackAdapter is null!");
        } else {
            pluginSportTrackAdapter.registerRealStepListener(new IRealStepCallback() { // from class: gul.4
                @Override // com.huawei.healthcloud.plugintrack.model.IRealStepCallback
                public void onChange(int i2, long j, long j2) {
                    if (gul.this.f == null) {
                        LogUtil.h("Track_StepRateUtils", "The mStepRateList is null");
                        gul.this.f = new CopyOnWriteArrayList();
                    }
                    StepRateData stepRateData = new StepRateData(j, i2);
                    LogUtil.a("Track_StepRateUtils", "registerRealStepListener onChange time : ", Long.valueOf(j), Constants.LINK, Long.valueOf(j2), " step : ", Integer.valueOf(i2));
                    if (j2 - j >= 60000 && hab.g()) {
                        hac.a().e(i2);
                    }
                    if (!gul.this.d) {
                        if (gul.this.o != null) {
                            gul.this.o.updateStepRate(stepRateData);
                        }
                        gul.this.f.add(stepRateData);
                        gul.this.k.add(stepRateData);
                        LogUtil.a("Track_StepRateUtils", "stepRatelist size:", Integer.valueOf(gul.this.f.size()));
                        return;
                    }
                    LogUtil.a("Track_StepRateUtils", "stop auto track, do not need steps");
                }
            }, 60L, i);
        }
    }

    private void r() {
        PluginSportTrackAdapter pluginSportTrackAdapter = this.j;
        if (pluginSportTrackAdapter == null) {
            LogUtil.b("Track_StepRateUtils", "unregisterRealStepListener mPluginTrackAdapter is null!");
        } else {
            pluginSportTrackAdapter.unRegisterRealStepCallback();
        }
    }

    private void d(boolean z) {
        PluginSportTrackAdapter pluginSportTrackAdapter = this.j;
        if (pluginSportTrackAdapter == null) {
            LogUtil.b("Track_StepRateUtils", "pauseRealStepTick mPluginTrackAdapter is null!");
        } else {
            pluginSportTrackAdapter.pauseOrResumeStepRateRecord(z);
        }
    }

    public ArrayList<StepRateData> i() {
        ArrayList<StepRateData> arrayList = new ArrayList<>(16);
        arrayList.addAll(this.f);
        return arrayList;
    }

    public void d() {
        this.i.clear();
        this.l.clear();
        this.i.addAll(this.f);
        this.l.addAll(this.k);
    }

    public ArrayList<StepRateData> a(boolean z) {
        ArrayList<StepRateData> arrayList = new ArrayList<>(16);
        if (this.k.size() > 0) {
            arrayList.addAll(this.k);
            if (z) {
                this.k.clear();
            }
        }
        return arrayList;
    }

    public ArrayList<StepRateData> c(boolean z) {
        ArrayList<StepRateData> arrayList = new ArrayList<>(16);
        if (this.l.size() > 0) {
            arrayList.addAll(this.l);
            if (z) {
                this.l.clear();
            }
        }
        return arrayList;
    }

    public int a(long j) {
        return e(n(), j(), j);
    }

    public int e(long j) {
        return e(m(), f(), j);
    }

    public int j() {
        return a(this.f);
    }

    public int f() {
        return a(this.i);
    }

    private int a(List<StepRateData> list) {
        int i = 0;
        if (koq.c(list)) {
            for (StepRateData stepRateData : list) {
                if (stepRateData != null && stepRateData.acquireStepRate() > i) {
                    i = stepRateData.acquireStepRate();
                }
            }
        }
        return i;
    }

    public int n() {
        return d(this.f);
    }

    public int m() {
        return d(this.i);
    }

    private int d(List<StepRateData> list) {
        int i = 0;
        if (koq.c(list)) {
            for (StepRateData stepRateData : list) {
                if (stepRateData != null) {
                    i += stepRateData.acquireStepRate();
                }
            }
            return i;
        }
        LogUtil.a("Track_StepRateUtils", "getTotalSteps = ", LogAnonymous.b(0));
        return 0;
    }

    public void e(StepRateData stepRateData) {
        if (stepRateData == null || this.f.size() <= 0) {
            return;
        }
        if (stepRateData.acquireTime() > this.f.get(r2.size() - 1).acquireTime()) {
            this.f.add(stepRateData);
            this.k.add(stepRateData);
        }
    }

    public void d(StepRateData stepRateData) {
        if (stepRateData == null || this.i.size() <= 0) {
            return;
        }
        if (stepRateData.acquireTime() > this.i.get(r2.size() - 1).acquireTime()) {
            this.i.add(stepRateData);
            this.l.add(stepRateData);
        }
    }

    private void s() {
        PluginSportTrackAdapter pluginSportTrackAdapter = this.j;
        if (pluginSportTrackAdapter == null) {
            LogUtil.b("Track_StepRateUtils", "getCurrentStep() mPluginTrackAdapter is null!");
        } else {
            pluginSportTrackAdapter.reportCurrentStepCallback(new IRealStepCallback() { // from class: gul.1
                @Override // com.huawei.healthcloud.plugintrack.model.IRealStepCallback
                public void onChange(int i, long j, long j2) {
                    StepRateData stepRateData = new StepRateData(j, i);
                    LogUtil.a("Track_StepRateUtils", "CurrentStep: ", Integer.valueOf(i), " time : ", Long.valueOf(j), Constants.LINK, Long.valueOf(j2));
                    gul.this.e = stepRateData;
                }
            });
        }
    }

    public void e(final CountDownLatch countDownLatch) {
        PluginSportTrackAdapter pluginSportTrackAdapter = this.j;
        if (pluginSportTrackAdapter == null) {
            LogUtil.b("Track_StepRateUtils", "getTargetCurrentStep() mPluginTrackAdapter is null!");
        } else {
            pluginSportTrackAdapter.reportCurrentStepCallback(new IRealStepCallback() { // from class: gul.3
                @Override // com.huawei.healthcloud.plugintrack.model.IRealStepCallback
                public void onChange(int i, long j, long j2) {
                    StepRateData stepRateData = new StepRateData(j, i);
                    LogUtil.a("Track_StepRateUtils", "TargetCurrentStep: ", Integer.valueOf(i), " time : ", Long.valueOf(j), Constants.LINK, Long.valueOf(j2));
                    gul.this.b = stepRateData;
                    gul gulVar = gul.this;
                    gulVar.d(gulVar.b);
                    countDownLatch.countDown();
                }
            });
        }
    }

    private void q() {
        StepRateData stepRateData = this.e;
        if (stepRateData != null) {
            this.f.add(stepRateData);
            this.k.add(this.e);
        }
    }

    public int b() {
        StepRateData stepRateData = this.e;
        if (stepRateData == null) {
            return 0;
        }
        return stepRateData.acquireStepRate();
    }

    public void b(IStepRateUpdater iStepRateUpdater) {
        this.o = iStepRateUpdater;
    }

    public void e() {
        this.l.clear();
        this.i.clear();
        this.b = null;
    }

    public void c(int i) {
        this.c.a(i);
    }

    public int g() {
        return this.c.d();
    }

    public void c() {
        this.c.e();
    }
}
