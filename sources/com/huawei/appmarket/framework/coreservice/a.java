package com.huawei.appmarket.framework.coreservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes3.dex */
public interface a extends IInterface {
    void call(Status status) throws RemoteException;

    /* renamed from: com.huawei.appmarket.framework.coreservice.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0043a extends Binder implements a {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.huawei.appmarket.framework.coreservice.IAppGalleryCallback");
                return true;
            }
            parcel.enforceInterface("com.huawei.appmarket.framework.coreservice.IAppGalleryCallback");
            call(parcel.readInt() != 0 ? Status.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }

        /* renamed from: com.huawei.appmarket.framework.coreservice.a$a$e */
        /* loaded from: classes8.dex */
        static class e implements a {
            public static a d;
            private IBinder c;

            @Override // com.huawei.appmarket.framework.coreservice.a
            public void call(Status status) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.appmarket.framework.coreservice.IAppGalleryCallback");
                    if (status != null) {
                        obtain.writeInt(1);
                        status.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.c.transact(1, obtain, obtain2, 0) || AbstractBinderC0043a.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        AbstractBinderC0043a.getDefaultImpl().call(status);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            e(IBinder iBinder) {
                this.c = iBinder;
            }
        }

        public static boolean setDefaultImpl(a aVar) {
            if (e.d != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (aVar == null) {
                return false;
            }
            e.d = aVar;
            return true;
        }

        public static a getDefaultImpl() {
            return e.d;
        }

        public static a asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.appmarket.framework.coreservice.IAppGalleryCallback");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof a)) ? new e(iBinder) : (a) queryLocalInterface;
        }

        public AbstractBinderC0043a() {
            attachInterface(this, "com.huawei.appmarket.framework.coreservice.IAppGalleryCallback");
        }
    }
}
