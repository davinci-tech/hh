package com.huawei.haf.application;

import android.app.Application;
import android.content.Context;
import com.huawei.haf.common.exception.HafRuntimeException;

/* loaded from: classes.dex */
public final class BaseApplicationCompat {
    private BaseApplicationCompat() {
    }

    public static void e(Context context) {
        if (context instanceof Application) {
            BaseApplication.wf_((Application) context);
            return;
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext instanceof Application) {
            BaseApplication.wf_((Application) applicationContext);
            return;
        }
        throw new HafRuntimeException("setBaseContext appContext is not Application type");
    }
}
