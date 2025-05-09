package com.huawei.hms.scankit.p;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public final class q6 {

    /* renamed from: a, reason: collision with root package name */
    private final o3 f5864a;
    private final List<p3> b;

    public q6(o3 o3Var) {
        this.f5864a = o3Var;
        ArrayList arrayList = new ArrayList();
        this.b = arrayList;
        arrayList.add(new p3(o3Var, new int[]{1}));
    }

    private p3 a(int i) {
        if (i >= this.b.size()) {
            List<p3> list = this.b;
            p3 p3Var = list.get(list.size() - 1);
            for (int size = this.b.size(); size <= i; size++) {
                o3 o3Var = this.f5864a;
                p3Var = p3Var.c(new p3(o3Var, new int[]{1, o3Var.a((size - 1) + o3Var.a())}));
                this.b.add(p3Var);
            }
        }
        return this.b.get(i);
    }

    public void a(int[] iArr, int i) {
        if (i != 0) {
            int length = iArr.length - i;
            if (length > 0) {
                p3 a2 = a(i);
                int[] iArr2 = new int[length];
                System.arraycopy(iArr, 0, iArr2, 0, length);
                int[] a3 = new p3(this.f5864a, iArr2).a(i, 1).b(a2)[1].a();
                int length2 = i - a3.length;
                for (int i2 = 0; i2 < length2; i2++) {
                    iArr[length + i2] = 0;
                }
                System.arraycopy(a3, 0, iArr, length + length2, a3.length);
                return;
            }
            throw new IllegalArgumentException("No data bytes provided");
        }
        throw new IllegalArgumentException("No error correction bytes");
    }
}
