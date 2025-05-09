package com.huawei.hmf.services.ui.ref;

import com.huawei.hmf.services.ui.internal.ReferenceTypeImpl;

/* loaded from: classes9.dex */
public final class Allocator {
    public static final Allocator DEFAULT = new Allocator();

    public <T> ReferenceType<T> alloc(T t) {
        return ReferenceTypeImpl.create(t);
    }
}
