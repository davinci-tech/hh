package com.huawei.hms.iap;

import android.app.Activity;
import android.content.Context;
import com.huawei.hms.utils.Checker;

/* loaded from: classes4.dex */
public class Iap {
    public static IapClient getIapClient(Context context, String str, boolean z) {
        Checker.assertNonNull(context);
        if (z) {
            Checker.assertNonNull(str, "Null productAppId is not permitted.");
        }
        return new d(context, str, z);
    }

    public static IapClient getIapClient(Context context, String str) {
        Checker.assertNonNull(context);
        Checker.assertNonNull(str);
        return new d(context, str);
    }

    public static IapClient getIapClient(Context context) {
        Checker.assertNonNull(context);
        return new d(context);
    }

    public static IapClient getIapClient(Activity activity, String str, boolean z) {
        Checker.assertNonNull(activity);
        if (z) {
            Checker.assertNonNull(str, "Null productAppId is not permitted.");
        }
        return new d(activity, str, z);
    }

    public static IapClient getIapClient(Activity activity, String str) {
        Checker.assertNonNull(activity);
        Checker.assertNonNull(str);
        return new d(activity, str);
    }

    public static IapClient getIapClient(Activity activity) {
        Checker.assertNonNull(activity);
        return new d(activity);
    }

    private Iap() {
    }
}
