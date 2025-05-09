package com.huawei.hwservicesmgr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes9.dex */
public interface IMusicChangedCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hwservicesmgr.IMusicChangedCallback";

    void onMusicChanged() throws RemoteException;

    public static abstract class Stub extends Binder implements IMusicChangedCallback {
        static final int TRANSACTION_onMusicChanged = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IMusicChangedCallback.DESCRIPTOR);
        }

        public static IMusicChangedCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IMusicChangedCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IMusicChangedCallback)) {
                return (IMusicChangedCallback) queryLocalInterface;
            }
            return new b(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IMusicChangedCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IMusicChangedCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onMusicChanged();
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class b implements IMusicChangedCallback {
            private IBinder b;

            b(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.hwservicesmgr.IMusicChangedCallback
            public void onMusicChanged() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicChangedCallback.DESCRIPTOR);
                    this.b.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
