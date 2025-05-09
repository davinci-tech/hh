package defpackage;

import com.huawei.hwidauth.api.Result;

/* loaded from: classes.dex */
public class kqc implements Result {
    private String b;
    private kqm c;

    public kqc(String str, kqm kqmVar) {
        this.b = str;
        this.c = kqmVar;
    }

    @Override // com.huawei.hwidauth.api.Result
    public kqm getStatus() {
        return this.c;
    }
}
