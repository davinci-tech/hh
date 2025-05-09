package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzaw;

/* loaded from: classes8.dex */
final class zzi extends zzaw {
    private final /* synthetic */ GoogleMap.OnMyLocationButtonClickListener zzq;

    @Override // com.google.android.gms.maps.internal.zzav
    public final boolean onMyLocationButtonClick() throws RemoteException {
        return this.zzq.onMyLocationButtonClick();
    }

    zzi(GoogleMap googleMap, GoogleMap.OnMyLocationButtonClickListener onMyLocationButtonClickListener) {
        this.zzq = onMyLocationButtonClickListener;
    }
}
