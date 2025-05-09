package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.core.H5ProWebView;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.listener.IWebViewRefreshListener;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.sleep.SleepApi;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.h5pro.H5proUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementProvider;
import defpackage.bzs;
import defpackage.fck;
import defpackage.fda;
import defpackage.fdp;
import defpackage.nhj;
import defpackage.nru;
import defpackage.pob;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class SleepManagementProvider extends MinorProvider<fdp> {

    /* renamed from: a, reason: collision with root package name */
    private volatile SleepManagementCallback<Boolean> f9822a;
    private long b;
    private volatile SleepManagementCallback<fck> c;
    private volatile b d;
    private volatile long e;
    private final a f = new a(this);
    private final e g = new e(this);
    private volatile SleepManagementCallback<Integer> h;
    private c i;
    private volatile BroadcastReceiver j;
    private volatile SleepManagementCallback k;
    private final List<Integer> m;

    public SleepManagementProvider() {
        ArrayList arrayList = new ArrayList();
        this.m = arrayList;
        this.b = 0L;
        this.e = 0L;
        this.d = new b();
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(10007);
        arrayList.add(9);
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.COURSE_RECORD.value()));
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.MINDFULNESS_TYPE.value()));
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.BREATH_TRAIN_SET.value()));
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        if (LoginInit.getInstance(context).isKidAccount()) {
            return false;
        }
        return VersionControlUtil.isShowSmartSleepImprovement();
    }

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        e();
        f();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, fdp fdpVar) {
        LogUtil.a("SleepManagementProvider", "parseParams");
        if (this.mData == 0) {
            LogUtil.h("SleepManagementProvider", "parseParams failed with null data.");
            return;
        }
        o();
        c();
        LogUtil.a("SleepManagementProvider", "parseParams curTime: ", Long.valueOf(this.d.c));
        if (this.i == null) {
            this.i = new c(this);
        }
        hashMap.put("LAYOUT_VIEW", this.i);
        hashMap.put("BAR_CHART_TIME_INT", Long.valueOf(this.d.c));
        nhj.c(fdpVar.l().get("OPEN_STATUS"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void o() {
        Object obj = ((fdp) this.mData).l().get("MGMT_DAILY_SLEEP_PROCESS");
        this.d.b(new Date(((fdp) this.mData).g()).getTime());
        this.d.b(((fdp) this.mData).m());
        if (obj instanceof fda) {
            LogUtil.b("SleepManagementProvider", "not sleep daily result");
            this.d.c((fda) obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void c() {
        if (!HandlerExecutor.c()) {
            HandlerExecutor.e(new Runnable() { // from class: poa
                @Override // java.lang.Runnable
                public final void run() {
                    SleepManagementProvider.this.c();
                }
            });
            return;
        }
        if (this.f9822a != null && this.d.e()) {
            this.d.i();
            ReleaseLogUtil.e("SleepManagementProvider", " notify sleep data isValid:", Boolean.valueOf(this.d.c()), " curTime: ", Long.valueOf(this.d.c));
            this.f9822a.onSuccess(Boolean.valueOf(this.d.c()));
        }
        if (this.c == null || !this.d.a()) {
            return;
        }
        this.d.b();
        final fda d = this.d.d();
        if (d == null) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: poe
            @Override // java.lang.Runnable
            public final void run() {
                SleepManagementProvider.this.a(d);
            }
        });
    }

    public /* synthetic */ void a(fda fdaVar) {
        fck fckVar = new fck();
        fckVar.b(fdaVar.s());
        fckVar.c(pob.b(fdaVar, fdaVar.o()));
        ReleaseLogUtil.e("SleepManagementProvider", " notify sleep daily result update:", Boolean.valueOf(fckVar.e()), " curTime: ", Long.valueOf(this.d.c));
        this.c.onSuccess(fckVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void c(H5ProWebView h5ProWebView) {
        Object[] objArr = new Object[4];
        objArr[0] = "loadH5proMiniProgram: ";
        objArr[1] = h5ProWebView;
        objArr[2] = ", instance: ";
        objArr[3] = h5ProWebView != null ? h5ProWebView.h : Constants.NULL;
        LogUtil.a("SleepManagementProvider", objArr);
        bzs e2 = bzs.e();
        this.e = new Date(((fdp) this.mData).g()).getTime();
        long currentTimeMillis = System.currentTimeMillis();
        H5proUtil.initH5pro();
        int d = nru.d((Map) ((fdp) this.mData).l(), "from", -1);
        LogUtil.a("SleepManagementProvider", "initH5pro cost: ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), ", from: ", Integer.valueOf(d));
        H5ProClient.startH5MiniProgram("com.huawei.health.h5.sleep-management", h5ProWebView, new H5ProLaunchOption.Builder().addPath("#/sleepScheme").addCustomizeArg("curTime", Long.toString(this.e)).addCustomizeArg("from", String.valueOf(d)).setForceDarkMode(1).enableImageCache().addCustomizeJsModule("innerapi", e2.getCommonJsModule("innerapi")).addCustomizeJsModule("SleepManagementApi", ((SleepApi) Services.c("Main", SleepApi.class)).getSleepManagementH5Bridge()).build());
    }

    public static class c implements IWebViewRefreshListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<SleepManagementProvider> f9825a;

        public c(SleepManagementProvider sleepManagementProvider) {
            this.f9825a = new WeakReference<>(sleepManagementProvider);
        }

        @Override // com.huawei.health.knit.section.listener.IWebViewRefreshListener
        public void notifyWebViewLoad(H5ProWebView h5ProWebView) {
            if (this.f9825a.get() == null) {
                return;
            }
            LogUtil.a("SleepManagementProvider", "notifyWebViewLoad: ");
            this.f9825a.get().c(h5ProWebView);
        }

        @Override // com.huawei.health.knit.section.listener.IWebViewRefreshListener
        public void notifyDataChanged(long j) {
            if (this.f9825a.get() == null) {
                return;
            }
            LogUtil.a("SleepManagementProvider", "notifyDataChanged", Long.valueOf(j));
            this.f9825a.get().e(j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(long j) {
        if (this.e == j) {
            LogUtil.b("SleepManagementProvider", "time is not changed");
            return;
        }
        LogUtil.a("SleepManagementProvider", "notifyDataChanged curTime: ", Long.valueOf(j));
        this.e = j;
        if (this.k != null) {
            this.k.onSuccess(Long.valueOf(j));
        }
    }

    private void f() {
        ObserverManagerUtil.d(new Observer() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementProvider.5
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                if (objArr == null || objArr.length != 1) {
                    return;
                }
                Object obj = objArr[0];
                if (obj instanceof SleepManagementCallback) {
                    SleepManagementProvider.this.k = (SleepManagementCallback) obj;
                }
            }
        }, "SLEEP_MANAGEMENT_TIME_TAG");
    }

    private void h() {
        ReleaseLogUtil.e("SleepManagementProvider", "registerH5DataValidObserver().");
        ObserverManagerUtil.d(new Observer() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementProvider.1
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                ReleaseLogUtil.e("SleepManagementProvider", "registerH5DataValidObserver().  registerObserver");
                if (objArr == null || objArr.length != 1) {
                    return;
                }
                Object obj = objArr[0];
                if (obj instanceof SleepManagementCallback) {
                    SleepManagementProvider.this.f9822a = (SleepManagementCallback) obj;
                    SleepManagementProvider.this.c();
                }
            }
        }, "SLEEP_MANAGEMENT_DATA_VALID_TAG");
    }

    private void d() {
        ReleaseLogUtil.e("SleepManagementProvider", "registerH5DailyResultObserver().");
        ObserverManagerUtil.d(new Observer() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementProvider.2
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                ReleaseLogUtil.e("SleepManagementProvider", "registerH5DailyResultObserver().  registerObserver");
                if (objArr == null || objArr.length != 1) {
                    return;
                }
                Object obj = objArr[0];
                if (obj instanceof SleepManagementCallback) {
                    SleepManagementProvider.this.c = (SleepManagementCallback) obj;
                    SleepManagementProvider.this.c();
                }
            }
        }, "SLEEP_MANAGEMENT_DAILY_RESULT_TAG");
    }

    private void k() {
        ReleaseLogUtil.e("SleepManagementProvider", "unregisterH5TimeObserver().");
        ObserverManagerUtil.e("SLEEP_MANAGEMENT_TIME_TAG");
    }

    private void l() {
        ReleaseLogUtil.e("SleepManagementProvider", "unregisterH5DataValidObserver().");
        ObserverManagerUtil.e("SLEEP_MANAGEMENT_DATA_VALID_TAG");
    }

    private void m() {
        ReleaseLogUtil.e("SleepManagementProvider", "unregisterH5DailyResultObserver().");
        ObserverManagerUtil.e("SLEEP_MANAGEMENT_DAILY_RESULT_TAG");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final SleepManagementCallback<Integer> sleepManagementCallback) {
        HandlerExecutor.e(new Runnable() { // from class: pod
            @Override // java.lang.Runnable
            public final void run() {
                SleepManagementProvider.this.b(sleepManagementCallback);
            }
        });
    }

    public /* synthetic */ void b(SleepManagementCallback sleepManagementCallback) {
        if (this.h == null) {
            this.h = sleepManagementCallback;
            g();
            e((SleepManagementCallback<Boolean>) null);
            return;
        }
        this.h = sleepManagementCallback;
    }

    private void g() {
        ReleaseLogUtil.e("SleepManagementProvider", "registerSyncResultBroadcast() mSyncStatusReceiver = ", this.j);
        if (this.j != null) {
            return;
        }
        this.j = new BroadcastReceiver() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementProvider.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent == null) {
                    ReleaseLogUtil.d("SleepManagementProvider", "mSyncStatusReceiver receiver intent is null.");
                    return;
                }
                if (!"com.huawei.hihealth.action_sync_data_result".equals(intent.getAction())) {
                    ReleaseLogUtil.d("SleepManagementProvider", "mSyncStatusReceiver receiver intent.action is error.");
                    return;
                }
                SleepManagementProvider.this.e(intent.getLongExtra("sync_data_result_id", 0L), intent.getBooleanExtra("sync_data_result_success", true), intent.getStringExtra("sync_data_result_type"));
            }
        };
        BroadcastManagerUtil.bFE_(BaseApplication.e(), this.j, new IntentFilter("com.huawei.hihealth.action_sync_data_result"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(long j, boolean z, String str) {
        ReleaseLogUtil.e("SleepManagementProvider", "mSyncStatusReceiver syncId:", Long.valueOf(j), " mLastSyncId:", Long.valueOf(this.b), " syncResult:", Boolean.valueOf(z), " syncType:", str);
        long j2 = this.b;
        if (j2 != 0) {
            if (j2 == j) {
                this.b = 0L;
                c(z);
                return;
            }
            return;
        }
        if (this.m.contains(Integer.valueOf(CommonUtil.h(str))) || CommonUtil.h(str) == 20000) {
            c(z);
        }
    }

    private void n() {
        ReleaseLogUtil.e("SleepManagementProvider", "unregisterSyncResultBroadcast(). mSyncStatusReceiver:", this.j);
        if (this.j != null) {
            BroadcastManagerUtil.bFK_(BaseApplication.e(), this.j);
            this.j = null;
        }
    }

    private void q() {
        ReleaseLogUtil.e("SleepManagementProvider", "updateSyncStatusStart().");
        if (this.h != null) {
            this.h.onSuccess(1);
        }
    }

    private void c(boolean z) {
        Object[] objArr = new Object[4];
        objArr[0] = "updateSyncStatusResult(). isSuccess = ";
        objArr[1] = Boolean.valueOf(z);
        objArr[2] = " mSyncDataStatusCallback:";
        objArr[3] = Boolean.valueOf(this.h == null);
        ReleaseLogUtil.e("SleepManagementProvider", objArr);
        if (this.h == null) {
            return;
        }
        if (z) {
            this.h.onSuccess(2);
        } else {
            this.h.onFailure(-1, "Sync failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(SleepManagementCallback<Boolean> sleepManagementCallback) {
        if (sleepManagementCallback != null) {
            sleepManagementCallback.onSuccess(true);
        }
        j();
        ObserverManagerUtil.c("INTEL_SECTION_LOADED", new Object[0]);
    }

    private void j() {
        long j = this.b;
        if (j != 0) {
            ReleaseLogUtil.e("SleepManagementProvider", "Last sync process has not finish. mLastSyncId:", Long.valueOf(j));
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long a2 = a();
        long currentTimeMillis2 = System.currentTimeMillis();
        long e2 = nhj.e();
        if (currentTimeMillis - a2 < 180000 || currentTimeMillis2 - e2 < 120000) {
            LogUtil.h("SleepManagementProvider", "Less than the number of minutes since the last sync, given by the condition");
            return;
        }
        LogUtil.a("SleepManagementProvider", "syncCloud start");
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataTypes(this.m);
        hiSyncOption.setForceSync(true);
        long currentTimeMillis3 = System.currentTimeMillis();
        this.b = currentTimeMillis3;
        hiSyncOption.setSyncId(currentTimeMillis3);
        hiSyncOption.setSyncMethod(2);
        HiHealthNativeApi.a(BaseApplication.e()).synCloud(hiSyncOption, null);
        q();
        c(this.b);
        ReleaseLogUtil.e("SleepManagementProvider", " syncCloud start:", Long.valueOf(this.b));
    }

    private void c(long j) {
        SharedPreferenceManager.e(BaseApplication.e(), String.valueOf(PrebakedEffectId.RT_COIN_DROP), "sleep_management_last_sync_time", String.valueOf(j), (StorageParams) null);
    }

    private long a() {
        return CommonUtil.n(BaseApplication.e(), SharedPreferenceManager.b(BaseApplication.e(), String.valueOf(PrebakedEffectId.RT_COIN_DROP), "sleep_management_last_sync_time"));
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        ReleaseLogUtil.e("SleepManagementProvider", "onDestroy()");
        super.onDestroy();
        i();
        n();
        k();
    }

    private void e() {
        ReleaseLogUtil.e("SleepManagementProvider", "registerH5CmdObserver().");
        ObserverManagerUtil.d(this.f, "registerSyncStatus");
        ObserverManagerUtil.d(this.g, "startSleepAllDataSync");
        d();
        h();
    }

    private void i() {
        ReleaseLogUtil.e("SleepManagementProvider", "unregisterH5CmdObserver().");
        ObserverManagerUtil.e("registerSyncStatus");
        ObserverManagerUtil.e("startSleepAllDataSync");
        l();
        m();
    }

    static class a implements Observer {
        private WeakReference<SleepManagementProvider> e;

        public a(SleepManagementProvider sleepManagementProvider) {
            this.e = new WeakReference<>(sleepManagementProvider);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            SleepManagementProvider sleepManagementProvider = this.e.get();
            if (sleepManagementProvider == null) {
                ReleaseLogUtil.e("SleepManagementProvider", "ReceiveSyncStatusObserver notify failed with SleepManagementProvider null.");
                return;
            }
            ReleaseLogUtil.e("SleepManagementProvider", "ReceiveSyncStatusObserver notify with args.", objArr);
            if (objArr == null || objArr.length == 0) {
                return;
            }
            Object obj = objArr[0];
            if (obj instanceof SleepManagementCallback) {
                sleepManagementProvider.a((SleepManagementCallback<Integer>) obj);
            }
        }
    }

    static class e implements Observer {
        private WeakReference<SleepManagementProvider> d;

        public e(SleepManagementProvider sleepManagementProvider) {
            this.d = new WeakReference<>(sleepManagementProvider);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            SleepManagementProvider sleepManagementProvider = this.d.get();
            if (sleepManagementProvider == null) {
                ReleaseLogUtil.e("SleepManagementProvider", "StartAllSyncObserver notify failed with SleepManagementProvider null.");
                return;
            }
            ReleaseLogUtil.e("SleepManagementProvider", "StartAllSyncObserver notify with args.", objArr);
            if (objArr == null || objArr.length == 0) {
                return;
            }
            Object obj = objArr[0];
            if (obj instanceof SleepManagementCallback) {
                sleepManagementProvider.e((SleepManagementCallback<Boolean>) obj);
            }
        }
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private boolean f9824a;
        private boolean b;
        private long c;
        private fda d;
        private boolean e;
        private boolean i;

        private b() {
            this.c = 0L;
            this.i = false;
            this.b = false;
            this.f9824a = false;
            this.d = null;
            this.e = false;
        }

        public void b(long j) {
            if (j != this.c) {
                this.c = j;
                this.i = true;
                this.b = false;
                this.f9824a = true;
                this.e = false;
                this.d = null;
            }
        }

        public boolean c() {
            return this.b;
        }

        public void b(boolean z) {
            if (z != this.b) {
                this.b = z;
                this.f9824a = true;
            }
        }

        public boolean e() {
            return this.f9824a;
        }

        public void i() {
            this.f9824a = false;
        }

        public fda d() {
            return this.d;
        }

        public void c(fda fdaVar) {
            if (fdaVar != this.d) {
                this.d = fdaVar;
                this.e = true;
            }
        }

        public boolean a() {
            return this.e;
        }

        public void b() {
            this.e = false;
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "SleepManagementProvider";
    }
}
