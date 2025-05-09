package com.huawei.wearengine;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.wearengine.auth.AuthListener;
import com.huawei.wearengine.auth.Permission;

/* loaded from: classes9.dex */
public interface AuthManager extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.AuthManager";

    boolean checkPermission(Permission permission) throws RemoteException;

    boolean[] checkPermissions(Permission[] permissionArr) throws RemoteException;

    String preStartAuth(AuthListener authListener) throws RemoteException;

    int requestPermission(AuthListener authListener, Permission[] permissionArr) throws RemoteException;

    public static abstract class Stub extends Binder implements AuthManager {
        static final int TRANSACTION_checkPermission = 2;
        static final int TRANSACTION_checkPermissions = 3;
        static final int TRANSACTION_preStartAuth = 4;
        static final int TRANSACTION_requestPermission = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, AuthManager.DESCRIPTOR);
        }

        public static AuthManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(AuthManager.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof AuthManager)) {
                return (AuthManager) queryLocalInterface;
            }
            return new c(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(AuthManager.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(AuthManager.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                int requestPermission = requestPermission(AuthListener.Stub.asInterface(parcel.readStrongBinder()), (Permission[]) parcel.createTypedArray(Permission.CREATOR));
                parcel2.writeNoException();
                parcel2.writeInt(requestPermission);
            } else if (i == 2) {
                boolean checkPermission = checkPermission((Permission) c.fbY_(parcel, Permission.CREATOR));
                parcel2.writeNoException();
                parcel2.writeInt(checkPermission ? 1 : 0);
            } else if (i == 3) {
                boolean[] checkPermissions = checkPermissions((Permission[]) parcel.createTypedArray(Permission.CREATOR));
                parcel2.writeNoException();
                parcel2.writeBooleanArray(checkPermissions);
            } else if (i == 4) {
                String preStartAuth = preStartAuth(AuthListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeString(preStartAuth);
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class c implements AuthManager {
            private IBinder b;

            c(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.wearengine.AuthManager
            public int requestPermission(AuthListener authListener, Permission[] permissionArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AuthManager.DESCRIPTOR);
                    obtain.writeStrongInterface(authListener);
                    obtain.writeTypedArray(permissionArr, 0);
                    this.b.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.AuthManager
            public boolean checkPermission(Permission permission) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AuthManager.DESCRIPTOR);
                    c.fbZ_(obtain, permission, 0);
                    this.b.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.AuthManager
            public boolean[] checkPermissions(Permission[] permissionArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AuthManager.DESCRIPTOR);
                    obtain.writeTypedArray(permissionArr, 0);
                    this.b.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createBooleanArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.AuthManager
            public String preStartAuth(AuthListener authListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AuthManager.DESCRIPTOR);
                    obtain.writeStrongInterface(authListener);
                    this.b.transact(4, obtain, obtain2, 0);
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
        public static <T> T fbY_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void fbZ_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
