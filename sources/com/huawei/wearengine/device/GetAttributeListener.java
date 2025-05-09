package com.huawei.wearengine.device;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes7.dex */
public interface GetAttributeListener extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.device.GetAttributeListener";

    /* loaded from: classes9.dex */
    public static class Default implements GetAttributeListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.wearengine.device.GetAttributeListener
        public void onGetString(String str) throws RemoteException {
        }
    }

    void onGetString(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements GetAttributeListener {
        static final int TRANSACTION_onGetString = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, GetAttributeListener.DESCRIPTOR);
        }

        public static GetAttributeListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(GetAttributeListener.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof GetAttributeListener)) {
                return (GetAttributeListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(GetAttributeListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(GetAttributeListener.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onGetString(parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class Proxy implements GetAttributeListener {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.wearengine.device.GetAttributeListener
            public void onGetString(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(GetAttributeListener.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return GetAttributeListener.DESCRIPTOR;
            }
        }
    }
}
