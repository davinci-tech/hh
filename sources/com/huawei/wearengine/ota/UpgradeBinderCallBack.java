package com.huawei.wearengine.ota;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.wearengine.device.Device;

/* loaded from: classes9.dex */
public interface UpgradeBinderCallBack extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.ota.UpgradeBinderCallBack";

    void onUpgradeStatus(Device device, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements UpgradeBinderCallBack {
        static final int TRANSACTION_onUpgradeStatus = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, UpgradeBinderCallBack.DESCRIPTOR);
        }

        public static UpgradeBinderCallBack asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(UpgradeBinderCallBack.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof UpgradeBinderCallBack)) {
                return (UpgradeBinderCallBack) queryLocalInterface;
            }
            return new c(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(UpgradeBinderCallBack.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(UpgradeBinderCallBack.DESCRIPTOR);
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            Device device = (Device) c.fdY_(parcel, Device.CREATOR);
            onUpgradeStatus(device, parcel.readString());
            parcel2.writeNoException();
            c.fdZ_(parcel2, device, 1);
            return true;
        }

        static class c implements UpgradeBinderCallBack {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f11233a;

            c(IBinder iBinder) {
                this.f11233a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f11233a;
            }

            @Override // com.huawei.wearengine.ota.UpgradeBinderCallBack
            public void onUpgradeStatus(Device device, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(UpgradeBinderCallBack.DESCRIPTOR);
                    c.fdZ_(obtain, device, 0);
                    obtain.writeString(str);
                    this.f11233a.transact(1, obtain, obtain2, 0);
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

    public static class c {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T fdY_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void fdZ_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
