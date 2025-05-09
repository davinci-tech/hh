package com.huawei.wearengine.ota;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.wearengine.device.Device;

/* loaded from: classes9.dex */
public interface CheckBinderCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.ota.CheckBinderCallback";

    void onCheckComplete(Device device, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements CheckBinderCallback {
        static final int TRANSACTION_onCheckComplete = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, CheckBinderCallback.DESCRIPTOR);
        }

        public static CheckBinderCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(CheckBinderCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof CheckBinderCallback)) {
                return (CheckBinderCallback) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(CheckBinderCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(CheckBinderCallback.DESCRIPTOR);
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            Device device = (Device) b.fdR_(parcel, Device.CREATOR);
            onCheckComplete(device, parcel.readString());
            parcel2.writeNoException();
            b.fdS_(parcel2, device, 1);
            return true;
        }

        static class a implements CheckBinderCallback {
            private IBinder c;

            a(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.wearengine.ota.CheckBinderCallback
            public void onCheckComplete(Device device, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(CheckBinderCallback.DESCRIPTOR);
                    b.fdS_(obtain, device, 0);
                    obtain.writeString(str);
                    this.c.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        device.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class b {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T fdR_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void fdS_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
