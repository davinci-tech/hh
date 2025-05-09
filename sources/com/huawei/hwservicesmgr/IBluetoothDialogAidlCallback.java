package com.huawei.hwservicesmgr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.hwbtsdk.btdatatype.datatype.BluetoothDeviceNode;
import java.util.List;

/* loaded from: classes5.dex */
public interface IBluetoothDialogAidlCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hwservicesmgr.IBluetoothDialogAidlCallback";

    void onScanFinished() throws RemoteException;

    void onSetList(List<BluetoothDeviceNode> list, boolean z, int i) throws RemoteException;

    void onSetNameFilter(List<String> list) throws RemoteException;

    public static abstract class Stub extends Binder implements IBluetoothDialogAidlCallback {
        static final int TRANSACTION_onScanFinished = 2;
        static final int TRANSACTION_onSetList = 1;
        static final int TRANSACTION_onSetNameFilter = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IBluetoothDialogAidlCallback.DESCRIPTOR);
        }

        public static IBluetoothDialogAidlCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IBluetoothDialogAidlCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IBluetoothDialogAidlCallback)) {
                return (IBluetoothDialogAidlCallback) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IBluetoothDialogAidlCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IBluetoothDialogAidlCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onSetList(parcel.createTypedArrayList(BluetoothDeviceNode.CREATOR), parcel.readInt() != 0, parcel.readInt());
                parcel2.writeNoException();
            } else if (i == 2) {
                onScanFinished();
                parcel2.writeNoException();
            } else if (i == 3) {
                onSetNameFilter(parcel.createStringArrayList());
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class a implements IBluetoothDialogAidlCallback {
            private IBinder d;

            a(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.hwservicesmgr.IBluetoothDialogAidlCallback
            public void onSetList(List<BluetoothDeviceNode> list, boolean z, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IBluetoothDialogAidlCallback.DESCRIPTOR);
                    a.bRw_(obtain, list, 0);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    this.d.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IBluetoothDialogAidlCallback
            public void onScanFinished() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IBluetoothDialogAidlCallback.DESCRIPTOR);
                    this.d.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IBluetoothDialogAidlCallback
            public void onSetNameFilter(List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IBluetoothDialogAidlCallback.DESCRIPTOR);
                    obtain.writeStringList(list);
                    this.d.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class a {
        private static <T extends Parcelable> void bRx_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bRw_(Parcel parcel, List<T> list, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                bRx_(parcel, list.get(i2), i);
            }
        }
    }
}
