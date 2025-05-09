package com.google.android.gms.maps;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/* loaded from: classes8.dex */
final class zzg extends com.google.android.gms.maps.internal.zzi {
    private final /* synthetic */ GoogleMap.InfoWindowAdapter zzo;

    @Override // com.google.android.gms.maps.internal.zzh
    public final IObjectWrapper zzi(com.google.android.gms.internal.maps.zzt zztVar) {
        return ObjectWrapper.wrap(this.zzo.getInfoContents(new Marker(zztVar)));
    }

    @Override // com.google.android.gms.maps.internal.zzh
    public final IObjectWrapper zzh(com.google.android.gms.internal.maps.zzt zztVar) {
        return ObjectWrapper.wrap(this.zzo.getInfoWindow(new Marker(zztVar)));
    }

    zzg(GoogleMap googleMap, GoogleMap.InfoWindowAdapter infoWindowAdapter) {
        this.zzo = infoWindowAdapter;
    }
}
