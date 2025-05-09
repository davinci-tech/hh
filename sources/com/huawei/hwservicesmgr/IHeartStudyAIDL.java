package com.huawei.hwservicesmgr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IHeartStudyAIDL extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hwservicesmgr.IHeartStudyAIDL";

    String getBtDeviceBondId(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IHeartStudyAIDL {
        static final int TRANSACTION_getBtDeviceBondId = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IHeartStudyAIDL.DESCRIPTOR);
        }

        public static IHeartStudyAIDL asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IHeartStudyAIDL.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IHeartStudyAIDL)) {
                return (IHeartStudyAIDL) queryLocalInterface;
            }
            return new d(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IHeartStudyAIDL.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IHeartStudyAIDL.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                String btDeviceBondId = getBtDeviceBondId(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(btDeviceBondId);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        /* loaded from: classes9.dex */
        static class d implements IHeartStudyAIDL {
            private IBinder b;

            d(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.hwservicesmgr.IHeartStudyAIDL
            public String getBtDeviceBondId(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHeartStudyAIDL.DESCRIPTOR);
                    obtain.writeString(str);
                    this.b.transact(1, obtain, obtain2, 0);
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
