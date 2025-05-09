package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;

/* loaded from: classes8.dex */
public interface zzbj extends IInterface {
    void onStreetViewPanoramaChange(StreetViewPanoramaLocation streetViewPanoramaLocation) throws RemoteException;
}
