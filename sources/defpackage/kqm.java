package defpackage;

import com.huawei.hwidauth.api.Result;

/* loaded from: classes.dex */
public final class kqm implements Result {

    /* renamed from: a, reason: collision with root package name */
    private String f14550a;
    private boolean b;
    private int c;
    private int d;

    @Override // com.huawei.hwidauth.api.Result
    public kqm getStatus() {
        return null;
    }

    public kqm(int i, String str) {
        this.c = i;
        this.f14550a = str;
    }

    public void b(boolean z) {
        this.b = z;
    }

    public boolean c() {
        return this.b;
    }

    public int e() {
        return this.c;
    }

    public void a(int i) {
        this.d = i;
    }

    public String a() {
        return this.f14550a;
    }
}
