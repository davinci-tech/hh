package com.huawei.hms.iap;

import com.huawei.hms.common.ApiException;
import com.huawei.hms.support.api.client.Status;

/* loaded from: classes4.dex */
public class IapApiException extends ApiException {
    public Status getStatus() {
        return this.mStatus;
    }

    public IapApiException(Status status) {
        super(status);
    }
}
