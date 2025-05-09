package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.maps.model.LatLng;

/* loaded from: classes8.dex */
public interface zzaj extends IInterface {
    void onMapClick(LatLng latLng) throws RemoteException;
}
