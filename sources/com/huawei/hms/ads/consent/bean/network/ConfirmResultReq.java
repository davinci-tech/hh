package com.huawei.hms.ads.consent.bean.network;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class ConfirmResultReq {
    private List<ApiStatisticsReq> caches = new ArrayList();

    public void setCaches(List<ApiStatisticsReq> list) {
        this.caches = list;
    }

    public List<ApiStatisticsReq> getCaches() {
        return this.caches;
    }
}
