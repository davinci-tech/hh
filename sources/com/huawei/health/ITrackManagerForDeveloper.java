package com.huawei.health;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.health.IBaseCommonCallback;
import com.huawei.health.ITrackDataForDeveloper;
import com.huawei.hihealth.StartSportParam;
import com.huawei.hihealth.StopSportParam;

/* loaded from: classes3.dex */
public interface ITrackManagerForDeveloper extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.health.ITrackManagerForDeveloper";

    void connectSportDevice(int i, IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    Bundle getSportData() throws RemoteException;

    int getSportState() throws RemoteException;

    void pauseSport(IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    boolean registerRealtimeSportCallback(ITrackDataForDeveloper iTrackDataForDeveloper) throws RemoteException;

    void resumeSport(IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    void sendDeviceControlinstruction(String str, IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    void startSport(int i, IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    void startSportEnhance(StartSportParam startSportParam, IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    void stopSport(IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    void stopSportEnhance(StopSportParam stopSportParam, IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    boolean unregisterRealtimeSportCallback(ITrackDataForDeveloper iTrackDataForDeveloper) throws RemoteException;

    public static abstract class Stub extends Binder implements ITrackManagerForDeveloper {
        static final int TRANSACTION_connectSportDevice = 11;
        static final int TRANSACTION_getSportData = 4;
        static final int TRANSACTION_getSportState = 3;
        static final int TRANSACTION_pauseSport = 9;
        static final int TRANSACTION_registerRealtimeSportCallback = 1;
        static final int TRANSACTION_resumeSport = 10;
        static final int TRANSACTION_sendDeviceControlinstruction = 8;
        static final int TRANSACTION_startSport = 5;
        static final int TRANSACTION_startSportEnhance = 7;
        static final int TRANSACTION_stopSport = 6;
        static final int TRANSACTION_stopSportEnhance = 12;
        static final int TRANSACTION_unregisterRealtimeSportCallback = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ITrackManagerForDeveloper.DESCRIPTOR);
        }

        public static ITrackManagerForDeveloper asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ITrackManagerForDeveloper.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ITrackManagerForDeveloper)) {
                return (ITrackManagerForDeveloper) queryLocalInterface;
            }
            return new e(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ITrackManagerForDeveloper.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ITrackManagerForDeveloper.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    boolean registerRealtimeSportCallback = registerRealtimeSportCallback(ITrackDataForDeveloper.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(registerRealtimeSportCallback ? 1 : 0);
                    return true;
                case 2:
                    boolean unregisterRealtimeSportCallback = unregisterRealtimeSportCallback(ITrackDataForDeveloper.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(unregisterRealtimeSportCallback ? 1 : 0);
                    return true;
                case 3:
                    int sportState = getSportState();
                    parcel2.writeNoException();
                    parcel2.writeInt(sportState);
                    return true;
                case 4:
                    Bundle sportData = getSportData();
                    parcel2.writeNoException();
                    c.AY_(parcel2, sportData, 1);
                    return true;
                case 5:
                    startSport(parcel.readInt(), IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    stopSport(IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    startSportEnhance((StartSportParam) c.AX_(parcel, StartSportParam.CREATOR), IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    sendDeviceControlinstruction(parcel.readString(), IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    pauseSport(IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    resumeSport(IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 11:
                    connectSportDevice(parcel.readInt(), IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 12:
                    stopSportEnhance((StopSportParam) c.AX_(parcel, StopSportParam.CREATOR), IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* loaded from: classes7.dex */
        static class e implements ITrackManagerForDeveloper {
            private IBinder c;

            e(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.health.ITrackManagerForDeveloper
            public boolean registerRealtimeSportCallback(ITrackDataForDeveloper iTrackDataForDeveloper) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITrackManagerForDeveloper.DESCRIPTOR);
                    obtain.writeStrongInterface(iTrackDataForDeveloper);
                    this.c.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ITrackManagerForDeveloper
            public boolean unregisterRealtimeSportCallback(ITrackDataForDeveloper iTrackDataForDeveloper) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITrackManagerForDeveloper.DESCRIPTOR);
                    obtain.writeStrongInterface(iTrackDataForDeveloper);
                    this.c.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ITrackManagerForDeveloper
            public int getSportState() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITrackManagerForDeveloper.DESCRIPTOR);
                    this.c.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ITrackManagerForDeveloper
            public Bundle getSportData() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITrackManagerForDeveloper.DESCRIPTOR);
                    this.c.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return (Bundle) c.AX_(obtain2, Bundle.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ITrackManagerForDeveloper
            public void startSport(int i, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITrackManagerForDeveloper.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iBaseCommonCallback);
                    this.c.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ITrackManagerForDeveloper
            public void stopSport(IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITrackManagerForDeveloper.DESCRIPTOR);
                    obtain.writeStrongInterface(iBaseCommonCallback);
                    this.c.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ITrackManagerForDeveloper
            public void startSportEnhance(StartSportParam startSportParam, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITrackManagerForDeveloper.DESCRIPTOR);
                    c.AY_(obtain, startSportParam, 0);
                    obtain.writeStrongInterface(iBaseCommonCallback);
                    this.c.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ITrackManagerForDeveloper
            public void sendDeviceControlinstruction(String str, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITrackManagerForDeveloper.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iBaseCommonCallback);
                    this.c.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ITrackManagerForDeveloper
            public void pauseSport(IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITrackManagerForDeveloper.DESCRIPTOR);
                    obtain.writeStrongInterface(iBaseCommonCallback);
                    this.c.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ITrackManagerForDeveloper
            public void resumeSport(IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITrackManagerForDeveloper.DESCRIPTOR);
                    obtain.writeStrongInterface(iBaseCommonCallback);
                    this.c.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ITrackManagerForDeveloper
            public void connectSportDevice(int i, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITrackManagerForDeveloper.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iBaseCommonCallback);
                    this.c.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ITrackManagerForDeveloper
            public void stopSportEnhance(StopSportParam stopSportParam, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITrackManagerForDeveloper.DESCRIPTOR);
                    c.AY_(obtain, stopSportParam, 0);
                    obtain.writeStrongInterface(iBaseCommonCallback);
                    this.c.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class c {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T AX_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void AY_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
