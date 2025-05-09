package com.huawei.openalliance.ad.beans.server;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.openalliance.ad.beans.base.ReqBean;
import com.huawei.openalliance.ad.beans.metadata.AppCollection;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.utils.af;
import java.util.List;

/* loaded from: classes5.dex */
public class AppDataCollectionReq extends ReqBean {
    private List<AppCollection> appList;

    @Override // com.huawei.openalliance.ad.beans.base.ReqBean
    public String c() {
        return "100003";
    }

    @Override // com.huawei.openalliance.ad.beans.base.ReqBean
    public String b() {
        return Constants.SDK_APP_DATA_REPORT_SERVER_URI;
    }

    public void a(List<AppCollection> list) {
        this.appList = list;
    }

    @Override // com.huawei.openalliance.ad.beans.base.ReqBean
    public String a(Context context) {
        return context.getString(af.a() ? R.string._2130851124_res_0x7f023534 : R.string._2130851125_res_0x7f023535);
    }

    @Override // com.huawei.openalliance.ad.beans.base.ReqBean
    public String a() {
        return Constants.SDK_APP_DATA_REPORT_SERVER_REALM;
    }
}
