package defpackage;

import com.huawei.hwidauth.api.Result;

/* loaded from: classes9.dex */
public class kqp implements Result {
    private kqm d;

    public kqp(kqm kqmVar) {
        this.d = kqmVar;
    }

    @Override // com.huawei.hwidauth.api.Result
    public kqm getStatus() {
        return this.d;
    }
}
