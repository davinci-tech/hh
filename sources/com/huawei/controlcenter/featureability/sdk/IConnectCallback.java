package com.huawei.controlcenter.featureability.sdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes3.dex */
public interface IConnectCallback extends IInterface {
    void connect(String str, String str2, String str3) throws RemoteException;

    void disconnect(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IConnectCallback {
        private static final String DESCRIPTOR = "com.huawei.controlcenter.featureability.sdk.IConnectCallback";
        static final int TRANSACTION_connect = 1;
        static final int TRANSACTION_disconnect = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IConnectCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IConnectCallback)) {
                return (IConnectCallback) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                connect(parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            disconnect(parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        static class a implements IConnectCallback {
            public static IConnectCallback c;
            private IBinder b;

            a(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.controlcenter.featureability.sdk.IConnectCallback
            public void connect(String str, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (!this.b.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().connect(str, str2, str3);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.controlcenter.featureability.sdk.IConnectCallback
            public void disconnect(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.b.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disconnect(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IConnectCallback iConnectCallback) {
            if (a.c != null || iConnectCallback == null) {
                return false;
            }
            a.c = iConnectCallback;
            return true;
        }

        public static IConnectCallback getDefaultImpl() {
            return a.c;
        }
    }
}
