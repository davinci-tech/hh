package com.huawei.wearengine;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.p2p.IdentityInfo;

/* loaded from: classes7.dex */
public interface HwWearEngineNativeBinder extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.HwWearEngineNativeBinder";

    /* loaded from: classes9.dex */
    public static class Default implements HwWearEngineNativeBinder {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.wearengine.HwWearEngineNativeBinder
        public boolean isP2pReceiverExist(Device device, IdentityInfo identityInfo, IdentityInfo identityInfo2) throws RemoteException {
            return false;
        }

        @Override // com.huawei.wearengine.HwWearEngineNativeBinder
        public void setBinder(String str, IBinder iBinder) throws RemoteException {
        }
    }

    boolean isP2pReceiverExist(Device device, IdentityInfo identityInfo, IdentityInfo identityInfo2) throws RemoteException;

    void setBinder(String str, IBinder iBinder) throws RemoteException;

    public static abstract class Stub extends Binder implements HwWearEngineNativeBinder {
        static final int TRANSACTION_isP2pReceiverExist = 2;
        static final int TRANSACTION_setBinder = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, HwWearEngineNativeBinder.DESCRIPTOR);
        }

        public static HwWearEngineNativeBinder asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(HwWearEngineNativeBinder.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof HwWearEngineNativeBinder)) {
                return (HwWearEngineNativeBinder) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(HwWearEngineNativeBinder.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(HwWearEngineNativeBinder.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                setBinder(parcel.readString(), parcel.readStrongBinder());
                parcel2.writeNoException();
            } else if (i == 2) {
                boolean isP2pReceiverExist = isP2pReceiverExist((Device) c.fcp_(parcel, Device.CREATOR), (IdentityInfo) c.fcp_(parcel, IdentityInfo.CREATOR), (IdentityInfo) c.fcp_(parcel, IdentityInfo.CREATOR));
                parcel2.writeNoException();
                parcel2.writeInt(isP2pReceiverExist ? 1 : 0);
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class Proxy implements HwWearEngineNativeBinder {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.wearengine.HwWearEngineNativeBinder
            public void setBinder(String str, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(HwWearEngineNativeBinder.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.HwWearEngineNativeBinder
            public boolean isP2pReceiverExist(Device device, IdentityInfo identityInfo, IdentityInfo identityInfo2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(HwWearEngineNativeBinder.DESCRIPTOR);
                    c.fcq_(obtain, device, 0);
                    c.fcq_(obtain, identityInfo, 0);
                    c.fcq_(obtain, identityInfo2, 0);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return HwWearEngineNativeBinder.DESCRIPTOR;
            }
        }
    }

    public static class c {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T fcp_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void fcq_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
