package com.huawei.wearengine.monitor;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* loaded from: classes7.dex */
public interface QueryDataCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.monitor.QueryDataCallback";

    /* loaded from: classes9.dex */
    public static class Default implements QueryDataCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.wearengine.monitor.QueryDataCallback
        public void onDataReceived(int i, MonitorMessage monitorMessage) throws RemoteException {
        }
    }

    void onDataReceived(int i, MonitorMessage monitorMessage) throws RemoteException;

    public static abstract class Stub extends Binder implements QueryDataCallback {
        static final int TRANSACTION_onDataReceived = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, QueryDataCallback.DESCRIPTOR);
        }

        public static QueryDataCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(QueryDataCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof QueryDataCallback)) {
                return (QueryDataCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(QueryDataCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(QueryDataCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                int readInt = parcel.readInt();
                MonitorMessage monitorMessage = (MonitorMessage) a.fdC_(parcel, MonitorMessage.CREATOR);
                onDataReceived(readInt, monitorMessage);
                parcel2.writeNoException();
                a.fdD_(parcel2, monitorMessage, 1);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class Proxy implements QueryDataCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.wearengine.monitor.QueryDataCallback
            public void onDataReceived(int i, MonitorMessage monitorMessage) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(QueryDataCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    a.fdD_(obtain, monitorMessage, 0);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        monitorMessage.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return QueryDataCallback.DESCRIPTOR;
            }
        }
    }

    public static class a {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T fdC_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void fdD_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
