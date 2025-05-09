package com.huawei.wearengine.auth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* loaded from: classes9.dex */
public interface AuthListenerManager extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.auth.AuthListenerManager";

    void authListenerOnCancel(String str) throws RemoteException;

    void authListenerOnOk(String str, Permission[] permissionArr) throws RemoteException;

    List<String> getAllPackageName() throws RemoteException;

    public static abstract class Stub extends Binder implements AuthListenerManager {
        static final int TRANSACTION_authListenerOnCancel = 2;
        static final int TRANSACTION_authListenerOnOk = 1;
        static final int TRANSACTION_getAllPackageName = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, AuthListenerManager.DESCRIPTOR);
        }

        public static AuthListenerManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(AuthListenerManager.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof AuthListenerManager)) {
                return (AuthListenerManager) queryLocalInterface;
            }
            return new b(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(AuthListenerManager.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(AuthListenerManager.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                authListenerOnOk(parcel.readString(), (Permission[]) parcel.createTypedArray(Permission.CREATOR));
                parcel2.writeNoException();
            } else if (i == 2) {
                authListenerOnCancel(parcel.readString());
                parcel2.writeNoException();
            } else if (i == 3) {
                List<String> allPackageName = getAllPackageName();
                parcel2.writeNoException();
                parcel2.writeStringList(allPackageName);
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class b implements AuthListenerManager {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f11226a;

            b(IBinder iBinder) {
                this.f11226a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f11226a;
            }

            @Override // com.huawei.wearengine.auth.AuthListenerManager
            public void authListenerOnOk(String str, Permission[] permissionArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AuthListenerManager.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeTypedArray(permissionArr, 0);
                    this.f11226a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.auth.AuthListenerManager
            public void authListenerOnCancel(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AuthListenerManager.DESCRIPTOR);
                    obtain.writeString(str);
                    this.f11226a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.auth.AuthListenerManager
            public List<String> getAllPackageName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AuthListenerManager.DESCRIPTOR);
                    this.f11226a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
