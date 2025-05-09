package com.huawei.hms.framework.network.grs.f;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.AssetsUtil;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.network.grs.GrsApp;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;

/* loaded from: classes4.dex */
public class b {
    private static final Map<String, b> b = new ConcurrentHashMap(16);
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private a f4537a;

    public Set<String> b() {
        return this.f4537a.c();
    }

    public boolean a(Context context, String str) {
        boolean d = this.f4537a.d(str);
        b.put(context.getPackageName(), this);
        return d;
    }

    public void a(String str, com.huawei.hms.framework.network.grs.e.c cVar) {
        String str2;
        try {
            cVar.b(str + "#localConfig", this.f4537a.a().toString());
        } catch (JSONException unused) {
            str2 = "save local config encounter JSONException.";
            Logger.w("LocalManagerProxy", str2);
        } catch (Throwable th) {
            str2 = "save local config encounter Throwable:" + StringUtils.anonymizeMessage(th.getMessage());
            Logger.w("LocalManagerProxy", str2);
        }
    }

    public Map<String, String> a(Context context, com.huawei.hms.framework.network.grs.e.a aVar, GrsBaseInfo grsBaseInfo, String str, boolean z) {
        Map<String, String> a2;
        synchronized (c) {
            a2 = this.f4537a.a(context, aVar, grsBaseInfo, str, z);
        }
        return a2;
    }

    public String a(Context context, com.huawei.hms.framework.network.grs.e.a aVar, GrsBaseInfo grsBaseInfo, String str, String str2, boolean z) {
        String a2;
        synchronized (c) {
            a2 = this.f4537a.a(context, aVar, grsBaseInfo, str, str2, z);
        }
        return a2;
    }

    public com.huawei.hms.framework.network.grs.local.model.a a() {
        return this.f4537a.b();
    }

    private void a(Context context, boolean z) {
        String[] split;
        long currentTimeMillis = System.currentTimeMillis();
        String a2 = com.huawei.hms.framework.network.grs.h.c.a("grs_route_config_files_list.txt", context);
        Logger.i("LocalManagerProxy", "initLocalManager configFileListContent TimeCost:%d  Content:%s", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), a2);
        if (TextUtils.isEmpty(a2)) {
            long currentTimeMillis2 = System.currentTimeMillis();
            split = AssetsUtil.list(context, GrsApp.getInstance().getBrand(""));
            Logger.i("LocalManagerProxy", "list by AssetsManager, timeCost:" + (System.currentTimeMillis() - currentTimeMillis2));
        } else {
            split = a2.split("#");
        }
        List<String> arrayList = split == null ? new ArrayList<>() : Arrays.asList(split);
        String appConfigName = GrsApp.getInstance().getAppConfigName();
        Logger.i("LocalManagerProxy", "appConfigName is: " + appConfigName);
        this.f4537a = new d(false, z);
        if (arrayList.contains("grs_app_global_route_config.json") || !TextUtils.isEmpty(appConfigName)) {
            this.f4537a = new d(context, appConfigName, z);
        }
        if (!this.f4537a.d() && arrayList.contains("grs_sdk_global_route_config.json")) {
            this.f4537a = new c(context, z);
        }
        this.f4537a.a(context, arrayList);
        StringBuilder sb = new StringBuilder("on initLocalManager finish, check appGrs: ");
        sb.append(this.f4537a.f4536a == null);
        Logger.i("LocalManagerProxy", sb.toString());
    }

    public static b a(String str) {
        return b.get(str);
    }

    public b(Context context, boolean z) {
        a(context, z);
        b.put(context.getPackageName(), this);
    }

    public b() {
        this.f4537a = new d(true, true);
    }
}
