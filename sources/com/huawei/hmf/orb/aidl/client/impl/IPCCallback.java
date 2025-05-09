package com.huawei.hmf.orb.aidl.client.impl;

import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hmf.orb.IMessageEntity;
import com.huawei.hmf.orb.aidl.DatagramTransport;
import com.huawei.hmf.orb.aidl.IAIDLCallback;
import com.huawei.hmf.orb.aidl.communicate.CodecLookup;
import com.huawei.hmf.orb.aidl.communicate.DataBuffer;
import com.huawei.hmf.orb.aidl.communicate.ResponseHeader;
import com.huawei.hmf.services.codec.MessageCodec;

/* loaded from: classes9.dex */
public class IPCCallback extends IAIDLCallback.Stub {
    private static final String TAG = "IPCCallback";
    private final DatagramTransport.CallBack mCallback;
    private final Class<? extends IMessageEntity> mResponseClass;

    public IPCCallback(Class<? extends IMessageEntity> cls, DatagramTransport.CallBack callBack) {
        this.mResponseClass = cls;
        this.mCallback = callBack;
    }

    @Override // com.huawei.hmf.orb.aidl.IAIDLCallback
    public void call(DataBuffer dataBuffer) throws RemoteException {
        IMessageEntity iMessageEntity;
        if (dataBuffer == null || TextUtils.isEmpty(dataBuffer.URI)) {
            Log.e(TAG, "URI cannot be null.");
            throw new RemoteException();
        }
        MessageCodec find = CodecLookup.find(dataBuffer.getProtocol());
        ResponseHeader responseHeader = new ResponseHeader();
        find.decode(dataBuffer.header, (Bundle) responseHeader);
        if (dataBuffer.getBodySize() > 0) {
            iMessageEntity = newResponseInstance();
            if (iMessageEntity != null) {
                find.decode(dataBuffer.getBody(), (Bundle) iMessageEntity);
            }
        } else {
            iMessageEntity = null;
        }
        this.mCallback.onCallback(responseHeader.getStatusCode(), iMessageEntity);
    }

    protected IMessageEntity newResponseInstance() {
        Class<? extends IMessageEntity> cls = this.mResponseClass;
        if (cls == null) {
            return null;
        }
        try {
            return cls.newInstance();
        } catch (IllegalAccessException e) {
            Log.e(TAG, "instancing exception.", e);
            return null;
        } catch (InstantiationException e2) {
            Log.e(TAG, "instancing exception.", e2);
            return null;
        }
    }
}
