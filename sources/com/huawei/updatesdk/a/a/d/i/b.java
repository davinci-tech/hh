package com.huawei.updatesdk.a.a.d.i;

import android.content.Context;
import com.huawei.updatesdk.a.a.d.f;
import java.util.ArrayList;
import java.util.Set;

/* loaded from: classes7.dex */
public class b extends com.huawei.updatesdk.a.b.c.c.b {
    private String abis_;
    private String deviceFeatures_;
    private int dpi_;
    private String preferLan_;

    /* renamed from: com.huawei.updatesdk.a.a.d.i.b$b, reason: collision with other inner class name */
    public static class C0275b {

        /* renamed from: a, reason: collision with root package name */
        private final Context f10808a;
        private boolean b;
        private Set<String> c;
        private String[] d;
        private boolean e;

        public b a() {
            b bVar = new b();
            bVar.abis_ = f.a(c.i(), ",");
            bVar.dpi_ = Integer.parseInt(c.e(this.f10808a));
            bVar.preferLan_ = b();
            if (this.b) {
                bVar.deviceFeatures_ = c.a(this.f10808a);
            }
            return bVar;
        }

        public C0275b a(boolean z) {
            this.b = z;
            return this;
        }

        private String b() {
            ArrayList arrayList = new ArrayList(c.c(this.f10808a));
            Set<String> set = this.c;
            if (set != null) {
                for (String str : set) {
                    if (!arrayList.contains(str)) {
                        arrayList.add(str);
                    }
                }
            }
            return this.e ? f.a(c.a(arrayList, this.d), ",") : f.a(arrayList, ",");
        }

        public C0275b(Context context) {
            this.f10808a = context;
        }
    }

    private b() {
    }
}
