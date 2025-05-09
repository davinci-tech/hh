package com.huawei.hwotamanager;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes5.dex */
public interface IUpgradeCallBack extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hwotamanager.IUpgradeCallBack";

    void onUpgradeStatus(String str, String str2) throws RemoteException;

    public static abstract class Stub extends Binder implements IUpgradeCallBack {
        static final int TRANSACTION_onUpgradeStatus = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IUpgradeCallBack.DESCRIPTOR);
        }

        public static IUpgradeCallBack asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IUpgradeCallBack.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IUpgradeCallBack)) {
                return (IUpgradeCallBack) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IUpgradeCallBack.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IUpgradeCallBack.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onUpgradeStatus(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class a implements IUpgradeCallBack {
            private IBinder d;

            a(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.hwotamanager.IUpgradeCallBack
            public void onUpgradeStatus(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IUpgradeCallBack.DESCRIPTOR);
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
