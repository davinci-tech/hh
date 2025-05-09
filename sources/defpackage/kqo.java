package defpackage;

import com.huawei.hwidauth.api.Result;

/* loaded from: classes.dex */
public class kqo implements Result {
    private kqm c;
    private String e;

    public kqo(String str, kqm kqmVar) {
        this.e = str;
        this.c = kqmVar;
    }

    public String d() {
        return this.e;
    }

    @Override // com.huawei.hwidauth.api.Result
    public kqm getStatus() {
        return this.c;
    }

    public boolean c() {
        return this.c.c();
    }
}
