package com.huawei.controlcenter.featureability.sdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.controlcenter.featureability.sdk.IAuthCallback;
import com.huawei.controlcenter.featureability.sdk.IConnectCallback;
import com.huawei.controlcenter.featureability.sdk.model.ExtraParams;

/* loaded from: classes3.dex */
public interface IRemoteRegisterService extends IInterface {
    boolean authReq(String str, IAuthCallback iAuthCallback) throws RemoteException;

    int register(String str, IBinder iBinder, ExtraParams extraParams, IConnectCallback iConnectCallback) throws RemoteException;

    boolean requestBeckonCallback(String str) throws RemoteException;

    boolean showDeviceList(int i, ExtraParams extraParams) throws RemoteException;

    boolean unRequestBeckonCallback(String str) throws RemoteException;

    boolean unregister(int i) throws RemoteException;

    boolean updateConnectStatus(int i, String str, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IRemoteRegisterService {
        private static final String DESCRIPTOR = "com.huawei.controlcenter.featureability.sdk.IRemoteRegisterService";
        static final int TRANSACTION_authReq = 5;
        static final int TRANSACTION_register = 1;
        static final int TRANSACTION_requestBeckonCallback = 6;
        static final int TRANSACTION_showDeviceList = 4;
        static final int TRANSACTION_unRequestBeckonCallback = 7;
        static final int TRANSACTION_unregister = 2;
        static final int TRANSACTION_updateConnectStatus = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRemoteRegisterService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRemoteRegisterService)) {
                return (IRemoteRegisterService) queryLocalInterface;
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
                    int register = register(parcel.readString(), parcel.readStrongBinder(), parcel.readInt() != 0 ? ExtraParams.CREATOR.createFromParcel(parcel) : null, IConnectCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(register);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean unregister = unregister(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(unregister ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean updateConnectStatus = updateConnectStatus(parcel.readInt(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(updateConnectStatus ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean showDeviceList = showDeviceList(parcel.readInt(), parcel.readInt() != 0 ? ExtraParams.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(showDeviceList ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean authReq = authReq(parcel.readString(), IAuthCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(authReq ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean requestBeckonCallback = requestBeckonCallback(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(requestBeckonCallback ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean unRequestBeckonCallback = unRequestBeckonCallback(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(unRequestBeckonCallback ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class b implements IRemoteRegisterService {
            public static IRemoteRegisterService d;
            private IBinder b;

            b(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.controlcenter.featureability.sdk.IRemoteRegisterService
            public int register(String str, IBinder iBinder, ExtraParams extraParams, IConnectCallback iConnectCallback) throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iBinder);
                    if (extraParams != null) {
                        obtain.writeInt(1);
                        extraParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iConnectCallback != null ? iConnectCallback.asBinder() : null);
                    if (!this.b.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().register(str, iBinder, extraParams, iConnectCallback);
                    } else {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.controlcenter.featureability.sdk.IRemoteRegisterService
            public boolean unregister(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.b.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregister(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.controlcenter.featureability.sdk.IRemoteRegisterService
            public boolean updateConnectStatus(int i, String str, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    if (!this.b.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateConnectStatus(i, str, i2);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.controlcenter.featureability.sdk.IRemoteRegisterService
            public boolean showDeviceList(int i, ExtraParams extraParams) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (extraParams != null) {
                        obtain.writeInt(1);
                        extraParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.b.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().showDeviceList(i, extraParams);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.controlcenter.featureability.sdk.IRemoteRegisterService
            public boolean authReq(String str, IAuthCallback iAuthCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iAuthCallback != null ? iAuthCallback.asBinder() : null);
                    if (!this.b.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().authReq(str, iAuthCallback);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.controlcenter.featureability.sdk.IRemoteRegisterService
            public boolean requestBeckonCallback(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.b.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestBeckonCallback(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.controlcenter.featureability.sdk.IRemoteRegisterService
            public boolean unRequestBeckonCallback(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.b.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unRequestBeckonCallback(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRemoteRegisterService iRemoteRegisterService) {
            if (b.d != null || iRemoteRegisterService == null) {
                return false;
            }
            b.d = iRemoteRegisterService;
            return true;
        }

        public static IRemoteRegisterService getDefaultImpl() {
            return b.d;
        }
    }
}
