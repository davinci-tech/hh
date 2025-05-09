package com.huawei.harmonyos.interwork;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes3.dex */
public interface e extends IInterface {
    void a(String str) throws RemoteException;

    void a(String str, int i) throws RemoteException;

    public static abstract class a extends Binder implements e {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public a() {
            attachInterface(this, "com.huawei.harmonyos.interwork.IInitCallBack");
        }

        public static e a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.harmonyos.interwork.IInitCallBack");
            if (queryLocalInterface != null && (queryLocalInterface instanceof e)) {
                return (e) queryLocalInterface;
            }
            return new d(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.huawei.harmonyos.interwork.IInitCallBack");
                a(parcel.readString());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.huawei.harmonyos.interwork.IInitCallBack");
                a(parcel.readString(), parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString("com.huawei.harmonyos.interwork.IInitCallBack");
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static final class d implements e {
            public static e d;

            /* renamed from: a, reason: collision with root package name */
            private IBinder f2167a;

            d(IBinder iBinder) {
                this.f2167a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f2167a;
            }

            @Override // com.huawei.harmonyos.interwork.e
            public final void a(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.harmonyos.interwork.IInitCallBack");
                    obtain.writeString(str);
                    if (this.f2167a.transact(1, obtain, null, 1) || a.a() == null) {
                        return;
                    }
                    a.a().a(str);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.huawei.harmonyos.interwork.e
            public final void a(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.harmonyos.interwork.IInitCallBack");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (this.f2167a.transact(2, obtain, null, 1) || a.a() == null) {
                        return;
                    }
                    a.a().a(str, i);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static e a() {
            return d.d;
        }
    }
}
