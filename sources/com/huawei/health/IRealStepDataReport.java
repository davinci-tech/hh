package com.huawei.health;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IRealStepDataReport extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.health.IRealStepDataReport";

    void report(int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IRealStepDataReport {
        static final int TRANSACTION_report = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IRealStepDataReport.DESCRIPTOR);
        }

        public static IRealStepDataReport asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IRealStepDataReport.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRealStepDataReport)) {
                return (IRealStepDataReport) queryLocalInterface;
            }
            return new e(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IRealStepDataReport.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IRealStepDataReport.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                report(parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        /* loaded from: classes3.dex */
        static class e implements IRealStepDataReport {
            private IBinder c;

            e(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.health.IRealStepDataReport
            public void report(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRealStepDataReport.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.c.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
