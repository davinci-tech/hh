package com.huawei.unitedevice.callback;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.unitedevice.hwcommonfilemgr.entity.CommonFileInfoParcel;

/* loaded from: classes9.dex */
public interface IBaseFileResponseCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.unitedevice.callback.IBaseFileResponseCallback";

    void onResponse(int i, CommonFileInfoParcel commonFileInfoParcel) throws RemoteException;

    public static abstract class Stub extends Binder implements IBaseFileResponseCallback {
        static final int TRANSACTION_onResponse = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IBaseFileResponseCallback.DESCRIPTOR);
        }

        public static IBaseFileResponseCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IBaseFileResponseCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IBaseFileResponseCallback)) {
                return (IBaseFileResponseCallback) queryLocalInterface;
            }
            return new e(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IBaseFileResponseCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IBaseFileResponseCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResponse(parcel.readInt(), (CommonFileInfoParcel) e.ejA_(parcel, CommonFileInfoParcel.CREATOR));
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class e implements IBaseFileResponseCallback {
            private IBinder c;

            e(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.unitedevice.callback.IBaseFileResponseCallback
            public void onResponse(int i, CommonFileInfoParcel commonFileInfoParcel) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IBaseFileResponseCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    e.ejB_(obtain, commonFileInfoParcel, 0);
                    this.c.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class e {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T ejA_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void ejB_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
