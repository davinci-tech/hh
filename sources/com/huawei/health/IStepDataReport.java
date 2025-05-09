package com.huawei.health;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IStepDataReport extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.health.IStepDataReport";

    void report(Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements IStepDataReport {
        static final int TRANSACTION_report = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IStepDataReport.DESCRIPTOR);
        }

        public static IStepDataReport asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IStepDataReport.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IStepDataReport)) {
                return (IStepDataReport) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IStepDataReport.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IStepDataReport.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                report((Bundle) b.AL_(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        /* loaded from: classes3.dex */
        static class a implements IStepDataReport {
            private IBinder b;

            a(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.health.IStepDataReport
            public void report(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IStepDataReport.DESCRIPTOR);
                    b.AM_(obtain, bundle, 0);
                    this.b.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class b {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T AL_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void AM_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
