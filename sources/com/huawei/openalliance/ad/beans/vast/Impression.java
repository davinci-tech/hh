package com.huawei.openalliance.ad.beans.vast;

import com.huawei.openalliance.ad.annotations.a;
import com.huawei.openalliance.ad.utils.cz;

/* loaded from: classes5.dex */
public class Impression {
    private String id;

    @a
    private String url;

    public String toString() {
        return "Impression{id='" + this.id + "', url='" + cz.f(this.url) + "'}";
    }

    public String a() {
        return this.url;
    }

    public Impression(String str, String str2) {
        this.id = str;
        this.url = str2;
    }
}
