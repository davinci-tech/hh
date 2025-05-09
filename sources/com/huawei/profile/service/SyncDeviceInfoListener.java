package com.huawei.profile.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

/* loaded from: classes6.dex */
public interface SyncDeviceInfoListener extends IInterface {

    /* loaded from: classes9.dex */
    public static class Default implements SyncDeviceInfoListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.profile.service.SyncDeviceInfoListener
        public void onComplete(Map map) throws RemoteException {
        }
    }

    void onComplete(Map map) throws RemoteException;

    public static abstract class Stub extends Binder implements SyncDeviceInfoListener {
        private static final String DESCRIPTOR = "com.huawei.profile.service.SyncDeviceInfoListener";
        static final int TRANSACTION_onComplete = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static SyncDeviceInfoListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof SyncDeviceInfoListener)) {
                return (SyncDeviceInfoListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onComplete(parcel.readHashMap(getClass().getClassLoader()));
            parcel2.writeNoException();
            return true;
        }

        static class Proxy implements SyncDeviceInfoListener {
            public static SyncDeviceInfoListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.profile.service.SyncDeviceInfoListener
            public void onComplete(Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeMap(map);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onComplete(map);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }
        }

        public static boolean setDefaultImpl(SyncDeviceInfoListener syncDeviceInfoListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (syncDeviceInfoListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = syncDeviceInfoListener;
            return true;
        }

        public static SyncDeviceInfoListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
