package com.huawei.hms.framework.network.util;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.framework.common.PackageUtils;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.network.restclient.Version;

/* loaded from: classes4.dex */
public class HttpUtils {
    public static final String HTTPS_PREFIX = "https://";
    public static final String HTTP_PREFIX = "http://";
    private static final String TAG = "HttpUtils";

    public static boolean isHttpUrl(String str) {
        return !TextUtils.isEmpty(str) && (str.startsWith("http://") || str.startsWith("https://"));
    }

    public static boolean isHttpOrGrsUrl(String str) {
        return isHttpUrl(str) || str.startsWith("grs://");
    }

    public static String getAgent(Context context) {
        if (context == null) {
            return StringUtils.format("RestClient/%s", Version.getVersion());
        }
        return StringUtils.format("%s/%s (Linux; Android %s; %s) RestClient/%s", context.getPackageName(), PackageUtils.getVersionName(context), Build.VERSION.RELEASE, Build.MODEL, Version.getVersion());
    }
}
