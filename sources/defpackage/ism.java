package defpackage;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0;
import com.huawei.haf.application.BroadcastManager;
import com.huawei.haf.common.utils.ScreenUtil;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.data.type.HiSyncType;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.util.HiBroadcastUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.sync.HiSyncService;
import com.huawei.hihealthservice.sync.syncdata.dictionary.detail.HiSyncDictionaryDataDetail;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.ThermalCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.AiCoreSdkConstant;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import defpackage.ism;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.LocalBroadcast;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public class ism {
    private static int g;
    private final BroadcastReceiver k;
    private Timer l;
    private final AtomicReference<AlarmManager> m;
    private final Object n;
    private final AtomicReference<Timer> p;
    private final Object q;
    private e r;
    private SharedPreferences s;
    private b t;
    private static AtomicBoolean e = new AtomicBoolean(false);

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13580a = new Object();
    private static boolean i = false;
    private static boolean c = false;
    private static boolean b = false;
    private static boolean f = false;
    private static boolean j = false;
    private static boolean h = false;
    private static Hashtable<Integer, List<Integer>> o = new Hashtable<>(16);
    private static final Context d = BaseApplication.getContext();

    private boolean p(int i2) {
        return i2 == 15 || i2 == 10005 || i2 == 10028;
    }

    /* synthetic */ ism(AnonymousClass1 anonymousClass1) {
        this();
    }

    /* renamed from: ism$1, reason: invalid class name */
    class AnonymousClass1 extends BroadcastReceiver {
        AnonymousClass1() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, Intent intent) {
            if (context == null || intent == null || !"com.huawei.hihealth.action_one_hour_sync".equals(intent.getAction())) {
                return;
            }
            ReleaseLogUtil.e("HiH_HiSyncControl", "startAlarmManager start autoSync");
            ThreadPoolManager.d().d("HiSyncControl_BroadcastSync", new Runnable() { // from class: isu
                @Override // java.lang.Runnable
                public final void run() {
                    ism.AnonymousClass1.this.a(context);
                }
            });
        }

        /* synthetic */ void a(Context context) {
            ism.this.d(context.getPackageName(), 20000, 5);
            ism.this.x();
        }
    }

    private ism() {
        AnonymousClass1 anonymousClass1 = null;
        this.m = new AtomicReference<>(null);
        this.p = new AtomicReference<>(null);
        this.n = new Object();
        this.q = new Object();
        this.k = new AnonymousClass1();
        this.s = d.getSharedPreferences("synSp", 0);
        ag();
        if (this.t == null) {
            b bVar = new b(this, anonymousClass1);
            this.t = bVar;
            BroadcastManager.wo_(bVar);
        }
    }

    /* loaded from: classes7.dex */
    static class d {
        private static final ism b = new ism(null);
    }

    public static ism f() {
        return d.b;
    }

    private static void ag() {
        g = 2;
        Utils.d(2);
        LogUtil.a("HiH_HiSyncControl", "initSyncModel current SyncModel is ", 2);
    }

    public static void d(boolean z) {
        j = z;
        Object obj = f13580a;
        synchronized (obj) {
            if (!z) {
                obj.notifyAll();
            }
        }
    }

    public static boolean i() {
        return j;
    }

    public static void a(int i2, int i3) {
        List<Integer> list = o.get(Integer.valueOf(i2));
        if (HiCommonUtil.d(list)) {
            list = new ArrayList<>(1);
        }
        list.add(Integer.valueOf(i3));
        o.put(Integer.valueOf(i2), list);
    }

    public static Hashtable<Integer, List<Integer>> g() {
        return new Hashtable<>(o);
    }

    public static void e(int i2) {
        o.remove(Integer.valueOf(i2));
    }

    public static void a(boolean z) {
        h = z;
    }

    public static boolean e() {
        return h;
    }

    public static void d() {
        Object obj = f13580a;
        synchronized (obj) {
            if (j) {
                try {
                    obj.wait(180000L);
                } catch (InterruptedException e2) {
                    ReleaseLogUtil.e("HiH_HiSyncControl", "InterruptedException = " + e2.getMessage());
                }
            }
        }
    }

    public static boolean h() {
        return e.get();
    }

    public static boolean b(boolean z) {
        return e.compareAndSet(!z, z);
    }

    public static void a(Context context, int i2, int i3) {
        if (b(true)) {
            new Thread(new itc(context, i2, i3)).start();
        }
    }

    private static boolean al() {
        return f;
    }

    public static boolean l() {
        return c;
    }

    public static boolean m() {
        return b;
    }

    public static boolean o() {
        return i;
    }

    public static boolean c() {
        return !i && b();
    }

    public static boolean b() {
        return (c || b) ? false : true;
    }

    public static boolean k() {
        return c && b;
    }

    private void q(int i2) {
        this.s.edit().putInt("biCurrentDay", i2).apply();
    }

    private int w() {
        return this.s.getInt("biCurrentDay", 0);
    }

    public boolean c(String str) {
        return this.s.getBoolean("initialSync" + str, true);
    }

    public void a(String str, boolean z) {
        this.s.edit().putBoolean("initialSync" + str, z).apply();
    }

    public boolean d(String str) {
        return this.s.getBoolean("userConfigFirstSync" + str, true);
    }

    public void c(String str, boolean z) {
        this.s.edit().putBoolean("userConfigFirstSync" + str, z).apply();
    }

    private void r(int i2) {
        this.s.edit().putInt("currentDay", i2).apply();
    }

    public int s() {
        return this.s.getInt("currentDay", 0);
    }

    public void d(int i2) {
        this.s.edit().putInt("newcurrentDay", i2).apply();
    }

    public int r() {
        return this.s.getInt("newcurrentDay", 0);
    }

    private int z() {
        int i2 = this.s.getInt("randomSyncDeviceTimestamp", 0);
        if (i2 != 0) {
            return i2;
        }
        int nextInt = new SecureRandom().nextInt(19) * 3600000;
        this.s.edit().putInt("randomSyncDeviceTimestamp", nextInt).apply();
        return nextInt;
    }

    private int v() {
        return this.s.getInt("appAutoSyncTimes", 0);
    }

    private int ac() {
        return this.s.getInt("statsynctotalnum", 0);
    }

    private int af() {
        return this.s.getInt("totalSportStatSyncTimes", 0);
    }

    private long ab() {
        return this.s.getLong("lastSyncBeginTime", 0L);
    }

    private void aj() {
        this.s.edit().putLong("lastSyncBeginTime", System.currentTimeMillis()).apply();
    }

    private void g(int i2, int i3) {
        this.s.edit().putInt("syncThresholdUserDeviceNum_" + i2, i3).apply();
    }

    private int l(int i2) {
        return this.s.getInt("syncThresholdUserDeviceNum_" + i2, 2);
    }

    private long ad() {
        return this.s.getLong("lastSyncStatDataBeinTime", 0L);
    }

    private void c(long j2) {
        this.s.edit().putLong("lastSyncStatDataBeinTime", j2).apply();
    }

    private int i(int i2) {
        String b2 = SharedPreferenceManager.b(d, "sync_module", "totalCalorie_" + i2);
        LogUtil.c("HiH_HiSyncControl", "getLastTotalCalorie completed !!! id = ", b2);
        if (!HiCommonUtil.b(b2)) {
            try {
                return Integer.parseInt(b2, 10);
            } catch (NumberFormatException e2) {
                ReleaseLogUtil.e("HiH_HiSyncControl", "lastCalorie parse int error:", LogAnonymous.b((Throwable) e2));
                return 0;
            }
        }
        return this.s.getInt("totalCalorie_" + i2, 0);
    }

    private int g(int i2) {
        String b2 = SharedPreferenceManager.b(d, "sync_module", "totalStep_" + i2);
        LogUtil.c("HiH_HiSyncControl", "getLastTotalStep completed !!! id = ", b2);
        if (!HiCommonUtil.b(b2)) {
            try {
                return Integer.parseInt(b2, 10);
            } catch (NumberFormatException e2) {
                ReleaseLogUtil.e("HiH_HiSyncControl", "lastStep parse int error:", LogAnonymous.b((Throwable) e2));
                return 0;
            }
        }
        return this.s.getInt("totalStep_" + i2, 0);
    }

    private void h(int i2, int i3) {
        if (SharedPreferenceManager.e(d, "sync_module", "totalCalorie_" + i2, String.valueOf(i3), new StorageParams(2)) == 0) {
            LogUtil.c("HiH_HiSyncControl", "setLastTotalCalorie completed");
            this.s.edit().putInt("totalCalorie_" + i2, 0).apply();
            return;
        }
        this.s.edit().putInt("totalCalorie_" + i2, i3).apply();
    }

    private void i(int i2, int i3) {
        if (SharedPreferenceManager.e(d, "sync_module", "totalStep_" + i2, String.valueOf(i3), new StorageParams(2)) == 0) {
            LogUtil.c("HiH_HiSyncControl", "setLastTotalStep completed");
            this.s.edit().putInt("totalStep_" + i2, 0).apply();
            return;
        }
        this.s.edit().putInt("totalStep_" + i2, i3).apply();
    }

    private void a(int i2, boolean z) {
        this.s.edit().putBoolean("updatedevicestate_" + i2, z).apply();
    }

    private long aa() {
        return this.s.getLong("syncservicestarttimes", 0L);
    }

    private void am() {
        this.s.edit().putLong("syncservicestarttimes", System.currentTimeMillis()).apply();
    }

    private boolean an() {
        return CommonUtil.aa(d);
    }

    public static int j() {
        int i2 = g;
        if (i2 > 0) {
            return i2;
        }
        LogUtil.b("HiH_HiSyncControl", "error mSyncModel is ", Integer.valueOf(i2));
        return 2;
    }

    private static int y() {
        boolean o2 = Utils.o();
        boolean i2 = Utils.i();
        if (o2) {
            return i2 ? 2 : 3;
        }
        return 1;
    }

    private static void o(int i2) {
        String e2 = KeyValDbManager.b(d).e("cloud_user_privacy3");
        if (1 == i2) {
            if ("false".equals(e2)) {
                c = false;
                LogUtil.c("HiH_HiSyncControl", "initSportDataPrivacy the sportDataPrivacyState switch is closed, stop push health data!");
            } else {
                c = true;
                LogUtil.c("HiH_HiSyncControl", "initSportDataPrivacy the sportDataPrivacyState switch is open, start push health data!");
            }
        }
        if (2 == i2) {
            if ("true".equals(e2)) {
                c = true;
                LogUtil.c("HiH_HiSyncControl", "initSportDataPrivacy the sportDataPrivacyState switch is open, start push health data!");
            } else {
                c = false;
                LogUtil.c("HiH_HiSyncControl", "initSportDataPrivacy the sportDataPrivacyState switch is closed, stop push health data!");
            }
        }
        ReleaseLogUtil.e("HiH_HiSyncControl", "initSptDtPcy sptDtPcyState=", Boolean.valueOf(c));
    }

    private static void t(int i2) {
        String e2 = KeyValDbManager.b(d).e("cloud_user_privacy2");
        if (1 == i2) {
            if ("false".equals(e2)) {
                i = false;
                LogUtil.c("HiH_HiSyncControl", "initUserPrivacy the userPrivacyState switch is closed, stop push user data!");
            } else {
                i = true;
                LogUtil.c("HiH_HiSyncControl", "initUserPrivacy the userPrivacyState switch is open, start push user data!");
            }
        }
        if (2 == i2) {
            if ("true".equals(e2)) {
                i = true;
                LogUtil.c("HiH_HiSyncControl", "initUserPrivacy the userPrivacyState switch is open, start push user data!");
            } else {
                i = false;
                LogUtil.c("HiH_HiSyncControl", "initUserPrivacy the userPrivacyState switch is closed, stop push user data!");
            }
        }
        ReleaseLogUtil.e("HiH_", "initUPcy uPcyState=", Boolean.valueOf(i));
    }

    private static void n(int i2) {
        String e2 = KeyValDbManager.b(d).e("cloud_user_privacy7");
        if (1 == i2) {
            if ("false".equals(e2)) {
                b = false;
                LogUtil.c("HiH_HiSyncControl", "initHealthDataPrivacy the healthDataPrivacyState switch is closed, stop push health data!");
            } else if (HiCommonUtil.b(e2)) {
                b = l();
            } else {
                b = true;
                LogUtil.c("HiH_HiSyncControl", "initHealthDataPrivacy the healthDataPrivacyState switch is open, start push health data!");
            }
        }
        if (2 == i2) {
            if ("true".equals(e2)) {
                b = true;
                LogUtil.c("HiH_HiSyncControl", "initHealthDataPrivacy the healthDataPrivacyState switch is open, start push health data!");
            } else if (HiCommonUtil.b(e2)) {
                b = l();
            } else {
                b = false;
                LogUtil.c("HiH_HiSyncControl", "initHealthDataPrivacy the healthDataPrivacyState switch is closed, stop push health data!");
            }
        }
        ReleaseLogUtil.e("HiH_", "initHlhDtPcy hlhDtPcyState=", Boolean.valueOf(b));
    }

    private static void k(int i2) {
        String e2 = KeyValDbManager.b(d).e("key_wether_to_auth");
        if (1 == i2) {
            if ("false".equals(e2)) {
                f = false;
                LogUtil.c("HiH_HiSyncControl", "initUserConferPrivacy the userConferPrivacy switch is closed, can't do sync!");
            } else {
                f = true;
                LogUtil.c("HiH_HiSyncControl", "initUserConferPrivacy the userConferPrivacy switch is open, can do sync!");
            }
        }
        if (2 == i2) {
            if ("true".equals(e2)) {
                f = true;
                LogUtil.c("HiH_HiSyncControl", "initUserConferPrivacy the userConferPrivacy switch is open, can do sync!");
            } else {
                f = false;
                LogUtil.c("HiH_HiSyncControl", "initUserConferPrivacy the userConferPrivacy switch is closed, can't do sync!");
            }
        }
        ReleaseLogUtil.e("HiH_", "initUConfPcy uConfPcy=", Boolean.valueOf(f));
    }

    private static void ah() {
        int y = y();
        LogUtil.c("HiH_", "initPrivacy cloudversion is ", Integer.valueOf(y));
        k(y);
        o(y);
        t(y);
        n(y);
    }

    public void e(HiSyncOption hiSyncOption, int i2) {
        if (hiSyncOption == null) {
            LogUtil.h("HiH_HiSyncControl", " startOneDaySync hiSyncOption is null");
            return;
        }
        LogUtil.a("HiH_HiSyncControl", " startOneDaySync hiSyncOption = ", hiSyncOption);
        if (CommonUtil.bu()) {
            return;
        }
        if (y() == 3) {
            LogUtil.h("HiH_HiSyncControl", "startOneDaySync not, no cloud version.");
        } else {
            h(hiSyncOption, i2);
        }
    }

    public void a(HiSyncOption hiSyncOption, int i2) {
        if (hiSyncOption == null) {
            return;
        }
        iuz.e(hiSyncOption);
        if (Utils.f()) {
            d(hiSyncOption);
            ReleaseLogUtil.d("HiH_HiSyncControl", "startSync BrowseMode, can't sync!");
            return;
        }
        if (CommonUtil.bu()) {
            d(hiSyncOption);
            return;
        }
        Context context = d;
        int d2 = ijl.d(context, i2);
        synchronized (this.n) {
            a(hiSyncOption, i2, d2);
            if (this.r == null) {
                this.r = new e(null);
                context.registerReceiver(this.r, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            }
        }
    }

    private void d(HiSyncOption hiSyncOption) {
        String valueOf;
        if (hiSyncOption.getSyncDataType() == 0) {
            valueOf = HiJsonUtil.e(hiSyncOption.getSyncDataTypes());
        } else {
            valueOf = String.valueOf(hiSyncOption.getSyncDataType());
        }
        HiBroadcastUtil.a(d, valueOf, hiSyncOption.getSyncId(), false);
    }

    /* loaded from: classes7.dex */
    static class e extends BroadcastReceiver {
        private e() {
        }

        /* synthetic */ e(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, Intent intent) {
            Object systemService = context.getSystemService("connectivity");
            if (systemService instanceof ConnectivityManager) {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
                LogUtil.c("HiH_HiSyncControl", "receive active network info = ", activeNetworkInfo);
                if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
                    return;
                }
                ThreadPoolManager.d().execute(new Runnable() { // from class: ism.e.5
                    @Override // java.lang.Runnable
                    public void run() {
                        ism.f().a(HiSyncOption.getDefaultAutoSyncOption(), iip.b().a(context.getPackageName()));
                    }
                });
            }
        }
    }

    public void c(HiSyncOption hiSyncOption, int i2, int i3, int i4, int i5) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
        linkedHashMap.put("startSync", "dataType " + i5);
        Context context = d;
        ivz.d(context).e(OperationKey.HEALTH_APP_START_SYNC.value(), linkedHashMap, false);
        if (CommonUtil.bu()) {
            return;
        }
        int d2 = ijl.d(context, i2);
        synchronized (this.n) {
            LogUtil.a("HiH_HiSyncControl", "startSync ", hiSyncOption, ",app=", Integer.valueOf(i2), ",who=", Integer.valueOf(d2), ",sTm=", Integer.valueOf(i3), ",eTm=", Integer.valueOf(i4));
            if (hiSyncOption == null) {
                return;
            }
            int syncDataType = hiSyncOption.getSyncDataType();
            if (!b(i2, hiSyncOption)) {
                HiBroadcastUtil.b(context, syncDataType, -1);
                return;
            }
            Intent intent = new Intent(context, (Class<?>) HiSyncService.class);
            intent.putExtra("sync_option", hiSyncOption);
            intent.putExtra("sync_appId", i2);
            intent.putExtra("sync_main_UserID", d2);
            intent.putExtra("sync_starttime", i3);
            intent.putExtra("sync_endtime", i4);
            intent.putExtra("sync_datatype", i5);
            ReleaseLogUtil.e("HiH_HiSyncControl", "startSync componentName ", context.startService(intent));
        }
    }

    private void h(HiSyncOption hiSyncOption, int i2) {
        int d2 = ijl.d(d, i2);
        LogUtil.a("HiH_HiSyncControl", "doOneDaySync hiSyncOption = ", hiSyncOption, ", appId = ", Integer.valueOf(i2), ", who = ", Integer.valueOf(d2));
        if (b(hiSyncOption, i2, d2)) {
            l(hiSyncOption, d2);
        }
    }

    private void l(HiSyncOption hiSyncOption, int i2) {
        LogUtil.a("HiH_HiSyncControl", "startDaySync hiSyncOption = ", hiSyncOption, ", mainUserId = ", Integer.valueOf(i2));
        int syncDataType = hiSyncOption.getSyncDataType();
        if (syncDataType != 10019 && syncDataType != 10021 && syncDataType != 10023) {
            switch (syncDataType) {
                case 10011:
                case 10012:
                case 10013:
                    break;
                default:
                    if (HiHealthDictManager.d((Context) null).d(syncDataType) != null) {
                        try {
                            f(hiSyncOption, i2);
                        } catch (iut e2) {
                            ReleaseLogUtil.c("HiH_HiSyncControl", "startSync syncDataType!=right ", e2.getMessage());
                        }
                        LogUtil.h("HiH_HiSyncControl", "startSync syncDataType!=right, syncDataType=", Integer.valueOf(syncDataType));
                        break;
                    }
                    break;
            }
            return;
        }
        try {
            f(hiSyncOption, i2);
        } catch (iut e3) {
            ReleaseLogUtil.c("HiH_HiSyncControl", "startSync syncDataType!=right ", e3.getMessage());
        }
    }

    private void f(HiSyncOption hiSyncOption, int i2) throws iut {
        ReleaseLogUtil.e("HiH_HiSyncControl", "downloadOneDetailData syncDataType = " + hiSyncOption.getSyncDataType(), " getSyncType ", Integer.valueOf(hiSyncOption.getSyncType()));
        int syncDataType = hiSyncOption.getSyncDataType();
        if (syncDataType == 10019) {
            i(hiSyncOption, i2).c(11, hiSyncOption.getSyncDay() + 86400000);
            return;
        }
        if (syncDataType == 10021) {
            i(hiSyncOption, i2).c(12, hiSyncOption.getSyncDay() + 86400000);
            return;
        }
        if (syncDataType != 10023) {
            switch (syncDataType) {
                case 10011:
                    g(hiSyncOption, i2).a(hiSyncOption.getSyncDay() + 86400000);
                    break;
                case 10012:
                    i(hiSyncOption, i2).c(HiDateUtil.l(hiSyncOption.getSyncDay()) + 86340000, hiSyncOption.getSyncType());
                    break;
                case 10013:
                    i(hiSyncOption, i2).c(7, hiSyncOption.getSyncDay() + 86400000);
                    break;
                default:
                    if (HiHealthDictManager.d((Context) null).d(hiSyncOption.getSyncDataType()) != null) {
                        b(hiSyncOption, i2).downloadByTime(hiSyncOption.getSyncDataType(), HiDateUtil.t(hiSyncOption.getSyncDay()), HiDateUtil.f(hiSyncOption.getSyncDay()));
                        break;
                    }
                    break;
            }
            return;
        }
        d(hiSyncOption, i2).d(16, hiSyncOption.getSyncDay() + 86400000);
    }

    private itl g(HiSyncOption hiSyncOption, int i2) {
        return new itl(d, new HiSyncOption(hiSyncOption, 1), i2);
    }

    private ito i(HiSyncOption hiSyncOption, int i2) throws iut {
        return new ito(d, new HiSyncOption(hiSyncOption, 10001), i2);
    }

    private itk d(HiSyncOption hiSyncOption, int i2) {
        return new itk(d, new HiSyncOption(hiSyncOption, 10001), i2);
    }

    private HiSyncDictionaryDataDetail b(HiSyncOption hiSyncOption, int i2) {
        return new HiSyncDictionaryDataDetail(d, new HiSyncOption(hiSyncOption, hiSyncOption.getSyncDataType()), i2);
    }

    private void a(HiSyncOption hiSyncOption, int i2, int i3) {
        ReleaseLogUtil.e("HiH_HiSyncControl", "startSync hiSyncOption=", hiSyncOption, ",app=", Integer.valueOf(i2), ",who=", Integer.valueOf(i3));
        int syncAction = hiSyncOption.getSyncAction();
        if (!b(hiSyncOption, i2, i3)) {
            if (syncAction == 0) {
                d(hiSyncOption);
                HiBroadcastUtil.j(d);
                return;
            }
            return;
        }
        if (!p(hiSyncOption.getSyncDataType()) && !hiSyncOption.isForceSync() && j) {
            long aa = aa();
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - aa > 3600000) {
                LogUtil.b("HiH_HiSyncControl", "lastSyncStartTime=", Long.valueOf(aa), ",currTime=", Long.valueOf(currentTimeMillis), " destroy last sync service");
                Context context = d;
                context.stopService(new Intent(context, (Class<?>) HiSyncService.class));
                d(false);
                a(false);
                iuz.e(false);
            } else if (hiSyncOption.getSyncManual() == 1 && h) {
                try {
                    jdh.c().a(20210701);
                } catch (Exception e2) {
                    ReleaseLogUtil.c("HiH_HiSyncControl", "sync cancel notify throw exception", LogAnonymous.b((Throwable) e2));
                }
                Context context2 = d;
                context2.stopService(new Intent(context2, (Class<?>) HiSyncService.class));
                d(false);
                a(false);
                String accountInfo = LoginInit.getInstance(context2).getAccountInfo(1011);
                if (TextUtils.isEmpty(accountInfo)) {
                    ReleaseLogUtil.e("HiH_HiSyncControl", "setSharedPreference huid null");
                    return;
                }
                ReleaseLogUtil.e("HiH_HiSyncControl", "Manual stop allAreaSyncTask");
                SharedPreferenceManager.e(context2, String.valueOf(20003), accountInfo + "data_downing_flag", "", (StorageParams) null);
            } else {
                ReleaseLogUtil.d("HiH_HiSyncControl", "startSync it is syncing right now !  hiSyncOption is ", hiSyncOption);
                ivc.c(hiSyncOption.getSyncAction());
                ivc.d(d);
                return;
            }
        }
        e(syncAction, i3);
        if (syncAction != 0 && !b(hiSyncOption.getSyncAction(), i3)) {
            LogUtil.h("HiH_HiSyncControl", "ifCanAutoSync false");
            return;
        }
        if (!b(i2, hiSyncOption)) {
            Context context3 = d;
            HiBroadcastUtil.j(context3);
            if (iuz.i(context3, i3)) {
                d(hiSyncOption);
                return;
            }
            return;
        }
        d(hiSyncOption, i2, i3);
        if (4 == syncAction) {
            ao();
        } else {
            f(syncAction);
        }
    }

    private void d(HiSyncOption hiSyncOption, int i2, int i3) {
        if (CompileParameterUtil.a("SUPPORT_WIFI_WEIGHT_DEVICE", false)) {
            HiBroadcastUtil.h(d);
        }
        if (20000 == hiSyncOption.getSyncDataType()) {
            am();
        }
        if (hiSyncOption.getSyncDataType() == 20000 && hiSyncOption.getSyncAction() != 0 && iuz.d(d, i3, 1, 0L)) {
            ReleaseLogUtil.e("HiH_HiSyncControl", "First all sync task, change sync action to MANUAL_SYNC");
            hiSyncOption.setSyncAction(0);
        }
        Context context = d;
        Intent intent = new Intent(context, (Class<?>) HiSyncService.class);
        intent.putExtra("sync_option", hiSyncOption);
        intent.putExtra("sync_appId", i2);
        intent.putExtra("sync_main_UserID", i3);
        try {
            ComponentName startService = context.startService(intent);
            ReleaseLogUtil.e("HiH_HiSyncControl", "handleSyncService cptNm=", startService);
            if (startService == null || hiSyncOption.getSyncDataType() != 20000) {
                return;
            }
            ReleaseLogUtil.e("HiH_HiSyncControl", "All sync task arrive， set mHasAllSyncTask true");
            d(true);
            if (hiSyncOption.getSyncDataArea() == 1) {
                LogUtil.a("HiH_HiSyncControl", "All sync task arrive，setHasAllAreaSyncTask true");
                a(true);
            }
        } catch (IllegalStateException e2) {
            ReleaseLogUtil.d("HiH_HiSyncControl", "doSync ", e2.getMessage());
        } catch (Exception e3) {
            ReleaseLogUtil.d("HiH_HiSyncControl", "handleSyncService Exception", LogAnonymous.b((Throwable) e3));
        }
    }

    private void ao() {
        int af = af() + 1;
        LogUtil.a("HiH_HiSyncControl", "total sport stat sync times is ", Integer.valueOf(af));
        this.s.edit().putInt("totalSportStatSyncTimes", af).apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, int i2, int i3) {
        LogUtil.a("HiH_HiSyncControl", "broadcastSync begin dataType is ", Integer.valueOf(i2));
        if (str == null) {
            return;
        }
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(i3);
        hiSyncOption.setSyncDataType(i2);
        hiSyncOption.setSyncMethod(2);
        int a2 = iip.b().a(str);
        if (i2 == 20000 && i3 == 5 && iuz.a()) {
            j(hiSyncOption, a2);
            return;
        }
        a(hiSyncOption, a2);
        if (CommonUtil.as() && i2 == 20000 && i3 == 5) {
            f(i2, i3);
        }
    }

    private void j(final HiSyncOption hiSyncOption, final int i2) {
        HandlerCenter.d().e(new Runnable() { // from class: isp
            @Override // java.lang.Runnable
            public final void run() {
                ism.this.c(hiSyncOption, i2);
            }
        }, iuz.b());
    }

    /* synthetic */ void c(HiSyncOption hiSyncOption, int i2) {
        a(hiSyncOption, i2);
        if (CommonUtil.as() && hiSyncOption.getSyncDataType() == 20000 && hiSyncOption.getSyncAction() == 5) {
            f(hiSyncOption.getSyncDataType(), hiSyncOption.getSyncAction());
        }
    }

    private void f(int i2, int i3) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("sync_datatype", String.valueOf(i2));
        linkedHashMap.put("sync_action", String.valueOf(i3));
        linkedHashMap.put("sync_time", String.valueOf(System.currentTimeMillis()));
        linkedHashMap.put("sdk_ver", Build.VERSION.RELEASE);
        linkedHashMap.put("sdk_int", String.valueOf(Build.VERSION.SDK_INT));
        linkedHashMap.put("dev_manufacturer", Build.MANUFACTURER);
        linkedHashMap.put("dev_model", Build.MODEL);
        linkedHashMap.put("dev_brand", Build.BRAND);
        linkedHashMap.put("dev_display", Build.DISPLAY);
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_DATA_SYNC_ALARM_2129018.value(), linkedHashMap);
    }

    private int c(int i2, int i3) {
        long j2;
        long currentTimeMillis = System.currentTimeMillis();
        long ab = currentTimeMillis - ab();
        long ad = currentTimeMillis - ad();
        if (ab < 0) {
            ab = 0 - ab;
        }
        if (ad < 0) {
            ad = 0 - ad;
        }
        if (l(i2) > 1) {
            j2 = 600000;
        } else {
            j2 = iuz.i() ? 18000000L : AiCoreSdkConstant.CHECK_SUPPORT_INTERVAL;
        }
        LogUtil.a("HiH_HiSyncControl", "checkSyncIntervalTime autoSyncTime=", Long.valueOf(j2), ",detailinterval=", Long.valueOf(ab), ",statinterval=", Long.valueOf(ad), "syncType:", Integer.valueOf(i3));
        if (i3 == 1) {
            return ab > j2 ? 10008 : -1;
        }
        if (i3 == 2) {
            return e(ad) ? 10004 : -1;
        }
        if (i3 != 3) {
            return -1;
        }
        if (ab > j2) {
            return 10008;
        }
        return e(ad) ? 10004 : -1;
    }

    private boolean e(long j2) {
        if (ScreenUtil.a()) {
            return j2 > 60000;
        }
        if (j2 > 600000) {
            return true;
        }
        if (j2 <= 60000) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        return HiDateUtil.b(currentTimeMillis, 21, 30) <= currentTimeMillis && currentTimeMillis <= HiDateUtil.b(currentTimeMillis, 22, 30);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean u() {
        int v = v();
        LogUtil.a("HiH_HiSyncControl", "appSynTimes is ", Integer.valueOf(v), ", statsyncTimes is ", Integer.valueOf(ac()), ", totalSportStatSyncTimes is ", Integer.valueOf(af()));
        if (v <= 400) {
            return true;
        }
        LogUtil.h("HiH_HiSyncControl", "basicSyncCondition! the app has sync more times,appSynTimes is ", Integer.valueOf(v));
        return false;
    }

    private boolean b(int i2, HiSyncOption hiSyncOption) {
        if (!ThermalCallback.getInstance().isTriggerTask() && !hiSyncOption.isForceSync() && !iuz.f()) {
            ReleaseLogUtil.d("HiH_HiSyncControl", "ifCanSync not! temperature is too high");
            return false;
        }
        ah();
        if (!al()) {
            ReleaseLogUtil.d("HiH_HiSyncControl", "ifCanSync not! user didn't auth user confer! can not do sync!");
            return false;
        }
        if (3 == y()) {
            ReleaseLogUtil.d("HiH_HiSyncControl", "ifCanSync not! no cloud version");
            return false;
        }
        if (!an()) {
            t();
            ReleaseLogUtil.d("HiH_HiSyncControl", "ifCanSync not! no networkConnected");
            return false;
        }
        if (PowerKitManager.e().b()) {
            t();
            ReleaseLogUtil.d("HiH_HiSyncControl", "ifCanSync not! isUserSleeping");
            return false;
        }
        if (s(i2)) {
            return true;
        }
        ReleaseLogUtil.d("HiH_HiSyncControl", "ifCanSync not! not login in database");
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x000a, code lost:
    
        if (r5 != 6) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean b(int r5, int r6) {
        /*
            r4 = this;
            r0 = 0
            java.lang.String r1 = "HiH_HiSyncControl"
            r2 = 1
            if (r5 == r2) goto L21
            r3 = 5
            if (r5 == r3) goto Ld
            r3 = 6
            if (r5 == r3) goto L21
            goto L35
        Ld:
            boolean r6 = r4.ae()
            if (r6 != 0) goto L35
            java.lang.String r6 = "ifCanAutoSync isInSyncTimes is false,auto sync action is "
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Object[] r5 = new java.lang.Object[]{r6, r5}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r1, r5)
            return r0
        L21:
            boolean r6 = r4.d(r6, r5)
            if (r6 != 0) goto L35
            java.lang.String r6 = "ifCanAutoSync basicSyncCondition is false , auto sync action is "
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Object[] r5 = new java.lang.Object[]{r6, r5}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r1, r5)
            return r0
        L35:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ism.b(int, int):boolean");
    }

    private boolean ae() {
        return v() <= 400;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean d(int r8, int r9) {
        /*
            r7 = this;
            r7.h(r8)
            int r0 = y()
            r1 = 1
            if (r1 != r0) goto L1b
            int r8 = r7.l(r8)
            if (r1 >= r8) goto L17
            r8 = 6
            if (r9 != r8) goto L1b
            r8 = 60000(0xea60, double:2.9644E-319)
            goto L1e
        L17:
            r8 = 7200000(0x6ddd00, double:3.5572727E-317)
            goto L1e
        L1b:
            r8 = 600000(0x927c0, double:2.964394E-318)
        L1e:
            r2 = 2
            if (r2 != r0) goto L24
            r8 = 18000000(0x112a880, double:8.8931816E-317)
        L24:
            java.lang.String r0 = "checkSyncIntervalTime autoSyncTime="
            java.lang.Long r2 = java.lang.Long.valueOf(r8)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r2}
            java.lang.String r2 = "HiH_HiSyncControl"
            com.huawei.hwlogsmodel.LogUtil.c(r2, r0)
            long r3 = java.lang.System.currentTimeMillis()
            long r5 = r7.ab()
            long r3 = r3 - r5
            r5 = 0
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 <= 0) goto L44
            long r3 = r5 - r3
        L44:
            int r8 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            r9 = 0
            if (r8 > 0) goto L57
            java.lang.String r8 = "basicSyncCondition NOT! the app has sync too quick,intervalTime is "
            java.lang.Long r0 = java.lang.Long.valueOf(r3)
            java.lang.Object[] r8 = new java.lang.Object[]{r8, r0}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r8)
            return r9
        L57:
            int r8 = r7.v()
            java.lang.String r0 = "stepSyncOrNot appSynTimes is "
            java.lang.Integer r3 = java.lang.Integer.valueOf(r8)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r3}
            com.huawei.hwlogsmodel.LogUtil.c(r2, r0)
            r0 = 400(0x190, float:5.6E-43)
            if (r8 <= r0) goto L7b
            java.lang.String r0 = "basicSyncCondition NOT! the app has sync too many times,appSynTimes is "
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            java.lang.Object[] r8 = new java.lang.Object[]{r0, r8}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r8)
            return r9
        L7b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ism.d(int, int):boolean");
    }

    private boolean s(int i2) {
        String a2 = iio.c().a(i2);
        if (a2 != null && !a2.contains("com.")) {
            return true;
        }
        LogUtil.h("HiH_HiSyncControl", "isLogin such app can not sync , app is ", Integer.valueOf(i2));
        return false;
    }

    private void f(int i2) {
        if (3 == i2) {
            c(System.currentTimeMillis());
            this.s.edit().putInt("statsynctotalnum", ac() + 1).apply();
        } else {
            aj();
        }
        this.s.edit().putInt("appAutoSyncTimes", this.s.getInt("appAutoSyncTimes", 0) + 1).apply();
    }

    public void b(int i2) {
        Context context = d;
        int a2 = iuz.a(context, i2, HiDateUtil.c(System.currentTimeMillis()), 40002);
        h(i2, iuz.a(context, i2, HiDateUtil.c(System.currentTimeMillis()), 40003));
        i(i2, a2);
    }

    private void ak() {
        this.s.edit().putInt("appAutoSyncTimes", 0).apply();
        if (0 == ab()) {
            this.s.edit().putLong("lastSyncBeginTime", HiDateUtil.t(System.currentTimeMillis())).apply();
        }
        this.s.edit().putInt("statsynctotalnum", 0).apply();
        this.s.edit().putInt("totalSportStatSyncTimes", 0).apply();
    }

    private void h(int i2) {
        int c2 = HiDateUtil.c(System.currentTimeMillis());
        int s = s();
        if (c2 != s) {
            LogUtil.a("HiH_HiSyncControl", "checkCurrentDay a new day comes , reset basicSyncCondition, currentDay is ", Integer.valueOf(c2), " oldDay is ", Integer.valueOf(s));
            Context context = d;
            iwe.h(context, v() + af());
            ivo.b(context).d();
            ivo.b(context).c();
            iwe.d(context, c2);
            r(c2);
            ak();
            c(System.currentTimeMillis());
            h(i2, 0);
            i(i2, 0);
            String b2 = ijz.c().b(i2);
            iwe.a(context, b2, 0);
            KeyValDbManager.b(context).e(b2 + "step_sum_dvalue", String.valueOf(0));
        }
    }

    private void e(int i2, int i3) {
        int c2;
        h(i3);
        if (4 != i2 || (c2 = HiDateUtil.c(System.currentTimeMillis())) == w()) {
            return;
        }
        Context context = d;
        ivo.b(context).b();
        if (!Utils.o()) {
            ivo.b(context).b(l(i3));
        }
        q(c2);
    }

    public void a(int i2) {
        int g2;
        int c2 = HiDateUtil.c(System.currentTimeMillis());
        int r = r();
        int z = z();
        LogUtil.a("HiH_HiSyncControl", "checkCurrentDayBindDevice a new day comes, currentday is ", Integer.valueOf(c2), " oldDay is ", Integer.valueOf(r), " r is ", Integer.valueOf(z));
        if (c2 != r) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis >= HiDateUtil.t(currentTimeMillis) + z) {
                if (y() == 3) {
                    LogUtil.h("HiH_HiSyncControl", "checkCurrentDayBindDevice not! no cloud version");
                    g2 = -1;
                } else {
                    g2 = iuz.g(d, i2);
                    LogUtil.a("HiH_HiSyncControl", "checkCurrentDayBindDevice n:", Integer.valueOf(g2));
                }
                if (g2 < 0) {
                    LogUtil.b("HiH_HiSyncControl", "checkCurrentDayBindDevice fail !");
                    a(i2, true);
                    g(i2, 1);
                } else {
                    a(i2, true);
                    g(i2, g2);
                }
                d(c2);
            }
        }
    }

    public void p() {
        if (EnvironmentInfo.r()) {
            ap();
        } else {
            ar();
        }
    }

    private void ap() {
        if (this.m.get() != null) {
            return;
        }
        BroadcastManagerUtil.bFA_(d, this.k, new IntentFilter("com.huawei.hihealth.action_one_hour_sync"), LocalBroadcast.c, null);
        ReleaseLogUtil.e("HiH_HiSyncControl", "register AlarmManagerBroadcastReceiver end");
        ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(this.m, null, CommonUtil.xy_());
        if (this.m.get() != null) {
            ReleaseLogUtil.e("HiH_HiSyncControl", "start background sync alarmManager.");
            ArrayList arrayList = new ArrayList();
            arrayList.add("com.huawei.hihealth.action_one_hour_sync");
            PowerKitManager.e().e(arrayList);
            Intent intent = new Intent("com.huawei.hihealth.action_one_hour_sync");
            intent.setPackage(BaseApplication.getAppPackage());
            this.m.get().setRepeating(0, System.currentTimeMillis(), ai(), PendingIntent.getBroadcast(BaseApplication.getContext(), 0, intent, AppRouterExtras.COLDSTART));
            ReleaseLogUtil.e("HiH_HiSyncControl", "startAlarmManager success");
        }
    }

    private void ar() {
        if (this.p.get() == null && ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(this.p, null, new Timer("HiH_HiSyncControl"))) {
            ReleaseLogUtil.e("HiH_HiSyncControl", "start background sync timer.");
            this.p.get().schedule(new TimerTask() { // from class: ism.2
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    ReleaseLogUtil.e("HiH_HiSyncControl", "startTimer start autoSync");
                    ism.this.d(ism.d.getPackageName(), 20000, 5);
                    ism.this.x();
                }
            }, ai(), ai());
            ReleaseLogUtil.e("HiH_HiSyncControl", "startTimer success");
        }
    }

    private long ai() {
        return new SecureRandom().nextInt(300000) + 3600000;
    }

    public void t() {
        if (Utils.o()) {
            return;
        }
        synchronized (this.q) {
            if (this.l != null) {
                return;
            }
            LogUtil.a("HiH_HiSyncControl", "startNetErrorTimer");
            Timer timer = new Timer("HiH_HiSyncControl");
            this.l = timer;
            timer.schedule(new TimerTask() { // from class: ism.4
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    ism.this.d(ism.d.getPackageName(), 20000, 5);
                }
            }, 600000L, 600000L);
        }
    }

    public void n() {
        synchronized (this.q) {
            if (this.l != null) {
                LogUtil.a("HiH_HiSyncControl", "cancelNetErrorTimer");
                this.l.cancel();
                this.l = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        iip b2 = iip.b();
        Context context = d;
        int d2 = ijl.d(context, b2.a(context.getPackageName()));
        int e2 = iuz.e();
        if (e2 < 0) {
            e2 = iuz.b(context, d2);
        }
        if (e2 == 0) {
            return;
        }
        if (System.currentTimeMillis() - iuz.d(context, d2) > 43200000) {
            a(context, d2, 300000);
        }
    }

    public static boolean b(HiSyncOption hiSyncOption, int i2, int i3) {
        if (hiSyncOption == null) {
            ReleaseLogUtil.d("HiH_HiSyncControl", "paraCheck hiSyncOption is null");
            return false;
        }
        if (hiSyncOption.getSyncMethod() != 2) {
            ReleaseLogUtil.d("HiH_HiSyncControl", "paraCheck syncMethod is not by_user");
            return false;
        }
        if (i2 <= 0) {
            ReleaseLogUtil.d("HiH_HiSyncControl", "paraCheck app <= 0 ");
            return false;
        }
        if (i3 <= 0) {
            ReleaseLogUtil.d("HiH_HiSyncControl", "paraCheck who <= 0 ");
            return false;
        }
        if (HiSyncType.d(hiSyncOption.getSyncDataType()) || hiSyncOption.getSyncDataTypes() != null) {
            return true;
        }
        ReleaseLogUtil.d("HiH_HiSyncControl", "paraCheck error syncType type is ", Integer.valueOf(hiSyncOption.getSyncDataType()));
        return false;
    }

    public void a(int i2, List<HiHealthData> list) {
        if (list == null) {
            ReleaseLogUtil.d("HiH_HiSyncControl", "data is null");
            return;
        }
        if (!iwj.d(list)) {
            ReleaseLogUtil.d("HiH_HiSyncControl", "startInsSptSync no SptDt");
            return;
        }
        Context context = d;
        int d2 = ijl.d(context, i2);
        if (d2 <= 0) {
            ReleaseLogUtil.d("HiH_HiSyncControl", "startInsSptSync who<=0");
            return;
        }
        if (!u()) {
            ReleaseLogUtil.d("HiH_HiSyncControl", "startInsSptSync CkSyncTm=false");
            return;
        }
        h(d2);
        int d3 = d(context, d2);
        if (d3 == 0) {
            ReleaseLogUtil.d("HiH_HiSyncControl", "ckSptDtNum Less StepAndCals");
            return;
        }
        int c2 = c(d2, d3);
        if (-1 == c2) {
            ReleaseLogUtil.d("HiH_HiSyncControl", "startInsSptSync CkSyncItvalTm=false");
            return;
        }
        aj();
        c(System.currentTimeMillis());
        LogUtil.a("HiH_HiSyncControl", "startInsertSportSync start auto sync,app is ", Integer.valueOf(i2));
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        d(c2, hiSyncOption, ijl.d(context, i2));
        hiSyncOption.setSyncMethod(2);
        a(hiSyncOption, i2);
    }

    private void d(int i2, HiSyncOption hiSyncOption, int i3) {
        if (10008 == i2) {
            Context context = d;
            if (iuz.d(context, i3, 1, 0L)) {
                LogUtil.a("HiH_HiSyncControl", "startInsertSportSync first 500 steps sync,do all sync");
                hiSyncOption.setSyncDataType(20000);
            } else {
                LogUtil.c("HiH_HiSyncControl", "startInsertSportSync not first 500 steps sync,do push_sport_data sync");
                hiSyncOption.setSyncDataType(10008);
            }
            hiSyncOption.setSyncAction(2);
            iwe.e(context, iwe.d(context) + 1);
        }
        if (10004 == i2) {
            Context context2 = d;
            if (iuz.d(context2, i3, 1, 0L)) {
                LogUtil.a("HiH_HiSyncControl", "startInsertSportSync first stat 500 steps sync,do all sync");
                hiSyncOption.setSyncDataType(20000);
            } else {
                LogUtil.c("HiH_HiSyncControl", "startInsertSportSync sync stat data");
                hiSyncOption.setSyncDataType(i2);
            }
            hiSyncOption.setSyncAction(3);
            iwe.b(context2, iwe.e(context2) + 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int d(Context context, int i2) {
        int m = m(i2);
        int j2 = j(i2);
        o(y());
        int i3 = 0;
        if (!l()) {
            LogUtil.c("HiH_HiSyncControl", "checkSportDataNum sport privacy is close return 0");
            return 0;
        }
        HiHealthData c2 = iuz.c(context, i2);
        if (c2 != null) {
            int i4 = c2.getInt("step");
            int i5 = c2.getInt("calorie");
            if (i4 >= m || i5 >= j2) {
                LogUtil.a("HiH_HiSyncControl", "checkInsertStatus stepSum or calorieSum is enough");
                i3 = 1;
            }
        }
        Context context2 = d;
        int a2 = iuz.a(context2, i2, HiDateUtil.c(System.currentTimeMillis()), 40002);
        int g2 = g(i2);
        int a3 = iuz.a(context2, i2, HiDateUtil.c(System.currentTimeMillis()), 40003);
        int i6 = i(i2);
        if (a2 - g2 < m && a3 - i6 < j2) {
            return i3;
        }
        LogUtil.c("HiH_HiSyncControl", "checkInsertStatus stepStatSum or calorieStatSum is enough");
        return i3 + 2;
    }

    private int m(int i2) {
        return (2 != y() || 2 <= l(i2)) ? 500 : 1500;
    }

    private int j(int i2) {
        return (2 != y() || 2 <= l(i2)) ? 100000 : 300000;
    }

    public void q() {
        synchronized (this.n) {
            e eVar = this.r;
            if (eVar != null) {
                d.unregisterReceiver(eVar);
                this.r = null;
            }
        }
    }

    public static boolean c(int i2) {
        return d.getSharedPreferences("downloadsevendaysleepflag", 0).getBoolean("downloadsevendaysleepflag_" + Integer.toString(i2), true);
    }

    public static void b(int i2, boolean z) {
        d.getSharedPreferences("downloadsevendaysleepflag", 0).edit().putBoolean("downloadsevendaysleepflag_" + Integer.toString(i2), z).apply();
    }

    /* loaded from: classes7.dex */
    class b extends BroadcastReceiver {
        private b() {
        }

        /* synthetic */ b(ism ismVar, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, Intent intent) {
            if (context == null || intent == null || !"android.intent.action.SCREEN_ON".equals(intent.getAction())) {
                return;
            }
            if (!ism.this.u()) {
                ReleaseLogUtil.d("HiH_HiSyncControl", "Screen on checkSyncTimes is false");
            } else {
                ThreadPoolManager.d().execute(new Runnable() { // from class: ist
                    @Override // java.lang.Runnable
                    public final void run() {
                        ism.b.this.d(context);
                    }
                });
            }
        }

        /* synthetic */ void d(Context context) {
            HiBroadcastUtil.i(context);
            e();
        }

        private void e() {
            if (ism.this.d(ism.d, ijl.d(ism.d, iip.b().a(ism.d.getPackageName()))) != 0) {
                ism.this.d(ism.d.getPackageName(), 10004, 6);
            } else {
                ReleaseLogUtil.d("HiH_HiSyncControl", "ckStepDtNum LESS StepAndCals OnScreen");
            }
        }
    }
}
