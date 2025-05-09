package com.huawei.hms.maps.utils;

import android.content.Context;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.AGConnectOptions;

/* loaded from: classes4.dex */
public class AgcCoreUtil {
    public static String getString(Context context, String str) {
        if (AGConnectInstance.getInstance() == null) {
            AGConnectInstance.initialize(context);
        }
        if (AGConnectInstance.getInstance() == null) {
            LogM.e("AgcCoreUtil", "Agc instance is null.");
            return "";
        }
        AGConnectOptions options = AGConnectInstance.getInstance().getOptions();
        return options != null ? options.getString(str, "") : "";
    }
}
