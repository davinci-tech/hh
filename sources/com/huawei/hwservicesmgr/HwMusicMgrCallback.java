package com.huawei.hwservicesmgr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.hwservicesmgr.IBaseCallback;

/* loaded from: classes5.dex */
public interface HwMusicMgrCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hwservicesmgr.HwMusicMgrCallback";

    void executeCommand(String str, IBaseCallback iBaseCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements HwMusicMgrCallback {
        static final int TRANSACTION_executeCommand = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, HwMusicMgrCallback.DESCRIPTOR);
        }

        public static HwMusicMgrCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(HwMusicMgrCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof HwMusicMgrCallback)) {
                return (HwMusicMgrCallback) queryLocalInterface;
            }
            return new d(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(HwMusicMgrCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(HwMusicMgrCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                executeCommand(parcel.readString(), IBaseCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class d implements HwMusicMgrCallback {
            private IBinder c;

            d(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.hwservicesmgr.HwMusicMgrCallback
            public void executeCommand(String str, IBaseCallback iBaseCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(HwMusicMgrCallback.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iBaseCallback);
                    this.c.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
