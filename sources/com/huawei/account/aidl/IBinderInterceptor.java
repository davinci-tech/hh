package com.huawei.account.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface IBinderInterceptor extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.account.aidl.IBinderInterceptor";

    IBinder getServiceBinder(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IBinderInterceptor {
        static final int TRANSACTION_getServiceBinder = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IBinderInterceptor.DESCRIPTOR);
        }

        public static IBinderInterceptor asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IBinderInterceptor.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IBinderInterceptor)) {
                return (IBinderInterceptor) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IBinderInterceptor.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IBinderInterceptor.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                IBinder serviceBinder = getServiceBinder(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeStrongBinder(serviceBinder);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class a implements IBinderInterceptor {
            private IBinder b;

            a(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.account.aidl.IBinderInterceptor
            public IBinder getServiceBinder(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IBinderInterceptor.DESCRIPTOR);
                    obtain.writeString(str);
                    this.b.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
