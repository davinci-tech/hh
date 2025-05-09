package com.huawei.health.h5pro.utils;

import android.text.TextUtils;
import com.huawei.health.h5pro.vengine.H5ProAppInfo;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

/* loaded from: classes3.dex */
public class AppInfoUtil {
    public static AppInfoUtil b;

    public String getPkgNameFromUrl(String str, String str2) {
        int indexOf;
        int i;
        int indexOf2;
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !str.startsWith(str2) || (indexOf = str.indexOf("/h5pro")) == -1 || (indexOf2 = str.indexOf(47, (i = indexOf + 7))) == -1) ? "" : str.substring(i, indexOf2);
    }

    public String getHostOrPkgNameFroUrl(String str, H5ProAppInfo h5ProAppInfo) {
        if (h5ProAppInfo != null) {
            String pkgNameFromUrl = getPkgNameFromUrl(str, h5ProAppInfo.getBaseUrl());
            if (!TextUtils.isEmpty(pkgNameFromUrl)) {
                return pkgNameFromUrl;
            }
        }
        return getHostFromUrl(str);
    }

    public String getHostFromUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return new URL(str).getHost();
        } catch (MalformedURLException e) {
            LogUtil.e("H5PRO_AppInfoUtil", "getHostOrPkgName MalformedURLException:" + e.getMessage());
            return "";
        }
    }

    public String getBaseUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] split = str.toLowerCase(Locale.ENGLISH).split("\\.");
        StringBuilder sb = new StringBuilder();
        for (String str2 : split) {
            sb.insert(0, ".");
            sb.insert(0, str2);
        }
        sb.insert(0, "https://");
        return sb.substring(0, sb.length() - 1);
    }

    public static AppInfoUtil getInstance() {
        if (b == null) {
            synchronized (AppInfoUtil.class) {
                if (b == null) {
                    b = new AppInfoUtil();
                }
            }
        }
        return b;
    }
}
