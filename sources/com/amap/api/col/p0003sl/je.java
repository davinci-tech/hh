package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.col.p0003sl.hz;
import com.amap.api.col.p0003sl.jg;
import java.util.List;

/* loaded from: classes2.dex */
public final class je {

    /* renamed from: a, reason: collision with root package name */
    private Context f1213a;
    private hz b;
    private boolean c = true;
    private String d = "40C27E38DCAD404B5465362914090908";
    private mz e = new mz("40C27E38DCAD404B5465362914090908");

    public final void a(Context context, boolean z, String str, String str2, String str3, String[] strArr) {
        try {
            hz a2 = new hz.a(str, str2, str).a(strArr).a(str3).a();
            if (context == null) {
                return;
            }
            Context applicationContext = context.getApplicationContext();
            this.f1213a = applicationContext;
            this.b = a2;
            this.c = z;
            this.e.a(applicationContext, a2);
        } catch (hm unused) {
        }
    }

    public final void a(String str, String str2) {
        List<hz> a2 = this.e.a(this.f1213a);
        jg jgVar = jg.a.f1215a;
        jg.a(this.f1213a, str, str2, a2, this.c, this.b);
    }
}
