package com.huawei.wearengine;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.monitor.MonitorData;
import com.huawei.wearengine.monitor.MonitorDataCallback;
import com.huawei.wearengine.monitor.MonitorItem;
import java.util.List;

/* loaded from: classes7.dex */
public interface MonitorManager extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.MonitorManager";

    MonitorData query(Device device, MonitorItem monitorItem) throws RemoteException;

    int registerListListener(Device device, String str, List<MonitorItem> list, MonitorDataCallback monitorDataCallback, int i) throws RemoteException;

    int registerListener(Device device, String str, MonitorItem monitorItem, MonitorDataCallback monitorDataCallback, int i) throws RemoteException;

    int unregisterListener(MonitorDataCallback monitorDataCallback, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements MonitorManager {
        static final int TRANSACTION_query = 4;
        static final int TRANSACTION_registerListListener = 2;
        static final int TRANSACTION_registerListener = 1;
        static final int TRANSACTION_unregisterListener = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, MonitorManager.DESCRIPTOR);
        }

        public static MonitorManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(MonitorManager.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof MonitorManager)) {
                return (MonitorManager) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(MonitorManager.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(MonitorManager.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                int registerListener = registerListener((Device) d.fcu_(parcel, Device.CREATOR), parcel.readString(), (MonitorItem) d.fcu_(parcel, MonitorItem.CREATOR), MonitorDataCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(registerListener);
            } else if (i == 2) {
                int registerListListener = registerListListener((Device) d.fcu_(parcel, Device.CREATOR), parcel.readString(), parcel.createTypedArrayList(MonitorItem.CREATOR), MonitorDataCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(registerListListener);
            } else if (i == 3) {
                int unregisterListener = unregisterListener(MonitorDataCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(unregisterListener);
            } else if (i == 4) {
                MonitorData query = query((Device) d.fcu_(parcel, Device.CREATOR), (MonitorItem) d.fcu_(parcel, MonitorItem.CREATOR));
                parcel2.writeNoException();
                d.fcw_(parcel2, query, 1);
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class a implements MonitorManager {
            private IBinder e;

            a(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.wearengine.MonitorManager
            public int registerListener(Device device, String str, MonitorItem monitorItem, MonitorDataCallback monitorDataCallback, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(MonitorManager.DESCRIPTOR);
                    d.fcw_(obtain, device, 0);
                    obtain.writeString(str);
                    d.fcw_(obtain, monitorItem, 0);
                    obtain.writeStrongInterface(monitorDataCallback);
                    obtain.writeInt(i);
                    this.e.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.MonitorManager
            public int registerListListener(Device device, String str, List<MonitorItem> list, MonitorDataCallback monitorDataCallback, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(MonitorManager.DESCRIPTOR);
                    d.fcw_(obtain, device, 0);
                    obtain.writeString(str);
                    d.fcv_(obtain, list, 0);
                    obtain.writeStrongInterface(monitorDataCallback);
                    obtain.writeInt(i);
                    this.e.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.MonitorManager
            public int unregisterListener(MonitorDataCallback monitorDataCallback, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(MonitorManager.DESCRIPTOR);
                    obtain.writeStrongInterface(monitorDataCallback);
                    obtain.writeInt(i);
                    this.e.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.MonitorManager
            public MonitorData query(Device device, MonitorItem monitorItem) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(MonitorManager.DESCRIPTOR);
                    d.fcw_(obtain, device, 0);
                    d.fcw_(obtain, monitorItem, 0);
                    this.e.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return (MonitorData) d.fcu_(obtain2, MonitorData.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class d {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T fcu_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void fcw_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void fcv_(Parcel parcel, List<T> list, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                fcw_(parcel, list.get(i2), i);
            }
        }
    }
}
