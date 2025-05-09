package com.huawei.hms.hihealth.options;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.hms.health.aaci;

/* loaded from: classes4.dex */
public interface aaba extends IInterface {

    public static abstract class aab extends Binder implements aaba {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("com.huawei.hms.hihealth.options.OnCommonResultListener");
            }
            if (i == 1598968902) {
                parcel2.writeString("com.huawei.hms.hihealth.options.OnCommonResultListener");
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            ((aaci.aab) this).aab(parcel.readArrayList(getClass().getClassLoader()), parcel.readInt(), parcel.readInt(), parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        public aab() {
            attachInterface(this, "com.huawei.hms.hihealth.options.OnCommonResultListener");
        }
    }
}
