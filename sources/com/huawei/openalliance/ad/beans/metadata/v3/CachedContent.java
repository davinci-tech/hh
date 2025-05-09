package com.huawei.openalliance.ad.beans.metadata.v3;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes5.dex */
public class CachedContent implements Serializable {
    private static final long serialVersionUID = 3809421214062903233L;
    private Integer apiVer;
    private String contentId;
    private List<String> templateIds;

    public List<String> c() {
        return this.templateIds;
    }

    public Integer b() {
        return this.apiVer;
    }

    public String a() {
        return this.contentId;
    }

    public CachedContent(String str, Integer num, List<String> list) {
        this.contentId = str;
        this.apiVer = num;
        this.templateIds = list;
    }

    public CachedContent() {
        this.apiVer = 2;
    }
}
