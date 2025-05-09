package com.huawei.harmonyos.interwork;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes3.dex */
public interface c extends IInterface {
    void a() throws RemoteException;

    public static abstract class a extends Binder implements c {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public a() {
            attachInterface(this, "com.huawei.harmonyos.interwork.IDeathRecipientCallback");
        }

        public static c a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.harmonyos.interwork.IDeathRecipientCallback");
            if (queryLocalInterface != null && (queryLocalInterface instanceof c)) {
                return (c) queryLocalInterface;
            }
            return new e(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.huawei.harmonyos.interwork.IDeathRecipientCallback");
                a();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString("com.huawei.harmonyos.interwork.IDeathRecipientCallback");
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static final class e implements c {
            public static c c;
            private IBinder e;

            e(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.harmonyos.interwork.c
            public final void a() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.harmonyos.interwork.IDeathRecipientCallback");
                    if (this.e.transact(1, obtain, null, 1) || a.b() == null) {
                        return;
                    }
                    a.b().a();
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static c b() {
            return e.c;
        }
    }
}
