package com.huawei.appgallery.marketinstallerservice.a.e;

import android.content.Context;
import com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.InstallerNetTransmission;
import com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.RequestBean;
import com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.ResponseBean;
import defpackage.agk;
import defpackage.agq;
import defpackage.agt;

/* loaded from: classes3.dex */
public class a extends RequestBean {
    public static final String METHOD = "client.getMarketInfo";

    @InstallerNetTransmission
    private String marketPkg;

    @InstallerNetTransmission
    private String resolution;

    @InstallerNetTransmission
    private String version;

    public void setVersion(String str) {
        this.version = str;
    }

    public void setResolution(String str) {
        this.resolution = str;
    }

    public void setMarketPkg(String str) {
        this.marketPkg = str;
    }

    public String getVersion() {
        return this.version;
    }

    @Override // com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.RequestBean
    public ResponseBean getResponseBean() {
        return new agk();
    }

    public String getResolution() {
        return this.resolution;
    }

    public String getMarketPkg() {
        return this.marketPkg;
    }

    public static a newInstance(Context context) {
        a aVar = new a(context);
        aVar.setMethod("client.getMarketInfo");
        aVar.version = agt.c(context);
        aVar.resolution = agq.c(context);
        return aVar;
    }

    public a(Context context) {
        super(context);
    }

    public a() {
    }
}
