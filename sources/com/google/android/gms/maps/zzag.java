package com.google.android.gms.maps;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.internal.zzbo;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

/* loaded from: classes8.dex */
final class zzag extends zzbo {
    private final /* synthetic */ StreetViewPanorama.OnStreetViewPanoramaLongClickListener zzbr;

    @Override // com.google.android.gms.maps.internal.zzbn
    public final void onStreetViewPanoramaLongClick(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        this.zzbr.onStreetViewPanoramaLongClick(streetViewPanoramaOrientation);
    }

    zzag(StreetViewPanorama streetViewPanorama, StreetViewPanorama.OnStreetViewPanoramaLongClickListener onStreetViewPanoramaLongClickListener) {
        this.zzbr = onStreetViewPanoramaLongClickListener;
    }
}
