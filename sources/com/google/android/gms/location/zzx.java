package com.google.android.gms.location;

import android.location.Location;
import android.os.IInterface;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public interface zzx extends IInterface {
    void onLocationChanged(Location location) throws RemoteException;
}
