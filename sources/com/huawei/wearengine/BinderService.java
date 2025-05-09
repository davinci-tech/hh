package com.huawei.wearengine;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.wearengine.ClientToken;
import com.huawei.wearengine.IdentityStoreCallback;

/* loaded from: classes7.dex */
public interface BinderService extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.BinderService";

    /* loaded from: classes9.dex */
    public static class Default implements BinderService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.wearengine.BinderService
        public void checkPermissionIdentity(String str, String str2, IdentityStoreCallback identityStoreCallback) throws RemoteException {
        }

        @Override // com.huawei.wearengine.BinderService
        public void checkPermissionIdentityProxy(int i, int i2, String str, String str2, IdentityStoreCallback identityStoreCallback) throws RemoteException {
        }

        @Override // com.huawei.wearengine.BinderService
        public int exchangeApiLevel(int i) throws RemoteException {
            return 0;
        }

        @Override // com.huawei.wearengine.BinderService
        public int exchangeApiLevelProxy(int i, int i2, int i3) throws RemoteException {
            return 0;
        }

        @Override // com.huawei.wearengine.BinderService
        public IBinder getBinder(int i) throws RemoteException {
            return null;
        }

        @Override // com.huawei.wearengine.BinderService
        public IBinder getBinderProxy(int i, int i2, int i3) throws RemoteException {
            return null;
        }

        @Override // com.huawei.wearengine.BinderService
        public void registerToken(String str, ClientToken clientToken) throws RemoteException {
        }

        @Override // com.huawei.wearengine.BinderService
        public void registerTokenProxy(int i, int i2, String str, ClientToken clientToken) throws RemoteException {
        }
    }

    void checkPermissionIdentity(String str, String str2, IdentityStoreCallback identityStoreCallback) throws RemoteException;

    void checkPermissionIdentityProxy(int i, int i2, String str, String str2, IdentityStoreCallback identityStoreCallback) throws RemoteException;

    int exchangeApiLevel(int i) throws RemoteException;

    int exchangeApiLevelProxy(int i, int i2, int i3) throws RemoteException;

    IBinder getBinder(int i) throws RemoteException;

    IBinder getBinderProxy(int i, int i2, int i3) throws RemoteException;

    void registerToken(String str, ClientToken clientToken) throws RemoteException;

    void registerTokenProxy(int i, int i2, String str, ClientToken clientToken) throws RemoteException;

    public static abstract class Stub extends Binder implements BinderService {
        static final int TRANSACTION_checkPermissionIdentity = 3;
        static final int TRANSACTION_checkPermissionIdentityProxy = 7;
        static final int TRANSACTION_exchangeApiLevel = 4;
        static final int TRANSACTION_exchangeApiLevelProxy = 8;
        static final int TRANSACTION_getBinder = 1;
        static final int TRANSACTION_getBinderProxy = 5;
        static final int TRANSACTION_registerToken = 2;
        static final int TRANSACTION_registerTokenProxy = 6;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, BinderService.DESCRIPTOR);
        }

        public static BinderService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(BinderService.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof BinderService)) {
                return (BinderService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(BinderService.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(BinderService.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    IBinder binder = getBinder(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(binder);
                    return true;
                case 2:
                    registerToken(parcel.readString(), ClientToken.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    checkPermissionIdentity(parcel.readString(), parcel.readString(), IdentityStoreCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    int exchangeApiLevel = exchangeApiLevel(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(exchangeApiLevel);
                    return true;
                case 5:
                    IBinder binderProxy = getBinderProxy(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(binderProxy);
                    return true;
                case 6:
                    registerTokenProxy(parcel.readInt(), parcel.readInt(), parcel.readString(), ClientToken.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    checkPermissionIdentityProxy(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), IdentityStoreCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    int exchangeApiLevelProxy = exchangeApiLevelProxy(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(exchangeApiLevelProxy);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class Proxy implements BinderService {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.wearengine.BinderService
            public IBinder getBinder(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(BinderService.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.BinderService
            public void registerToken(String str, ClientToken clientToken) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(BinderService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(clientToken);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.BinderService
            public void checkPermissionIdentity(String str, String str2, IdentityStoreCallback identityStoreCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(BinderService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongInterface(identityStoreCallback);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.BinderService
            public int exchangeApiLevel(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(BinderService.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.BinderService
            public IBinder getBinderProxy(int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(BinderService.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.BinderService
            public void registerTokenProxy(int i, int i2, String str, ClientToken clientToken) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(BinderService.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(clientToken);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.BinderService
            public void checkPermissionIdentityProxy(int i, int i2, String str, String str2, IdentityStoreCallback identityStoreCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(BinderService.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongInterface(identityStoreCallback);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.BinderService
            public int exchangeApiLevelProxy(int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(BinderService.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return BinderService.DESCRIPTOR;
            }
        }
    }
}
