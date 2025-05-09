package com.huawei.hwsmartinteractmgr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject;

/* loaded from: classes.dex */
public interface ISmartMsgReadResultListener extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hwsmartinteractmgr.ISmartMsgReadResultListener";

    void onResult(int i, SmartMsgDbObject smartMsgDbObject) throws RemoteException;

    public static abstract class Stub extends Binder implements ISmartMsgReadResultListener {
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ISmartMsgReadResultListener.DESCRIPTOR);
        }

        public static ISmartMsgReadResultListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ISmartMsgReadResultListener.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISmartMsgReadResultListener)) {
                return (ISmartMsgReadResultListener) queryLocalInterface;
            }
            return new d(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ISmartMsgReadResultListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ISmartMsgReadResultListener.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResult(parcel.readInt(), (SmartMsgDbObject) b.bSh_(parcel, SmartMsgDbObject.CREATOR));
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        /* loaded from: classes5.dex */
        static class d implements ISmartMsgReadResultListener {
            private IBinder c;

            d(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.hwsmartinteractmgr.ISmartMsgReadResultListener
            public void onResult(int i, SmartMsgDbObject smartMsgDbObject) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISmartMsgReadResultListener.DESCRIPTOR);
                    obtain.writeInt(i);
                    b.bSi_(obtain, smartMsgDbObject, 0);
                    this.c.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    /* loaded from: classes5.dex */
    public static class b {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T bSh_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bSi_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
