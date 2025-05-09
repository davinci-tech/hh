package com.huawei.openalliance.ad.beans.metadata;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class ContentExt implements Serializable {
    private static final long serialVersionUID = 5565833272963480072L;
    private String key;
    private String value;

    public void b(String str) {
        this.value = str;
    }

    public String b() {
        return this.value;
    }

    public void a(String str) {
        this.key = str;
    }

    public String a() {
        return this.key;
    }
}
