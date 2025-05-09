package defpackage;

import com.huawei.hwidauth.api.Result;

/* loaded from: classes.dex */
public class kqn implements Result {
    private kqm c;

    public kqn(kqm kqmVar) {
        this.c = kqmVar;
    }

    @Override // com.huawei.hwidauth.api.Result
    public kqm getStatus() {
        return this.c;
    }
}
