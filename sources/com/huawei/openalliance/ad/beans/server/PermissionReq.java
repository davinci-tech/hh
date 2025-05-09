package com.huawei.openalliance.ad.beans.server;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.openalliance.ad.annotations.d;
import com.huawei.openalliance.ad.beans.base.ReqBean;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.utils.af;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.x;

/* loaded from: classes5.dex */
public class PermissionReq extends ReqBean {
    private int appType;
    private String appcountry;
    private String applang;
    private String devcountry;
    private int deviceType;
    private String devlang;
    private String model;

    @d(a = "app")
    private String packageName;
    private String sdkver;
    private String ver;

    @Override // com.huawei.openalliance.ad.beans.base.ReqBean
    public String b() {
        return Constants.PERMISSION_SERVER_REQ_URI;
    }

    @Override // com.huawei.openalliance.ad.beans.base.ReqBean
    public String a(Context context) {
        return context.getString(af.a() ? R.string._2130850984_res_0x7f0234a8 : R.string._2130850985_res_0x7f0234a9);
    }

    @Override // com.huawei.openalliance.ad.beans.base.ReqBean
    public String a() {
        return Constants.PERMISSION_SERVER_REALM;
    }

    public PermissionReq(String str, String str2, String str3, int i, int i2) {
        this.ver = Constants.INTER_VERSION;
        this.sdkver = "3.4.74.310";
        this.packageName = str;
        this.applang = str2;
        this.appcountry = str3;
        this.devlang = com.huawei.openalliance.ad.utils.d.a();
        this.devcountry = dd.d();
        this.appType = i;
        this.deviceType = i2;
        this.model = x.b();
    }

    public PermissionReq() {
        this.ver = Constants.INTER_VERSION;
        this.sdkver = "3.4.74.310";
    }
}
