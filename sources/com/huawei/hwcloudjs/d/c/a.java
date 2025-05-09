package com.huawei.hwcloudjs.d.c;

import com.huawei.hwcloudjs.e.b.c;
import com.huawei.hwcloudjs.e.b.d;

/* loaded from: classes5.dex */
public class a extends c<C0158a> {
    private static a b = new a();

    /* renamed from: com.huawei.hwcloudjs.d.c.a$a, reason: collision with other inner class name */
    public static final class C0158a extends d {

        /* renamed from: a, reason: collision with root package name */
        private int f6213a;
        private int[] b;

        public int b() {
            return this.f6213a;
        }

        public int[] a() {
            int[] iArr = this.b;
            return iArr != null ? (int[]) iArr.clone() : new int[0];
        }

        public void a(int[] iArr) {
            if (iArr != null) {
                this.b = (int[]) iArr.clone();
            }
        }

        public void a(int i) {
            this.f6213a = i;
        }
    }

    public static a a() {
        return b;
    }
}
