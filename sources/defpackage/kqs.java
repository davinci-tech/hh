package defpackage;

import com.huawei.hwidauth.api.Result;

/* loaded from: classes.dex */
public class kqs implements Result {
    private kqm d;

    public kqs(kqm kqmVar) {
        this.d = kqmVar;
    }

    @Override // com.huawei.hwidauth.api.Result
    public kqm getStatus() {
        return this.d;
    }
}
