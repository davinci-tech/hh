package com.huawei.hmf.orb.aidl.client.impl;

import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.hmf.orb.CommonCode;
import com.huawei.hmf.orb.IMessageEntity;
import com.huawei.hmf.orb.aidl.DatagramTransport;
import com.huawei.hmf.orb.aidl.IAIDLCallback;
import com.huawei.hmf.orb.aidl.client.ApiClient;
import com.huawei.hmf.orb.aidl.communicate.CodecLookup;
import com.huawei.hmf.orb.aidl.communicate.DataBuffer;
import com.huawei.hmf.orb.aidl.communicate.RequestHeader;
import com.huawei.hmf.services.codec.MessageCodec;

/* loaded from: classes9.dex */
public class IPCTransport implements DatagramTransport {
    private final IMessageEntity mEntity;
    private final Class<? extends IMessageEntity> mResponseClass;
    private final String mURI;

    public IPCTransport(String str, IMessageEntity iMessageEntity, Class<? extends IMessageEntity> cls) {
        this.mURI = str;
        this.mEntity = iMessageEntity;
        this.mResponseClass = cls;
    }

    @Override // com.huawei.hmf.orb.aidl.DatagramTransport
    public final void send(ApiClient apiClient, DatagramTransport.CallBack callBack) {
        int syncCall = syncCall(apiClient, new IPCCallback(this.mResponseClass, callBack));
        if (syncCall != 0) {
            callBack.onCallback(syncCall, null);
        }
    }

    @Override // com.huawei.hmf.orb.aidl.DatagramTransport
    public final void post(ApiClient apiClient, DatagramTransport.CallBack callBack) {
        send(apiClient, callBack);
    }

    private int syncCall(ApiClient apiClient, IAIDLCallback iAIDLCallback) {
        DataBuffer dataBuffer = new DataBuffer(this.mURI, apiClient.getProtocol());
        MessageCodec find = CodecLookup.find(dataBuffer.getProtocol());
        dataBuffer.addBody(find.encode(this.mEntity, new Bundle()));
        RequestHeader requestHeader = new RequestHeader();
        requestHeader.appId = apiClient.getAppID();
        requestHeader.packageName = apiClient.getPackageName();
        dataBuffer.header = requestHeader.toBundle(find);
        try {
            apiClient.getService().asyncCall(dataBuffer, iAIDLCallback);
            return 0;
        } catch (RemoteException unused) {
            return CommonCode.ErrorCode.INTERNAL_ERROR;
        }
    }
}
