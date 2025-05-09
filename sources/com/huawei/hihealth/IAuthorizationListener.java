package com.huawei.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* loaded from: classes.dex */
public interface IAuthorizationListener extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hihealth.IAuthorizationListener";

    public static class Default implements IAuthorizationListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hihealth.IAuthorizationListener
        public void onResult(int i, List list) throws RemoteException {
        }
    }

    void onResult(int i, List list) throws RemoteException;

    public static abstract class Stub extends Binder implements IAuthorizationListener {
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IAuthorizationListener.DESCRIPTOR);
        }

        public static IAuthorizationListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IAuthorizationListener.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAuthorizationListener)) {
                return (IAuthorizationListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IAuthorizationListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IAuthorizationListener.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResult(parcel.readInt(), parcel.readArrayList(getClass().getClassLoader()));
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class Proxy implements IAuthorizationListener {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f4051a;

            Proxy(IBinder iBinder) {
                this.f4051a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4051a;
            }

            @Override // com.huawei.hihealth.IAuthorizationListener
            public void onResult(int i, List list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAuthorizationListener.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeList(list);
                    this.f4051a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
