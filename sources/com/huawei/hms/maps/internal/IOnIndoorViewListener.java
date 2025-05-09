package com.huawei.hms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.hms.maps.model.IndoorBuilding;

/* loaded from: classes9.dex */
public interface IOnIndoorViewListener extends IInterface {
    void onIndoorFocus(IndoorBuilding indoorBuilding);

    void onIndoorLeave();

    public static abstract class Stub extends Binder implements IOnIndoorViewListener {
        public static final String DESCRIPTOR = "com.huawei.hms.maps.internal.IOnIndoorViewListener";

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onIndoorFocus(parcel.readInt() != 0 ? IndoorBuilding.CREATOR.createFromParcel(parcel) : null);
            } else {
                if (i != 2) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel.enforceInterface(DESCRIPTOR);
                onIndoorLeave();
            }
            parcel2.writeNoException();
            return true;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }
    }
}
