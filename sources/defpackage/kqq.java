package defpackage;

import com.huawei.hwidauth.api.Result;

/* loaded from: classes.dex */
public class kqq implements Result {

    /* renamed from: a, reason: collision with root package name */
    private kqm f14551a;

    public kqq(kqm kqmVar) {
        this.f14551a = kqmVar;
    }

    @Override // com.huawei.hwidauth.api.Result
    public kqm getStatus() {
        return this.f14551a;
    }
}
