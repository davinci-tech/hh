package com.huawei.hms.ads.data;

import com.huawei.openalliance.ad.annotations.d;

/* loaded from: classes4.dex */
public class Keyword {

    @d(a = "kw")
    private String keyword;
    private Integer type;

    public void setType(Integer num) {
        this.type = num;
    }

    public void setKeyword(String str) {
        this.keyword = str;
    }

    public Integer getType() {
        return this.type;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public Keyword(Integer num, String str) {
        this.type = num;
        this.keyword = str;
    }

    public Keyword() {
    }
}
