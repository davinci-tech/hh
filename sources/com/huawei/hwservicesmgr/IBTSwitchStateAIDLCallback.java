package com.huawei.hwservicesmgr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes9.dex */
public interface IBTSwitchStateAIDLCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hwservicesmgr.IBTSwitchStateAIDLCallback";

    void onBTSwitchStateCallBack(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IBTSwitchStateAIDLCallback {
        static final int TRANSACTION_onBTSwitchStateCallBack = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IBTSwitchStateAIDLCallback.DESCRIPTOR);
        }

        public static IBTSwitchStateAIDLCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IBTSwitchStateAIDLCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IBTSwitchStateAIDLCallback)) {
                return (IBTSwitchStateAIDLCallback) queryLocalInterface;
            }
            return new d(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IBTSwitchStateAIDLCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IBTSwitchStateAIDLCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onBTSwitchStateCallBack(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class d implements IBTSwitchStateAIDLCallback {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f6390a;

            d(IBinder iBinder) {
                this.f6390a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f6390a;
            }

            @Override // com.huawei.hwservicesmgr.IBTSwitchStateAIDLCallback
            public void onBTSwitchStateCallBack(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IBTSwitchStateAIDLCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.f6390a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
