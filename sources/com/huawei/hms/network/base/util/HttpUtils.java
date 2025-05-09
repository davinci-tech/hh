package com.huawei.hms.network.base.util;

import android.text.TextUtils;

/* loaded from: classes4.dex */
public class HttpUtils {
    public static final String HTTPS_PREFIX = "https://";
    public static final String HTTP_PREFIX = "http://";

    /* renamed from: a, reason: collision with root package name */
    private static final String f5135a = "HttpUtils";

    public static boolean isHttpUrl(String str) {
        return !TextUtils.isEmpty(str) && (str.startsWith("http://") || str.startsWith("https://"));
    }

    public static boolean isHttpOrGrsUrl(String str) {
        return isHttpUrl(str) || str.startsWith("grs://");
    }
}
