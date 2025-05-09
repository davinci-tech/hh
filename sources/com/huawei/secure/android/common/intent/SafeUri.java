package com.huawei.secure.android.common.intent;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes9.dex */
public class SafeUri {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8612a = "SafeUri";
    private static final String b = "";

    private SafeUri() {
    }

    public static boolean getBooleanQueryParameter(Uri uri, String str, boolean z) {
        if (uri != null && !TextUtils.isEmpty(str)) {
            try {
                return uri.getBooleanQueryParameter(str, z);
            } catch (Exception e) {
                Log.e(f8612a, "getBooleanQueryParameter: " + e.getMessage());
            }
        }
        return z;
    }

    public static String getQueryParameter(Uri uri, String str) {
        if (uri == null || TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return uri.getQueryParameter(str);
        } catch (Exception e) {
            Log.e(f8612a, "getQueryParameter: " + e.getMessage());
            return "";
        }
    }

    public static Set<String> getQueryParameterNames(Uri uri) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (uri == null) {
            return linkedHashSet;
        }
        try {
            return uri.getQueryParameterNames();
        } catch (Exception e) {
            Log.e(f8612a, "getQueryParameterNames: " + e.getMessage());
            return linkedHashSet;
        }
    }

    public static List<String> getQueryParameters(Uri uri, String str) {
        ArrayList arrayList = new ArrayList();
        if (uri != null && !TextUtils.isEmpty(str)) {
            try {
                return uri.getQueryParameters(str);
            } catch (Exception e) {
                Log.e(f8612a, "getQueryParameters: " + e.getMessage());
            }
        }
        return arrayList;
    }
}
