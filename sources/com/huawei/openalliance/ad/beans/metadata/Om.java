package com.huawei.openalliance.ad.beans.metadata;

import com.huawei.openalliance.ad.annotations.a;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class Om implements Serializable {
    private static final long serialVersionUID = 6514947323166042687L;

    @a
    private String resourceUrl;
    private String vendorKey;

    @a
    private String verificationParameters;

    public String toString() {
        return "Om{vendorKey='" + this.vendorKey + "', resourceUrl='" + this.resourceUrl + "', verificationParameters='" + this.verificationParameters + "'}";
    }

    public void c(String str) {
        this.verificationParameters = str;
    }

    public String c() {
        return this.verificationParameters;
    }

    public void b(String str) {
        this.resourceUrl = str;
    }

    public String b() {
        return this.resourceUrl;
    }

    public void a(String str) {
        this.vendorKey = str;
    }

    public String a() {
        return this.vendorKey;
    }
}
