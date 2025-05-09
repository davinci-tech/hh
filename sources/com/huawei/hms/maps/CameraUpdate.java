package com.huawei.hms.maps;

import com.huawei.hms.maps.model.CameraUpdateParam;

/* loaded from: classes4.dex */
public final class CameraUpdate {

    /* renamed from: a, reason: collision with root package name */
    private CameraUpdateParam f4902a;

    public CameraUpdateParam getCameraUpdateParam() {
        return this.f4902a;
    }

    @Deprecated
    public CameraUpdateParam getCameraUpdate() {
        return this.f4902a;
    }

    public CameraUpdate(CameraUpdateParam cameraUpdateParam) {
        if (cameraUpdateParam == null) {
            throw new NullPointerException("Object is null");
        }
        this.f4902a = cameraUpdateParam;
    }
}
