package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.maps.model.CameraPosition;

/* loaded from: classes8.dex */
public interface zzl extends IInterface {
    void onCameraChange(CameraPosition cameraPosition) throws RemoteException;
}
