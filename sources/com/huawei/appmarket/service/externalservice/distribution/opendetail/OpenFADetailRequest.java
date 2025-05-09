package com.huawei.appmarket.service.externalservice.distribution.opendetail;

import android.os.Parcelable;
import android.util.Log;
import com.huawei.appgallery.agd.api.AgdApiClient;
import com.huawei.appgallery.agd.api.AgdHarmonyServiceApi;
import com.huawei.appgallery.agd.api.ApiStatusCodes;
import com.huawei.appgallery.agd.api.PendingResult;
import com.huawei.appgallery.agd.api.ResultCallback;
import com.huawei.appgallery.agd.api.ServiceInfo;
import com.huawei.appgallery.coreservice.internal.framework.ipc.transport.data.RequireVersion;
import com.huawei.appgallery.coreservice.internal.support.parcelable.AutoParcelable;
import com.huawei.appmarket.framework.coreservice.Status;
import com.huawei.appmarket.service.externalservice.distribution.BaseServiceRequest;
import com.huawei.appmarket.service.externalservice.distribution.opendetail.OpenFADetailRequest;

@RequireVersion(130301000)
/* loaded from: classes3.dex */
public class OpenFADetailRequest extends BaseServiceRequest {
    public static final Parcelable.Creator<OpenFADetailRequest> CREATOR = new AutoParcelable.AutoCreator(OpenFADetailRequest.class);

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private ResultCallback<OpenFADetailResponse> f1890a;
        private final AgdApiClient b;
        private final OpenFADetailRequest d = new OpenFADetailRequest();

        public Builder serviceInfo(ServiceInfo serviceInfo) {
            this.d.setServiceInfo(serviceInfo);
            return this;
        }

        public void send() {
            PendingResult<OpenFADetailRequest, OpenFADetailResponse> openDetail = AgdHarmonyServiceApi.openDetail(this.b, this.d);
            ResultCallback<OpenFADetailResponse> resultCallback = this.f1890a;
            if (resultCallback == null) {
                resultCallback = new ResultCallback() { // from class: anr
                    @Override // com.huawei.appgallery.agd.api.ResultCallback
                    public final void onResult(Status status) {
                        OpenFADetailRequest.Builder.b(status);
                    }
                };
            }
            openDetail.setResultCallback(resultCallback);
        }

        public Builder referrer(String str) {
            this.d.setReferrer(str);
            return this;
        }

        public Builder installType(String str) {
            this.d.setInstallType(str);
            return this;
        }

        public Builder downloadParams(String str) {
            this.d.setDownloadParams(str);
            return this;
        }

        public Builder callerContext(String str) {
            this.d.setCallerContext(str);
            return this;
        }

        public Builder callback(ResultCallback<OpenFADetailResponse> resultCallback) {
            this.f1890a = resultCallback;
            return this;
        }

        public Builder callSpec(String str) {
            this.d.setCallSpec(str);
            return this;
        }

        public OpenFADetailRequest build() {
            return this.d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void b(Status status) {
            Log.i("OpenFADetailRequest", "onStatus: " + ApiStatusCodes.getStatusCodeString(status.getStatusCode()));
        }

        public Builder(AgdApiClient agdApiClient) {
            this.b = agdApiClient;
        }
    }

    @Override // com.huawei.appmarket.service.externalservice.distribution.BaseServiceRequest, com.huawei.appgallery.coreservice.internal.framework.ipc.transport.data.BaseIPCRequest
    public String getMethod() {
        return "method.openFADetail";
    }
}
