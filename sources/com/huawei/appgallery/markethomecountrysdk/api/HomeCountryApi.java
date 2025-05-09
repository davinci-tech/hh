package com.huawei.appgallery.markethomecountrysdk.api;

import android.content.Context;
import com.huawei.hmf.tasks.Task;
import defpackage.afs;

/* loaded from: classes3.dex */
public class HomeCountryApi {
    public static Task<String> getHomeCountry(Context context, String str, boolean z) {
        return afs.c(context, str, z);
    }
}
