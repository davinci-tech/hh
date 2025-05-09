package com.huawei.openalliance.ad.beans.server;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.openalliance.ad.beans.base.ReqBean;
import com.huawei.openalliance.ad.beans.metadata.AdEvent;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.utils.af;
import java.util.List;

/* loaded from: classes5.dex */
public class EventReportReq extends ReqBean {
    private List<AdEvent> event;
    private String version = Constants.INTER_VERSION;
    private String sdkversion = "3.4.74.310";

    @Override // com.huawei.openalliance.ad.beans.base.ReqBean
    public String b() {
        return Constants.CONTENT_SERVER_REQ_URI;
    }

    @Override // com.huawei.openalliance.ad.beans.base.ReqBean
    public String a(Context context) {
        return context.getString(af.a() ? R.string._2130851055_res_0x7f0234ef : R.string._2130851056_res_0x7f0234f0);
    }

    @Override // com.huawei.openalliance.ad.beans.base.ReqBean
    public String a() {
        return "action";
    }

    public EventReportReq(List<AdEvent> list) {
        this.event = list;
    }

    public EventReportReq() {
    }
}
