package com.huawei.hms.maps.model;

import com.huawei.hms.feature.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public final class BitmapDescriptor {

    /* renamed from: a, reason: collision with root package name */
    private final IObjectWrapper f4978a;

    public IObjectWrapper getObject() {
        return this.f4978a;
    }

    public BitmapDescriptor(IObjectWrapper iObjectWrapper) {
        if (iObjectWrapper == null) {
            throw new NullPointerException("Object is null");
        }
        this.f4978a = iObjectWrapper;
    }
}
