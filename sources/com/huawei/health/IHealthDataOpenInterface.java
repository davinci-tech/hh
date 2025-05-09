package com.huawei.health;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.health.IBaseCommonCallback;

/* loaded from: classes3.dex */
public interface IHealthDataOpenInterface extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.health.IHealthDataOpenInterface";

    void getHealthData(long j, long j2, IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IHealthDataOpenInterface {
        static final int TRANSACTION_getHealthData = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IHealthDataOpenInterface.DESCRIPTOR);
        }

        public static IHealthDataOpenInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IHealthDataOpenInterface.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IHealthDataOpenInterface)) {
                return (IHealthDataOpenInterface) queryLocalInterface;
            }
            return new d(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IHealthDataOpenInterface.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IHealthDataOpenInterface.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                getHealthData(parcel.readLong(), parcel.readLong(), IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        /* loaded from: classes7.dex */
        static class d implements IHealthDataOpenInterface {
            private IBinder d;

            d(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.health.IHealthDataOpenInterface
            public void getHealthData(long j, long j2, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHealthDataOpenInterface.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeStrongInterface(iBaseCommonCallback);
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
