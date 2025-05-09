package com.huawei.health;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.health.ITrackDataReport;

/* loaded from: classes.dex */
public interface ITrackSportManager extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.health.ITrackSportManager";

    int isTrackWorking() throws RemoteException;

    void pauseSport() throws RemoteException;

    void registerDataCallback(ITrackDataReport iTrackDataReport) throws RemoteException;

    void resumeSport() throws RemoteException;

    void startSport() throws RemoteException;

    void stopSport() throws RemoteException;

    void unRegisterDataCallback(ITrackDataReport iTrackDataReport) throws RemoteException;

    public static abstract class Stub extends Binder implements ITrackSportManager {
        static final int TRANSACTION_isTrackWorking = 1;
        static final int TRANSACTION_pauseSport = 5;
        static final int TRANSACTION_registerDataCallback = 2;
        static final int TRANSACTION_resumeSport = 7;
        static final int TRANSACTION_startSport = 4;
        static final int TRANSACTION_stopSport = 6;
        static final int TRANSACTION_unRegisterDataCallback = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ITrackSportManager.DESCRIPTOR);
        }

        public static ITrackSportManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ITrackSportManager.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ITrackSportManager)) {
                return (ITrackSportManager) queryLocalInterface;
            }
            return new e(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ITrackSportManager.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ITrackSportManager.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    int isTrackWorking = isTrackWorking();
                    parcel2.writeNoException();
                    parcel2.writeInt(isTrackWorking);
                    return true;
                case 2:
                    registerDataCallback(ITrackDataReport.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    unRegisterDataCallback(ITrackDataReport.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    startSport();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    pauseSport();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    stopSport();
                    parcel2.writeNoException();
                    return true;
                case 7:
                    resumeSport();
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* loaded from: classes3.dex */
        static class e implements ITrackSportManager {
            private IBinder c;

            e(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.health.ITrackSportManager
            public int isTrackWorking() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITrackSportManager.DESCRIPTOR);
                    this.c.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ITrackSportManager
            public void registerDataCallback(ITrackDataReport iTrackDataReport) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITrackSportManager.DESCRIPTOR);
                    obtain.writeStrongInterface(iTrackDataReport);
                    this.c.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ITrackSportManager
            public void unRegisterDataCallback(ITrackDataReport iTrackDataReport) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITrackSportManager.DESCRIPTOR);
                    obtain.writeStrongInterface(iTrackDataReport);
                    this.c.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ITrackSportManager
            public void startSport() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITrackSportManager.DESCRIPTOR);
                    this.c.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ITrackSportManager
            public void pauseSport() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITrackSportManager.DESCRIPTOR);
                    this.c.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ITrackSportManager
            public void stopSport() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITrackSportManager.DESCRIPTOR);
                    this.c.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ITrackSportManager
            public void resumeSport() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITrackSportManager.DESCRIPTOR);
                    this.c.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
