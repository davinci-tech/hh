package com.huawei.maps.offlinedata.service.cloud.utils;

import android.content.Context;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.AGConnectOptions;
import com.huawei.maps.offlinedata.utils.g;

/* loaded from: classes5.dex */
public class a {
    public static String a(Context context, String str) {
        if (AGConnectInstance.getInstance() == null) {
            AGConnectInstance.initialize(context);
        }
        if (AGConnectInstance.getInstance() == null) {
            g.d("AgcCoreUtil", "Agc instance is null.");
            return "";
        }
        AGConnectOptions options = AGConnectInstance.getInstance().getOptions();
        return options != null ? options.getString(str, "") : "";
    }
}
