package defpackage;

import android.os.Bundle;
import android.os.Message;
import com.huawei.haf.handler.BaseHandlerCallback;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter;
import com.huawei.healthcloud.plugintrack.model.IRunningPostureCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class gwl {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f12971a = new Object();
    private PluginSportTrackAdapter b;
    private ExtendHandler e;
    private boolean d = false;
    private int o = 0;
    private int j = 0;
    private ffs f = new ffs();
    private CopyOnWriteArrayList<ffs> h = new CopyOnWriteArrayList<>();
    private ArrayList<ffs> g = new ArrayList<>(16);
    private long i = 0;
    private CopyOnWriteArrayList<ffs> c = new CopyOnWriteArrayList<>();

    public gwl(PluginSportTrackAdapter pluginSportTrackAdapter) {
        this.b = null;
        this.b = pluginSportTrackAdapter;
    }

    public void c(int i) {
        this.j = i;
    }

    public void a(int i) {
        this.o = i;
    }

    public void a(PluginSportTrackAdapter pluginSportTrackAdapter) {
        this.b = pluginSportTrackAdapter;
    }

    public CopyOnWriteArrayList<ffs> e() {
        if (this.c.size() >= 12) {
            return this.c;
        }
        return null;
    }

    public void b() {
        if (gwg.i(this.o) && gwg.h(this.j)) {
            LogUtil.a("Track_RunningPostureUtils", "registerRunningPosture begin");
            if (this.b == null) {
                LogUtil.b("Track_RunningPostureUtils", "registerRunningPosture mPluginTrackAdapter is null!");
                return;
            }
            d();
            this.b.regRunningPostureListener(new IRunningPostureCallback() { // from class: gwl.5
                @Override // com.huawei.healthcloud.plugintrack.model.IRunningPostureCallback
                public void onResult(int i) {
                }

                @Override // com.huawei.healthcloud.plugintrack.model.IRunningPostureCallback
                public void onChange(ffs ffsVar) {
                    if (ffsVar != null) {
                        gwl.this.a(ffsVar);
                        LogUtil.a("Track_RunningPostureUtils", "runningPosture is ", ffsVar.toString());
                    } else {
                        LogUtil.h("Track_RunningPostureUtils", "runningPosture is null");
                    }
                }
            });
            this.d = true;
            return;
        }
        LogUtil.a("Track_RunningPostureUtils", "registerRunningPosture not supportRunningPosture");
    }

    public void a(List<ffs> list) {
        LogUtil.a("Track_RunningPostureUtils", "recoveryRunningPostureList");
        this.h.clear();
        if (koq.b(list)) {
            return;
        }
        this.h.addAll(list);
    }

    private void d() {
        LogUtil.a("Track_RunningPostureUtils", "startHandlerThread enter");
        if (this.e == null) {
            this.e = HandlerCenter.yt_(new d(this), "RUNNING_HANDLER_THREAD");
        }
    }

    private void h() {
        LogUtil.a("Track_RunningPostureUtils", "stopHandlerThread enter");
        synchronized (f12971a) {
            ExtendHandler extendHandler = this.e;
            if (extendHandler != null) {
                extendHandler.removeTasksAndMessages();
                this.e.quit(false);
                this.e = null;
            }
        }
    }

    public void a() {
        PluginSportTrackAdapter pluginSportTrackAdapter;
        if (this.d) {
            if (gwg.i(this.o) && (pluginSportTrackAdapter = this.b) != null) {
                pluginSportTrackAdapter.unregRunningPostureListener();
            }
            this.d = false;
            h();
        }
    }

    public static boolean c() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "Track_RunningPostureUtils");
        if (deviceInfo == null) {
            LogUtil.h("Track_RunningPostureUtils", "device info null");
            return false;
        }
        return cwi.c(deviceInfo, 175);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(ffs ffsVar) {
        if (!this.h.contains(ffsVar)) {
            this.h.add(ffsVar);
            LogUtil.a("Track_RunningPostureUtils", "updateRunningPostureData runningPosture.toString() =  ", ffsVar.toString());
        }
        if (!this.g.contains(ffsVar)) {
            this.g.add(ffsVar);
        }
        if (this.c.size() >= 12) {
            this.c.remove(0);
            this.c.add(ffsVar);
        } else {
            this.c.add(ffsVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ffs ffsVar) {
        if (this.e == null) {
            LogUtil.b("Track_RunningPostureUtils", "sendRunningPostureToThread mExtendHandler == null");
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 7;
        obtain.obj = ffsVar;
        this.e.sendMessage(obtain);
    }

    public void c(MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify == null) {
            LogUtil.h("Track_RunningPostureUtils", "saveCadenceDta motionPathSimplify is null");
            return;
        }
        if (koq.b(this.h)) {
            return;
        }
        Bundle awQ_ = fft.awQ_(this.h);
        aUZ_(motionPathSimplify, awQ_);
        float f = awQ_.getFloat("averageverticalimpactrate", -1.0f);
        if (f > 0.0f) {
            motionPathSimplify.addExtendDataMap("avg_v_i_r", String.valueOf(f));
        }
        float f2 = awQ_.getFloat("averageimpactpeak", -1.0f);
        if (f2 > 0.0f) {
            motionPathSimplify.addExtendDataMap("avg_i_p", String.valueOf(f2));
        }
        float f3 = awQ_.getFloat("averagegctimebalance", -1.0f);
        if (f3 > 0.0f) {
            motionPathSimplify.addExtendDataMap("avg_gc_tb", String.valueOf(f3));
        }
        float f4 = awQ_.getFloat("averageverticaloscillation", -1.0f);
        if (f4 > 0.0f) {
            motionPathSimplify.addExtendDataMap("avg_v_osc", String.valueOf(f4));
        }
        float f5 = awQ_.getFloat("averageverticalration", -1.0f);
        if (f5 > 0.0f) {
            motionPathSimplify.addExtendDataMap("avg_v_s_r", String.valueOf(f5));
        }
        if (f > 0.0f || f3 > 0.0f || f4 > 0.0f) {
            motionPathSimplify.addExtendDataMap("bolt_connected_flag", "2");
        }
        LogUtil.a("Track_RunningPostureUtils", "saveRunningPostureData verticalImpactRate = ", Float.valueOf(f), " avgImpactPeak = ", Float.valueOf(f2), " gcTimeBalance = ", Float.valueOf(f3), " verticalOscillation = ", Float.valueOf(f4), "avgVerStrikeRatio = ", Float.valueOf(f5), " motionPathSimplify.getExtendDataFloat = ", Float.valueOf(motionPathSimplify.getExtendDataFloat("bolt_connected_flag")));
    }

    private void aUZ_(MotionPathSimplify motionPathSimplify, Bundle bundle) {
        motionPathSimplify.saveAverageHangTime(bundle.getInt("averageHangTime", 0));
        motionPathSimplify.saveGroundHangTimeRate(bundle.getFloat("groundHangTimeRate", 0.0f));
        motionPathSimplify.saveAvgGroundContactTime(bundle.getInt("avgGroundContactTime", 0));
        motionPathSimplify.saveAvgGroundImpactAcceleration(bundle.getInt("avgGroundImpactAcceleration", 0));
        motionPathSimplify.saveAvgSwingAngle(bundle.getInt("avgSwingAngle", 0));
        motionPathSimplify.saveAvgEversionExcursion(bundle.getInt("avgEversionExcursion", -101));
        motionPathSimplify.saveAvgForeFootStrikePattern(bundle.getInt("foreFootStrikePatternPercentage", 0));
        motionPathSimplify.saveAvgWholeFootStrikePattern(bundle.getInt("wholeFootStrikePatternPercentage", 0));
        motionPathSimplify.saveAvgHindFootStrikePattern(bundle.getInt("hindFootStrikePatternPercentage", 0));
    }

    public ArrayList<ffs> d(boolean z) {
        ArrayList<ffs> arrayList = new ArrayList<>(16);
        if (this.g.size() > 0) {
            arrayList.addAll(this.g);
            if (z) {
                this.g.clear();
            }
        }
        return arrayList;
    }

    public Bundle aVc_(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (this.f == null || (this.i != 0 && TimeUnit.MILLISECONDS.toSeconds(gtx.c(BaseApplication.getContext()).getSportDuration()) - this.i > 20)) {
            LogUtil.h("Track_RunningPostureUtils", "putReportData reportData is null or long time no update");
            return bundle;
        }
        LogUtil.c("Track_RunningPostureUtils", "putRunningPostureData mRunningPostureTemp " + this.f);
        double o = ((double) this.f.o()) / 100.0d;
        bundle.putString("flightRatio", o > 0.0d ? jed.b(o, 1, 1) : "");
        aUY_(bundle);
        float t = this.f.t();
        bundle.putString("verticalOscillation", t > 0.0f ? jed.b(t, 1, 1) : "");
        if (this.f.k() > 0 && t > 0.0f) {
            bundle.putString("mVerticalRatio", jed.b((t / r2) * 100.0f, 1, 1));
        }
        return bundle;
    }

    private void aUY_(Bundle bundle) {
        bundle.putString("groundContactTime", this.f.b() > 0 ? String.valueOf(this.f.b()) : "");
        bundle.putString("mGroundImpactAcceleration", this.f.e() > 0 ? String.valueOf(this.f.e()) : "");
        bundle.putString("swingAngle", this.f.i() > 0 ? String.valueOf(this.f.i()) : "");
        bundle.putString("eversion", this.f.a() == -101 ? "" : String.valueOf(this.f.a()));
        bundle.putString("hangTime", this.f.l() > 0 ? String.valueOf(this.f.l()) : "");
        bundle.putString("verticalLoadingRate", this.f.f() > 0.0f ? jed.b(this.f.f(), 1, 1) : "");
        bundle.putString("activePeak", this.f.m() > 0.0f ? String.valueOf(this.f.m()) : "");
        bundle.putString("GCTimeBalance", this.f.n() > 0.0f ? jed.b(this.f.n(), 1, 1) : "");
    }

    public Bundle aVb_(Bundle bundle) {
        if (this.f == null || (this.i != 0 && TimeUnit.MILLISECONDS.toSeconds(gtx.c(BaseApplication.getContext()).getSportDuration()) - this.i > 20)) {
            LogUtil.h("Track_RunningPostureUtils", "putReportData reportData is null or long time no update");
            return bundle;
        }
        aVa_(bundle, this.f);
        return bundle;
    }

    public static void aVa_(Bundle bundle, ffs ffsVar) {
        if (bundle == null || ffsVar == null) {
            return;
        }
        int b = ffsVar.b();
        int e = ffsVar.e();
        int i = ffsVar.i();
        int a2 = ffsVar.a();
        int l = ffsVar.l();
        int d2 = ffsVar.d();
        int h = ffsVar.h();
        int c = ffsVar.c();
        double o = ffsVar.o() / 100.0d;
        float f = ffsVar.f();
        float m = ffsVar.m();
        float n = ffsVar.n();
        float t = ffsVar.t();
        bundle.putInt("groundContactTime", b);
        bundle.putInt("mGroundImpactAcceleration", e);
        bundle.putInt("swingAngle", i);
        bundle.putInt("eversion", a2);
        bundle.putInt("hangTime", l);
        bundle.putDouble("flightRatio", o > 0.0d ? CommonUtil.a(jed.b(o, 1, 1)) : 0.0d);
        bundle.putInt("foreSteps", d2);
        bundle.putInt("wholeSteps", h);
        bundle.putInt("hindSteps", c);
        bundle.putFloat("verticalLoadingRate", f);
        bundle.putFloat("activePeak", m);
        bundle.putFloat("GCTimeBalance", n);
        bundle.putFloat("verticalOscillation", t);
        int k = ffsVar.k();
        bundle.putInt("stride", k);
        if (k <= 0 || t <= 0.0f) {
            return;
        }
        bundle.putFloat("mVerticalRatio", new BigDecimal((t / k) * 100.0f).setScale(1, 1).floatValue());
    }

    static class d extends BaseHandlerCallback<gwl> {
        private float d(float f, boolean z) {
            if (f != 0.0f || z) {
                return f;
            }
            return -1.0f;
        }

        private int e(int i, boolean z, float f) {
            return (i != 0 || z) ? i : (int) f;
        }

        d(gwl gwlVar) {
            super(gwlVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandlerCallback
        /* renamed from: aVd_, reason: merged with bridge method [inline-methods] */
        public boolean handleMessageWhenReferenceNotNull(gwl gwlVar, Message message) {
            if (message.what != 7) {
                return false;
            }
            if (message.obj instanceof ffs) {
                gwlVar.f = (ffs) message.obj;
                LogUtil.a("Track_RunningPostureUtils", " handleMessage MSG_UPDATE_RUNNING_POSTURE utils.mRunningPostureTemp = ", gwlVar.f);
                if (gwlVar.f.s()) {
                    return true;
                }
                ffs ffsVar = new ffs();
                ffsVar.e(TimeUnit.MILLISECONDS.toSeconds(gtx.c(BaseApplication.getContext()).getSportDuration()));
                b(ffsVar, gwlVar.f, false);
                gwlVar.c(ffsVar);
                gwlVar.i = gwlVar.f.g();
            }
            return true;
        }

        private void b(ffs ffsVar, ffs ffsVar2, boolean z) {
            if (ffsVar == null || ffsVar2 == null) {
                return;
            }
            if (ffsVar2.t() >= 0.0f) {
                ffsVar.e(d(ffsVar2.t(), z));
            }
            if (ffsVar2.n() >= 0.0f) {
                ffsVar.b(d(ffsVar2.n(), z));
            }
            if (ffsVar2.f() >= 0.0f) {
                ffsVar.d(d(ffsVar2.f(), z));
            }
            if (ffsVar2.m() >= 0.0f) {
                ffsVar.a(d(ffsVar2.m(), z));
            }
            if (ffsVar2.k() >= 0) {
                ffsVar.j(e(ffsVar2.k(), z));
            }
            if (ffsVar2.l() >= 0) {
                ffsVar.g(e(ffsVar2.l(), z));
            }
            if (ffsVar2.o() >= 0) {
                ffsVar.i(e(ffsVar2.o(), z));
            }
            d(ffsVar, z);
            if (ffsVar2.a() != -101) {
                ffsVar.c(e(ffsVar2.a(), z, -101.0f));
            }
            c(ffsVar, ffsVar2, z);
            if (ffsVar2.b() >= 0) {
                ffsVar.a(e(ffsVar2.b(), z));
            }
            if (ffsVar2.e() >= 0) {
                ffsVar.d(e(ffsVar2.e(), z));
            }
            if (ffsVar2.i() >= 0) {
                ffsVar.h(e(ffsVar2.i(), z));
            }
        }

        private void c(ffs ffsVar, ffs ffsVar2, boolean z) {
            if (ffsVar2.d() >= 0) {
                ffsVar.b(e(ffsVar2.d(), z));
            }
            if (ffsVar2.h() >= 0) {
                ffsVar.f(e(ffsVar2.h(), z));
            }
            if (ffsVar2.c() >= 0) {
                ffsVar.e(e(ffsVar2.c(), z));
            }
        }

        private void d(ffs ffsVar, boolean z) {
            int k = ffsVar.k();
            float t = ffsVar.t();
            LogUtil.a("Track_RunningPostureUtils", " updateRunningPostureTemp stepLength = ", Integer.valueOf(k), " oscillation = ", Float.valueOf(t));
            if (k <= 0 || t < 0.0f) {
                return;
            }
            float floatValue = new BigDecimal((t / k) * 100.0f).setScale(1, 1).floatValue();
            ffsVar.c(d(floatValue, z));
            LogUtil.a("Track_RunningPostureUtils", " updateRunningPostureTemp averageVerticalRatio hasSave = ", Float.valueOf(floatValue));
        }

        private int e(int i, boolean z) {
            return e(i, z, -1.0f);
        }
    }
}
