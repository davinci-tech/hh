package com.huawei.wearengine;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.wearengine.device.Device;
import java.util.List;

/* loaded from: classes7.dex */
public interface DeviceManager extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.DeviceManager";

    List<Device> getAllBondedDevices() throws RemoteException;

    List<Device> getBondedDeviceEx() throws RemoteException;

    List<Device> getBondedDevices() throws RemoteException;

    List<Device> getCommonDevice() throws RemoteException;

    String getDeviceSn(Device device) throws RemoteException;

    String getHiLinkDeviceId(Device device) throws RemoteException;

    boolean hasAvailableDevices() throws RemoteException;

    int queryDeviceCapability(Device device, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements DeviceManager {
        static final int TRANSACTION_getAllBondedDevices = 5;
        static final int TRANSACTION_getBondedDeviceEx = 4;
        static final int TRANSACTION_getBondedDevices = 1;
        static final int TRANSACTION_getCommonDevice = 6;
        static final int TRANSACTION_getDeviceSn = 8;
        static final int TRANSACTION_getHiLinkDeviceId = 3;
        static final int TRANSACTION_hasAvailableDevices = 2;
        static final int TRANSACTION_queryDeviceCapability = 7;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DeviceManager.DESCRIPTOR);
        }

        public static DeviceManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DeviceManager.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof DeviceManager)) {
                return (DeviceManager) queryLocalInterface;
            }
            return new b(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(DeviceManager.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(DeviceManager.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    List<Device> bondedDevices = getBondedDevices();
                    parcel2.writeNoException();
                    c.fcf_(parcel2, bondedDevices, 1);
                    return true;
                case 2:
                    boolean hasAvailableDevices = hasAvailableDevices();
                    parcel2.writeNoException();
                    parcel2.writeInt(hasAvailableDevices ? 1 : 0);
                    return true;
                case 3:
                    String hiLinkDeviceId = getHiLinkDeviceId((Device) c.fce_(parcel, Device.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeString(hiLinkDeviceId);
                    return true;
                case 4:
                    List<Device> bondedDeviceEx = getBondedDeviceEx();
                    parcel2.writeNoException();
                    c.fcf_(parcel2, bondedDeviceEx, 1);
                    return true;
                case 5:
                    List<Device> allBondedDevices = getAllBondedDevices();
                    parcel2.writeNoException();
                    c.fcf_(parcel2, allBondedDevices, 1);
                    return true;
                case 6:
                    List<Device> commonDevice = getCommonDevice();
                    parcel2.writeNoException();
                    c.fcf_(parcel2, commonDevice, 1);
                    return true;
                case 7:
                    int queryDeviceCapability = queryDeviceCapability((Device) c.fce_(parcel, Device.CREATOR), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(queryDeviceCapability);
                    return true;
                case 8:
                    String deviceSn = getDeviceSn((Device) c.fce_(parcel, Device.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeString(deviceSn);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class b implements DeviceManager {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f11219a;

            b(IBinder iBinder) {
                this.f11219a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f11219a;
            }

            @Override // com.huawei.wearengine.DeviceManager
            public List<Device> getBondedDevices() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DeviceManager.DESCRIPTOR);
                    this.f11219a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(Device.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.DeviceManager
            public boolean hasAvailableDevices() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DeviceManager.DESCRIPTOR);
                    this.f11219a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.DeviceManager
            public String getHiLinkDeviceId(Device device) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DeviceManager.DESCRIPTOR);
                    c.fcg_(obtain, device, 0);
                    this.f11219a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.DeviceManager
            public List<Device> getBondedDeviceEx() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DeviceManager.DESCRIPTOR);
                    this.f11219a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(Device.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.DeviceManager
            public List<Device> getAllBondedDevices() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DeviceManager.DESCRIPTOR);
                    this.f11219a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(Device.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.DeviceManager
            public List<Device> getCommonDevice() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DeviceManager.DESCRIPTOR);
                    this.f11219a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(Device.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.DeviceManager
            public int queryDeviceCapability(Device device, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DeviceManager.DESCRIPTOR);
                    c.fcg_(obtain, device, 0);
                    obtain.writeInt(i);
                    this.f11219a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.DeviceManager
            public String getDeviceSn(Device device) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DeviceManager.DESCRIPTOR);
                    c.fcg_(obtain, device, 0);
                    this.f11219a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class c {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T fce_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void fcg_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void fcf_(Parcel parcel, List<T> list, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                fcg_(parcel, list.get(i2), i);
            }
        }
    }
}
