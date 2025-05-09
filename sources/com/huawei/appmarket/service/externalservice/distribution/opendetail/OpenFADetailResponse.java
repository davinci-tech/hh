package com.huawei.appmarket.service.externalservice.distribution.opendetail;

import android.os.Parcelable;
import com.huawei.appgallery.coreservice.internal.framework.ipc.transport.data.BaseIPCResponse;
import com.huawei.appgallery.coreservice.internal.support.parcelable.AutoParcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.EnableAutoParcel;

/* loaded from: classes8.dex */
public class OpenFADetailResponse extends BaseIPCResponse {
    public static final Parcelable.Creator<OpenFADetailResponse> CREATOR = new AutoParcelable.AutoCreator(OpenFADetailResponse.class);

    @EnableAutoParcel(2)
    private String callerContext;

    @EnableAutoParcel(1)
    private String result;

    public String getResult() {
        return this.result;
    }

    public String getCallerContext() {
        return this.callerContext;
    }
}
