package com.huawei.devicesdk.callback;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.unitedevice.entity.UniteDevice;

/* loaded from: classes3.dex */
public interface StatusCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.devicesdk.callback.StatusCallback";

    void onStatusChanged(int i, UniteDevice uniteDevice, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements StatusCallback {
        static final int TRANSACTION_onStatusChanged = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, StatusCallback.DESCRIPTOR);
        }

        public static StatusCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(StatusCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof StatusCallback)) {
                return (StatusCallback) queryLocalInterface;
            }
            return new e(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(StatusCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(StatusCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onStatusChanged(parcel.readInt(), (UniteDevice) d.rm_(parcel, UniteDevice.CREATOR), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class e implements StatusCallback {
            private IBinder b;

            e(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.devicesdk.callback.StatusCallback
            public void onStatusChanged(int i, UniteDevice uniteDevice, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(StatusCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    d.rn_(obtain, uniteDevice, 0);
                    obtain.writeInt(i2);
                    this.b.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class d {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T rm_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void rn_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
