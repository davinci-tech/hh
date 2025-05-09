package com.hihonor.health.fw;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IReportCallback extends IInterface {
    public static final String DESCRIPTOR = "com.hihonor.health.fw.IReportCallback";

    void onResult(Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements IReportCallback {
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IReportCallback.DESCRIPTOR);
        }

        public static IReportCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IReportCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IReportCallback)) {
                return (IReportCallback) queryLocalInterface;
            }
            return new d(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IReportCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IReportCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResult((Bundle) e.cZ_(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        /* loaded from: classes2.dex */
        static class d implements IReportCallback {
            private IBinder c;

            d(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.hihonor.health.fw.IReportCallback
            public void onResult(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IReportCallback.DESCRIPTOR);
                    e.da_(obtain, bundle, 0);
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
        public static <T> T cZ_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void da_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
