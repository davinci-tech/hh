package com.huawei.hwcloudjs.a;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes9.dex */
public interface a extends IInterface {

    /* renamed from: com.huawei.hwcloudjs.a.a$a, reason: collision with other inner class name */
    public static class C0156a implements a {
        @Override // com.huawei.hwcloudjs.a.a
        public int a(Bundle bundle) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    int a(Bundle bundle) throws RemoteException;

    public static abstract class b extends Binder implements a {

        /* renamed from: a, reason: collision with root package name */
        private static final String f6186a = "com.huawei.hwcloudjs.aidl.IConnectionSdk";
        static final int b = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(f6186a);
                return true;
            }
            parcel.enforceInterface(f6186a);
            int a2 = a(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            parcel2.writeInt(a2);
            return true;
        }

        /* renamed from: com.huawei.hwcloudjs.a.a$b$a, reason: collision with other inner class name */
        static class C0157a implements a {

            /* renamed from: a, reason: collision with root package name */
            public static a f6187a;
            private IBinder b;

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            public String a() {
                return b.f6186a;
            }

            @Override // com.huawei.hwcloudjs.a.a
            public int a(Bundle bundle) throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(b.f6186a);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(1, obtain, obtain2, 0) || b.a() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = b.a().a(bundle);
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            C0157a(IBinder iBinder) {
                this.b = iBinder;
            }
        }

        public static boolean a(a aVar) {
            if (C0157a.f6187a != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (aVar == null) {
                return false;
            }
            C0157a.f6187a = aVar;
            return true;
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(f6186a);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof a)) ? new C0157a(iBinder) : (a) queryLocalInterface;
        }

        public static a a() {
            return C0157a.f6187a;
        }

        public b() {
            attachInterface(this, f6186a);
        }
    }
}
