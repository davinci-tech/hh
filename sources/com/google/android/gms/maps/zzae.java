package com.google.android.gms.maps;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.internal.zzbi;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

/* loaded from: classes8.dex */
final class zzae extends zzbi {
    private final /* synthetic */ StreetViewPanorama.OnStreetViewPanoramaCameraChangeListener zzbp;

    @Override // com.google.android.gms.maps.internal.zzbh
    public final void onStreetViewPanoramaCameraChange(StreetViewPanoramaCamera streetViewPanoramaCamera) {
        this.zzbp.onStreetViewPanoramaCameraChange(streetViewPanoramaCamera);
    }

    zzae(StreetViewPanorama streetViewPanorama, StreetViewPanorama.OnStreetViewPanoramaCameraChangeListener onStreetViewPanoramaCameraChangeListener) {
        this.zzbp = onStreetViewPanoramaCameraChangeListener;
    }
}
