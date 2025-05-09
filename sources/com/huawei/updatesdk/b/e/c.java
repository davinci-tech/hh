package com.huawei.updatesdk.b.e;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.appgallery.serviceverifykit.api.ServiceVerifyKit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class c extends a {
    private String c = null;
    private String d = "";

    @Override // com.huawei.updatesdk.b.e.a
    public boolean c(Context context) {
        return com.huawei.updatesdk.b.h.b.f(context, b()) > 100200000;
    }

    @Override // com.huawei.updatesdk.b.e.a
    public String b() {
        if (TextUtils.isEmpty(this.c)) {
            com.huawei.updatesdk.a.a.a.b("DefaultTaskInit", "appgallery packagename is null and query again!");
            this.c = b(new ArrayList());
        }
        return this.c;
    }

    @Override // com.huawei.updatesdk.b.e.a
    public void a(List<String> list) {
        Context a2 = com.huawei.updatesdk.a.b.a.a.c().a();
        if (!TextUtils.isEmpty(this.c) && com.huawei.updatesdk.b.h.b.g(a2, this.c)) {
            com.huawei.updatesdk.a.a.a.b("DefaultTaskInit", "AppMarket packageName has been obtained and is: " + this.c);
            com.huawei.updatesdk.b.b.a.d().d(this.c);
            return;
        }
        String b = b(list);
        this.c = b;
        if (TextUtils.isEmpty(b)) {
            c();
        }
        com.huawei.updatesdk.b.b.a.d().d(this.c);
        com.huawei.updatesdk.a.a.a.b("DefaultTaskInit", "initAppGalleryPkg and get AppMarket packageName is: " + this.c);
    }

    @Override // com.huawei.updatesdk.b.e.a
    public void a(String str) {
        com.huawei.updatesdk.a.a.a.b("DefaultTaskInit", "resetMarketPkgName: " + str);
        this.c = str;
    }

    @Override // com.huawei.updatesdk.b.e.a
    String a(Context context) {
        if (!TextUtils.isEmpty(this.d)) {
            return this.d;
        }
        if (TextUtils.equals("SECURITY", com.huawei.updatesdk.a.a.c.a.a.b.a())) {
            this.d = d.a(context, "grs_sdk_global_route_config_updatesdk.json", "SECURITY");
            com.huawei.updatesdk.a.a.a.b("DefaultTaskInit", "UpdateSDK Get url is security url");
        } else {
            this.d = d.a(context, "grs_sdk_global_route_config_updatesdk.json", "DR3");
        }
        return this.d;
    }

    @Override // com.huawei.updatesdk.b.e.a
    String a() {
        return "com.huawei.updatesdk";
    }

    @Override // com.huawei.updatesdk.b.e.a
    public int a(Context context, String str) {
        return com.huawei.updatesdk.b.h.b.c(context, str);
    }

    private void c() {
        com.huawei.updatesdk.a.b.c.c.c.c(b(com.huawei.updatesdk.a.b.a.a.c().a()));
        com.huawei.updatesdk.a.b.c.c.d a2 = com.huawei.updatesdk.b.g.b.a(new com.huawei.updatesdk.service.appmgr.bean.a(null));
        if (a2.e()) {
            com.huawei.updatesdk.service.appmgr.bean.b bVar = (com.huawei.updatesdk.service.appmgr.bean.b) a2;
            if (bVar.f() != null) {
                this.c = bVar.f().d();
            } else {
                com.huawei.updatesdk.a.a.a.b("DefaultTaskInit", "can not getPackageName from store, HiApp info is null.");
            }
        }
    }

    private String b(List<String> list) {
        String str = null;
        try {
            Context a2 = com.huawei.updatesdk.a.b.a.a.c().a();
            ServiceVerifyKit.Builder builder = new ServiceVerifyKit.Builder();
            builder.b(a2).hN_(new Intent("com.huawei.appmarket.appmarket.intent.action.AppDetail.withdetailId"), ServiceVerifyKit.Builder.ComponentType.ACTIVITY);
            a(list, a2, builder);
            com.huawei.updatesdk.b.c.c a3 = com.huawei.updatesdk.b.c.b.a();
            Iterator<String> it = a3.f().iterator();
            while (it.hasNext()) {
                builder.a(a3.b(), it.next());
            }
            str = builder.d();
            com.huawei.updatesdk.a.a.a.b("DefaultTaskInit", "get market packagename from verify kit is: " + str);
            return str;
        } catch (Throwable th) {
            com.huawei.updatesdk.a.a.a.c("DefaultTaskInit", "genVerifiedPackageName error: " + th.getMessage());
            return str;
        }
    }

    private void a(List<String> list, Context context, ServiceVerifyKit.Builder builder) {
        if (com.huawei.updatesdk.b.h.d.a(list) || list.size() >= 2) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String a2 = com.huawei.updatesdk.b.h.b.a(context, it.next());
            if (!TextUtils.isEmpty(a2)) {
                arrayList.add(a2);
            }
        }
        if (com.huawei.updatesdk.b.h.d.a(arrayList)) {
            return;
        }
        builder.a(arrayList);
    }
}
