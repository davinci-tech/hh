package com.huawei.hms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.hms.maps.model.internal.IMarkerDelegate;

/* loaded from: classes9.dex */
public interface IOnInfoWindowClickListener extends IInterface {
    void onInfoWindowClick(IMarkerDelegate iMarkerDelegate);

    public static abstract class Stub extends Binder implements IOnInfoWindowClickListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.huawei.hms.maps.internal.IOnInfoWindowClickListener");
                return true;
            }
            parcel.enforceInterface("com.huawei.hms.maps.internal.IOnInfoWindowClickListener");
            onInfoWindowClick(IMarkerDelegate.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.maps.internal.IOnInfoWindowClickListener");
        }
    }
}
