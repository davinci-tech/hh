package com.huawei.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ISendDataCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hihealth.ISendDataCallback";

    public static class Default implements ISendDataCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hihealth.ISendDataCallback
        public void onFinish(int i, String str) throws RemoteException {
        }

        @Override // com.huawei.hihealth.ISendDataCallback
        public void onSend(int i, String str, long j) throws RemoteException {
        }
    }

    void onFinish(int i, String str) throws RemoteException;

    void onSend(int i, String str, long j) throws RemoteException;

    public static abstract class Stub extends Binder implements ISendDataCallback {
        static final int TRANSACTION_onFinish = 2;
        static final int TRANSACTION_onSend = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ISendDataCallback.DESCRIPTOR);
        }

        public static ISendDataCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ISendDataCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISendDataCallback)) {
                return (ISendDataCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ISendDataCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ISendDataCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onSend(parcel.readInt(), parcel.readString(), parcel.readLong());
                parcel2.writeNoException();
            } else if (i == 2) {
                onFinish(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class Proxy implements ISendDataCallback {
            private IBinder d;

            Proxy(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.hihealth.ISendDataCallback
            public void onSend(int i, String str, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISendDataCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    this.d.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.ISendDataCallback
            public void onFinish(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISendDataCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.d.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
