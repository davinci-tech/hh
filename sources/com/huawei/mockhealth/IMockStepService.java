package com.huawei.mockhealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.mockhealth.IMockStepReport;

/* loaded from: classes.dex */
public interface IMockStepService extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.mockhealth.IMockStepService";

    int getCapacity() throws RemoteException;

    void registerMockStepReport(IMockStepReport iMockStepReport) throws RemoteException;

    void unregisterMockStepReport(IMockStepReport iMockStepReport) throws RemoteException;

    public static abstract class Stub extends Binder implements IMockStepService {
        static final int TRANSACTION_getCapacity = 3;
        static final int TRANSACTION_registerMockStepReport = 1;
        static final int TRANSACTION_unregisterMockStepReport = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IMockStepService.DESCRIPTOR);
        }

        public static IMockStepService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IMockStepService.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IMockStepService)) {
                return (IMockStepService) queryLocalInterface;
            }
            return new c(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IMockStepService.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IMockStepService.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                registerMockStepReport(IMockStepReport.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
            } else if (i == 2) {
                unregisterMockStepReport(IMockStepReport.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
            } else if (i == 3) {
                int capacity = getCapacity();
                parcel2.writeNoException();
                parcel2.writeInt(capacity);
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        /* loaded from: classes5.dex */
        static class c implements IMockStepService {
            private IBinder e;

            c(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.mockhealth.IMockStepService
            public void registerMockStepReport(IMockStepReport iMockStepReport) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMockStepService.DESCRIPTOR);
                    obtain.writeStrongInterface(iMockStepReport);
                    this.e.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.mockhealth.IMockStepService
            public void unregisterMockStepReport(IMockStepReport iMockStepReport) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMockStepService.DESCRIPTOR);
                    obtain.writeStrongInterface(iMockStepReport);
                    this.e.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.mockhealth.IMockStepService
            public int getCapacity() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMockStepService.DESCRIPTOR);
                    this.e.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
