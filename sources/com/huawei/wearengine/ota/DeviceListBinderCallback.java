package com.huawei.wearengine.ota;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.wearengine.device.Device;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public interface DeviceListBinderCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.ota.DeviceListBinderCallback";

    void onDeviceListObtain(List<Device> list) throws RemoteException;

    public static abstract class Stub extends Binder implements DeviceListBinderCallback {
        static final int TRANSACTION_onDeviceListObtain = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DeviceListBinderCallback.DESCRIPTOR);
        }

        public static DeviceListBinderCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DeviceListBinderCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof DeviceListBinderCallback)) {
                return (DeviceListBinderCallback) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(DeviceListBinderCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(DeviceListBinderCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                ArrayList createTypedArrayList = parcel.createTypedArrayList(Device.CREATOR);
                onDeviceListObtain(createTypedArrayList);
                parcel2.writeNoException();
                a.fdU_(parcel2, createTypedArrayList, 1);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class a implements DeviceListBinderCallback {
            private IBinder e;

            a(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.wearengine.ota.DeviceListBinderCallback
            public void onDeviceListObtain(List<Device> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DeviceListBinderCallback.DESCRIPTOR);
                    a.fdU_(obtain, list, 0);
                    this.e.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    obtain2.readTypedList(list, Device.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class a {
        private static <T extends Parcelable> void fdV_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void fdU_(Parcel parcel, List<T> list, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                fdV_(parcel, list.get(i2), i);
            }
        }
    }
}
