package com.huawei.hihealthservice;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import com.huawei.haf.application.BroadcastManager;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.IBinderInterceptor;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hihealthservice.db.helper.HiHealthDBHelper;
import com.huawei.hihealthservice.hihealthkit.HiHealthKitBinder;
import com.huawei.hihealthservice.hihealthkit.HiHealthKitCommonOhosBinder;
import com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder;
import com.huawei.hihealthservice.hihealthkit.HiHealthKitOhosBinder;
import com.huawei.hihealthservice.hihealthkit.WearKitBinder;
import com.huawei.hwauthutil.HsfSignValidator;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwlogsmodel.common.LogConfig;
import com.huawei.login.ui.login.HealthAccessTokenUtil;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.wearkit.IWearBinderInterceptor;
import defpackage.ifp;
import defpackage.ifx;
import defpackage.ini;
import defpackage.inv;
import defpackage.iqr;
import defpackage.iqw;
import defpackage.ird;
import defpackage.ism;
import defpackage.iuz;
import defpackage.ivd;
import defpackage.ivr;
import defpackage.iwe;
import defpackage.iwu;
import health.compact.a.ApplicationLazyLoad;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.BuildConfigProperties;
import health.compact.a.CommonUtil;
import health.compact.a.DfxMonitorCenter;
import health.compact.a.HiDateUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.LogAllowListConfig;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.ParseException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/* loaded from: classes.dex */
public class HiHealthService extends Service {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f4146a = new Object();
    private static final Object d = new Object();
    private static final Object b = new Object();
    private static final Object e = new Object();
    private ifx i = null;
    private HiHealthKitCommonOhosBinder n = null;
    private HiHealthKitBinder f = null;
    private HiHealthKitExtendBinder m = null;
    private HiHealthKitOhosBinder o = null;
    private WearKitBinder r = null;
    private IBinderInterceptor.Stub c = null;
    private IWearBinderInterceptor.Stub s = null;
    private a j = null;
    private BroadcastReceiver p = new BroadcastReceiver() { // from class: com.huawei.hihealthservice.HiHealthService.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("com.huawei.health.action.DATA_DICTIONARY_SHOULD_RELOAD".equals(intent.getAction())) {
                HiHealthDictManager.d(BaseApplication.getContext()).e(true);
            }
        }
    };
    private BroadcastReceiver l = new BroadcastReceiver() { // from class: com.huawei.hihealthservice.HiHealthService.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("com.huawei.plugin.account.logout".equals(action)) {
                LogUtil.a("HiH_HiHealthService", "logout");
                inv.c(BaseApplication.getContext()).a();
            } else if ("com.huawei.plugin.account.login".equals(action)) {
                LogUtil.a("HiH_HiHealthService", "login");
                inv.c(BaseApplication.getContext()).e();
            }
        }
    };
    private BroadcastReceiver g = new AnonymousClass5();
    private BroadcastReceiver k = new BroadcastReceiver() { // from class: com.huawei.hihealthservice.HiHealthService.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("HiH_HiHealthService", "mLogReceiver onReceive intent is null");
                return;
            }
            String action = intent.getAction();
            if ("com.huawei.health.action.ACTION_OVERSEA_BETA_LOG".equals(action)) {
                ReleaseLogUtil.e("HiH_HiHealthService", "begin to switch beta log from oversea to domestic");
                LogAllowListConfig.e(!Boolean.parseBoolean(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(2039), "beta_log_switch")));
                LogUtil.h();
            }
            if ("com.huawei.health.action.ACTION_BETA_DEBUG_LOG".equals(action)) {
                ReleaseLogUtil.e("HiH_HiHealthService", "begin to open debug level for beta log");
                LogConfig.c(Boolean.parseBoolean(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(Constants.START_TO_INDOOR_RUNNING), "beta_debug_log_switch")));
                LogUtil.a("HiH_HiHealthService", "begin to set log level, current minimum logLevel is debug: true, BUILD_TYPE: ", BuildConfigProperties.b(), ", LOG_LEVEL: ", Integer.valueOf(health.compact.a.LogConfig.e()));
            }
        }
    };
    private BroadcastReceiver h = new BroadcastReceiver() { // from class: com.huawei.hihealthservice.HiHealthService.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("HiH_HiHealthService", "mDeviceStatusReceiver onReceive intent is null");
                return;
            }
            try {
                if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                    ReleaseLogUtil.e("HiH_HiHealthService", "device action state has changed.");
                    inv.c(BaseApplication.getContext()).bBR_(intent);
                }
            } catch (RuntimeException e2) {
                ReleaseLogUtil.c("HiH_HiHealthService", "mDeviceStatusReceiver onReceive error", LogAnonymous.b((Throwable) e2));
            }
        }
    };
    private BroadcastReceiver t = new BroadcastReceiver() { // from class: com.huawei.hihealthservice.HiHealthService.8
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("HiH_HiHealthService", "mProductMapUpdateReceiver onReceive intent is null");
            } else if ("com.huawei.health.action.PRODUCT_MAP_UPDATE".equals(intent.getAction())) {
                ReleaseLogUtil.e("HiH_HiHealthService", "product_map has updated,reload product_map config");
                ProductMapParseUtil.b(BaseApplication.getContext());
            }
        }
    };

    /* renamed from: com.huawei.hihealthservice.HiHealthService$5, reason: invalid class name */
    public class AnonymousClass5 extends BroadcastReceiver {
        AnonymousClass5() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean booleanExtra = intent.getBooleanExtra("isForeground", true);
            boolean z = System.currentTimeMillis() - iwe.c(context, "WAL_CHECKPOINT_TIME", 0, 0L) >= 60000;
            if (booleanExtra || !z || iuz.f() || ism.i()) {
                return;
            }
            iwe.e(context, "WAL_CHECKPOINT_TIME", System.currentTimeMillis(), 0);
            ThreadPoolManager.d().execute(new Runnable() { // from class: ifz
                @Override // java.lang.Runnable
                public final void run() {
                    HiHealthDBHelper.a().c();
                }
            });
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        ApplicationLazyLoad.b();
        ReleaseLogUtil.e("HiH_HiHealthService", "onCreate()");
        a();
        Context applicationContext = getApplicationContext();
        if (CommonUtil.bu()) {
            if (e(applicationContext)) {
                LogUtil.c("HiH_HiHealthService", "need delete DB file");
                if (!applicationContext.deleteDatabase("hihealth_003.db")) {
                    LogUtil.c("HiH_HiHealthService", "try delete again return ", Boolean.valueOf(applicationContext.deleteDatabase("hihealth_003.db")));
                }
            }
            d(applicationContext);
        }
        this.j = new a();
        HealthAccessTokenUtil.getAtInstance().registerReceiver();
        if (this.p != null) {
            BroadcastManagerUtil.bFD_(BaseApplication.getContext(), this.p, new IntentFilter("com.huawei.health.action.DATA_DICTIONARY_SHOULD_RELOAD"));
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.plugin.account.logout");
        if (SharedPreferenceUtil.getInstance(BaseApplication.getContext()).getIsLogined()) {
            inv.c(applicationContext).e();
        } else {
            LogUtil.a("HiH_HiHealthService", "app has not login, delayed registration.");
            intentFilter.addAction("com.huawei.plugin.account.login");
        }
        if (this.l != null) {
            BroadcastManagerUtil.bFD_(BaseApplication.getContext(), this.l, intentFilter);
        }
        if (this.k != null && LogConfig.d()) {
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction("com.huawei.health.action.ACTION_OVERSEA_BETA_LOG");
            intentFilter2.addAction("com.huawei.health.action.ACTION_BETA_DEBUG_LOG");
            BroadcastManagerUtil.bFA_(BaseApplication.getContext(), this.k, intentFilter2, LocalBroadcast.c, null);
        }
        BroadcastReceiver broadcastReceiver = this.g;
        if (broadcastReceiver != null) {
            BroadcastManager.wj_(broadcastReceiver);
        }
        if (this.h != null) {
            BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.h, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
        }
        if (this.t != null) {
            IntentFilter intentFilter3 = new IntentFilter();
            intentFilter3.addAction("com.huawei.health.action.PRODUCT_MAP_UPDATE");
            BroadcastManagerUtil.bFA_(BaseApplication.getContext(), this.t, intentFilter3, LocalBroadcast.c, null);
        }
        j();
    }

    private void a() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.hihealthservice.HiHealthService.6
            @Override // java.lang.Runnable
            public void run() {
                ifp.c().e();
            }
        });
    }

    private void j() {
        LogUtil.a("HiH_HiHealthService", "sendHiHealthServiceStartBroadcast");
        BroadcastManagerUtil.bFG_(BaseApplication.getContext(), new Intent("com.huawei.health.action.HIHEALTH_SERVICE_START"));
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Object[] objArr = new Object[2];
        objArr[0] = "onBind intent = ";
        objArr[1] = intent == null ? null : intent.getAction();
        ReleaseLogUtil.e("HiH_HiHealthService", objArr);
        if (intent != null) {
            if ("com.huawei.health.action.KIT_SERVICE".equals(intent.getAction()) || "com.huawei.health.action.KIT_OHOS".equals(intent.getAction()) || "com.huawei.health.action.KIT_COMMON_OHOS".equals(intent.getAction())) {
                if (this.c == null) {
                    this.c = new b(this);
                }
                return this.c;
            }
            if ("com.huawei.health.action.WEAR_KIT_SERVICE".equals(intent.getAction())) {
                if (this.s == null) {
                    this.s = new c(this);
                }
                return this.s;
            }
        }
        if (this.i == null) {
            DfxMonitorCenter.e();
            this.i = new ifx(getApplicationContext(), this.j);
            HiHealthDictManager.d(BaseApplication.getContext()).e(false);
            ivr.b();
            ivd.c().e(getApplicationContext());
        }
        return this.i;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        Object[] objArr = new Object[2];
        objArr[0] = "onUnBind intent = ";
        objArr[1] = intent == null ? null : intent.getAction();
        ReleaseLogUtil.e("HiH_HiHealthService", objArr);
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        LogUtil.a("HiH_HiHealthService", "onStartCommand() intent = ", intent, ",flags = ", Integer.valueOf(i), ",startId = ", Integer.valueOf(i2));
        stopSelf(i2);
        return 2;
    }

    @Override // android.app.Service
    public void onDestroy() {
        ReleaseLogUtil.e("HiH_HiHealthService", "onDestroy()");
        ifx ifxVar = this.i;
        if (ifxVar != null) {
            ifxVar.a();
        }
        a aVar = this.j;
        if (aVar != null) {
            aVar.c();
        }
        HiHealthKitBinder hiHealthKitBinder = this.f;
        if (hiHealthKitBinder != null) {
            hiHealthKitBinder.onDestroy();
        }
        HiHealthKitExtendBinder hiHealthKitExtendBinder = this.m;
        if (hiHealthKitExtendBinder != null) {
            hiHealthKitExtendBinder.onDestroy();
        }
        HiHealthKitOhosBinder hiHealthKitOhosBinder = this.o;
        if (hiHealthKitOhosBinder != null) {
            hiHealthKitOhosBinder.onDestroy();
        }
        HiHealthKitCommonOhosBinder hiHealthKitCommonOhosBinder = this.n;
        if (hiHealthKitCommonOhosBinder != null) {
            hiHealthKitCommonOhosBinder.onDestroy();
        }
        WearKitBinder wearKitBinder = this.r;
        if (wearKitBinder != null) {
            wearKitBinder.onDestroy();
        }
        if (this.p != null) {
            BaseApplication.getContext().unregisterReceiver(this.p);
        }
        if (this.l != null) {
            BaseApplication.getContext().unregisterReceiver(this.l);
        }
        if (this.k != null && LogConfig.d()) {
            BaseApplication.getContext().unregisterReceiver(this.k);
        }
        if (this.h != null) {
            BaseApplication.getContext().unregisterReceiver(this.h);
        }
        BroadcastReceiver broadcastReceiver = this.g;
        if (broadcastReceiver != null) {
            BroadcastManager.wx_(broadcastReceiver);
        }
        if (this.t != null) {
            BaseApplication.getContext().unregisterReceiver(this.t);
        }
        ism.f().q();
        HealthAccessTokenUtil.getAtInstance().unregisterReceiverToGetAt();
        super.onDestroy();
    }

    static class b extends IBinderInterceptor.Stub {
        private volatile HiHealthService d;

        public b(HiHealthService hiHealthService) {
            this.d = hiHealthService;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.huawei.hihealth.IBinderInterceptor
        public IBinder getServiceBinder(String str) {
            char c;
            if (str == null) {
                return bxa_();
            }
            str.hashCode();
            switch (str.hashCode()) {
                case -144001039:
                    if (str.equals("KIT_COMMON_OHOS_EXTEND")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 92424430:
                    if (str.equals("KIT_COMMON_OHOS_NATIVE")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 186135747:
                    if (str.equals("KIT_EXTEND")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 425056614:
                    if (str.equals("KIT_OHOS")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0 || c == 1) {
                return bxb_(str);
            }
            if (c == 2) {
                return bxc_();
            }
            if (c == 3) {
                return bxd_();
            }
            LogUtil.a("HiH_HiHealthService", "no such service");
            return null;
        }

        private IBinder bxd_() {
            int callingUid = Binder.getCallingUid();
            Context applicationContext = this.d.getApplicationContext();
            String nameForUid = applicationContext.getPackageManager().getNameForUid(callingUid);
            LogUtil.a("HiH_HiHealthService", "getServiceBinder uid:", Integer.valueOf(callingUid), " packageName:", nameForUid);
            ini.b(this.d, this.d.j);
            if (this.d.o == null) {
                synchronized (HiHealthService.f4146a) {
                    if (this.d.o == null) {
                        try {
                            this.d.o = new HiHealthKitOhosBinder(applicationContext, this.d.j);
                        } catch (iwu e) {
                            ReleaseLogUtil.c("HiH_HiHealthService", e.getMessage());
                            return null;
                        }
                    }
                }
            }
            try {
                if (HsfSignValidator.e(nameForUid)) {
                    return this.d.o;
                }
            } catch (Exception e2) {
                ReleaseLogUtil.c("HiH_HiHealthService", "getServiceBinder Exception", LogAnonymous.b((Throwable) e2));
            }
            return this.d.o;
        }

        private IBinder bxb_(String str) {
            int callingUid = Binder.getCallingUid();
            Context applicationContext = this.d.getApplicationContext();
            String nameForUid = applicationContext.getPackageManager().getNameForUid(callingUid);
            LogUtil.a("HiH_HiHealthService", "getServiceBinder uid:", Integer.valueOf(callingUid), " packageName:", nameForUid);
            ini.b(this.d, this.d.j);
            if (!"KIT_COMMON_OHOS_EXTEND".equals(str) || iqw.d(this.d) || iqw.c(this.d)) {
                if (this.d.n == null) {
                    synchronized (HiHealthService.b) {
                        if (this.d.n == null) {
                            try {
                                this.d.n = new HiHealthKitCommonOhosBinder(applicationContext, this.d.j);
                            } catch (iwu e) {
                                ReleaseLogUtil.c("HiH_HiHealthService", "mKitCommonOhosBinder() e = ", e.getMessage());
                            }
                        }
                    }
                }
                iqr.d(applicationContext, nameForUid);
                return this.d.n;
            }
            LogUtil.b("HiH_HiHealthService", "Illegal app engine.");
            return null;
        }

        private IBinder bxc_() {
            int callingUid = Binder.getCallingUid();
            Context applicationContext = this.d.getApplicationContext();
            String nameForUid = applicationContext.getPackageManager().getNameForUid(callingUid);
            LogUtil.a("HiH_HiHealthService", "getServiceBinder uid:", Integer.valueOf(callingUid), " packageName:", nameForUid);
            if (nameForUid != null && nameForUid.length() > 5) {
                ReleaseLogUtil.e("HiH_HiHealthService", " packageName:", nameForUid.substring(nameForUid.length() - 5));
            }
            if (!iqw.d(this.d) && !iqw.c(this.d) && !iqw.b(this.d)) {
                ReleaseLogUtil.c("HiH_HiHealthService", "Illegal caller, extendbinder is null");
                return null;
            }
            ReleaseLogUtil.e("HiH_HiHealthService", "verified caller success, return extendbinder");
            ini.b(this.d, this.d.j);
            if (this.d.m == null) {
                synchronized (HiHealthService.d) {
                    if (this.d.m == null) {
                        try {
                            this.d.m = new HiHealthKitExtendBinder(applicationContext, this.d.j);
                        } catch (iwu e) {
                            ReleaseLogUtil.c("HiH_HiHealthService", "getKitExtendBinder() e = ", e.getMessage());
                        }
                    }
                }
            }
            return this.d.m;
        }

        private IBinder bxa_() {
            int callingUid = Binder.getCallingUid();
            Context applicationContext = this.d.getApplicationContext();
            String nameForUid = applicationContext.getPackageManager().getNameForUid(callingUid);
            LogUtil.a("HiH_HiHealthService", "getServiceBinder uid:", Integer.valueOf(callingUid), " packageName:", nameForUid);
            if (nameForUid != null && nameForUid.length() > 5) {
                ReleaseLogUtil.e("HiH_HiHealthService", " packageName:", nameForUid.substring(nameForUid.length() - 5));
            }
            ird.d(applicationContext).d();
            ini.b(this.d, this.d.j);
            if (this.d.f == null) {
                synchronized (HiHealthService.f4146a) {
                    if (this.d.f == null) {
                        try {
                            this.d.f = new HiHealthKitBinder(applicationContext, this.d.j);
                        } catch (iwu e) {
                            ReleaseLogUtil.c("HiH_HiHealthService", e.getMessage());
                            return null;
                        }
                    }
                }
            }
            try {
                if (HsfSignValidator.e(nameForUid)) {
                    return this.d.f;
                }
            } catch (Exception e2) {
                ReleaseLogUtil.c("HiH_HiHealthService", "getServiceBinder Exception", LogAnonymous.b((Throwable) e2));
            }
            return this.d.f;
        }
    }

    static class c extends IWearBinderInterceptor.Stub {

        /* renamed from: a, reason: collision with root package name */
        private HiHealthService f4148a;

        c(HiHealthService hiHealthService) {
            this.f4148a = hiHealthService;
        }

        @Override // com.huawei.wearkit.IWearBinderInterceptor
        public IBinder getServiceBinder(String str) {
            WearKitBinder wearKitBinder;
            synchronized (HiHealthService.e) {
                Context applicationContext = this.f4148a.getApplicationContext();
                if (this.f4148a.r == null) {
                    this.f4148a.r = new WearKitBinder(applicationContext);
                }
                wearKitBinder = this.f4148a.r;
            }
            return wearKitBinder;
        }
    }

    private boolean e(Context context) {
        int c2 = HiDateUtil.c(System.currentTimeMillis());
        int i = context != null ? context.getSharedPreferences("demo_hihealth_config", 0).getInt("demo_last_open_time", 20140101) : 20140101;
        try {
        } catch (ParseException e2) {
            ReleaseLogUtil.c("HiH_HiHealthService", "needDelDBFile parse date fail, e=", e2.getMessage());
        }
        if (1 >= HiDateUtil.b(i, c2, "yyyyMMdd")) {
            if (1 >= HiDateUtil.b(c2, i, "yyyyMMdd")) {
                return false;
            }
        }
        return true;
    }

    private void d(Context context) {
        if (context != null) {
            context.getSharedPreferences("demo_hihealth_config", 0).edit().putInt("demo_last_open_time", HiDateUtil.c(System.currentTimeMillis())).apply();
        }
    }

    static class a implements InsertExecutor {
        private volatile ExecutorService c;

        a() {
        }

        private ExecutorService a() {
            if (this.c == null || this.c.isShutdown()) {
                synchronized (this) {
                    if (this.c == null || this.c.isShutdown()) {
                        this.c = Executors.newSingleThreadExecutor();
                    }
                }
            }
            return this.c;
        }

        @Override // com.huawei.hihealthservice.InsertExecutor, java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            if (runnable == null) {
                return;
            }
            a().execute(runnable);
        }

        @Override // com.huawei.hihealthservice.InsertExecutor
        public Future<?> submit(Runnable runnable) {
            runnable.getClass();
            FutureTask futureTask = new FutureTask(runnable, null);
            a().execute(futureTask);
            return futureTask;
        }

        void c() {
            if (this.c != null) {
                this.c.shutdown();
            }
        }
    }
}
