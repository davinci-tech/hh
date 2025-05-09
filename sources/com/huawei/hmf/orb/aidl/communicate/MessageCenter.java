package com.huawei.hmf.orb.aidl.communicate;

import android.text.TextUtils;
import com.huawei.hmf.orb.IMessageEntity;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public final class MessageCenter {
    private static final String TAG = "MessageCenter";
    private static MessageCenter instance = new MessageCenter();
    private Map<String, CallObject> msg = new HashMap();

    public static MessageCenter getInstance() {
        return instance;
    }

    private MessageCenter() {
    }

    public void register(String str, Class<? extends AIDLRequest<?>> cls) {
        register(str, cls, true);
    }

    public void register(String str, Class<? extends AIDLRequest<?>> cls, boolean z) {
        if (cls == null) {
            throw new IllegalArgumentException("requestClass cannot be null.");
        }
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("URI cannot be null.");
        }
        CallObject callObject = new CallObject(z);
        callObject.requestClass = cls;
        this.msg.put(str, callObject);
    }

    public CallObject query(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("URI cannot be null.");
        }
        return this.msg.get(str);
    }

    public AIDLRequest<IMessageEntity> makeRequest(String str) {
        return makeRequest(str, false);
    }

    public AIDLRequest<IMessageEntity> makeRequest(String str, boolean z) {
        CallObject query = query(str);
        if (query == null) {
            return null;
        }
        if (!z || query.isExport()) {
            return query.makeRequest();
        }
        return null;
    }
}
