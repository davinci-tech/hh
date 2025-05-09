package com.huawei.multisimservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.multisimservice.model.IOpenMultiSimCalbcak;

/* loaded from: classes9.dex */
public interface IOpenMultiSim extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.multisimservice.IOpenMultiSim";

    void downloadESimProfile(String str) throws RemoteException;

    void downloadEsimProfile(String str, long j, String str2) throws RemoteException;

    void getAttachedDeviceMultiSimInfo() throws RemoteException;

    void registerCallback(IOpenMultiSimCalbcak iOpenMultiSimCalbcak) throws RemoteException;

    void unRegisterCallback(IOpenMultiSimCalbcak iOpenMultiSimCalbcak) throws RemoteException;

    public static abstract class Stub extends Binder implements IOpenMultiSim {
        static final int TRANSACTION_downloadESimProfile = 5;
        static final int TRANSACTION_downloadEsimProfile = 4;
        static final int TRANSACTION_getAttachedDeviceMultiSimInfo = 3;
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_unRegisterCallback = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IOpenMultiSim.DESCRIPTOR);
        }

        public static IOpenMultiSim asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IOpenMultiSim.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IOpenMultiSim)) {
                return (IOpenMultiSim) queryLocalInterface;
            }
            return new c(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IOpenMultiSim.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IOpenMultiSim.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                registerCallback(IOpenMultiSimCalbcak.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
            } else if (i == 2) {
                unRegisterCallback(IOpenMultiSimCalbcak.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
            } else if (i == 3) {
                getAttachedDeviceMultiSimInfo();
                parcel2.writeNoException();
            } else if (i == 4) {
                downloadEsimProfile(parcel.readString(), parcel.readLong(), parcel.readString());
                parcel2.writeNoException();
            } else if (i == 5) {
                downloadESimProfile(parcel.readString());
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class c implements IOpenMultiSim {
            private IBinder d;

            c(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.multisimservice.IOpenMultiSim
            public void registerCallback(IOpenMultiSimCalbcak iOpenMultiSimCalbcak) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOpenMultiSim.DESCRIPTOR);
                    obtain.writeStrongInterface(iOpenMultiSimCalbcak);
                    this.d.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.multisimservice.IOpenMultiSim
            public void unRegisterCallback(IOpenMultiSimCalbcak iOpenMultiSimCalbcak) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOpenMultiSim.DESCRIPTOR);
                    obtain.writeStrongInterface(iOpenMultiSimCalbcak);
                    this.d.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.multisimservice.IOpenMultiSim
            public void getAttachedDeviceMultiSimInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOpenMultiSim.DESCRIPTOR);
                    this.d.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.multisimservice.IOpenMultiSim
            public void downloadEsimProfile(String str, long j, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOpenMultiSim.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    obtain.writeString(str2);
                    this.d.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.multisimservice.IOpenMultiSim
            public void downloadESimProfile(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOpenMultiSim.DESCRIPTOR);
                    obtain.writeString(str);
                    this.d.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
