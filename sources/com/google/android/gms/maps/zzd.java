package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/* loaded from: classes8.dex */
final class zzd extends com.google.android.gms.maps.internal.zzac {
    private final /* synthetic */ GoogleMap.OnInfoWindowClickListener zzl;

    @Override // com.google.android.gms.maps.internal.zzab
    public final void zze(com.google.android.gms.internal.maps.zzt zztVar) {
        this.zzl.onInfoWindowClick(new Marker(zztVar));
    }

    zzd(GoogleMap googleMap, GoogleMap.OnInfoWindowClickListener onInfoWindowClickListener) {
        this.zzl = onInfoWindowClickListener;
    }
}
