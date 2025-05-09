package com.huawei.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IUnSubscribeListener extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hihealth.IUnSubscribeListener";

    public static class Default implements IUnSubscribeListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hihealth.IUnSubscribeListener
        public void onResult(boolean z) throws RemoteException {
        }
    }

    void onResult(boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IUnSubscribeListener {
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IUnSubscribeListener.DESCRIPTOR);
        }

        public static IUnSubscribeListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IUnSubscribeListener.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IUnSubscribeListener)) {
                return (IUnSubscribeListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IUnSubscribeListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IUnSubscribeListener.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResult(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class Proxy implements IUnSubscribeListener {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f4054a;

            Proxy(IBinder iBinder) {
                this.f4054a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4054a;
            }

            @Override // com.huawei.hihealth.IUnSubscribeListener
            public void onResult(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IUnSubscribeListener.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.f4054a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
