package com.hihonor.health.fw;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.hihonor.health.fw.IReportCallback;

/* loaded from: classes.dex */
public interface IHwStepCounterManager extends IInterface {
    public static final String DESCRIPTOR = "com.hihonor.health.fw.IHwStepCounterManager";

    int closeStepCounter() throws RemoteException;

    int[] getAbility() throws RemoteException;

    boolean getHistoryData(long j, long j2, IReportCallback iReportCallback) throws RemoteException;

    String getVersion() throws RemoteException;

    boolean insertDiffStep(Bundle bundle) throws RemoteException;

    int openStepCounter() throws RemoteException;

    void registerDataCallback(IReportCallback iReportCallback) throws RemoteException;

    void registerLogCallback(IReportCallback iReportCallback) throws RemoteException;

    void unRegisterDataCallback(IReportCallback iReportCallback) throws RemoteException;

    void unRegisterLogCallback(IReportCallback iReportCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IHwStepCounterManager {
        static final int TRANSACTION_closeStepCounter = 4;
        static final int TRANSACTION_getAbility = 2;
        static final int TRANSACTION_getHistoryData = 8;
        static final int TRANSACTION_getVersion = 1;
        static final int TRANSACTION_insertDiffStep = 7;
        static final int TRANSACTION_openStepCounter = 3;
        static final int TRANSACTION_registerDataCallback = 5;
        static final int TRANSACTION_registerLogCallback = 9;
        static final int TRANSACTION_unRegisterDataCallback = 6;
        static final int TRANSACTION_unRegisterLogCallback = 10;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IHwStepCounterManager.DESCRIPTOR);
        }

        public static IHwStepCounterManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IHwStepCounterManager.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IHwStepCounterManager)) {
                return (IHwStepCounterManager) queryLocalInterface;
            }
            return new d(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IHwStepCounterManager.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IHwStepCounterManager.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    String version = getVersion();
                    parcel2.writeNoException();
                    parcel2.writeString(version);
                    return true;
                case 2:
                    int[] ability = getAbility();
                    parcel2.writeNoException();
                    parcel2.writeIntArray(ability);
                    return true;
                case 3:
                    int openStepCounter = openStepCounter();
                    parcel2.writeNoException();
                    parcel2.writeInt(openStepCounter);
                    return true;
                case 4:
                    int closeStepCounter = closeStepCounter();
                    parcel2.writeNoException();
                    parcel2.writeInt(closeStepCounter);
                    return true;
                case 5:
                    registerDataCallback(IReportCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    unRegisterDataCallback(IReportCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    boolean insertDiffStep = insertDiffStep((Bundle) b.cV_(parcel, Bundle.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(insertDiffStep ? 1 : 0);
                    return true;
                case 8:
                    boolean historyData = getHistoryData(parcel.readLong(), parcel.readLong(), IReportCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(historyData ? 1 : 0);
                    return true;
                case 9:
                    registerLogCallback(IReportCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    unRegisterLogCallback(IReportCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* loaded from: classes2.dex */
        static class d implements IHwStepCounterManager {
            private IBinder e;

            d(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.hihonor.health.fw.IHwStepCounterManager
            public String getVersion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHwStepCounterManager.DESCRIPTOR);
                    this.e.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.hihonor.health.fw.IHwStepCounterManager
            public int[] getAbility() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHwStepCounterManager.DESCRIPTOR);
                    this.e.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createIntArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.hihonor.health.fw.IHwStepCounterManager
            public int openStepCounter() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHwStepCounterManager.DESCRIPTOR);
                    this.e.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.hihonor.health.fw.IHwStepCounterManager
            public int closeStepCounter() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHwStepCounterManager.DESCRIPTOR);
                    this.e.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.hihonor.health.fw.IHwStepCounterManager
            public void registerDataCallback(IReportCallback iReportCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHwStepCounterManager.DESCRIPTOR);
                    obtain.writeStrongInterface(iReportCallback);
                    this.e.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.hihonor.health.fw.IHwStepCounterManager
            public void unRegisterDataCallback(IReportCallback iReportCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHwStepCounterManager.DESCRIPTOR);
                    obtain.writeStrongInterface(iReportCallback);
                    this.e.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.hihonor.health.fw.IHwStepCounterManager
            public boolean insertDiffStep(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHwStepCounterManager.DESCRIPTOR);
                    b.cW_(obtain, bundle, 0);
                    this.e.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.hihonor.health.fw.IHwStepCounterManager
            public boolean getHistoryData(long j, long j2, IReportCallback iReportCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHwStepCounterManager.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeStrongInterface(iReportCallback);
                    this.e.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.hihonor.health.fw.IHwStepCounterManager
            public void registerLogCallback(IReportCallback iReportCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHwStepCounterManager.DESCRIPTOR);
                    obtain.writeStrongInterface(iReportCallback);
                    this.e.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.hihonor.health.fw.IHwStepCounterManager
            public void unRegisterLogCallback(IReportCallback iReportCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHwStepCounterManager.DESCRIPTOR);
                    obtain.writeStrongInterface(iReportCallback);
                    this.e.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class b {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T cV_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void cW_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
