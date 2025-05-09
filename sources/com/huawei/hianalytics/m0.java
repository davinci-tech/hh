package com.huawei.hianalytics;

import android.content.Context;

/* loaded from: classes4.dex */
public class m0 {

    /* renamed from: a, reason: collision with root package name */
    public static m0 f3885a;

    /* renamed from: a, reason: collision with other field name */
    public Context f56a;

    public static class a extends n0 {

        /* renamed from: a, reason: collision with root package name */
        public String f3886a;
        public String b;

        @Override // com.huawei.hianalytics.n0
        public int a() {
            b1 m552a = j.m552a(this.f3886a, this.b);
            int i = 0;
            int i2 = (m552a == null || !m552a.f23c) ? 0 : 4;
            b1 m552a2 = j.m552a(this.f3886a, this.b);
            int i3 = (m552a2 == null || !m552a2.f21a) ? 0 : 2;
            b1 m552a3 = j.m552a(this.f3886a, this.b);
            if (m552a3 != null && m552a3.f22b) {
                i = 1;
            }
            return i2 | i3 | i;
        }

        public a(String str, String str2) {
            this.f3886a = str;
            this.b = str2;
        }
    }

    public static m0 a() {
        m0 m0Var;
        synchronized (m0.class) {
            if (f3885a == null) {
                f3885a = new m0();
            }
            m0Var = f3885a;
        }
        return m0Var;
    }
}
