package com.google.android.gms.location;

import android.os.IInterface;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public interface zzu extends IInterface {
    void onLocationAvailability(LocationAvailability locationAvailability) throws RemoteException;

    void onLocationResult(LocationResult locationResult) throws RemoteException;
}
