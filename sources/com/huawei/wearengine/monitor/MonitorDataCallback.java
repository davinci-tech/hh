package com.huawei.wearengine.monitor;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* loaded from: classes7.dex */
public interface MonitorDataCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.monitor.MonitorDataCallback";

    /* loaded from: classes9.dex */
    public static class Default implements MonitorDataCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.wearengine.monitor.MonitorDataCallback
        public void onChanged(int i, MonitorItem monitorItem, MonitorData monitorData) throws RemoteException {
        }
    }

    void onChanged(int i, MonitorItem monitorItem, MonitorData monitorData) throws RemoteException;

    public static abstract class Stub extends Binder implements MonitorDataCallback {
        static final int TRANSACTION_onChanged = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, MonitorDataCallback.DESCRIPTOR);
        }

        public static MonitorDataCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(MonitorDataCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof MonitorDataCallback)) {
                return (MonitorDataCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(MonitorDataCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(MonitorDataCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                int readInt = parcel.readInt();
                MonitorItem monitorItem = (MonitorItem) c.fdv_(parcel, MonitorItem.CREATOR);
                MonitorData monitorData = (MonitorData) c.fdv_(parcel, MonitorData.CREATOR);
                onChanged(readInt, monitorItem, monitorData);
                parcel2.writeNoException();
                c.fdw_(parcel2, monitorItem, 1);
                c.fdw_(parcel2, monitorData, 1);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class Proxy implements MonitorDataCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.wearengine.monitor.MonitorDataCallback
            public void onChanged(int i, MonitorItem monitorItem, MonitorData monitorData) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(MonitorDataCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    c.fdw_(obtain, monitorItem, 0);
                    c.fdw_(obtain, monitorData, 0);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        monitorItem.readFromParcel(obtain2);
                    }
                    if (obtain2.readInt() != 0) {
                        monitorData.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return MonitorDataCallback.DESCRIPTOR;
            }
        }
    }

    public static class c {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T fdv_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void fdw_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
