package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.zzaq;

/* loaded from: classes8.dex */
final class zzac extends zzaq {
    private final /* synthetic */ OnMapReadyCallback zzbb;

    @Override // com.google.android.gms.maps.internal.zzap
    public final void zza(IGoogleMapDelegate iGoogleMapDelegate) throws RemoteException {
        this.zzbb.onMapReady(new GoogleMap(iGoogleMapDelegate));
    }

    zzac(MapView.zza zzaVar, OnMapReadyCallback onMapReadyCallback) {
        this.zzbb = onMapReadyCallback;
    }
}
