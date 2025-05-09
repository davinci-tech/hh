package com.huawei.openalliance.ad;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes5.dex */
public interface a extends IInterface {
    void a(int i);

    /* renamed from: com.huawei.openalliance.ad.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0171a extends Binder implements a {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString("com.huawei.appmarket.service.externalservice.activityresult.IActivityResult");
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.huawei.appmarket.service.externalservice.activityresult.IActivityResult");
            a(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        public AbstractBinderC0171a() {
            attachInterface(this, "com.huawei.appmarket.service.externalservice.activityresult.IActivityResult");
        }
    }
}
