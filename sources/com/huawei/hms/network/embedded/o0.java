package com.huawei.hms.network.embedded;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.NetworkUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class o0 {
    public static final String e = "HttpDnsHelper";
    public static final String f = "httpdns_serviceId";

    /* renamed from: a, reason: collision with root package name */
    public String f5394a;
    public String b;
    public String c;
    public List<String> d;

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        public static final o0 f5395a = new o0();
    }

    public String d() {
        ApplicationInfo applicationInfo;
        if (!TextUtils.isEmpty(this.c)) {
            return this.c;
        }
        try {
            applicationInfo = ContextHolder.getAppContext().getPackageManager().getApplicationInfo(ContextHolder.getAppContext().getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException | RuntimeException e2) {
            Logger.w(e, "NameNotFoundException: ", e2);
            applicationInfo = null;
        }
        if (applicationInfo == null) {
            Logger.w(e, "appInfo == null ");
            return "";
        }
        Object obj = applicationInfo.metaData.get(f);
        if (obj == null) {
            return "";
        }
        this.c = obj.toString();
        Logger.v(e, "get serviceId form metaData：" + this.c);
        return this.c;
    }

    public String c() {
        if (TextUtils.isEmpty(this.f5394a)) {
            this.f5394a = ContextHolder.getResourceContext().getString(R.string._2130851405_res_0x7f02364d);
        }
        return this.f5394a;
    }

    public String b() {
        if (TextUtils.isEmpty(this.b)) {
            this.b = NetworkUtil.getHost(c());
        }
        return this.b;
    }

    public List<String> a() {
        List<String> list;
        synchronized (this) {
            if (this.d == null) {
                this.d = new ArrayList();
                for (String str : ContextHolder.getResourceContext().getResources().getStringArray(R.array._2130968687_res_0x7f04006f)) {
                    this.d.add(str);
                }
                if (this.d.isEmpty()) {
                    Logger.w(e, "current anyCastIps have not configured yet!");
                }
            }
            list = this.d;
        }
        return list;
    }

    public static o0 e() {
        return b.f5395a;
    }

    public o0() {
    }
}
