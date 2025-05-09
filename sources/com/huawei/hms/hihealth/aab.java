package com.huawei.hms.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes4.dex */
public interface aab extends IInterface {

    /* renamed from: com.huawei.hms.hihealth.aab$aab, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0099aab extends Binder implements aab {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("com.huawei.hms.hihealth.ClientToken");
            return true;
        }

        public AbstractBinderC0099aab() {
            attachInterface(this, "com.huawei.hms.hihealth.ClientToken");
        }
    }
}
