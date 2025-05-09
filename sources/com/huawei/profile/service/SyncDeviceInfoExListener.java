package com.huawei.profile.service;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

/* loaded from: classes6.dex */
public interface SyncDeviceInfoExListener extends IInterface {

    /* loaded from: classes9.dex */
    public static class Default implements SyncDeviceInfoExListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.profile.service.SyncDeviceInfoExListener
        public void onComplete(Map map, Bundle bundle) throws RemoteException {
        }
    }

    void onComplete(Map map, Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements SyncDeviceInfoExListener {
        private static final String DESCRIPTOR = "com.huawei.profile.service.SyncDeviceInfoExListener";
        static final int TRANSACTION_onComplete = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static SyncDeviceInfoExListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof SyncDeviceInfoExListener)) {
                return (SyncDeviceInfoExListener) queryLocalInterface;
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
            onComplete(parcel.readHashMap(getClass().getClassLoader()), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }

        static class Proxy implements SyncDeviceInfoExListener {
            public static SyncDeviceInfoExListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.profile.service.SyncDeviceInfoExListener
            public void onComplete(Map map, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeMap(map);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onComplete(map, bundle);
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

        public static boolean setDefaultImpl(SyncDeviceInfoExListener syncDeviceInfoExListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (syncDeviceInfoExListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = syncDeviceInfoExListener;
            return true;
        }

        public static SyncDeviceInfoExListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
