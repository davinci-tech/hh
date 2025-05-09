package com.huawei.multisimservice.model;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* loaded from: classes5.dex */
public interface IOpenMultiSimCalbcak extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.multisimservice.model.IOpenMultiSimCalbcak";

    void getDeviceEUICCInfo(EUICCDeviceInfo eUICCDeviceInfo) throws RemoteException;

    void getDeviceMultiSimInfo(MultiSimDeviceInfo multiSimDeviceInfo) throws RemoteException;

    /* loaded from: classes9.dex */
    public static abstract class Stub extends Binder implements IOpenMultiSimCalbcak {
        static final int TRANSACTION_getDeviceEUICCInfo = 2;
        static final int TRANSACTION_getDeviceMultiSimInfo = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IOpenMultiSimCalbcak.DESCRIPTOR);
        }

        public static IOpenMultiSimCalbcak asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IOpenMultiSimCalbcak.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IOpenMultiSimCalbcak)) {
                return (IOpenMultiSimCalbcak) queryLocalInterface;
            }
            return new d(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IOpenMultiSimCalbcak.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IOpenMultiSimCalbcak.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                getDeviceMultiSimInfo((MultiSimDeviceInfo) b.bZg_(parcel, MultiSimDeviceInfo.CREATOR));
                parcel2.writeNoException();
            } else if (i == 2) {
                getDeviceEUICCInfo((EUICCDeviceInfo) b.bZg_(parcel, EUICCDeviceInfo.CREATOR));
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class d implements IOpenMultiSimCalbcak {
            private IBinder c;

            d(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.multisimservice.model.IOpenMultiSimCalbcak
            public void getDeviceMultiSimInfo(MultiSimDeviceInfo multiSimDeviceInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOpenMultiSimCalbcak.DESCRIPTOR);
                    b.bZh_(obtain, multiSimDeviceInfo, 0);
                    this.c.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.multisimservice.model.IOpenMultiSimCalbcak
            public void getDeviceEUICCInfo(EUICCDeviceInfo eUICCDeviceInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOpenMultiSimCalbcak.DESCRIPTOR);
                    b.bZh_(obtain, eUICCDeviceInfo, 0);
                    this.c.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class b {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T bZg_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bZh_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
