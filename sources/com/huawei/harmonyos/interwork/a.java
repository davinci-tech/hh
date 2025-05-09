package com.huawei.harmonyos.interwork;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.harmonyos.interwork.base.bundle.ElementName;

/* loaded from: classes3.dex */
public interface a extends IInterface {
    void a(ElementName elementName, int i) throws RemoteException;

    void a(ElementName elementName, IBinder iBinder, int i) throws RemoteException;

    /* renamed from: com.huawei.harmonyos.interwork.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0048a extends Binder implements a {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public AbstractBinderC0048a() {
            attachInterface(this, "com.huawei.harmonyos.interwork.IAbilityConnection");
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.huawei.harmonyos.interwork.IAbilityConnection");
                a(parcel.readInt() != 0 ? ElementName.CREATOR.createFromParcel(parcel) : null, parcel.readStrongBinder(), parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.huawei.harmonyos.interwork.IAbilityConnection");
                a(parcel.readInt() != 0 ? ElementName.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString("com.huawei.harmonyos.interwork.IAbilityConnection");
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
