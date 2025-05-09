package com.huawei.hmf.orb.aidl.communicate;

import com.huawei.hmf.services.codec.MessageCodec;

/* loaded from: classes9.dex */
public final class CodecLookup {
    private CodecLookup() {
    }

    public static MessageCodec find(int i) {
        return new MessageCodec();
    }
}
