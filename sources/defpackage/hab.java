package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class hab {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f13036a = false;
    private static boolean b = false;
    private static boolean c = false;
    private static boolean d = false;
    private static gzu e;
    private static int f;
    private static int i;
    private static gzy j;

    public static int a() {
        return i;
    }

    public static void c(boolean z) {
        c = z;
    }

    public static boolean g() {
        return b;
    }

    public static boolean c() {
        return d;
    }

    public static void a(boolean z) {
        d = z;
    }

    public static boolean j() {
        return d(BaseApplication.getContext(), "smart_coach_enable_type");
    }

    public static boolean h() {
        return d() >= new gws().d("1.1.1");
    }

    public static long d() {
        return new File(had.d().b()).listFiles() == null ? 0 : r0.length;
    }

    public static boolean e(int i2) {
        return i2 == -1 && j() && h();
    }

    public static boolean b(int i2, int i3) {
        if (!gws.a()) {
            LogUtil.h("Track_SmartCoachStatusControl", "phone not support smart coach");
            b = false;
            return false;
        }
        if (!LanguageUtil.m(BaseApplication.getContext()) || Utils.o()) {
            b = false;
            return false;
        }
        if (i2 != 264 && i2 != 258) {
            b = false;
            return false;
        }
        if (UnitUtil.h()) {
            LogUtil.h("Track_SmartCoachStatusControl", "isOpenSmartCoachModule isShowImperialUnit.");
            b = false;
            return false;
        }
        if (!l()) {
            LogUtil.h("Track_SmartCoachStatusControl", "isOpenSmartCoachModule age is not int 18-59 section.");
            b = false;
            return false;
        }
        if (f()) {
            if (m()) {
                b = e(i3);
            } else {
                b = true;
            }
        } else {
            b = e(i3);
        }
        return b;
    }

    public static boolean f() {
        PluginSportTrackAdapter c2 = gso.e().c();
        return c2 != null && c2.isWearDeviceConnected();
    }

    public static boolean m() {
        DeviceCapability d2 = cvs.d();
        if (d2 == null) {
            LogUtil.b("Track_SmartCoachStatusControl", "isWearDeviceSupportHeartRate deviceCapability is null.");
            return false;
        }
        return d2.isSupportHeartRateInfo();
    }

    public static boolean i() {
        boolean z = false;
        if (c) {
            return false;
        }
        if (f() && m()) {
            z = true;
        }
        a(z);
        return z;
    }

    public static boolean l() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        HiHealthNativeApi.a(BaseApplication.getContext()).fetchUserData(new HiCommonListener() { // from class: hab.4
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i2, Object obj) {
                LogUtil.a("Track_SmartCoachStatusControl", "readUserInfomation fetchUserData onSuccess");
                List list = koq.e(obj, HiUserInfo.class) ? (List) obj : null;
                if (list != null && list.size() > 0) {
                    int unused = hab.f = ((HiUserInfo) list.get(0)).getAge();
                }
                countDownLatch.countDown();
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i2, Object obj) {
                LogUtil.a("Track_SmartCoachStatusControl", "isUserAgeMatchOpenSmartCoach fetchUserData onFailure");
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(3L, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("Track_SmartCoachStatusControl", "isUserAgeMatchOpenSmartCoach interruptedException");
        }
        int i2 = f;
        return i2 >= 18 && i2 <= 59;
    }

    public static boolean d(Context context, String str) {
        if (context == null) {
            LogUtil.h("Track_SmartCoachStatusControl", "getVoiceEnable context is null.");
            return true;
        }
        if (str == null || str.length() <= 0) {
            LogUtil.h("Track_SmartCoachStatusControl", "getVoiceEnable storage is null.");
            return true;
        }
        String b2 = SharedPreferenceManager.b(context, Integer.toString(20002), str);
        try {
            if (!TextUtils.isEmpty(b2)) {
                if (Integer.parseInt(b2) == 1) {
                    return true;
                }
            }
            return false;
        } catch (NumberFormatException e2) {
            LogUtil.b("Track_SmartCoachStatusControl", e2.getMessage());
            return true;
        }
    }

    public static void d(int i2) {
        if (i()) {
            gzy gzyVar = new gzy();
            j = gzyVar;
            gzyVar.c(i2);
        } else {
            gzu gzuVar = new gzu();
            e = gzuVar;
            gzuVar.d(i2);
        }
    }

    public static void c(long j2) {
        new Handler().postDelayed(new Runnable() { // from class: hab.5
            @Override // java.lang.Runnable
            public void run() {
                hab.b(hab.i);
            }
        }, j2 + 1000);
    }

    public static void b(int i2) {
        if (!b) {
            LogUtil.h("Track_SmartCoachStatusControl", "openSmartCoachVoicePlay error.");
            return;
        }
        if (gtx.c(BaseApplication.getContext()).m() != 1 && hae.e().h() != 1) {
            LogUtil.h("Track_SmartCoachStatusControl", "openSmartCoachVoicePlay not in sport.");
            return;
        }
        String[] strArr = {had.d().d("L128"), had.d().d("empty_500")};
        if (d) {
            d(strArr);
            HashMap hashMap = new HashMap(10);
            hashMap.put("click", 1);
            hashMap.put("type", 5);
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_SMART_COACH_2010101.value(), hashMap, 0);
        } else {
            c(i2, strArr);
        }
        haa.c().d(i);
    }

    private static void d(String[] strArr) {
        gzy gzyVar = j;
        if (gzyVar != null) {
            gzyVar.d(strArr);
            if (j.i()) {
                return;
            }
            hac.a().c(f13036a);
            hac.a().f();
            hac.a().h();
            hac.a().i();
        }
    }

    private static void c(int i2, String[] strArr) {
        gzu gzuVar = e;
        if (gzuVar != null) {
            gzuVar.c(i2, strArr);
            if (e.e()) {
                return;
            }
            hac.a().c(f13036a);
            hac.a().k();
            hac.a().i();
        }
    }

    public static void d(boolean z) {
        LogUtil.a("Track_SmartCoachStatusControl", "closeSmartCoachForDFH ", Boolean.valueOf(z));
        hac.a().j();
        haa.c().b();
        if (z) {
            b();
        }
        gtx.c(BaseApplication.getContext()).bm();
    }

    public static void c(final int i2, final int i3, final boolean z) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: hab.3
                @Override // java.lang.Runnable
                public void run() {
                    hab.c(i2, i3, z);
                }
            });
            return;
        }
        if (!b(i2, i3)) {
            LogUtil.a("Track_SmartCoachStatusControl", "startSmartCoachVoiceFunction isOpenSmartCoachModule error");
            return;
        }
        HashMap hashMap = new HashMap(10);
        hashMap.put("click", 1);
        hashMap.put("type", 2);
        f13036a = z;
        if (z) {
            hashMap.put("type", 4);
        }
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_SMART_COACH_2010101.value(), hashMap, 0);
        gxd.a().h("Track_SmartCoachStatusControl");
        i = i2;
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        newSingleThreadExecutor.execute(new Runnable() { // from class: hab.2
            @Override // java.lang.Runnable
            public void run() {
                hab.d(i2);
            }
        });
        newSingleThreadExecutor.shutdown();
    }

    public static void b() {
        i = -1;
        f = 0;
        c = false;
        b = false;
        j = null;
        e = null;
        hae.e().c();
        HandlerExecutor.d(new Runnable() { // from class: haf
            @Override // java.lang.Runnable
            public final void run() {
                gxd.a().f("Track_SmartCoachStatusControl");
            }
        }, 1000L);
    }

    public static void b(Object obj) {
        if (!d(BaseApplication.getContext(), "voice_enable_type")) {
            LogUtil.h("Track_SmartCoachStatusControl", "smartCoachCommonVoicePlay isVoicePlayEnable false, object = ", obj);
            return;
        }
        if (obj instanceof gxi) {
            gxd.a().d((gxi) obj);
        } else if (obj instanceof String[]) {
            gxd.a().b((String[]) obj);
        } else if (obj instanceof String) {
            gxd.a().b((String) obj);
        } else {
            LogUtil.h("Track_SmartCoachStatusControl", "smartCoachCommonVoicePlay object = ", obj);
        }
    }
}
