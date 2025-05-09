package com.huawei.hwservicesmgr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import java.util.List;

/* loaded from: classes9.dex */
public interface IPhoneServiceAIDL extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hwservicesmgr.IPhoneServiceAIDL";

    DeviceCapability getDeviceCapability() throws RemoteException;

    List<DeviceInfo> getUsedDeviceList() throws RemoteException;

    void registerBinder(IBinder iBinder, String str, String str2) throws RemoteException;

    public static abstract class Stub extends Binder implements IPhoneServiceAIDL {
        static final int TRANSACTION_getDeviceCapability = 3;
        static final int TRANSACTION_getUsedDeviceList = 2;
        static final int TRANSACTION_registerBinder = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IPhoneServiceAIDL.DESCRIPTOR);
        }

        public static IPhoneServiceAIDL asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IPhoneServiceAIDL.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IPhoneServiceAIDL)) {
                return (IPhoneServiceAIDL) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IPhoneServiceAIDL.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IPhoneServiceAIDL.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                registerBinder(parcel.readStrongBinder(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
            } else if (i == 2) {
                List<DeviceInfo> usedDeviceList = getUsedDeviceList();
                parcel2.writeNoException();
                c.bRO_(parcel2, usedDeviceList, 1);
            } else if (i == 3) {
                DeviceCapability deviceCapability = getDeviceCapability();
                parcel2.writeNoException();
                c.bRP_(parcel2, deviceCapability, 1);
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class a implements IPhoneServiceAIDL {
            private IBinder c;

            a(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.hwservicesmgr.IPhoneServiceAIDL
            public void registerBinder(IBinder iBinder, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IPhoneServiceAIDL.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.c.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IPhoneServiceAIDL
            public List<DeviceInfo> getUsedDeviceList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IPhoneServiceAIDL.DESCRIPTOR);
                    this.c.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(DeviceInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IPhoneServiceAIDL
            public DeviceCapability getDeviceCapability() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IPhoneServiceAIDL.DESCRIPTOR);
                    this.c.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return (DeviceCapability) c.bRN_(obtain2, DeviceCapability.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class c {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T bRN_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bRP_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bRO_(Parcel parcel, List<T> list, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                bRP_(parcel, list.get(i2), i);
            }
        }
    }
}
