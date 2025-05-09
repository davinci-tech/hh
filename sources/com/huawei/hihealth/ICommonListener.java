package com.huawei.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* loaded from: classes.dex */
public interface ICommonListener extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hihealth.ICommonListener";

    public static class Default implements ICommonListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hihealth.ICommonListener
        public void onFailure(int i, List list) throws RemoteException {
        }

        @Override // com.huawei.hihealth.ICommonListener
        public void onSuccess(int i, List list) throws RemoteException {
        }
    }

    void onFailure(int i, List list) throws RemoteException;

    void onSuccess(int i, List list) throws RemoteException;

    public static abstract class Stub extends Binder implements ICommonListener {
        static final int TRANSACTION_onFailure = 2;
        static final int TRANSACTION_onSuccess = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ICommonListener.DESCRIPTOR);
        }

        public static ICommonListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ICommonListener.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ICommonListener)) {
                return (ICommonListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ICommonListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ICommonListener.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onSuccess(parcel.readInt(), parcel.readArrayList(getClass().getClassLoader()));
                parcel2.writeNoException();
            } else if (i == 2) {
                onFailure(parcel.readInt(), parcel.readArrayList(getClass().getClassLoader()));
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class Proxy implements ICommonListener {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f4053a;

            Proxy(IBinder iBinder) {
                this.f4053a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4053a;
            }

            @Override // com.huawei.hihealth.ICommonListener
            public void onSuccess(int i, List list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICommonListener.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeList(list);
                    this.f4053a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.ICommonListener
            public void onFailure(int i, List list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICommonListener.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeList(list);
                    this.f4053a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
