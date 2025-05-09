package com.huawei.controlcenter.featureability.sdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes3.dex */
public interface IAuthCallback extends IInterface {
    void onAuthResult(boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IAuthCallback {
        private static final String DESCRIPTOR = "com.huawei.controlcenter.featureability.sdk.IAuthCallback";
        static final int TRANSACTION_onAuthResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAuthCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAuthCallback)) {
                return (IAuthCallback) queryLocalInterface;
            }
            return new d(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onAuthResult(parcel.readInt() != 0);
            parcel2.writeNoException();
            return true;
        }

        static class d implements IAuthCallback {
            public static IAuthCallback e;

            /* renamed from: a, reason: collision with root package name */
            private IBinder f1937a;

            d(IBinder iBinder) {
                this.f1937a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f1937a;
            }

            @Override // com.huawei.controlcenter.featureability.sdk.IAuthCallback
            public void onAuthResult(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.f1937a.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAuthResult(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAuthCallback iAuthCallback) {
            if (d.e != null || iAuthCallback == null) {
                return false;
            }
            d.e = iAuthCallback;
            return true;
        }

        public static IAuthCallback getDefaultImpl() {
            return d.e;
        }
    }
}
