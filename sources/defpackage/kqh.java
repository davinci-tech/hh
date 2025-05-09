package defpackage;

import com.huawei.hwidauth.api.Result;

/* loaded from: classes5.dex */
public class kqh implements Result {

    /* renamed from: a, reason: collision with root package name */
    private kqm f14546a;

    public kqh(kqm kqmVar) {
        this.f14546a = kqmVar;
    }

    @Override // com.huawei.hwidauth.api.Result
    public kqm getStatus() {
        return this.f14546a;
    }
}
