package com.huawei.hmf.orb.aidl.communicate;

import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.hmf.orb.CommonCode;
import com.huawei.hmf.orb.IMessageEntity;
import com.huawei.hmf.orb.aidl.IAIDLCallback;
import com.huawei.hmf.orb.aidl.IAIDLInvoke;
import com.huawei.hmf.services.codec.MessageCodec;

/* loaded from: classes9.dex */
public class AIDLInvoke extends IAIDLInvoke.Stub {
    private static final String TAG = "AIDLInvoke";
    private MessageCenter center;

    protected void onRequest(AIDLRequest aIDLRequest, IMessageEntity iMessageEntity) {
    }

    public AIDLInvoke() {
        this.center = null;
        this.center = MessageCenter.getInstance();
    }

    @Override // com.huawei.hmf.orb.aidl.IAIDLInvoke
    public void syncCall(DataBuffer dataBuffer) throws RemoteException {
        handleCall(dataBuffer, new VoidAIDLResponse());
    }

    @Override // com.huawei.hmf.orb.aidl.IAIDLInvoke
    public void asyncCall(DataBuffer dataBuffer, IAIDLCallback iAIDLCallback) throws RemoteException {
        handleCall(dataBuffer, new AIDLResponse(iAIDLCallback));
    }

    private void handleCall(DataBuffer dataBuffer, AIDLResponse aIDLResponse) throws RemoteException {
        if (dataBuffer == null) {
            return;
        }
        aIDLResponse.setProtocol(dataBuffer.getProtocol());
        AIDLRequest<IMessageEntity> makeRequest = this.center.makeRequest(dataBuffer.URI, true);
        if (makeRequest != null) {
            MessageCodec find = CodecLookup.find(dataBuffer.getProtocol());
            if (dataBuffer.header != null) {
                makeRequest.clientIdentity = new ClientIdentity(RequestHeader.from(find, dataBuffer.header));
            }
            IMessageEntity makeParam = makeRequest.makeParam();
            if (dataBuffer.getBodySize() > 0) {
                find.decode(dataBuffer.getBody(), (Bundle) makeParam);
            }
            aIDLResponse.setURI(dataBuffer.URI);
            makeRequest.response = aIDLResponse;
            try {
                onRequest(makeRequest, makeParam);
                makeRequest.execute(makeParam);
                return;
            } catch (Exception unused) {
                aIDLResponse.failure(CommonCode.ErrorCode.INTERNAL_ERROR);
                return;
            }
        }
        aIDLResponse.failure(CommonCode.ErrorCode.NAMING_INVALID);
    }
}
