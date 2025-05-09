package com.huawei.hwservicesmgr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* loaded from: classes9.dex */
public interface ISmartWatchFoundAidlCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hwservicesmgr.ISmartWatchFoundAidlCallback";

    void onResponse(List<String> list) throws RemoteException;

    public static abstract class Stub extends Binder implements ISmartWatchFoundAidlCallback {
        static final int TRANSACTION_onResponse = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ISmartWatchFoundAidlCallback.DESCRIPTOR);
        }

        public static ISmartWatchFoundAidlCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ISmartWatchFoundAidlCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISmartWatchFoundAidlCallback)) {
                return (ISmartWatchFoundAidlCallback) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ISmartWatchFoundAidlCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ISmartWatchFoundAidlCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResponse(parcel.createStringArrayList());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class a implements ISmartWatchFoundAidlCallback {
            private IBinder d;

            a(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.hwservicesmgr.ISmartWatchFoundAidlCallback
            public void onResponse(List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISmartWatchFoundAidlCallback.DESCRIPTOR);
                    obtain.writeStringList(list);
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
