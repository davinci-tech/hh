package com.huawei.hwcloudjs.d.a;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwcloudjs.api.ValidateWhiteListListener;
import com.huawei.hwcloudjs.service.auth.bean.AuthBean;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* loaded from: classes5.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    private static final String f6204a = "AuthManager";
    private static ValidateWhiteListListener b;
    private static c c = new c();
    private final HashMap<String, AuthBean> d = new HashMap<>();
    private final ArrayList<String> e = new ArrayList<>();
    private final com.huawei.hwcloudjs.d.a.a f = new com.huawei.hwcloudjs.d.a.a();

    /* loaded from: classes9.dex */
    public interface a {

        /* renamed from: a, reason: collision with root package name */
        public static final int f6205a = 0;
        public static final int b = -1;
        public static final int c = -2;
        public static final int d = -3;
        public static final int e = -4;

        void a(int i);
    }

    public void b(String str) {
        synchronized (this.e) {
            if (!this.e.contains(str)) {
                this.e.add(str);
            }
        }
    }

    public boolean a(String str, String str2, List<String> list) {
        boolean z;
        synchronized (this.d) {
            AuthBean authBean = this.d.get(str);
            z = authBean != null && d.a(str2, list, authBean);
        }
        return z;
    }

    public boolean a(String str, String str2, Context context) {
        com.huawei.hwcloudjs.service.auth.bean.b bVar = (com.huawei.hwcloudjs.service.auth.bean.b) com.huawei.hwcloudjs.d.b.b.a(new com.huawei.hwcloudjs.service.auth.bean.a(str), context);
        return bVar.c() == 1 && bVar.e() == 0 && d.a(bVar.f(), str2);
    }

    public boolean a(String str) {
        com.huawei.hwcloudjs.f.d.c(f6204a, "isUrlWhileList begin", true);
        if (!TextUtils.isEmpty(str)) {
            return c(str) || d(str);
        }
        com.huawei.hwcloudjs.f.d.b(f6204a, "isUrlWhileList isEmpty", true);
        return false;
    }

    public void a(String str, String str2, List<String> list, a aVar, Context context) {
        com.huawei.hwcloudjs.f.d.c(f6204a, "auth begin", true);
        com.huawei.hwcloudjs.f.d.c(f6204a, "auth appId:" + str, false);
        com.huawei.hwcloudjs.f.d.c(f6204a, "auth url:" + str2, false);
        if (!a(str, str2, list)) {
            new Thread(new b(this, str, str2, list, aVar, context)).start();
        } else {
            com.huawei.hwcloudjs.f.d.c(f6204a, "auth memoryAuth", true);
            aVar.a(0);
        }
    }

    private boolean d(String str) {
        com.huawei.hwcloudjs.f.d.c(f6204a, "isUrlAllowDomin begin", true);
        try {
            String host = new URL(str).getHost();
            if (TextUtils.isEmpty(host)) {
                return false;
            }
            synchronized (this.e) {
                if (this.e.size() == 0) {
                    com.huawei.hwcloudjs.f.d.b(f6204a, "getSercuredUrl empty ,true", true);
                    return false;
                }
                Iterator<String> it = this.e.iterator();
                while (it.hasNext()) {
                    try {
                    } catch (PatternSyntaxException unused) {
                        com.huawei.hwcloudjs.f.d.b(f6204a, "PatternSyntaxException", true);
                    }
                    if (Pattern.compile(it.next()).matcher(host).matches()) {
                        com.huawei.hwcloudjs.f.d.b(f6204a, "pattern true", true);
                        return true;
                    }
                    continue;
                }
                com.huawei.hwcloudjs.f.d.b(f6204a, "isUrlWhileList false!", true);
                return false;
            }
        } catch (MalformedURLException unused2) {
            com.huawei.hwcloudjs.f.d.b(f6204a, "isUrlWhileList MalformedURLException", true);
            return false;
        }
    }

    private boolean c(String str) {
        com.huawei.hwcloudjs.f.d.c(f6204a, "isUrlAllow begin", true);
        synchronized (this.e) {
            if (this.e.size() == 0) {
                com.huawei.hwcloudjs.f.d.b(f6204a, "getSercuredUrl empty", true);
                return false;
            }
            Iterator<String> it = this.e.iterator();
            while (it.hasNext()) {
                if (Pattern.matches(it.next(), str)) {
                    return true;
                }
            }
            com.huawei.hwcloudjs.f.d.b(f6204a, "isUrlWhileList false!", true);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, String str2, List<String> list, a aVar, Context context) {
        int b2;
        com.huawei.hwcloudjs.f.d.c(f6204a, "getAuthFromGw begin", true);
        com.huawei.hwcloudjs.f.d.c(f6204a, "getAuthFromGw appId:" + str, false);
        com.huawei.hwcloudjs.f.d.c(f6204a, "getAuthFromGw url:" + str2, false);
        com.huawei.hwcloudjs.f.d.c(f6204a, "getAuthFromGw permissionList:" + list, false);
        if (!com.huawei.hwcloudjs.f.b.f()) {
            com.huawei.hwcloudjs.f.d.b(f6204a, "isNetOpen is not open", true);
            aVar.a(-2);
            return;
        }
        com.huawei.hwcloudjs.service.auth.bean.b bVar = (com.huawei.hwcloudjs.service.auth.bean.b) com.huawei.hwcloudjs.d.b.b.a(new com.huawei.hwcloudjs.service.auth.bean.a(str), context);
        if (bVar.c() != 1) {
            b2 = bVar.c() == 2 ? bVar.b() : bVar.c() == 3 ? -3 : -4;
        } else if (bVar.e() == 0) {
            com.huawei.hwcloudjs.f.d.c(f6204a, "response getUrlList:" + bVar.f(), false);
            com.huawei.hwcloudjs.f.d.c(f6204a, "response getPermissionList:" + bVar.d(), false);
            AuthBean authBean = new AuthBean();
            authBean.setUrlList(bVar.f());
            authBean.setPermissionList(bVar.d());
            authBean.setAppId(str);
            authBean.setTimestamp(System.currentTimeMillis());
            if (d.a(str2, list, authBean)) {
                aVar.a(0);
                a(str, authBean);
                a(authBean);
                return;
            }
            com.huawei.hwcloudjs.f.d.b(f6204a, "getAuthFromGw checkH5App failed", true);
            b2 = -1;
        } else {
            com.huawei.hwcloudjs.f.d.b(f6204a, "getAuthFromGw callback failed", true);
            b2 = bVar.e();
        }
        aVar.a(b2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, AuthBean authBean) {
        synchronized (this.d) {
            this.d.put(str, authBean);
        }
    }

    private void a(AuthBean authBean) {
        this.f.a(authBean);
    }

    public static void a(ValidateWhiteListListener validateWhiteListListener) {
        b = validateWhiteListListener;
    }

    public static c a() {
        return c;
    }

    private c() {
    }
}
