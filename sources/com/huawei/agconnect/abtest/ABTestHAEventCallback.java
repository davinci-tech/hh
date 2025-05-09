package com.huawei.agconnect.abtest;

import android.content.Context;
import android.os.Bundle;
import com.huawei.agconnect.abtest.a.b;
import com.huawei.agconnect.common.api.Logger;

/* loaded from: classes8.dex */
public class ABTestHAEventCallback {
    private b impl = new b();

    public void onEvent(String str, Bundle bundle, Bundle bundle2) {
        Logger.d("ABTest", "abtest ha event :" + str);
        this.impl.a(str, bundle, bundle2);
    }

    public void initialize(Context context) {
        this.impl.a(context);
    }
}
