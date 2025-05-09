package com.huawei.devicesdk.callback;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.devicesdk.entity.CommandMessageParcel;
import com.huawei.unitedevice.entity.UniteDevice;

/* loaded from: classes3.dex */
public interface DeviceHandshakeCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.devicesdk.callback.DeviceHandshakeCallback";

    int onProcess(UniteDevice uniteDevice, String str, CommandMessageParcel commandMessageParcel) throws RemoteException;

    String preProcess(UniteDevice uniteDevice, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements DeviceHandshakeCallback {
        static final int TRANSACTION_onProcess = 1;
        static final int TRANSACTION_preProcess = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DeviceHandshakeCallback.DESCRIPTOR);
        }

        public static DeviceHandshakeCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DeviceHandshakeCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof DeviceHandshakeCallback)) {
                return (DeviceHandshakeCallback) queryLocalInterface;
            }
            return new b(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(DeviceHandshakeCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(DeviceHandshakeCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                UniteDevice uniteDevice = (UniteDevice) b.ra_(parcel, UniteDevice.CREATOR);
                String readString = parcel.readString();
                CommandMessageParcel commandMessageParcel = (CommandMessageParcel) b.ra_(parcel, CommandMessageParcel.CREATOR);
                int onProcess = onProcess(uniteDevice, readString, commandMessageParcel);
                parcel2.writeNoException();
                parcel2.writeInt(onProcess);
                b.rb_(parcel2, commandMessageParcel, 1);
            } else if (i == 2) {
                String preProcess = preProcess((UniteDevice) b.ra_(parcel, UniteDevice.CREATOR), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(preProcess);
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class b implements DeviceHandshakeCallback {
            private IBinder d;

            b(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.devicesdk.callback.DeviceHandshakeCallback
            public int onProcess(UniteDevice uniteDevice, String str, CommandMessageParcel commandMessageParcel) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DeviceHandshakeCallback.DESCRIPTOR);
                    b.rb_(obtain, uniteDevice, 0);
                    obtain.writeString(str);
                    b.rb_(obtain, commandMessageParcel, 0);
                    this.d.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    if (obtain2.readInt() != 0) {
                        commandMessageParcel.readFromParcel(obtain2);
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.callback.DeviceHandshakeCallback
            public String preProcess(UniteDevice uniteDevice, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DeviceHandshakeCallback.DESCRIPTOR);
                    b.rb_(obtain, uniteDevice, 0);
                    obtain.writeString(str);
                    this.d.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class b {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T ra_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void rb_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
