package com.huawei.hms.ads.data;

import com.huawei.openalliance.ad.annotations.d;
import java.util.List;

/* loaded from: classes4.dex */
public class SearchInfo {

    @d(a = "chnl")
    private String channel;

    @d(a = "kws")
    private List<Keyword> keywords;

    @d(a = "qry")
    private String query;

    public void setQuery(String str) {
        this.query = str;
    }

    public void setKeywords(List<Keyword> list) {
        this.keywords = list;
    }

    public void setChannel(String str) {
        this.channel = str;
    }

    public String getQuery() {
        return this.query;
    }

    public List<Keyword> getKeywords() {
        return this.keywords;
    }

    public String getChannel() {
        return this.channel;
    }

    public SearchInfo(String str, List<Keyword> list, String str2) {
        this.query = str;
        this.keywords = list;
        this.channel = str2;
    }

    public SearchInfo() {
    }
}
