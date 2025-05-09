package com.huawei.hms.network.embedded;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public final class l7 {

    /* renamed from: a, reason: collision with root package name */
    public final p6 f5363a;
    public final List<y8> b = new ArrayList();
    public int c = 0;

    public boolean c() {
        return this.b.isEmpty();
    }

    public void b(y8 y8Var) {
        this.b.remove(y8Var);
    }

    public y8 b() {
        return e();
    }

    public void a(y8 y8Var) {
        if (this.b.contains(y8Var)) {
            return;
        }
        this.b.add(y8Var);
    }

    public p6 a() {
        return this.f5363a;
    }

    private y8 e() {
        y8 y8Var = null;
        int i = Integer.MAX_VALUE;
        for (y8 y8Var2 : this.b) {
            int size = y8Var2.p.size();
            if (size < y8Var2.o && !y8Var2.k && size < i) {
                y8Var = y8Var2;
                i = size;
            }
        }
        return y8Var;
    }

    @Deprecated
    private y8 d() {
        if (this.b.isEmpty()) {
            return null;
        }
        if (this.c >= this.b.size()) {
            this.c = 0;
        }
        int size = this.b.size();
        for (int i = this.c; i < size; i++) {
            y8 y8Var = this.b.get(i);
            if (y8Var.p.size() < y8Var.o && !y8Var.k) {
                this.c++;
                return y8Var;
            }
        }
        for (int i2 = 0; i2 < this.c; i2++) {
            y8 y8Var2 = this.b.get(i2);
            if (y8Var2.p.size() < y8Var2.o && !y8Var2.k) {
                this.c++;
                return y8Var2;
            }
        }
        return null;
    }

    public l7(p6 p6Var) {
        this.f5363a = p6Var;
    }
}
