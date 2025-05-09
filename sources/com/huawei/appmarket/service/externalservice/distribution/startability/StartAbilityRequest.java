package com.huawei.appmarket.service.externalservice.distribution.startability;

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
import com.huawei.appmarket.service.externalservice.distribution.startability.StartAbilityRequest;

@RequireVersion(130201000)
/* loaded from: classes8.dex */
public class StartAbilityRequest extends BaseServiceRequest {
    public static final Parcelable.Creator<StartAbilityRequest> CREATOR = new AutoParcelable.AutoCreator(StartAbilityRequest.class);

    public static class Builder {
        private ResultCallback<StartAbilityResponse> b;
        private final StartAbilityRequest d = new StartAbilityRequest();
        private final AgdApiClient e;

        public Builder serviceInfo(ServiceInfo serviceInfo) {
            this.d.setServiceInfo(serviceInfo);
            return this;
        }

        public void send() {
            PendingResult<StartAbilityRequest, StartAbilityResponse> startAbility = AgdHarmonyServiceApi.startAbility(this.e, this.d);
            ResultCallback<StartAbilityResponse> resultCallback = this.b;
            if (resultCallback == null) {
                resultCallback = new ResultCallback() { // from class: anp
                    @Override // com.huawei.appgallery.agd.api.ResultCallback
                    public final void onResult(Status status) {
                        StartAbilityRequest.Builder.e(status);
                    }
                };
            }
            startAbility.setResultCallback(resultCallback);
        }

        public Builder referrer(String str) {
            this.d.setReferrer(str);
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

        public Builder callback(ResultCallback<StartAbilityResponse> resultCallback) {
            this.b = resultCallback;
            return this;
        }

        public Builder callSpec(String str) {
            this.d.setCallSpec(str);
            return this;
        }

        public StartAbilityRequest build() {
            return this.d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void e(Status status) {
            Log.i("StartAbilityRequest", "onStatus: " + ApiStatusCodes.getStatusCodeString(status.getStatusCode()));
        }

        public Builder(AgdApiClient agdApiClient) {
            this.e = agdApiClient;
        }
    }

    @Override // com.huawei.appmarket.service.externalservice.distribution.BaseServiceRequest, com.huawei.appgallery.coreservice.internal.framework.ipc.transport.data.BaseIPCRequest
    public String getMethod() {
        return "method.startAbility";
    }
}
