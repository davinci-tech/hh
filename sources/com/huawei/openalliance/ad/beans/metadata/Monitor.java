package com.huawei.openalliance.ad.beans.metadata;

import com.huawei.openalliance.ad.annotations.a;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes5.dex */
public class Monitor implements Serializable {
    private static final long serialVersionUID = -478373722166817617L;
    private String eventType;
    private int retryFlag = 1;

    @a
    private List<String> url;

    public int c() {
        return this.retryFlag;
    }

    public List<String> b() {
        return this.url;
    }

    public void a(List<String> list) {
        this.url = list;
    }

    public void a(String str) {
        this.eventType = str;
    }

    public void a(int i) {
        this.retryFlag = i;
    }

    public String a() {
        return this.eventType;
    }
}
