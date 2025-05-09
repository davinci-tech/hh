package defpackage;

import android.os.Bundle;
import com.huawei.basefitnessadvice.callback.FitnessCallback;
import health.compact.a.LogUtil;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class fhp implements FitnessCallback {
    private int e = -1;
    private static Map<String, FitnessCallback> b = new LinkedHashMap();
    private static fhp d = new fhp();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f12517a = new Object();

    private fhp() {
    }

    public static fhp c() {
        return d;
    }

    public int b() {
        return this.e;
    }

    public void d(String str, FitnessCallback fitnessCallback) {
        synchronized (f12517a) {
            LogUtil.c("Suggestion_FitListenerUtil", "addListener tab=", str, " callback=", fitnessCallback);
            if (str != null && fitnessCallback != null) {
                b.put(str, fitnessCallback);
            }
        }
    }

    public void a(String str) {
        synchronized (f12517a) {
            LogUtil.c("Suggestion_FitListenerUtil", "removeListener = ", str);
            if (str != null) {
                b.remove(str);
            }
        }
    }

    @Override // com.huawei.basefitnessadvice.callback.FitnessCallback
    public void onChange(String str, int i, Bundle bundle) {
        LogUtil.c("Suggestion_FitListenerUtil", "onChange workoutId = ", str, ", type =", Integer.valueOf(i));
        this.e = i;
        synchronized (f12517a) {
            for (Map.Entry<String, FitnessCallback> entry : b.entrySet()) {
                LogUtil.c("Suggestion_FitListenerUtil", "onChange notify = ", entry.getKey());
                entry.getValue().onChange(str, i, bundle);
            }
        }
    }
}
