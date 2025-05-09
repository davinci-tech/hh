package com.huawei.unitedevice.p2p;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.devicesdk.entity.DeviceInfo;

/* loaded from: classes7.dex */
public interface ReceiverCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.unitedevice.p2p.ReceiverCallback";

    void onReceiveMessage(DeviceInfo deviceInfo, MessageParcel messageParcel) throws RemoteException;

    public static abstract class Stub extends Binder implements ReceiverCallback {
        static final int TRANSACTION_onReceiveMessage = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ReceiverCallback.DESCRIPTOR);
        }

        public static ReceiverCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ReceiverCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ReceiverCallback)) {
                return (ReceiverCallback) queryLocalInterface;
            }
            return new c(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ReceiverCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ReceiverCallback.DESCRIPTOR);
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            DeviceInfo deviceInfo = (DeviceInfo) b.ekk_(parcel, DeviceInfo.CREATOR);
            MessageParcel messageParcel = (MessageParcel) b.ekk_(parcel, MessageParcel.CREATOR);
            onReceiveMessage(deviceInfo, messageParcel);
            parcel2.writeNoException();
            b.ekl_(parcel2, deviceInfo, 1);
            b.ekl_(parcel2, messageParcel, 1);
            return true;
        }

        static class c implements ReceiverCallback {
            private IBinder c;

            c(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.unitedevice.p2p.ReceiverCallback
            public void onReceiveMessage(DeviceInfo deviceInfo, MessageParcel messageParcel) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ReceiverCallback.DESCRIPTOR);
                    b.ekl_(obtain, deviceInfo, 0);
                    b.ekl_(obtain, messageParcel, 0);
                    this.c.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        deviceInfo.readFromParcel(obtain2);
                    }
                    if (obtain2.readInt() != 0) {
                        messageParcel.readFromParcel(obtain2);
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
        public static <T> T ekk_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void ekl_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
