package com.huawei.hms.kit.awareness.internal.communication;

import android.os.Parcelable;
import com.huawei.hms.kit.awareness.barrier.internal.g;

/* loaded from: classes9.dex */
public interface IInnerKitRequest extends Parcelable {
    AppInfo getAppInfo();

    String getKey();

    g getRequest();

    int getUid();

    default InnerKitResponse buildResponse(int i) {
        return new InnerKitResponse(getAppInfo(), getKey(), i);
    }
}
