package com.huawei.agconnect.credential.obs;

import com.huawei.agconnect.https.annotation.Result;
import java.util.List;

/* loaded from: classes2.dex */
public class av {

    @Result("backDomain")
    private List<as> backDomain;

    @Result("mainDomain")
    private List<as> mainDomain;

    @Result("ret")
    private ao ret;

    @Result("ttl")
    String ttl;

    public String d() {
        return this.ttl;
    }

    public List<as> c() {
        return this.backDomain;
    }

    public List<as> b() {
        return this.mainDomain;
    }

    public ao a() {
        return this.ret;
    }
}
