package com.huawei.harmonyos.interwork;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes3.dex */
public interface d extends IInterface {
    void a(String str, int i) throws RemoteException;

    void b(String str, int i) throws RemoteException;

    public static abstract class a extends Binder implements d {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public a() {
            attachInterface(this, "com.huawei.harmonyos.interwork.IDeviceStateCallback");
        }

        public static d a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.harmonyos.interwork.IDeviceStateCallback");
            if (queryLocalInterface != null && (queryLocalInterface instanceof d)) {
                return (d) queryLocalInterface;
            }
            return new C0050a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.huawei.harmonyos.interwork.IDeviceStateCallback");
                a(parcel.readString(), parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.huawei.harmonyos.interwork.IDeviceStateCallback");
                b(parcel.readString(), parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString("com.huawei.harmonyos.interwork.IDeviceStateCallback");
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        /* renamed from: com.huawei.harmonyos.interwork.d$a$a, reason: collision with other inner class name */
        static final class C0050a implements d {
            public static d b;
            private IBinder e;

            C0050a(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.harmonyos.interwork.d
            public final void a(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.harmonyos.interwork.IDeviceStateCallback");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (this.e.transact(1, obtain, null, 1) || a.a() == null) {
                        return;
                    }
                    a.a().a(str, i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.huawei.harmonyos.interwork.d
            public final void b(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.harmonyos.interwork.IDeviceStateCallback");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (this.e.transact(2, obtain, null, 1) || a.a() == null) {
                        return;
                    }
                    a.a().b(str, i);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static d a() {
            return C0050a.b;
        }
    }
}
