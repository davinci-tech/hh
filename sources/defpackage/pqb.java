package defpackage;

import android.text.TextUtils;
import android.text.format.Formatter;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;

/* loaded from: classes6.dex */
public class pqb {
    public static boolean d() {
        if (f() || !g()) {
            return false;
        }
        if (SharedPreferenceManager.a(String.valueOf(PrebakedEffectId.RT_COIN_DROP), "isAddSleepCacheThreshold", false)) {
            a();
            e(false);
            LogUtil.a("ClearAudioDialogHelper", "sleepAudioCacheThreshold add 500MB");
        }
        if (!i()) {
            return false;
        }
        e(true);
        return true;
    }

    private static boolean f() {
        fca fcaVar = new fca(1000, 7, 0, null);
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "SLEEP_RECORD_ALARM_INFO");
        if (!TextUtils.isEmpty(b)) {
            fcaVar = (fca) HiJsonUtil.e(b, fca.class);
        }
        LogUtil.a("ClearAudioDialogHelper", "AutoClear: ", Boolean.valueOf(fcaVar.k()));
        return fcaVar.k();
    }

    public static void d(long j) {
        long j2 = j + 2592000000L;
        SharedPreferenceManager.e(String.valueOf(PrebakedEffectId.RT_COIN_DROP), "nextPopTime", j2);
        LogUtil.a("ClearAudioDialogHelper", "setNextPopTime: ", Long.valueOf(j2));
    }

    private static boolean g() {
        long currentTimeMillis = System.currentTimeMillis();
        long b = SharedPreferenceManager.b(String.valueOf(PrebakedEffectId.RT_COIN_DROP), "nextPopTime", 0L);
        LogUtil.a("ClearAudioDialogHelper", "currentTime: ", Long.valueOf(currentTimeMillis), "nextPopTime: ", Long.valueOf(b));
        return currentTimeMillis >= b;
    }

    private static boolean i() {
        long d = scl.d(BaseApplication.getContext(), "sleepMonitor");
        long b = b();
        LogUtil.a("ClearAudioDialogHelper", "sleepCacheSize: ", Long.valueOf(d), "sleepCacheThreshold: ", Long.valueOf(b));
        return d >= b;
    }

    public static String e() {
        return Formatter.formatShortFileSize(BaseApplication.getContext().getApplicationContext(), scl.d(BaseApplication.getContext(), "sleepMonitor"));
    }

    private static void a() {
        SharedPreferenceManager.e(String.valueOf(PrebakedEffectId.RT_COIN_DROP), "sleepCacheThreshold", b() + 524288000);
    }

    private static long b() {
        return SharedPreferenceManager.b(String.valueOf(PrebakedEffectId.RT_COIN_DROP), "sleepCacheThreshold", 524288000L);
    }

    public static void c() {
        long b = b();
        long d = scl.d(BaseApplication.getContext(), "sleepMonitor");
        if (d < b) {
            e(false);
            while (d < b) {
                b -= 524288000;
            }
            b += 524288000;
            SharedPreferenceManager.e(String.valueOf(PrebakedEffectId.RT_COIN_DROP), "sleepCacheThreshold", b);
        }
        LogUtil.a("ClearAudioDialogHelper", "sleepAudioCacheThreshold changed, now it's: ", Long.valueOf(b));
    }

    private static void e(boolean z) {
        SharedPreferenceManager.e(String.valueOf(PrebakedEffectId.RT_COIN_DROP), "isAddSleepCacheThreshold", z);
    }
}
