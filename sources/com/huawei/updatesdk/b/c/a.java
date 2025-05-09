package com.huawei.updatesdk.b.c;

import android.os.Build;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class a extends c {
    @Override // com.huawei.updatesdk.b.c.c
    public List<String> f() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("AF31957DA537B4791BD8F0B92B5ADCA38DBEC1743449CE90046CB05632BC02F3");
        return arrayList;
    }

    @Override // com.huawei.updatesdk.b.c.c
    String c() {
        return (com.huawei.updatesdk.b.h.b.d(com.huawei.updatesdk.a.b.a.a.c().a(), "com.huawei.appmarket.car.bmw") == null && com.huawei.updatesdk.b.h.b.d(com.huawei.updatesdk.a.b.a.a.c().a(), "com.huawei.appmarket.car.landscape") == null && com.huawei.updatesdk.b.h.b.d(com.huawei.updatesdk.a.b.a.a.c().a(), "com.huawei.appmarket.vehicle") == null) ? com.huawei.updatesdk.a.a.d.i.a.d() : "default";
    }

    @Override // com.huawei.updatesdk.b.c.c
    public String b() {
        if (com.huawei.updatesdk.b.h.b.d(com.huawei.updatesdk.a.b.a.a.c().a(), "com.huawei.appmarket.car.bmw") != null) {
            return "com.huawei.appmarket.car.bmw";
        }
        if (com.huawei.updatesdk.b.h.b.d(com.huawei.updatesdk.a.b.a.a.c().a(), "com.huawei.appmarket.vehicle") != null) {
            return "com.huawei.appmarket.vehicle";
        }
        if (com.huawei.updatesdk.b.h.b.d(com.huawei.updatesdk.a.b.a.a.c().a(), "com.huawei.appmarket.car.landscape") != null) {
            return "com.huawei.appmarket.car.landscape";
        }
        if (com.huawei.updatesdk.b.h.b.d(com.huawei.updatesdk.a.b.a.a.c().a(), "com.huawei.appmarket.car.volvo") != null) {
            return "com.huawei.appmarket.car.volvo";
        }
        if (com.huawei.updatesdk.b.h.b.d(com.huawei.updatesdk.a.b.a.a.c().a(), "com.huawei.appmarket.car.svw") != null) {
            return "com.huawei.appmarket.car.svw";
        }
        if (com.huawei.updatesdk.b.h.b.d(com.huawei.updatesdk.a.b.a.a.c().a(), "com.huawei.appmarket.car") != null) {
            return "com.huawei.appmarket.car";
        }
        return null;
    }

    @Override // com.huawei.updatesdk.b.c.c
    String a() {
        String g = g();
        if (!TextUtils.isEmpty(g)) {
            return g;
        }
        String i = i();
        if (!TextUtils.isEmpty(i)) {
            return i;
        }
        String j = j();
        if (!TextUtils.isEmpty(j)) {
            return j;
        }
        String e = com.huawei.updatesdk.a.a.d.i.a.e();
        return TextUtils.isEmpty(e) ? h() : e;
    }

    private String j() {
        if (com.huawei.updatesdk.b.h.b.d(com.huawei.updatesdk.a.b.a.a.c().a(), "com.huawei.appmarket.vehicle") == null) {
            return null;
        }
        return h();
    }

    private String i() {
        if (com.huawei.updatesdk.b.h.b.d(com.huawei.updatesdk.a.b.a.a.c().a(), "com.huawei.appmarket.car.landscape") == null) {
            return null;
        }
        String a2 = com.huawei.updatesdk.a.a.d.i.c.a("persist.vendor.vehicle.car_type", "");
        if (!TextUtils.isEmpty(a2)) {
            return a2;
        }
        String a3 = com.huawei.updatesdk.a.a.d.i.c.a("ro.product.ext.model", "");
        return !TextUtils.isEmpty(a3) ? a3 : h();
    }

    private String h() {
        String str = Build.MODEL;
        return (TextUtils.isEmpty(str) || TextUtils.equals("unknown", str)) ? "default" : str;
    }

    private String g() {
        if (com.huawei.updatesdk.b.h.b.d(com.huawei.updatesdk.a.b.a.a.c().a(), "com.huawei.appmarket.car.bmw") == null) {
            return null;
        }
        String h = h();
        String a2 = com.huawei.updatesdk.a.a.d.i.c.a("ro.vendor.build.RSEappstoredistributionscope", "");
        if (TextUtils.isEmpty(a2)) {
            return h;
        }
        return h + "_" + a2;
    }
}
