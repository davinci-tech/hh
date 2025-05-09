package com.huawei.hms.kit.awareness;

import android.app.Activity;
import android.content.Context;
import com.huawei.hms.kit.awareness.b.HHE;

/* loaded from: classes4.dex */
public final class Awareness {
    public static DonateClient getDonateClient(Context context) {
        return HHE.a(context);
    }

    public static DonateClient getDonateClient(Activity activity) {
        return HHE.a(activity);
    }

    public static CaptureClient getCaptureClient(Context context) {
        return HHE.a(context);
    }

    public static CaptureClient getCaptureClient(Activity activity) {
        return HHE.a(activity);
    }

    public static BarrierClient getBarrierClient(Context context) {
        return HHE.a(context);
    }

    public static BarrierClient getBarrierClient(Activity activity) {
        return HHE.a(activity);
    }

    private Awareness() {
    }
}
