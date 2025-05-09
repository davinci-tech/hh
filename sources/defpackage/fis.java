package defpackage;

import android.content.Context;
import android.os.Bundle;
import com.huawei.health.sport.model.data.RecordPlanInfo;
import com.huawei.pluginfitnessadvice.FitWorkout;
import java.util.Map;

/* loaded from: classes.dex */
public class fis {
    private static final Object c = new Object();
    private static volatile fis d;

    private fis() {
    }

    public static fis d() {
        if (d == null) {
            synchronized (c) {
                if (d == null) {
                    d = new fis();
                }
            }
        }
        return d;
    }

    public void a(String str) {
        fiq.d(str);
    }

    public void e() {
        fiq.d();
    }

    public void a(Context context, int i, int i2, float f) {
        fiq.d(context, i, i2, f);
    }

    public int b(Context context, String str, Map<String, Object> map) {
        return fio.a(context, str, map);
    }

    public float b() {
        return fim.c();
    }

    public String a() {
        return fim.d();
    }

    public int axT_(Context context, FitWorkout fitWorkout, RecordPlanInfo recordPlanInfo, Bundle bundle) {
        return fiq.axU_(context, fitWorkout, recordPlanInfo, bundle);
    }

    public void a(Object obj, String str) {
        fiq.b(obj, str);
    }

    public void d(String str, Object obj, String str2) {
        fiq.b(str, obj, str2);
    }

    public void c(String str, int i) {
        fiq.d(str, i);
    }

    public void d(String str, String str2, int i) {
        fiq.a(str, str2, i);
    }

    public void b(String str) {
        fiq.e(str);
    }
}
