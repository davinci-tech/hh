package com.google.android.clockwork.companion.partnerapi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface DeviceRemovalCallback extends IInterface {
    public static final String DESCRIPTOR = "com.google.android.clockwork.companion.partnerapi.DeviceRemovalCallback";

    void onDeviceRemovalFailed(String str, int i) throws RemoteException;

    void onDeviceRemovalSucceeded(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements DeviceRemovalCallback {
        static final int TRANSACTION_onDeviceRemovalFailed = 2;
        static final int TRANSACTION_onDeviceRemovalSucceeded = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DeviceRemovalCallback.DESCRIPTOR);
        }

        public static DeviceRemovalCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DeviceRemovalCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof DeviceRemovalCallback)) {
                return (DeviceRemovalCallback) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(DeviceRemovalCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(DeviceRemovalCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onDeviceRemovalSucceeded(parcel.readString());
            } else if (i == 2) {
                onDeviceRemovalFailed(parcel.readString(), parcel.readInt());
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class a implements DeviceRemovalCallback {
            private IBinder e;

            a(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.google.android.clockwork.companion.partnerapi.DeviceRemovalCallback
            public void onDeviceRemovalSucceeded(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DeviceRemovalCallback.DESCRIPTOR);
                    obtain.writeString(str);
                    this.e.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.google.android.clockwork.companion.partnerapi.DeviceRemovalCallback
            public void onDeviceRemovalFailed(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DeviceRemovalCallback.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.e.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
