package com.huawei.hmf.orb.aidl.request;

import com.huawei.hmf.orb.IMessageEntity;
import com.huawei.hmf.orb.RemoteSessionManager;
import com.huawei.hmf.orb.aidl.ExportedRemoteModule;
import com.huawei.hmf.orb.aidl.client.ApiClient;
import com.huawei.hmf.orb.aidl.client.impl.ResolvePendingResult;
import com.huawei.hmf.orb.aidl.communicate.AIDLRequest;
import java.util.Set;

/* loaded from: classes9.dex */
public class ConnectService extends AIDLRequest<Request> {
    public static final String name = "ConnectService";

    public static class Request implements IMessageEntity {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hmf.orb.aidl.communicate.AIDLRequest
    public void onRequest(Request request) {
        Response response = new Response();
        RemoteSessionManager.add(this.clientIdentity.appID);
        response.mRemoteModules = ExportedRemoteModule.getInstance().get();
        this.response.call(response);
    }

    public static class Response implements IMessageEntity {
        Set<String> mRemoteModules;

        public Set<String> getRemoteModules() {
            return this.mRemoteModules;
        }
    }

    public static ResolvePendingResult<Response> build(ApiClient apiClient) {
        return ResolvePendingResult.build(apiClient, name, new Request(), Response.class);
    }
}
