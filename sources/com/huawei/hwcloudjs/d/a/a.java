package com.huawei.hwcloudjs.d.a;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.huawei.hwcloudjs.service.auth.bean.AuthBean;
import java.io.File;
import java.io.UnsupportedEncodingException;

/* loaded from: classes5.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static final String f6202a = "AuthCache";
    private static final String b = "jssdkcache";
    private static final long c = 86400000;

    public AuthBean b(String str) {
        String d = d(str);
        if (d != null && new File(d).exists()) {
            return (AuthBean) new f(d).b();
        }
        return null;
    }

    public void a(AuthBean authBean) {
        String d = d(authBean.getAppId());
        if (d == null) {
            return;
        }
        new f(d).a(authBean);
    }

    public AuthBean a(String str) {
        com.huawei.hwcloudjs.f.d.c(f6202a, "getValidCache begin", false);
        AuthBean b2 = b(str);
        if (b2 == null) {
            return null;
        }
        if (!b(b2)) {
            return b2;
        }
        e(str);
        return null;
    }

    private void e(String str) {
        String d = d(str);
        if (d == null) {
            return;
        }
        File file = new File(d);
        if (!file.exists() || file.delete()) {
            return;
        }
        com.huawei.hwcloudjs.f.d.b(f6202a, "removeFile error", true);
    }

    private String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String c2 = c(str);
        if (TextUtils.isEmpty(c2)) {
            return str;
        }
        return a(com.huawei.hwcloudjs.b.a.a(), b) + c2;
    }

    private String c(String str) {
        if (str == null) {
            return null;
        }
        try {
            return Base64.encodeToString(str.getBytes("UTF-8"), 2);
        } catch (UnsupportedEncodingException unused) {
            return String.valueOf(str.hashCode());
        }
    }

    private boolean b(AuthBean authBean) {
        long currentTimeMillis = System.currentTimeMillis() - authBean.getTimestamp();
        return currentTimeMillis >= 86400000 || currentTimeMillis < 0;
    }

    private String a(Context context, String str) {
        File cacheDir;
        if (context == null || (cacheDir = context.getCacheDir()) == null) {
            return null;
        }
        File file = new File(cacheDir.getPath() + File.separator + str + File.separator);
        if (!file.exists() && !file.mkdir()) {
            com.huawei.hwcloudjs.f.d.b(f6202a, "getCacheDir mkdir error", true);
        }
        return file.getAbsolutePath() + File.separator;
    }
}
