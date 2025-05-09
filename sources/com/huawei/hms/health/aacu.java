package com.huawei.hms.health;

import com.huawei.hms.hihealth.result.ReadDetailResult;
import com.huawei.hms.hihealth.result.ReadReply;
import com.huawei.hms.support.api.client.Status;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public final class aacu<T> implements aacw<T> {
    private ReadReply aab = new ReadReply();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.huawei.hms.health.aacw
    public com.huawei.hms.hihealth.result.aab aab(T t) {
        ReadDetailResult create;
        ReadReply readReply;
        if (t instanceof ReadDetailResult) {
            readReply = this.aab;
            create = (ReadDetailResult) t;
        } else {
            if (t instanceof Exception) {
                create = ReadDetailResult.create(aabz.aab(((Exception) t).getMessage()), new ArrayList(), new ArrayList());
            } else {
                create = ReadDetailResult.create(Status.FAILURE, new ArrayList(), new ArrayList());
            }
            readReply = this.aab;
        }
        readReply.setResult(create);
        return this.aab;
    }
}
