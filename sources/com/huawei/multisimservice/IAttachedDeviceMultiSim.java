package com.huawei.multisimservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.multisimservice.model.IAttachedDeviceMultiSimCallback;
import com.huawei.multisimservice.model.SimInfo;
import java.util.List;

/* loaded from: classes5.dex */
public interface IAttachedDeviceMultiSim extends IInterface {
    void deleteESimProfile(List<SimInfo> list) throws RemoteException;

    void downloadESimProfile(String str) throws RemoteException;

    void getAttachedDeviceMultiSimInfo() throws RemoteException;

    void multiSimStatusNotify(int i, String str) throws RemoteException;

    void registerCallback(IAttachedDeviceMultiSimCallback iAttachedDeviceMultiSimCallback) throws RemoteException;

    void unRegisterCallback(IAttachedDeviceMultiSimCallback iAttachedDeviceMultiSimCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IAttachedDeviceMultiSim {
        private static final String DESCRIPTOR = "com.huawei.multisimservice.IAttachedDeviceMultiSim";
        static final int TRANSACTION_deleteESimProfile = 6;
        static final int TRANSACTION_downloadESimProfile = 4;
        static final int TRANSACTION_getAttachedDeviceMultiSimInfo = 3;
        static final int TRANSACTION_multiSimStatusNotify = 5;
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_unRegisterCallback = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAttachedDeviceMultiSim asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAttachedDeviceMultiSim)) {
                return (IAttachedDeviceMultiSim) queryLocalInterface;
            }
            return new b(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerCallback(IAttachedDeviceMultiSimCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unRegisterCallback(IAttachedDeviceMultiSimCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    getAttachedDeviceMultiSimInfo();
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    downloadESimProfile(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    multiSimStatusNotify(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteESimProfile(parcel.createTypedArrayList(SimInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class b implements IAttachedDeviceMultiSim {
            public static IAttachedDeviceMultiSim e;
            private IBinder d;

            b(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.multisimservice.IAttachedDeviceMultiSim
            public void registerCallback(IAttachedDeviceMultiSimCallback iAttachedDeviceMultiSimCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAttachedDeviceMultiSimCallback != null ? iAttachedDeviceMultiSimCallback.asBinder() : null);
                    if (!this.d.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerCallback(iAttachedDeviceMultiSimCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.multisimservice.IAttachedDeviceMultiSim
            public void unRegisterCallback(IAttachedDeviceMultiSimCallback iAttachedDeviceMultiSimCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAttachedDeviceMultiSimCallback != null ? iAttachedDeviceMultiSimCallback.asBinder() : null);
                    if (!this.d.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unRegisterCallback(iAttachedDeviceMultiSimCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.multisimservice.IAttachedDeviceMultiSim
            public void getAttachedDeviceMultiSimInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.d.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getAttachedDeviceMultiSimInfo();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.multisimservice.IAttachedDeviceMultiSim
            public void downloadESimProfile(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.d.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().downloadESimProfile(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.multisimservice.IAttachedDeviceMultiSim
            public void multiSimStatusNotify(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (!this.d.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().multiSimStatusNotify(i, str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.multisimservice.IAttachedDeviceMultiSim
            public void deleteESimProfile(List<SimInfo> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    if (!this.d.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteESimProfile(list);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAttachedDeviceMultiSim iAttachedDeviceMultiSim) {
            if (b.e != null || iAttachedDeviceMultiSim == null) {
                return false;
            }
            b.e = iAttachedDeviceMultiSim;
            return true;
        }

        public static IAttachedDeviceMultiSim getDefaultImpl() {
            return b.e;
        }
    }
}
