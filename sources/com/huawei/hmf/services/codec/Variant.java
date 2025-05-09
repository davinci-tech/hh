package com.huawei.hmf.services.codec;

import android.os.Bundle;
import com.huawei.hmf.orb.IMessageEntity;
import java.lang.reflect.Type;

/* loaded from: classes9.dex */
public class Variant<T> implements IMessageEntity {
    public T mObject;

    public Variant() {
    }

    public Variant(T t) {
        this.mObject = t;
    }

    public Object cast(Type type) {
        if (type != null && (this.mObject instanceof Bundle)) {
            return new MessageCodec().decode((Bundle) this.mObject, type);
        }
        return this.mObject;
    }
}
