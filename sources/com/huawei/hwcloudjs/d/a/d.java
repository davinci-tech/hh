package com.huawei.hwcloudjs.d.a;

import android.text.TextUtils;
import com.huawei.hwcloudjs.service.auth.bean.AuthBean;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static final String f6206a = "AuthUtils";

    public static boolean a(List<String> list, String str) {
        if (TextUtils.isEmpty(str) || list == null) {
            com.huawei.hwcloudjs.f.d.b(f6206a, "isUrlValid error!", true);
            return false;
        }
        synchronized (list) {
            if (list.size() == 0) {
                com.huawei.hwcloudjs.f.d.a(f6206a, "urlList is empty", true);
                return false;
            }
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                if (a(str, it.next())) {
                    com.huawei.hwcloudjs.f.d.c(f6206a, "isUrlValid patternStr OK", true);
                    return true;
                }
            }
            com.huawei.hwcloudjs.f.d.b(f6206a, "isUrlValid patternStr fail!", true);
            return false;
        }
    }

    public static boolean a(String str, List<String> list, AuthBean authBean) {
        com.huawei.hwcloudjs.f.d.c(f6206a, "checkH5App begin", true);
        com.huawei.hwcloudjs.f.d.c(f6206a, "checkH5App url：" + str, false);
        com.huawei.hwcloudjs.f.d.c(f6206a, "checkH5App permissionList：" + list, false);
        if (a(authBean.getUrlList(), str)) {
            return list.isEmpty() || (authBean.getPermissionList() != null && authBean.getPermissionList().containsAll(list));
        }
        return false;
    }

    private static boolean a(String str, String str2) {
        boolean z;
        boolean z2;
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        if (str2.startsWith("*")) {
            if (str2.length() > 1) {
                str2 = str2.substring(1);
            }
            z = true;
        } else {
            z = false;
        }
        if (str2.endsWith("*")) {
            str2 = str2.substring(0, str2.length() - 1);
            z2 = true;
        } else {
            z2 = false;
        }
        int indexOf = str.indexOf(str2);
        if (indexOf == -1) {
            return false;
        }
        if (indexOf > 0 && !z) {
            return false;
        }
        if ((str2.length() + indexOf) - 1 < str.length() - 1 && !z2) {
            return false;
        }
        int indexOf2 = str.indexOf("/", str.startsWith("http://") ? 7 : str.startsWith("https://") ? 8 : 0);
        return indexOf2 == -1 || indexOf2 > indexOf;
    }
}
