package defpackage;

import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import health.compact.a.HarmonyBuild;
import health.compact.a.ProcessUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class kts {

    /* renamed from: a, reason: collision with root package name */
    private static int f14590a;
    private static Method d;
    private static int e;

    private kts() {
    }

    static {
        if (HarmonyBuild.d) {
            try {
                d = Class.forName("android.util.Jlog").getDeclaredMethod(FitRunPlayAudio.PLAY_TYPE_D, Integer.TYPE, String.class);
            } catch (ClassNotFoundException | NoSuchMethodException e2) {
                ReleaseLogUtil.c("R_ScenarioJlogUtil", "Exception: ", ExceptionUtils.d(e2));
            }
        }
    }

    public static void d(int i) {
        synchronized (kts.class) {
            if (d == null) {
                return;
            }
            int i2 = e;
            int i3 = i2 | i;
            if (i3 == i2) {
                ReleaseLogUtil.e("R_ScenarioJlogUtil", "enterCpu nothing todo, scenarioCode: 0x", Integer.toHexString(i));
            } else {
                e = i3;
                d(i3, 570);
            }
        }
    }

    public static void c(int i) {
        synchronized (kts.class) {
            if (d == null) {
                return;
            }
            int i2 = e;
            int i3 = (~i) & i2;
            if (i3 == i2) {
                ReleaseLogUtil.e("R_ScenarioJlogUtil", "exitCpu nothing todo, scenarioCode: 0x", Integer.toHexString(i));
            } else {
                e = i3;
                d(i3, 570);
            }
        }
    }

    public static void b(int i) {
        synchronized (kts.class) {
            if (d == null) {
                return;
            }
            int i2 = f14590a;
            int i3 = i2 | i;
            if (i3 == i2) {
                ReleaseLogUtil.e("R_ScenarioJlogUtil", "enterMem nothing todo, scenarioCode: 0x", Integer.toHexString(i));
            } else {
                f14590a = i3;
                d(i3, 571);
            }
        }
    }

    public static void a(int i) {
        synchronized (kts.class) {
            if (d == null) {
                return;
            }
            int i2 = f14590a;
            int i3 = (~i) & i2;
            if (i3 == i2) {
                ReleaseLogUtil.e("R_ScenarioJlogUtil", "exitMem nothing todo, scenarioCode: 0x", Integer.toHexString(i));
            } else {
                f14590a = i3;
                d(i3, 571);
            }
        }
    }

    private static void d(int i, int i2) {
        String str = "#P:" + ProcessUtil.b() + "#SD:" + i + "#FRT:" + System.currentTimeMillis();
        ReleaseLogUtil.e("R_ScenarioJlogUtil", "report info: ", str, ", scenarioCode: 0x", Integer.toHexString(i), ", jlogId: ", Integer.valueOf(i2));
        try {
            d.invoke(null, Integer.valueOf(i2), str);
        } catch (IllegalAccessException | InvocationTargetException e2) {
            ReleaseLogUtil.c("R_ScenarioJlogUtil", "JlogUtil Exception: ", ExceptionUtils.d(e2));
        }
    }
}
