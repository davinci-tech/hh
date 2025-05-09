package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;

/* loaded from: classes8.dex */
final class zzt extends com.google.android.gms.maps.internal.zzm {
    private final /* synthetic */ GoogleMap.OnCameraChangeListener zzab;

    @Override // com.google.android.gms.maps.internal.zzl
    public final void onCameraChange(CameraPosition cameraPosition) {
        this.zzab.onCameraChange(cameraPosition);
    }

    zzt(GoogleMap googleMap, GoogleMap.OnCameraChangeListener onCameraChangeListener) {
        this.zzab = onCameraChangeListener;
    }
}
