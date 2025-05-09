package com.huawei.hms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes4.dex */
public interface ICancelableCallback extends IInterface {
    void onCancel();

    void onFinish();

    public static abstract class Stub extends Binder implements ICancelableCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("com.huawei.hms.maps.internal.ICancelableCallback");
                onFinish();
            } else {
                if (i != 2) {
                    if (i != 1598968902) {
                        return super.onTransact(i, parcel, parcel2, i2);
                    }
                    parcel2.writeString("com.huawei.hms.maps.internal.ICancelableCallback");
                    return true;
                }
                parcel.enforceInterface("com.huawei.hms.maps.internal.ICancelableCallback");
                onCancel();
            }
            parcel2.writeNoException();
            return true;
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.maps.internal.ICancelableCallback");
        }
    }
}
