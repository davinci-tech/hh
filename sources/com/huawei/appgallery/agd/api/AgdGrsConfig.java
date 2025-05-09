package com.huawei.appgallery.agd.api;

import android.content.Context;
import com.huawei.appgallery.coreservice.api.CoreServiceApi;

/* loaded from: classes8.dex */
public class AgdGrsConfig {
    public static void setHomeCountry(Context context, String str) {
        CoreServiceApi.setHomeCountry(context, str);
    }

    @Deprecated
    public static void setAppName(Context context, String str) {
        CoreServiceApi.setAppName(context, str);
    }
}
