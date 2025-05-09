package com.huawei.hms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes4.dex */
public interface IAnimationListener extends IInterface {
    void onAnimationEnd();

    void onAnimationStart();

    public static abstract class Stub extends Binder implements IAnimationListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        protected boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("com.huawei.hms.maps.internal.IAnimationListener");
                onAnimationStart();
            } else {
                if (i != 2) {
                    if (i != 1598968902) {
                        return super.onTransact(i, parcel, parcel2, i2);
                    }
                    parcel2.writeString("com.huawei.hms.maps.internal.IAnimationListener");
                    return true;
                }
                parcel.enforceInterface("com.huawei.hms.maps.internal.IAnimationListener");
                onAnimationEnd();
            }
            parcel2.writeNoException();
            return true;
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.maps.internal.IAnimationListener");
        }
    }
}
