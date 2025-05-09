package com.huawei.hihealth;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IRegisterRealTimeCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hihealth.IRegisterRealTimeCallback";

    public static class Default implements IRegisterRealTimeCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hihealth.IRegisterRealTimeCallback
        public void onDataChanged(Bundle bundle) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IRegisterRealTimeCallback
        public void onResult(int i, String str) throws RemoteException {
        }
    }

    void onDataChanged(Bundle bundle) throws RemoteException;

    void onResult(int i, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IRegisterRealTimeCallback {
        static final int TRANSACTION_onDataChanged = 2;
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IRegisterRealTimeCallback.DESCRIPTOR);
        }

        public static IRegisterRealTimeCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IRegisterRealTimeCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRegisterRealTimeCallback)) {
                return (IRegisterRealTimeCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IRegisterRealTimeCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IRegisterRealTimeCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResult(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
            } else {
                if (i != 2) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                Bundle bundle = (Bundle) _Parcel.bvN_(parcel, Bundle.CREATOR);
                onDataChanged(bundle);
                parcel2.writeNoException();
                _Parcel.bvO_(parcel2, bundle, 1);
            }
            return true;
        }

        static class Proxy implements IRegisterRealTimeCallback {
            private IBinder b;

            Proxy(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.hihealth.IRegisterRealTimeCallback
            public void onResult(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRegisterRealTimeCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.b.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IRegisterRealTimeCallback
            public void onDataChanged(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRegisterRealTimeCallback.DESCRIPTOR);
                    _Parcel.bvO_(obtain, bundle, 0);
                    this.b.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        bundle.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class _Parcel {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T bvN_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bvO_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
