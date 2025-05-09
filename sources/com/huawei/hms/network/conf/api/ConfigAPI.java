package com.huawei.hms.network.conf.api;

import android.content.Context;
import android.os.Bundle;
import com.huawei.hms.network.embedded.j;
import com.huawei.hms.network.embedded.k;

/* loaded from: classes9.dex */
public class ConfigAPI {
    public static Bundle providerCall(Context context, String str, String str2, Bundle bundle) {
        return j.e().a(context, str, str2, bundle);
    }

    public static void init(Context context) {
        j.e().a(context);
    }

    public static Object getValue(String str) {
        Object a2 = j.e().a(str);
        return a2 == null ? k.b().a(str) : String.valueOf(a2);
    }
}
