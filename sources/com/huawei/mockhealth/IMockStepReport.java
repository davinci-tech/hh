package com.huawei.mockhealth;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IMockStepReport extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.mockhealth.IMockStepReport";

    void onExtendStepChanged(Bundle bundle) throws RemoteException;

    void onStandStepChanged(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IMockStepReport {
        static final int TRANSACTION_onExtendStepChanged = 2;
        static final int TRANSACTION_onStandStepChanged = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IMockStepReport.DESCRIPTOR);
        }

        public static IMockStepReport asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IMockStepReport.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IMockStepReport)) {
                return (IMockStepReport) queryLocalInterface;
            }
            return new c(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IMockStepReport.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IMockStepReport.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onStandStepChanged(parcel.readInt());
                parcel2.writeNoException();
            } else if (i == 2) {
                onExtendStepChanged((Bundle) a.bXw_(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        /* loaded from: classes5.dex */
        static class c implements IMockStepReport {
            private IBinder e;

            c(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.mockhealth.IMockStepReport
            public void onStandStepChanged(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMockStepReport.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.e.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.mockhealth.IMockStepReport
            public void onExtendStepChanged(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMockStepReport.DESCRIPTOR);
                    a.bXx_(obtain, bundle, 0);
                    this.e.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    /* loaded from: classes5.dex */
    public static class a {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T bXw_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bXx_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
