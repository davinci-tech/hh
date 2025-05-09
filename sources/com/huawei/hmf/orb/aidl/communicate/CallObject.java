package com.huawei.hmf.orb.aidl.communicate;

import com.huawei.hmf.orb.IMessageEntity;

/* loaded from: classes9.dex */
public final class CallObject {
    private boolean export;
    Class<? extends AIDLRequest<?>> requestClass = null;

    public CallObject(boolean z) {
        this.export = z;
    }

    public boolean isExport() {
        return this.export;
    }

    public AIDLRequest<IMessageEntity> makeRequest() {
        try {
            return (AIDLRequest) this.requestClass.newInstance();
        } catch (Exception unused) {
            return null;
        }
    }

    public String toString() {
        return "CallObject{export=" + this.export + ", requestClass=" + this.requestClass.getName() + '}';
    }
}
