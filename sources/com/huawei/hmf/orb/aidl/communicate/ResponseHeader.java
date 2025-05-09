package com.huawei.hmf.orb.aidl.communicate;

import com.huawei.hmf.annotation.Packed;
import com.huawei.hmf.orb.IMessageEntity;

/* loaded from: classes9.dex */
public class ResponseHeader implements IMessageEntity {

    @Packed
    protected int statusCode;

    public ResponseHeader(int i) {
        this.statusCode = i;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public ResponseHeader() {
    }
}
