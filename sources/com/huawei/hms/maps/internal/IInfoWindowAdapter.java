package com.huawei.hms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.hms.feature.dynamic.IObjectWrapper;
import com.huawei.hms.maps.model.internal.IMarkerDelegate;

/* loaded from: classes9.dex */
public interface IInfoWindowAdapter extends IInterface {
    IObjectWrapper getInfoContents(IMarkerDelegate iMarkerDelegate);

    IObjectWrapper getInfoWindow(IMarkerDelegate iMarkerDelegate);

    public static abstract class Stub extends Binder implements IInfoWindowAdapter {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("com.huawei.hms.maps.internal.IInfoWindowAdapter");
                IObjectWrapper infoWindow = getInfoWindow(IMarkerDelegate.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeStrongBinder(infoWindow != null ? infoWindow.asBinder() : null);
                return true;
            }
            if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.huawei.hms.maps.internal.IInfoWindowAdapter");
                return true;
            }
            parcel.enforceInterface("com.huawei.hms.maps.internal.IInfoWindowAdapter");
            IObjectWrapper infoContents = getInfoContents(IMarkerDelegate.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            parcel2.writeStrongBinder(infoContents != null ? infoContents.asBinder() : null);
            return true;
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.maps.internal.IInfoWindowAdapter");
        }
    }
}
