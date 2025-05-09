package com.huawei.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IRealTimeDataCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hihealth.IRealTimeDataCallback";

    public static class Default implements IRealTimeDataCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hihealth.IRealTimeDataCallback
        public void onChange(int i, String str) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IRealTimeDataCallback
        public void onResult(int i) throws RemoteException {
        }
    }

    void onChange(int i, String str) throws RemoteException;

    void onResult(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IRealTimeDataCallback {
        static final int TRANSACTION_onChange = 2;
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IRealTimeDataCallback.DESCRIPTOR);
        }

        public static IRealTimeDataCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IRealTimeDataCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRealTimeDataCallback)) {
                return (IRealTimeDataCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IRealTimeDataCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IRealTimeDataCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResult(parcel.readInt());
                parcel2.writeNoException();
            } else if (i == 2) {
                onChange(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class Proxy implements IRealTimeDataCallback {
            private IBinder c;

            Proxy(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.hihealth.IRealTimeDataCallback
            public void onResult(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRealTimeDataCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.c.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IRealTimeDataCallback
            public void onChange(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRealTimeDataCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.c.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
