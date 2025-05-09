package com.google.android.gms.maps;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes2.dex */
public final class CameraUpdate {
    private final IObjectWrapper zze;

    public final IObjectWrapper zza() {
        return this.zze;
    }

    CameraUpdate(IObjectWrapper iObjectWrapper) {
        this.zze = (IObjectWrapper) Preconditions.checkNotNull(iObjectWrapper);
    }
}
