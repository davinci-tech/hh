package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/* loaded from: classes8.dex */
final class zze extends com.google.android.gms.maps.internal.zzag {
    private final /* synthetic */ GoogleMap.OnInfoWindowLongClickListener zzm;

    @Override // com.google.android.gms.maps.internal.zzaf
    public final void zzf(com.google.android.gms.internal.maps.zzt zztVar) {
        this.zzm.onInfoWindowLongClick(new Marker(zztVar));
    }

    zze(GoogleMap googleMap, GoogleMap.OnInfoWindowLongClickListener onInfoWindowLongClickListener) {
        this.zzm = onInfoWindowLongClickListener;
    }
}
