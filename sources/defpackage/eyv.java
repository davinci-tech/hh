package defpackage;

import android.content.Context;
import android.os.Bundle;
import com.huawei.health.pressure.PressureApi;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;

/* loaded from: classes3.dex */
public class eyv {
    private static PressureApi a() {
        return (PressureApi) Services.a("Main", PressureApi.class);
    }

    public static void atT_(Context context, Bundle bundle) {
        PressureApi a2 = a();
        if (a2 == null) {
            ReleaseLogUtil.a("R_EmotionStressUtil", "gotoEmotionStress pressureApi is null");
        } else {
            a2.gotoPressure(context, bundle);
        }
    }
}
