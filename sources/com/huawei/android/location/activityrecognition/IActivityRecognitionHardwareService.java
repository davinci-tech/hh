package com.huawei.android.location.activityrecognition;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.android.location.activityrecognition.IActivityRecognitionHardwareSink;

/* loaded from: classes2.dex */
public interface IActivityRecognitionHardwareService extends IInterface {
    boolean disableActivityEvent(String str, int i) throws RemoteException;

    boolean enableActivityEvent(String str, int i, long j) throws RemoteException;

    boolean flush() throws RemoteException;

    String getCurrentActivity() throws RemoteException;

    String[] getSupportedActivities() throws RemoteException;

    boolean providerLoadOk() throws RemoteException;

    boolean registerSink(IActivityRecognitionHardwareSink iActivityRecognitionHardwareSink) throws RemoteException;

    boolean unregisterSink(IActivityRecognitionHardwareSink iActivityRecognitionHardwareSink) throws RemoteException;

    public static abstract class Stub extends Binder implements IActivityRecognitionHardwareService {
        private static final String DESCRIPTOR = "com.huawei.android.location.activityrecognition.IActivityRecognitionHardwareService";
        static final int TRANSACTION_disableActivityEvent = 5;
        static final int TRANSACTION_enableActivityEvent = 4;
        static final int TRANSACTION_flush = 7;
        static final int TRANSACTION_getCurrentActivity = 6;
        static final int TRANSACTION_getSupportedActivities = 1;
        static final int TRANSACTION_providerLoadOk = 8;
        static final int TRANSACTION_registerSink = 2;
        static final int TRANSACTION_unregisterSink = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IActivityRecognitionHardwareService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IActivityRecognitionHardwareService)) {
                return (IActivityRecognitionHardwareService) queryLocalInterface;
            }
            return new e(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] supportedActivities = getSupportedActivities();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(supportedActivities);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerSink = registerSink(IActivityRecognitionHardwareSink.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(registerSink ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean unregisterSink = unregisterSink(IActivityRecognitionHardwareSink.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(unregisterSink ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean enableActivityEvent = enableActivityEvent(parcel.readString(), parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(enableActivityEvent ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean disableActivityEvent = disableActivityEvent(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(disableActivityEvent ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    String currentActivity = getCurrentActivity();
                    parcel2.writeNoException();
                    parcel2.writeString(currentActivity);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean flush = flush();
                    parcel2.writeNoException();
                    parcel2.writeInt(flush ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean providerLoadOk = providerLoadOk();
                    parcel2.writeNoException();
                    parcel2.writeInt(providerLoadOk ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class e implements IActivityRecognitionHardwareService {
            private IBinder e;

            e(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.android.location.activityrecognition.IActivityRecognitionHardwareService
            public String[] getSupportedActivities() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.e.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.location.activityrecognition.IActivityRecognitionHardwareService
            public boolean registerSink(IActivityRecognitionHardwareSink iActivityRecognitionHardwareSink) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iActivityRecognitionHardwareSink != null ? iActivityRecognitionHardwareSink.asBinder() : null);
                    this.e.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.location.activityrecognition.IActivityRecognitionHardwareService
            public boolean unregisterSink(IActivityRecognitionHardwareSink iActivityRecognitionHardwareSink) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iActivityRecognitionHardwareSink != null ? iActivityRecognitionHardwareSink.asBinder() : null);
                    this.e.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.location.activityrecognition.IActivityRecognitionHardwareService
            public boolean enableActivityEvent(String str, int i, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    this.e.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.location.activityrecognition.IActivityRecognitionHardwareService
            public boolean disableActivityEvent(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.e.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.location.activityrecognition.IActivityRecognitionHardwareService
            public String getCurrentActivity() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.e.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.location.activityrecognition.IActivityRecognitionHardwareService
            public boolean flush() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.e.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.location.activityrecognition.IActivityRecognitionHardwareService
            public boolean providerLoadOk() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.e.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
