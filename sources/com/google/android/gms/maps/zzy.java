package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/* loaded from: classes8.dex */
final class zzy extends com.google.android.gms.maps.internal.zzak {
    private final /* synthetic */ GoogleMap.OnMapClickListener zzag;

    @Override // com.google.android.gms.maps.internal.zzaj
    public final void onMapClick(LatLng latLng) {
        this.zzag.onMapClick(latLng);
    }

    zzy(GoogleMap googleMap, GoogleMap.OnMapClickListener onMapClickListener) {
        this.zzag = onMapClickListener;
    }
}
