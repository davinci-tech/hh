package com.huawei.hwdevicemgr.framework;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;

/* loaded from: classes5.dex */
public interface INoitifyPhoneServiceCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hwdevicemgr.framework.INoitifyPhoneServiceCallback";

    void executeResponse(String str, int i, DeviceInfo deviceInfo, int i2, String str2) throws RemoteException;

    public static abstract class Stub extends Binder implements INoitifyPhoneServiceCallback {
        static final int TRANSACTION_executeResponse = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, INoitifyPhoneServiceCallback.DESCRIPTOR);
        }

        public static INoitifyPhoneServiceCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(INoitifyPhoneServiceCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof INoitifyPhoneServiceCallback)) {
                return (INoitifyPhoneServiceCallback) queryLocalInterface;
            }
            return new e(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(INoitifyPhoneServiceCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(INoitifyPhoneServiceCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                executeResponse(parcel.readString(), parcel.readInt(), (DeviceInfo) b.bPn_(parcel, DeviceInfo.CREATOR), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class e implements INoitifyPhoneServiceCallback {
            private IBinder d;

            e(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.hwdevicemgr.framework.INoitifyPhoneServiceCallback
            public void executeResponse(String str, int i, DeviceInfo deviceInfo, int i2, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(INoitifyPhoneServiceCallback.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    b.bPo_(obtain, deviceInfo, 0);
                    obtain.writeInt(i2);
                    obtain.writeString(str2);
                    this.d.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class b {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T bPn_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bPo_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
