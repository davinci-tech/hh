package com.huawei.hms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.hms.maps.internal.IOnLocationChangeListener;

/* loaded from: classes9.dex */
public interface ILocationSourceDelegate extends IInterface {
    void activate(IOnLocationChangeListener iOnLocationChangeListener);

    void deactivate();

    public static abstract class Stub extends Binder implements ILocationSourceDelegate {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("com.huawei.hms.maps.internal.ILocationSourceDelegate");
                activate(IOnLocationChangeListener.Stub.asInterface(parcel.readStrongBinder()));
            } else {
                if (i != 2) {
                    if (i != 1598968902) {
                        return super.onTransact(i, parcel, parcel2, i2);
                    }
                    parcel2.writeString("com.huawei.hms.maps.internal.ILocationSourceDelegate");
                    return true;
                }
                parcel.enforceInterface("com.huawei.hms.maps.internal.ILocationSourceDelegate");
                deactivate();
            }
            parcel2.writeNoException();
            return true;
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.maps.internal.ILocationSourceDelegate");
        }
    }
}
