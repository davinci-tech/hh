package com.huawei.iconnect;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes5.dex */
public interface IWearConnectService extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.iconnect.IWearConnectService";

    String getHuaweiPhoneIndex() throws RemoteException;

    public static abstract class Stub extends Binder implements IWearConnectService {
        static final int TRANSACTION_getHuaweiPhoneIndex = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IWearConnectService.DESCRIPTOR);
        }

        public static IWearConnectService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IWearConnectService.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IWearConnectService)) {
                return (IWearConnectService) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IWearConnectService.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IWearConnectService.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                String huaweiPhoneIndex = getHuaweiPhoneIndex();
                parcel2.writeNoException();
                parcel2.writeString(huaweiPhoneIndex);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class a implements IWearConnectService {
            private IBinder d;

            a(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.iconnect.IWearConnectService
            public String getHuaweiPhoneIndex() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearConnectService.DESCRIPTOR);
                    this.d.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
