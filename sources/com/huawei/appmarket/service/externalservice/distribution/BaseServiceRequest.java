package com.huawei.appmarket.service.externalservice.distribution;

import android.os.Parcelable;
import com.huawei.appgallery.agd.api.ServiceInfo;
import com.huawei.appgallery.coreservice.internal.framework.ipc.transport.data.BaseIPCRequest;
import com.huawei.appgallery.coreservice.internal.support.parcelable.AutoParcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.EnableAutoParcel;

/* loaded from: classes3.dex */
public class BaseServiceRequest extends BaseIPCRequest {
    public static final Parcelable.Creator<BaseServiceRequest> CREATOR = new AutoParcelable.AutoCreator(BaseServiceRequest.class);

    @EnableAutoParcel(6)
    private String callSpec;

    @EnableAutoParcel(5)
    private String callerContext;

    @EnableAutoParcel(1)
    private String downloadParams;

    @EnableAutoParcel(3)
    private String installType;

    @EnableAutoParcel(4)
    private String referrer;

    @EnableAutoParcel(2)
    private ServiceInfo serviceInfo;

    @Override // com.huawei.appgallery.coreservice.internal.framework.ipc.transport.data.BaseIPCRequest
    public String getMethod() {
        return null;
    }

    public void setServiceInfo(ServiceInfo serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public void setReferrer(String str) {
        this.referrer = str;
    }

    public void setInstallType(String str) {
        this.installType = str;
    }

    public void setDownloadParams(String str) {
        this.downloadParams = str;
    }

    public void setCallerContext(String str) {
        this.callerContext = str;
    }

    public void setCallSpec(String str) {
        this.callSpec = str;
    }
}
