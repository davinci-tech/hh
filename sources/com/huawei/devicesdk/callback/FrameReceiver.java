package com.huawei.devicesdk.callback;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.devicesdk.entity.DeviceDataFrameParcel;
import com.huawei.unitedevice.entity.UniteDevice;

/* loaded from: classes3.dex */
public interface FrameReceiver extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.devicesdk.callback.FrameReceiver";

    void onChannelResult(int i, UniteDevice uniteDevice, String str) throws RemoteException;

    void onFrameReceived(int i, UniteDevice uniteDevice, DeviceDataFrameParcel deviceDataFrameParcel) throws RemoteException;

    public static abstract class Stub extends Binder implements FrameReceiver {
        static final int TRANSACTION_onChannelResult = 2;
        static final int TRANSACTION_onFrameReceived = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, FrameReceiver.DESCRIPTOR);
        }

        public static FrameReceiver asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(FrameReceiver.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof FrameReceiver)) {
                return (FrameReceiver) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(FrameReceiver.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(FrameReceiver.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onFrameReceived(parcel.readInt(), (UniteDevice) d.re_(parcel, UniteDevice.CREATOR), (DeviceDataFrameParcel) d.re_(parcel, DeviceDataFrameParcel.CREATOR));
                parcel2.writeNoException();
            } else if (i == 2) {
                onChannelResult(parcel.readInt(), (UniteDevice) d.re_(parcel, UniteDevice.CREATOR), parcel.readString());
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class a implements FrameReceiver {
            private IBinder e;

            a(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.devicesdk.callback.FrameReceiver
            public void onFrameReceived(int i, UniteDevice uniteDevice, DeviceDataFrameParcel deviceDataFrameParcel) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(FrameReceiver.DESCRIPTOR);
                    obtain.writeInt(i);
                    d.rf_(obtain, uniteDevice, 0);
                    d.rf_(obtain, deviceDataFrameParcel, 0);
                    this.e.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.callback.FrameReceiver
            public void onChannelResult(int i, UniteDevice uniteDevice, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(FrameReceiver.DESCRIPTOR);
                    obtain.writeInt(i);
                    d.rf_(obtain, uniteDevice, 0);
                    obtain.writeString(str);
                    this.e.transact(2, obtain, obtain2, 0);
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
        public static <T> T re_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void rf_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
