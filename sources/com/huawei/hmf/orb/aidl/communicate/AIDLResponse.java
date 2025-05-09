package com.huawei.hmf.orb.aidl.communicate;

import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.hmf.orb.IMessageEntity;
import com.huawei.hmf.orb.aidl.IAIDLCallback;
import com.huawei.hmf.services.codec.MessageCodec;

/* loaded from: classes9.dex */
public class AIDLResponse {
    private static final String TAG = "AIDLResponse";
    private IAIDLCallback callBack;
    protected String URI = "void";
    protected int version = 1;

    public AIDLResponse(IAIDLCallback iAIDLCallback) {
        this.callBack = iAIDLCallback;
    }

    public final void call(IMessageEntity iMessageEntity) {
        call(0, iMessageEntity);
    }

    public final void failure(int i) {
        call(i, null);
    }

    public String getURI() {
        return this.URI;
    }

    public void setURI(String str) {
        this.URI = str;
    }

    public void setProtocol(int i) {
        this.version = i;
    }

    protected void call(int i, IMessageEntity iMessageEntity) {
        DataBuffer dataBuffer = new DataBuffer(this.URI, this.version);
        MessageCodec find = CodecLookup.find(dataBuffer.getProtocol());
        if (iMessageEntity != null) {
            dataBuffer.URI = iMessageEntity.getClass().getName();
            dataBuffer.addBody(find.encode(iMessageEntity, new Bundle()));
        }
        dataBuffer.header = find.encode(new ResponseHeader(i), new Bundle());
        try {
            this.callBack.call(dataBuffer);
        } catch (RemoteException | Exception unused) {
        }
    }
}
