package com.huawei.openalliance.ad.beans.server;

import com.huawei.openalliance.ad.beans.base.RspBean;
import com.huawei.openalliance.ad.beans.metadata.AdEventResult;
import com.huawei.openalliance.ad.beans.metadata.AdEventResultV2;
import java.util.List;

/* loaded from: classes5.dex */
public class EventReportRsp extends RspBean {
    private List<AdEventResultV2> e;
    private Integer r;
    private List<AdEventResult> result;

    public List<AdEventResultV2> c() {
        return this.e;
    }

    public void b(List<AdEventResultV2> list) {
        this.e = list;
    }

    public Integer b() {
        return this.r;
    }

    public void a(List<AdEventResult> list) {
        this.result = list;
    }

    public List<AdEventResult> a() {
        return this.result;
    }
}
