package com.huawei.updatesdk.b.c;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class d extends c {
    @Override // com.huawei.updatesdk.b.c.c
    public List<String> f() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("3BAF59A2E5331C30675FAB35FF5FFF0D116142D3D4664F1C3CB804068B40614F");
        arrayList.add("FFE391E0EA186D0734ED601E4E70E3224B7309D48E2075BAC46D8C667EAE7212");
        arrayList.add("A9436644E0BD71FF512C63839F8AC27114399F36956958688555DFCC63257EDE");
        return arrayList;
    }

    @Override // com.huawei.updatesdk.b.c.c
    String c() {
        return com.huawei.updatesdk.a.a.d.i.c.a("ro.product.manufacturer", "");
    }

    @Override // com.huawei.updatesdk.b.c.c
    public String b() {
        return "com.huawei.appmarket";
    }

    @Override // com.huawei.updatesdk.b.c.c
    String a() {
        String a2 = com.huawei.updatesdk.a.a.d.i.c.a("ro.product.hw_model", "");
        return (TextUtils.isEmpty(a2) || TextUtils.equals(a2, "unknown")) ? com.huawei.updatesdk.a.a.d.i.c.a("ro.product.hn_model", "") : a2;
    }
}
