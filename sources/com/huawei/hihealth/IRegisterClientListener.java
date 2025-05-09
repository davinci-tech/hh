package com.huawei.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IRegisterClientListener extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hihealth.IRegisterClientListener";

    public static class Default implements IRegisterClientListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hihealth.IRegisterClientListener
        public void onResult(HiHealthClient hiHealthClient) throws RemoteException {
        }
    }

    void onResult(HiHealthClient hiHealthClient) throws RemoteException;

    public static abstract class Stub extends Binder implements IRegisterClientListener {
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IRegisterClientListener.DESCRIPTOR);
        }

        public static IRegisterClientListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IRegisterClientListener.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRegisterClientListener)) {
                return (IRegisterClientListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IRegisterClientListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IRegisterClientListener.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResult((HiHealthClient) _Parcel.bvJ_(parcel, HiHealthClient.CREATOR));
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class Proxy implements IRegisterClientListener {
            private IBinder e;

            Proxy(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.hihealth.IRegisterClientListener
            public void onResult(HiHealthClient hiHealthClient) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRegisterClientListener.DESCRIPTOR);
                    _Parcel.bvK_(obtain, hiHealthClient, 0);
                    this.e.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class _Parcel {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T bvJ_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bvK_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
