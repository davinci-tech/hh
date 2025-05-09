package com.huawei.hwotamanager;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes5.dex */
public interface IUpgradeStatusCallBack extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hwotamanager.IUpgradeStatusCallBack";

    void onStatus(String str, String str2) throws RemoteException;

    public static abstract class Stub extends Binder implements IUpgradeStatusCallBack {
        static final int TRANSACTION_onStatus = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IUpgradeStatusCallBack.DESCRIPTOR);
        }

        public static IUpgradeStatusCallBack asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IUpgradeStatusCallBack.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IUpgradeStatusCallBack)) {
                return (IUpgradeStatusCallBack) queryLocalInterface;
            }
            return new e(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IUpgradeStatusCallBack.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IUpgradeStatusCallBack.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onStatus(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class e implements IUpgradeStatusCallBack {
            private IBinder d;

            e(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.hwotamanager.IUpgradeStatusCallBack
            public void onStatus(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IUpgradeStatusCallBack.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.d.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
