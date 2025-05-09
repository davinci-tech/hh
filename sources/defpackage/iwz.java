package defpackage;

import com.huawei.hihealth.motion.HealthOpenSDK;

/* loaded from: classes4.dex */
public class iwz {
    private static HealthOpenSDK c;

    public static HealthOpenSDK b() {
        if (c == null) {
            c = new HealthOpenSDK();
        }
        return c;
    }
}
