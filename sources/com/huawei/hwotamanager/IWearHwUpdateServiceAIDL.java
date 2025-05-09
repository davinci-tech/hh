package com.huawei.hwotamanager;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.hwotamanager.ICheckCallback;
import com.huawei.hwotamanager.IDeviceListCallback;
import com.huawei.hwotamanager.IUpgradeCallBack;
import com.huawei.hwotamanager.IUpgradeStatusCallBack;

/* loaded from: classes5.dex */
public interface IWearHwUpdateServiceAIDL extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hwotamanager.IWearHwUpdateServiceAIDL";

    void doUpgrade(String str, String str2, IUpgradeCallBack iUpgradeCallBack) throws RemoteException;

    void getConnectedDevices(IDeviceListCallback iDeviceListCallback) throws RemoteException;

    void getDeviceNewVersion(String str, ICheckCallback iCheckCallback) throws RemoteException;

    void getUpgradeStatus(String str, IUpgradeStatusCallBack iUpgradeStatusCallBack) throws RemoteException;

    void registerUpgradeListener(String str, IUpgradeStatusCallBack iUpgradeStatusCallBack) throws RemoteException;

    void unRegisterUpgradeListener(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IWearHwUpdateServiceAIDL {
        static final int TRANSACTION_doUpgrade = 3;
        static final int TRANSACTION_getConnectedDevices = 1;
        static final int TRANSACTION_getDeviceNewVersion = 2;
        static final int TRANSACTION_getUpgradeStatus = 4;
        static final int TRANSACTION_registerUpgradeListener = 5;
        static final int TRANSACTION_unRegisterUpgradeListener = 6;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IWearHwUpdateServiceAIDL.DESCRIPTOR);
        }

        public static IWearHwUpdateServiceAIDL asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IWearHwUpdateServiceAIDL.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IWearHwUpdateServiceAIDL)) {
                return (IWearHwUpdateServiceAIDL) queryLocalInterface;
            }
            return new d(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IWearHwUpdateServiceAIDL.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IWearHwUpdateServiceAIDL.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    getConnectedDevices(IDeviceListCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    getDeviceNewVersion(parcel.readString(), ICheckCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    doUpgrade(parcel.readString(), parcel.readString(), IUpgradeCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    getUpgradeStatus(parcel.readString(), IUpgradeStatusCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    registerUpgradeListener(parcel.readString(), IUpgradeStatusCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    unRegisterUpgradeListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* loaded from: classes9.dex */
        static class d implements IWearHwUpdateServiceAIDL {
            private IBinder c;

            d(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.hwotamanager.IWearHwUpdateServiceAIDL
            public void getConnectedDevices(IDeviceListCallback iDeviceListCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearHwUpdateServiceAIDL.DESCRIPTOR);
                    obtain.writeStrongInterface(iDeviceListCallback);
                    this.c.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwotamanager.IWearHwUpdateServiceAIDL
            public void getDeviceNewVersion(String str, ICheckCallback iCheckCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearHwUpdateServiceAIDL.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iCheckCallback);
                    this.c.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwotamanager.IWearHwUpdateServiceAIDL
            public void doUpgrade(String str, String str2, IUpgradeCallBack iUpgradeCallBack) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearHwUpdateServiceAIDL.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongInterface(iUpgradeCallBack);
                    this.c.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwotamanager.IWearHwUpdateServiceAIDL
            public void getUpgradeStatus(String str, IUpgradeStatusCallBack iUpgradeStatusCallBack) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearHwUpdateServiceAIDL.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iUpgradeStatusCallBack);
                    this.c.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwotamanager.IWearHwUpdateServiceAIDL
            public void registerUpgradeListener(String str, IUpgradeStatusCallBack iUpgradeStatusCallBack) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearHwUpdateServiceAIDL.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iUpgradeStatusCallBack);
                    this.c.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwotamanager.IWearHwUpdateServiceAIDL
            public void unRegisterUpgradeListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearHwUpdateServiceAIDL.DESCRIPTOR);
                    obtain.writeString(str);
                    this.c.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
