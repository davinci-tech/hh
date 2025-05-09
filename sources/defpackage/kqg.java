package defpackage;

import com.huawei.hwidauth.api.Result;

/* loaded from: classes5.dex */
public class kqg implements Result {
    private kqm e;

    public kqg(kqm kqmVar) {
        this.e = kqmVar;
    }

    @Override // com.huawei.hwidauth.api.Result
    public kqm getStatus() {
        return this.e;
    }

    public boolean e() {
        return this.e.c();
    }
}
