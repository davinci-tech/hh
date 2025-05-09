package com.huawei.agconnect.credential.obs;

import android.text.TextUtils;
import com.huawei.agconnect.common.api.Logger;
import java.net.MalformedURLException;
import java.net.URL;

/* loaded from: classes8.dex */
public class x {

    /* renamed from: a, reason: collision with root package name */
    private static final String f1785a = "SafeUrl";

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            Logger.i(f1785a, "url is null");
            return str;
        }
        try {
            return new URL(str.replaceAll("[\\\\#]", "/")).getHost();
        } catch (MalformedURLException e) {
            Logger.e(f1785a, "getHostByURI error  MalformedURLException : " + e.getMessage());
            return "";
        }
    }
}
