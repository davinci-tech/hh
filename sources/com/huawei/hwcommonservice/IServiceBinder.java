package com.huawei.hwcommonservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IServiceBinder extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hwcommonservice.IServiceBinder";

    /* loaded from: classes9.dex */
    public static class Default implements IServiceBinder {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hwcommonservice.IServiceBinder
        public IBinder getServiceBinder(String str, int i) throws RemoteException {
            return null;
        }
    }

    IBinder getServiceBinder(String str, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IServiceBinder {
        static final int TRANSACTION_getServiceBinder = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IServiceBinder.DESCRIPTOR);
        }

        public static IServiceBinder asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IServiceBinder.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IServiceBinder)) {
                return (IServiceBinder) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IServiceBinder.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IServiceBinder.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                IBinder serviceBinder = getServiceBinder(parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeStrongBinder(serviceBinder);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        /* loaded from: classes5.dex */
        static class Proxy implements IServiceBinder {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.hwcommonservice.IServiceBinder
            public IBinder getServiceBinder(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IServiceBinder.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return IServiceBinder.DESCRIPTOR;
            }
        }
    }
}
