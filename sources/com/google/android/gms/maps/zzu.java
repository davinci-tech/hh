package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;

/* loaded from: classes8.dex */
final class zzu extends com.google.android.gms.maps.internal.zzu {
    private final /* synthetic */ GoogleMap.OnCameraMoveStartedListener zzac;

    @Override // com.google.android.gms.maps.internal.zzt
    public final void onCameraMoveStarted(int i) {
        this.zzac.onCameraMoveStarted(i);
    }

    zzu(GoogleMap googleMap, GoogleMap.OnCameraMoveStartedListener onCameraMoveStartedListener) {
        this.zzac = onCameraMoveStartedListener;
    }
}
