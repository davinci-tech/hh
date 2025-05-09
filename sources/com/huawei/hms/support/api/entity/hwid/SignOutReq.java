package com.huawei.hms.support.api.entity.hwid;

import com.huawei.hms.core.aidl.IMessageEntity;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class SignOutReq implements IMessageEntity {
    public String toJson() {
        return new JSONObject().toString();
    }
}
