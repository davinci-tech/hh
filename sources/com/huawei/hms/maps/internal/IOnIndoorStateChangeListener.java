package com.huawei.hms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.hms.maps.model.internal.IIndoorBuildingDelegate;

/* loaded from: classes9.dex */
public interface IOnIndoorStateChangeListener extends IInterface {
    void onIndoorBuildingFocused();

    void onIndoorLevelActivated(IIndoorBuildingDelegate iIndoorBuildingDelegate);

    public static abstract class Stub extends Binder implements IOnIndoorStateChangeListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("com.huawei.hms.maps.internal.IOnIndoorStateChangeListener");
                onIndoorBuildingFocused();
            } else {
                if (i != 2) {
                    if (i != 1598968902) {
                        return super.onTransact(i, parcel, parcel2, i2);
                    }
                    parcel2.writeString("com.huawei.hms.maps.internal.IOnIndoorStateChangeListener");
                    return true;
                }
                parcel.enforceInterface("com.huawei.hms.maps.internal.IOnIndoorStateChangeListener");
                onIndoorLevelActivated(IIndoorBuildingDelegate.Stub.asInterface(parcel.readStrongBinder()));
            }
            parcel2.writeNoException();
            return true;
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.maps.internal.IOnIndoorStateChangeListener");
        }
    }
}
