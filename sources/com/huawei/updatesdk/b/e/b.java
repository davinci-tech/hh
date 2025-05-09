package com.huawei.updatesdk.b.e;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.updatesdk.service.otaupdate.f;
import java.util.List;

/* loaded from: classes7.dex */
public class b extends a {
    private String c = "";

    @Override // com.huawei.updatesdk.b.e.a
    public int a(Context context, String str) {
        return 34;
    }

    @Override // com.huawei.updatesdk.b.e.a
    public void a(List<String> list) {
    }

    @Override // com.huawei.updatesdk.b.e.a
    public boolean c(Context context) {
        return com.huawei.updatesdk.b.h.b.g(context, b());
    }

    @Override // com.huawei.updatesdk.b.e.a
    public String b() {
        return f.e().c();
    }

    @Override // com.huawei.updatesdk.b.e.a
    String a(Context context) {
        if (!TextUtils.isEmpty(this.c)) {
            return this.c;
        }
        String a2 = TextUtils.equals("SECURITY", com.huawei.updatesdk.a.a.c.a.a.b.a()) ? d.a(context, "grs_sdk_global_route_config_apptouchupdatesdk.json", "SECURITY") : d.a(context, "grs_sdk_global_route_config_apptouchupdatesdk.json", "DR3");
        this.c = a2;
        return a2;
    }

    @Override // com.huawei.updatesdk.b.e.a
    String a() {
        return "com.huawei.apptouch.updatesdk";
    }
}
