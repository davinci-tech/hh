package com.huawei.hms.framework.network.grs.e;

import android.content.Context;
import android.content.pm.PackageManager;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.PLSharedPreferences;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class c {
    private static final String b = "c";
    private static final Map<String, PLSharedPreferences> c = new ConcurrentHashMap(16);

    /* renamed from: a, reason: collision with root package name */
    private final PLSharedPreferences f4535a;

    public void b(String str, String str2) {
        PLSharedPreferences pLSharedPreferences = this.f4535a;
        if (pLSharedPreferences == null) {
            return;
        }
        synchronized (pLSharedPreferences) {
            this.f4535a.putString(str, str2);
        }
    }

    public void b() {
        PLSharedPreferences pLSharedPreferences = this.f4535a;
        if (pLSharedPreferences == null) {
            return;
        }
        synchronized (pLSharedPreferences) {
            this.f4535a.clear();
        }
    }

    public void a(String str) {
        PLSharedPreferences pLSharedPreferences = this.f4535a;
        if (pLSharedPreferences == null) {
            return;
        }
        synchronized (pLSharedPreferences) {
            this.f4535a.remove(str);
        }
    }

    public Map<String, ?> a() {
        Map<String, ?> all;
        PLSharedPreferences pLSharedPreferences = this.f4535a;
        if (pLSharedPreferences == null) {
            return new HashMap();
        }
        synchronized (pLSharedPreferences) {
            all = this.f4535a.getAll();
        }
        return all;
    }

    public String a(String str, String str2) {
        String string;
        PLSharedPreferences pLSharedPreferences = this.f4535a;
        if (pLSharedPreferences == null) {
            return str2;
        }
        synchronized (pLSharedPreferences) {
            string = this.f4535a.getString(str, str2);
        }
        return string;
    }

    private void a(Context context) {
        String str = b;
        Logger.i(str, "ContextHolder.getAppContext() from GRS is:" + ContextHolder.getAppContext());
        if (ContextHolder.getAppContext() != null) {
            context = ContextHolder.getAppContext();
        }
        try {
            String l = Long.toString(context.getPackageManager().getPackageInfo(context.getPackageName(), 16384).versionCode);
            String a2 = a("version", "");
            if (l.equals(a2)) {
                return;
            }
            Logger.i(str, "app version changed! old version{%s} and new version{%s}", a2, l);
            b();
            b("version", l);
        } catch (PackageManager.NameNotFoundException | RuntimeException unused) {
            Logger.w(b, "get app version failed and catch NameNotFoundException");
        }
    }

    public c(Context context, String str) {
        String packageName = context.getPackageName();
        Logger.d(b, "get pkgname from context is{%s}", packageName);
        Map<String, PLSharedPreferences> map = c;
        if (map.containsKey(str + packageName)) {
            this.f4535a = map.get(str + packageName);
        } else {
            PLSharedPreferences pLSharedPreferences = new PLSharedPreferences(context, str + packageName);
            this.f4535a = pLSharedPreferences;
            map.put(str + packageName, pLSharedPreferences);
        }
        a(context);
    }
}
