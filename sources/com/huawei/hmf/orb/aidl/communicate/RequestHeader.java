package com.huawei.hmf.orb.aidl.communicate;

import android.os.Bundle;
import com.huawei.hmf.annotation.Packed;
import com.huawei.hmf.orb.IMessageEntity;
import com.huawei.hmf.services.codec.MessageCodec;

/* loaded from: classes9.dex */
public class RequestHeader implements IMessageEntity {
    private static final transient String DESCRIPTOR = "com.huawei.hmf.orb.aidl.communicate.RequestHeader";

    @Packed
    public String appId;

    @Packed
    public String packageName;

    public Bundle toBundle(MessageCodec messageCodec) {
        return messageCodec.encode(this, new Bundle());
    }

    public Bundle toBundle() {
        return toBundle(new MessageCodec());
    }

    public static RequestHeader from(MessageCodec messageCodec, Bundle bundle) {
        return (RequestHeader) messageCodec.decode(bundle, (Bundle) new RequestHeader());
    }

    public static RequestHeader from(Bundle bundle) {
        return from(new MessageCodec(), bundle);
    }

    public static String getDescriptor() {
        return DESCRIPTOR;
    }
}
