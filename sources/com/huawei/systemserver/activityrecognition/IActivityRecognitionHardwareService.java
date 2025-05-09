package com.huawei.systemserver.activityrecognition;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareSink;

/* loaded from: classes6.dex */
public interface IActivityRecognitionHardwareService extends IInterface {
    boolean disableActivityEvent(String str, String str2, int i) throws RemoteException;

    boolean disableEnvironmentEvent(String str, String str2, int i) throws RemoteException;

    boolean enableActivityEvent(String str, String str2, int i, long j) throws RemoteException;

    boolean enableActivityExtendEvent(String str, String str2, int i, long j, OtherParameters otherParameters) throws RemoteException;

    boolean enableEnvironmentEvent(String str, String str2, int i, long j, OtherParameters otherParameters) throws RemoteException;

    boolean exitEnvironmentFunction(String str, String str2, OtherParameters otherParameters) throws RemoteException;

    boolean flush() throws RemoteException;

    int getARVersion(String str, int i) throws RemoteException;

    HwActivityChangedExtendEvent getCurrentActivity() throws RemoteException;

    HwActivityChangedExtendEvent getCurrentActivityV1_1() throws RemoteException;

    HwEnvironmentChangedEvent getCurrentEnvironment() throws RemoteException;

    HwEnvironmentChangedEvent getCurrentEnvironmentV1_1() throws RemoteException;

    String[] getSupportedActivities() throws RemoteException;

    String[] getSupportedEnvironments() throws RemoteException;

    int getSupportedModule() throws RemoteException;

    boolean initEnvironmentFunction(String str, String str2, OtherParameters otherParameters) throws RemoteException;

    boolean registerSink(String str, IActivityRecognitionHardwareSink iActivityRecognitionHardwareSink) throws RemoteException;

    boolean unregisterSink(String str, IActivityRecognitionHardwareSink iActivityRecognitionHardwareSink) throws RemoteException;

    public static abstract class Stub extends Binder implements IActivityRecognitionHardwareService {
        private static final String DESCRIPTOR = "com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService";
        static final int TRANSACTION_disableActivityEvent = 7;
        static final int TRANSACTION_disableEnvironmentEvent = 15;
        static final int TRANSACTION_enableActivityEvent = 5;
        static final int TRANSACTION_enableActivityExtendEvent = 6;
        static final int TRANSACTION_enableEnvironmentEvent = 14;
        static final int TRANSACTION_exitEnvironmentFunction = 12;
        static final int TRANSACTION_flush = 9;
        static final int TRANSACTION_getARVersion = 16;
        static final int TRANSACTION_getCurrentActivity = 8;
        static final int TRANSACTION_getCurrentActivityV1_1 = 17;
        static final int TRANSACTION_getCurrentEnvironment = 13;
        static final int TRANSACTION_getCurrentEnvironmentV1_1 = 18;
        static final int TRANSACTION_getSupportedActivities = 2;
        static final int TRANSACTION_getSupportedEnvironments = 10;
        static final int TRANSACTION_getSupportedModule = 1;
        static final int TRANSACTION_initEnvironmentFunction = 11;
        static final int TRANSACTION_registerSink = 3;
        static final int TRANSACTION_unregisterSink = 4;

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
            return new d(iBinder);
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
                    int supportedModule = getSupportedModule();
                    parcel2.writeNoException();
                    parcel2.writeInt(supportedModule);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] supportedActivities = getSupportedActivities();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(supportedActivities);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerSink = registerSink(parcel.readString(), IActivityRecognitionHardwareSink.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(registerSink ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean unregisterSink = unregisterSink(parcel.readString(), IActivityRecognitionHardwareSink.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(unregisterSink ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean enableActivityEvent = enableActivityEvent(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(enableActivityEvent ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean enableActivityExtendEvent = enableActivityExtendEvent(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readLong(), parcel.readInt() != 0 ? OtherParameters.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(enableActivityExtendEvent ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean disableActivityEvent = disableActivityEvent(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(disableActivityEvent ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    HwActivityChangedExtendEvent currentActivity = getCurrentActivity();
                    parcel2.writeNoException();
                    if (currentActivity != null) {
                        parcel2.writeInt(1);
                        currentActivity.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean flush = flush();
                    parcel2.writeNoException();
                    parcel2.writeInt(flush ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] supportedEnvironments = getSupportedEnvironments();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(supportedEnvironments);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean initEnvironmentFunction = initEnvironmentFunction(parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? OtherParameters.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(initEnvironmentFunction ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean exitEnvironmentFunction = exitEnvironmentFunction(parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? OtherParameters.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(exitEnvironmentFunction ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    HwEnvironmentChangedEvent currentEnvironment = getCurrentEnvironment();
                    parcel2.writeNoException();
                    if (currentEnvironment != null) {
                        parcel2.writeInt(1);
                        currentEnvironment.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean enableEnvironmentEvent = enableEnvironmentEvent(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readLong(), parcel.readInt() != 0 ? OtherParameters.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(enableEnvironmentEvent ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean disableEnvironmentEvent = disableEnvironmentEvent(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(disableEnvironmentEvent ? 1 : 0);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    int aRVersion = getARVersion(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(aRVersion);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    HwActivityChangedExtendEvent currentActivityV1_1 = getCurrentActivityV1_1();
                    parcel2.writeNoException();
                    if (currentActivityV1_1 != null) {
                        parcel2.writeInt(1);
                        currentActivityV1_1.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    HwEnvironmentChangedEvent currentEnvironmentV1_1 = getCurrentEnvironmentV1_1();
                    parcel2.writeNoException();
                    if (currentEnvironmentV1_1 != null) {
                        parcel2.writeInt(1);
                        currentEnvironmentV1_1.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class d implements IActivityRecognitionHardwareService {
            private IBinder d;

            d(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService
            public int getSupportedModule() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.d.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService
            public String[] getSupportedActivities() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.d.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService
            public boolean registerSink(String str, IActivityRecognitionHardwareSink iActivityRecognitionHardwareSink) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iActivityRecognitionHardwareSink != null ? iActivityRecognitionHardwareSink.asBinder() : null);
                    this.d.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService
            public boolean unregisterSink(String str, IActivityRecognitionHardwareSink iActivityRecognitionHardwareSink) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iActivityRecognitionHardwareSink != null ? iActivityRecognitionHardwareSink.asBinder() : null);
                    this.d.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService
            public boolean enableActivityEvent(String str, String str2, int i, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    this.d.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService
            public boolean enableActivityExtendEvent(String str, String str2, int i, long j, OtherParameters otherParameters) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    if (otherParameters != null) {
                        obtain.writeInt(1);
                        otherParameters.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.d.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService
            public boolean disableActivityEvent(String str, String str2, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    this.d.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService
            public HwActivityChangedExtendEvent getCurrentActivity() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.d.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? HwActivityChangedExtendEvent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService
            public boolean flush() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.d.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService
            public String[] getSupportedEnvironments() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.d.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService
            public boolean initEnvironmentFunction(String str, String str2, OtherParameters otherParameters) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (otherParameters != null) {
                        obtain.writeInt(1);
                        otherParameters.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.d.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService
            public boolean exitEnvironmentFunction(String str, String str2, OtherParameters otherParameters) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (otherParameters != null) {
                        obtain.writeInt(1);
                        otherParameters.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.d.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService
            public HwEnvironmentChangedEvent getCurrentEnvironment() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.d.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? HwEnvironmentChangedEvent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService
            public boolean enableEnvironmentEvent(String str, String str2, int i, long j, OtherParameters otherParameters) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    if (otherParameters != null) {
                        obtain.writeInt(1);
                        otherParameters.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.d.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService
            public boolean disableEnvironmentEvent(String str, String str2, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    this.d.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService
            public int getARVersion(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.d.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService
            public HwActivityChangedExtendEvent getCurrentActivityV1_1() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.d.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? HwActivityChangedExtendEvent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService
            public HwEnvironmentChangedEvent getCurrentEnvironmentV1_1() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.d.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? HwEnvironmentChangedEvent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
