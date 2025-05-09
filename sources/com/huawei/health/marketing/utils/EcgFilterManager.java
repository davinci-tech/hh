package com.huawei.health.marketing.utils;

import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.util.Pair;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.marketing.utils.EcgFilterManager;
import com.huawei.health.section.section.MeasureCardView;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import defpackage.cun;
import defpackage.cvs;
import defpackage.cwi;
import defpackage.dqo;
import defpackage.drl;
import defpackage.eid;
import defpackage.eig;
import defpackage.eii;
import defpackage.eiu;
import defpackage.fbp;
import defpackage.ixx;
import defpackage.jah;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public final class EcgFilterManager {

    /* renamed from: a, reason: collision with root package name */
    private Pair<String, WeakReference<View>> f2816a;
    private double b;
    private int c;
    private a d;
    private final EcgDataInquirer e;
    private boolean f;
    private List<String> g;
    private EcgMode h;
    private Pair<String, WeakReference<View>> i;
    private Pair<String, WeakReference<View>> j;
    private boolean k;
    private final EcgDataInquirer l;
    private boolean m;
    private final EcgDataInquirer o;

    enum EcgMode {
        NONE,
        COLLECTION_CARD_DIAGRAM_ICON,
        COLLECTION_CARD_ONLY,
        DIAGRAM_CARD_COLLECTION_ICON,
        DIAGRAM_CARD_ONLY
    }

    static final class e {
        private static final EcgFilterManager d = new EcgFilterManager();
    }

    public static EcgFilterManager a() {
        return e.d;
    }

    private void ab() {
        if (this.d.e) {
            ThreadPoolManager.d().a(this.d);
        }
        this.d.a();
        ThreadPoolManager.d().execute(this.d);
    }

    class a implements Runnable {
        private volatile boolean b;
        CountDownLatch c;
        private volatile boolean d;
        volatile boolean e;
        private volatile boolean h;

        private a() {
            this.e = false;
            this.c = new CountDownLatch(3);
        }

        public void a() {
            this.e = false;
            this.c = new CountDownLatch(3);
            this.b = false;
            this.d = false;
            this.h = false;
        }

        public void d() {
            if (this.b) {
                return;
            }
            this.c.countDown();
            this.b = true;
        }

        public void c() {
            if (this.d) {
                return;
            }
            this.c.countDown();
            this.d = true;
        }

        public void b() {
            if (this.h) {
                return;
            }
            this.c.countDown();
            this.h = true;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.e = true;
            try {
                if (this.c.await(5000L, TimeUnit.MILLISECONDS)) {
                    e();
                }
            } catch (InterruptedException e) {
                LogUtil.b("EcgFilterManager", "reportCardTypeBi failed, cause exception = ", e.getMessage(), ", cause = ", e.getCause());
            }
            this.e = false;
        }

        private void e() {
            HashMap hashMap = new HashMap();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            fbp measureCardBean = EcgFilterManager.this.l.getMeasureCardBean();
            if (measureCardBean.h()) {
                if (measureCardBean.g()) {
                    arrayList.add(1);
                    arrayList2.add(Boolean.valueOf(measureCardBean.b() != null));
                } else {
                    arrayList.add(0);
                    arrayList2.add(Boolean.valueOf(measureCardBean.d() != null));
                }
            }
            fbp measureCardBean2 = EcgFilterManager.this.e.getMeasureCardBean();
            if (measureCardBean2.h()) {
                arrayList.add(3);
                arrayList2.add(Boolean.valueOf(measureCardBean2.d() != null));
            }
            fbp measureCardBean3 = EcgFilterManager.this.o.getMeasureCardBean();
            if (measureCardBean3.h()) {
                arrayList.add(2);
                arrayList2.add(Boolean.valueOf(measureCardBean3.d() != null));
            }
            hashMap.put("click", 1);
            hashMap.put("card_type", arrayList);
            hashMap.put("banner_status", arrayList2);
            hashMap.put("more_app", EcgFilterManager.this.g);
            ixx.d().d(BaseApplication.e(), "2190016", hashMap, 0);
        }
    }

    public void o() {
        LogUtil.a("EcgFilterManager", "start queryEcgDataAsync");
        m();
        p();
        this.l.querySummary();
        this.e.querySummary();
        this.o.querySummary();
        ab();
    }

    public void g() {
        this.l.clearAndRefreshUI();
        this.e.clearAndRefreshUI();
        this.o.clearAndRefreshUI();
    }

    private boolean b(String str) {
        return !TextUtils.isEmpty(str) && str.contains("?");
    }

    public String alD_(View view, String str, String str2) {
        if (view == null || TextUtils.isEmpty(str)) {
            return str;
        }
        if (!this.g.contains(str2)) {
            this.g.add(str2);
        }
        if (d(str)) {
            this.j = new Pair<>(str2, new WeakReference(view));
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(b(str) ? "?from=2" : "&from=2");
            String sb2 = sb.toString();
            e(this.j, true);
            return sb2;
        }
        if (c(str)) {
            Pair<String, WeakReference<View>> pair = new Pair<>(str2, new WeakReference(view));
            this.i = pair;
            e(pair, true);
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str);
            sb3.append(b(str) ? "?from=2" : "&from=2");
            return sb3.toString();
        }
        if (e(str)) {
            Pair<String, WeakReference<View>> pair2 = new Pair<>(str2, new WeakReference(view));
            this.f2816a = pair2;
            e(pair2, true);
            return str;
        }
        if (!g(str)) {
            return str;
        }
        StringBuilder sb4 = new StringBuilder();
        sb4.append(str);
        sb4.append(b(str) ? "?from=2" : "&from=2");
        return sb4.toString();
    }

    private void e(Pair<String, WeakReference<View>> pair, boolean z) {
        View view;
        if (pair.second == null || (view = pair.second.get()) == null) {
            return;
        }
        if (z && view.getVisibility() == 8) {
            return;
        }
        if (z || view.getVisibility() != 0) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (z) {
                view.setTag(Integer.valueOf(layoutParams.width));
                layoutParams.width = 0;
                view.setVisibility(8);
                view.post(new Runnable() { // from class: eib
                    @Override // java.lang.Runnable
                    public final void run() {
                        EcgFilterManager.this.s();
                    }
                });
                this.g.remove(pair.first);
            } else {
                if (view.getTag() instanceof Integer) {
                    layoutParams.width = ((Integer) view.getTag()).intValue();
                } else {
                    LogUtil.b("EcgFilterManager", "handleEcgIconVisibility failed, cause icon tag is ", view.getTag());
                }
                view.setVisibility(0);
                if (!this.g.contains(pair.first)) {
                    this.g.add(pair.first);
                }
            }
            view.setLayoutParams(layoutParams);
        }
    }

    private boolean d(String str) {
        return (str.contains("com.huawei.health.ecg.collection") || str.contains("com.huawei.ecgh5") || str.contains("com.huawei.health.h5.ecg")) && !c(str);
    }

    private boolean c(String str) {
        return str.contains("com.huawei.health.h5.ecgce");
    }

    private boolean e(String str) {
        return str.contains("com.huawei.health.h5.ppg");
    }

    private boolean g(String str) {
        return str.contains("com.huawei.health.h5.vascular-health");
    }

    public void d(final MeasureCardView measureCardView, final int i, final boolean z) {
        if (!Looper.getMainLooper().isCurrentThread()) {
            HandlerExecutor.a(new Runnable() { // from class: eia
                @Override // java.lang.Runnable
                public final void run() {
                    EcgFilterManager.this.a(measureCardView, i, z);
                }
            });
        } else {
            a(measureCardView, i, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        boolean z = true;
        e(this.j, this.h != EcgMode.DIAGRAM_CARD_COLLECTION_ICON || EnvironmentInfo.k());
        e(this.i, this.h != EcgMode.COLLECTION_CARD_DIAGRAM_ICON || EnvironmentInfo.k());
        Pair<String, WeakReference<View>> pair = this.f2816a;
        if (e() && a("support_arrhythmia") && !EnvironmentInfo.k()) {
            z = false;
        }
        e(pair, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void a(MeasureCardView measureCardView, int i, boolean z) {
        if ((i & 1) != 0) {
            d(measureCardView, z);
            s();
            this.d.d();
        }
        if ((i & 4) != 0) {
            d(measureCardView);
            this.d.c();
        }
        if ((i & 16) != 0) {
            a(measureCardView);
            this.d.b();
        }
        if (measureCardView != null) {
            if (this.c == 0 || EnvironmentInfo.k()) {
                measureCardView.b();
            } else {
                measureCardView.a();
            }
        }
    }

    public void a(final MeasureCardView measureCardView, final int i) {
        if (!Looper.getMainLooper().isCurrentThread()) {
            HandlerExecutor.a(new Runnable() { // from class: eif
                @Override // java.lang.Runnable
                public final void run() {
                    EcgFilterManager.this.b(measureCardView, i);
                }
            });
        } else {
            b(measureCardView, i);
        }
    }

    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void b(MeasureCardView measureCardView, int i) {
        if (measureCardView == null) {
            LogUtil.h("EcgFilterManager", "handleEcgNormalChartInternal failed cause measureCardView is null!");
            ReleaseLogUtil.d("EcgFilterManager", "handleEcgNormalChartInternal failed cause measureCardView is null!");
            return;
        }
        if ((i & 2) != 0) {
            measureCardView.e(this.l.getMeasureCardBean());
        }
        if ((i & 8) != 0) {
            measureCardView.e(this.e.getMeasureCardBean());
        }
    }

    private void a(MeasureCardView measureCardView) {
        if (measureCardView == null) {
            LogUtil.h("EcgFilterManager", "refreshVascularDesc failed cause measureCardView is null!");
            ReleaseLogUtil.d("EcgFilterManager", "refreshVascularDesc failed cause measureCardView is null!");
            return;
        }
        if (this.o.isQuerying()) {
            LogUtil.h("EcgFilterManager", "refreshVascularDesc failed while quering");
            ReleaseLogUtil.d("EcgFilterManager", "refreshVascularDesc failed while quering");
            return;
        }
        fbp measureCardBean = this.o.getMeasureCardBean();
        measureCardBean.a("com.huawei.health.h5.vascular-health", Constants.H5PRO_PAGE_PREFIX, "?from=1&support=1");
        if (!CommonUtil.aq() && !CommonUtil.cc() && (!b() || !a("support_vascular"))) {
            LogUtil.b("EcgFilterManager", "refreshVascularDesc failed, cause isSupportVascular is false!");
            measureCardBean.c(false);
            this.c &= 6;
        } else {
            measureCardBean.c(true);
            this.c |= -7;
        }
        if (EnvironmentInfo.k()) {
            measureCardBean.c(false);
        }
        measureCardView.c(measureCardBean);
    }

    private void d(MeasureCardView measureCardView) {
        if (measureCardView == null) {
            LogUtil.h("EcgFilterManager", "refreshEcgArrhythmiaDesc failed cause measureCardView is null!");
            ReleaseLogUtil.d("EcgFilterManager", "refreshEcgArrhythmiaDesc failed cause measureCardView is null!");
            return;
        }
        if (this.e.isQuerying()) {
            LogUtil.h("EcgFilterManager", "refreshEcgArrhythmiaDesc failed while quering");
            ReleaseLogUtil.d("EcgFilterManager", "refreshEcgArrhythmiaDesc failed while quering");
            return;
        }
        fbp measureCardBean = this.e.getMeasureCardBean();
        measureCardBean.a("com.huawei.health.h5.ppg", Constants.H5PRO_PAGE_PREFIX, "?from=1&support=1");
        if (!CommonUtil.aq() && !CommonUtil.cc() && (!e() || !a("support_arrhythmia"))) {
            LogUtil.b("EcgFilterManager", "refreshEcgArrhythmiaDesc failed, cause isSupportEcgArrhythmia is false!");
            measureCardBean.c(false);
            this.c &= 5;
        } else {
            measureCardBean.c(true);
            this.c |= -6;
        }
        if (EnvironmentInfo.k()) {
            measureCardBean.c(false);
        }
        measureCardView.c(measureCardBean);
    }

    private void d(MeasureCardView measureCardView, boolean z) {
        String str;
        boolean z2;
        if (measureCardView == null) {
            LogUtil.h("EcgFilterManager", "refreshEcgNormalDesc failed cause measureCardView is null!");
            ReleaseLogUtil.d("EcgFilterManager", "refreshEcgNormalDesc failed cause measureCardView is null!");
            return;
        }
        if (this.l.isQuerying()) {
            LogUtil.h("EcgFilterManager", "refreshEcgNormalDesc failed while quering");
            ReleaseLogUtil.d("EcgFilterManager", "refreshEcgNormalDesc failed while quering");
            return;
        }
        EcgMode d = d(z);
        this.h = d;
        boolean z3 = true;
        if (d == EcgMode.DIAGRAM_CARD_ONLY || this.h == EcgMode.DIAGRAM_CARD_COLLECTION_ICON) {
            LogUtil.a("EcgFilterManager", "configMeasureCard shouldShowEcgDiagramInTwoGrid");
            str = "com.huawei.health.h5.ecgce";
            z2 = true;
        } else if (this.h == EcgMode.COLLECTION_CARD_ONLY || this.h == EcgMode.COLLECTION_CARD_DIAGRAM_ICON) {
            LogUtil.a("EcgFilterManager", "configMeasureCard shouldShowEcgCollectionInTwoGrid");
            str = "com.huawei.health.h5.ecg";
            z2 = false;
        } else {
            LogUtil.b("EcgFilterManager", "configMeasureCard failed, hide measure card");
            str = null;
            z2 = false;
            z3 = false;
        }
        boolean z4 = EnvironmentInfo.k() ? false : z3;
        fbp measureCardBean = this.l.getMeasureCardBean();
        measureCardBean.a(str, Constants.H5PRO_PAGE_PREFIX, "?from=1&support=1").c(z4).d(z2);
        if (!z4) {
            this.c &= 3;
        } else {
            this.c |= -4;
        }
        measureCardView.c(measureCardBean);
    }

    private Map r() {
        dqo a2 = drl.a("com.huawei.health_deviceFeature_config", "txt", "com.huawei.health.h5.ecgce");
        if (a2 == null) {
            ReleaseLogUtil.d("EcgFilterManager", "getExtInfo featureConfig is null");
            return null;
        }
        return a2.a();
    }

    private void m() {
        this.m = eii.e("CE");
        this.k = eii.e("PMDA");
        l();
    }

    private void l() {
        Map r = r();
        if (r == null) {
            return;
        }
        Object obj = r.get("dia");
        if (obj instanceof Double) {
            this.b = ((Double) obj).doubleValue();
        }
    }

    public boolean f() {
        return this.m;
    }

    public boolean k() {
        return this.k;
    }

    public boolean h() {
        return Double.compare(this.b, 1.0d) == 0;
    }

    private void p() {
        Map r = r();
        if (r == null) {
            return;
        }
        Object obj = r.get("prematureBeats");
        if (obj instanceof Double) {
            this.f = ((Double) obj).doubleValue() > 0.0d;
        } else {
            this.f = false;
        }
    }

    public boolean j() {
        return this.f;
    }

    private boolean v() {
        boolean z = false;
        if (!w()) {
            LogUtil.b("EcgFilterManager", "hasEcgCollectionDevice isDeviceConnected false!");
            return false;
        }
        DeviceCapability d = cvs.d();
        if (d != null && d.isSupportEcgAuth()) {
            z = true;
        }
        LogUtil.a("EcgFilterManager", "hasEcgCollectionDevice: ", Boolean.valueOf(z));
        return z;
    }

    public boolean i() {
        DeviceInfo t = t();
        boolean z = d(t) && cwi.c(t, 106);
        LogUtil.a("EcgFilterManager", "hasEcgDiagramDevice: ", Boolean.valueOf(z));
        return z;
    }

    private DeviceInfo t() {
        return cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "EcgFilterManager");
    }

    private boolean w() {
        return d(t());
    }

    private boolean d(DeviceInfo deviceInfo) {
        return deviceInfo != null && deviceInfo.getDeviceConnectState() == 2;
    }

    private static boolean h(String str) {
        return drl.c("com.huawei.health_deviceFeature_config", "txt", str);
    }

    public static boolean d() {
        return h("com.huawei.health.h5.ecgce") || eii.e();
    }

    public static boolean b() {
        return h("com.huawei.health.h5.vascular-health");
    }

    public static boolean e() {
        return h("com.huawei.health.h5.ppg") || c();
    }

    public static boolean c() {
        dqo a2 = drl.a("com.huawei.health_deviceFeature_config", "txt", "com.huawei.health.h5.ppg");
        if (a2 == null || a2.a() == null) {
            LogUtil.b("EcgFilterManager", "isSupportPpg featureConfig or getExtInfo() is null");
            return false;
        }
        Map a3 = a2.a();
        if (a3 == null) {
            LogUtil.b("EcgFilterManager", "isSupportPpg extInfo is null");
            return false;
        }
        Object obj = a3.get("support_ppg");
        LogUtil.a("EcgFilterManager", "isSupportPpg supportPpg:", obj);
        return (obj instanceof Double) && ((Double) obj).doubleValue() > 0.0d;
    }

    public boolean n() {
        return a("support_ecg_diagram");
    }

    private boolean a(String str) {
        String e2 = jah.c().e(str);
        boolean equals = "true".equals(e2);
        boolean bv = CommonUtil.bv();
        boolean bu = CommonUtil.bu();
        ReleaseLogUtil.e("EcgFilterManager", "isSupportConfig supportKey = ", str, ", supportValue = ", e2, ", isReleaseVersion = ", Boolean.valueOf(bv), ", isDemoVersion = ", Boolean.valueOf(bu));
        return !(bv || bu) || equals;
    }

    private EcgFilterManager() {
        this.j = new Pair<>(null, null);
        this.i = new Pair<>(null, null);
        this.f2816a = new Pair<>(null, null);
        this.g = new ArrayList();
        this.d = new a();
        this.c = 7;
        this.f = false;
        this.m = false;
        this.k = false;
        this.l = new eig(new int[]{31002, DicDataTypeUtil.DataType.ELECTROCARDIOGRAM.value()});
        this.e = new eid(new int[]{DicDataTypeUtil.DataType.ARRHYTHMIA_PPG_TYPE.value(), DicDataTypeUtil.DataType.PPG_IRREGULAR_HEARTBEAT.value()});
        this.o = new eiu(new int[]{DicDataTypeUtil.DataType.PULSE_WAVE_VELOCITY.value()});
    }

    private boolean aa() {
        return (i() && q()) || (!v() && x() && q());
    }

    private boolean ac() {
        return (i() && !q()) || !(i() || v() || q());
    }

    private boolean u() {
        return !i() && v() && x();
    }

    private boolean y() {
        return (i() || x() || (!v() && !q())) ? false : true;
    }

    private boolean x() {
        fbp measureCardBean = this.l.getMeasureCardBean();
        return (measureCardBean == null || measureCardBean.b() == null) ? false : true;
    }

    private boolean q() {
        fbp measureCardBean = this.l.getMeasureCardBean();
        return (measureCardBean == null || measureCardBean.d() == null) ? false : true;
    }

    private EcgMode d(boolean z) {
        LogUtil.a("EcgFilterManager", "start to checkEcgMode");
        if (!Utils.o()) {
            if (Utils.f() || !z) {
                return EcgMode.DIAGRAM_CARD_COLLECTION_ICON;
            }
            if (!a("support_ecg_diagram")) {
                return EcgMode.COLLECTION_CARD_ONLY;
            }
        } else if (!a("support_ecg_diagram") && Utils.o()) {
            return EcgMode.NONE;
        }
        EcgMode ecgMode = EcgMode.NONE;
        if (aa()) {
            LogUtil.a("EcgFilterManager", "checkEcgMode DIAGRAM_CARD_COLLECTION_ICON");
            ecgMode = EcgMode.DIAGRAM_CARD_COLLECTION_ICON;
        }
        if (y()) {
            LogUtil.a("EcgFilterManager", "checkEcgMode COLLECTION_CARD_ONLY");
            ecgMode = EcgMode.COLLECTION_CARD_ONLY;
        }
        if (u()) {
            LogUtil.a("EcgFilterManager", "checkEcgMode COLLECTION_CARD_DIAGRAM_ICON");
            ecgMode = EcgMode.COLLECTION_CARD_DIAGRAM_ICON;
        }
        if (ac()) {
            LogUtil.a("EcgFilterManager", "checkEcgMode DIAGRAM_CARD_ONLY");
            ecgMode = EcgMode.DIAGRAM_CARD_ONLY;
        }
        if (Utils.o()) {
            if (ecgMode == EcgMode.COLLECTION_CARD_ONLY) {
                LogUtil.a("EcgFilterManager", "checkEcgMode is oversea, COLLECTION_CARD_ONLY to NONE");
                ecgMode = EcgMode.NONE;
            }
            if (ecgMode == EcgMode.COLLECTION_CARD_DIAGRAM_ICON) {
                LogUtil.a("EcgFilterManager", "checkEcgMode is oversea, COLLECTION_CARD_DIAGRAM_ICON to DIAGRAM_CARD_ONLY");
                ecgMode = EcgMode.DIAGRAM_CARD_ONLY;
            }
        }
        if ((ecgMode != EcgMode.DIAGRAM_CARD_COLLECTION_ICON && ecgMode != EcgMode.DIAGRAM_CARD_ONLY) || CommonUtil.aq() || CommonUtil.cc()) {
            return ecgMode;
        }
        if (d() && a("support_ecg_diagram")) {
            return ecgMode;
        }
        if (Utils.o()) {
            return EcgMode.NONE;
        }
        return EcgMode.COLLECTION_CARD_ONLY;
    }
}
