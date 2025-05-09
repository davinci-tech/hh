package com.huawei.hms.support.api.paytask;

import android.app.Activity;
import android.content.Context;
import com.huawei.hms.utils.Checker;

/* loaded from: classes4.dex */
public class Pay {
    public static PayClient getPayClient(Context context) {
        Checker.assertNonNull(context);
        return new PayClientEntryImpl(context);
    }

    public static PayClient getPayClient(Activity activity) {
        Checker.assertNonNull(activity);
        return new PayClientEntryImpl(activity);
    }

    private Pay() {
    }
}
