package com.huawei.android.powerkit.adapter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.android.powerkit.adapter.IStateSink;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public interface IPowerKitApi extends IInterface {
    boolean applyForAlarmAdjustInterval(String str, List<String> list, boolean z, long j) throws RemoteException;

    boolean applyForAlarmExemption(String str, List<String> list, boolean z) throws RemoteException;

    boolean applyForResourceUse(String str, boolean z, String str2, int i, long j, String str3) throws RemoteException;

    void clearCycleUsedInfo(String str) throws RemoteException;

    boolean disableStateEvent(int i) throws RemoteException;

    boolean enableStateEvent(int i) throws RemoteException;

    List<PowerUsageState> getAppPowerUsage(String str, String str2, long j, long j2) throws RemoteException;

    List<AppCycleUsedInfo> getAppsCycleUsedInfo(String str, List<String> list) throws RemoteException;

    Map getAppsUnusedTime(String str) throws RemoteException;

    int getCurrentFps(String str) throws RemoteException;

    float getCurrentResolutionRatio(String str) throws RemoteException;

    String getPowerKitVersion(String str) throws RemoteException;

    int getPowerMode(String str) throws RemoteException;

    int getPowerOptimizeType(String str) throws RemoteException;

    boolean isUserSleeping(String str) throws RemoteException;

    boolean notifyCallingModules(String str, String str2, List<String> list) throws RemoteException;

    boolean registerMaintenanceTime(String str, boolean z, String str2, long j, long j2) throws RemoteException;

    boolean registerSink(IStateSink iStateSink) throws RemoteException;

    boolean setActiveState(String str, int i, int i2) throws RemoteException;

    int setFps(String str, int i) throws RemoteException;

    boolean setPowerOptimizeType(String str, boolean z, int i, int i2) throws RemoteException;

    boolean setPushMessageType(String str, String str2, int i) throws RemoteException;

    boolean unregisterSink(IStateSink iStateSink) throws RemoteException;

    public static abstract class Stub extends Binder implements IPowerKitApi {
        private static final String DESCRIPTOR = "com.huawei.android.powerkit.adapter.IPowerKitApi";
        static final int TRANSACTION_applyForAlarmAdjustInterval = 19;
        static final int TRANSACTION_applyForAlarmExemption = 20;
        static final int TRANSACTION_applyForResourceUse = 9;
        static final int TRANSACTION_clearCycleUsedInfo = 22;
        static final int TRANSACTION_disableStateEvent = 8;
        static final int TRANSACTION_enableStateEvent = 7;
        static final int TRANSACTION_getAppPowerUsage = 18;
        static final int TRANSACTION_getAppsCycleUsedInfo = 21;
        static final int TRANSACTION_getAppsUnusedTime = 17;
        static final int TRANSACTION_getCurrentFps = 3;
        static final int TRANSACTION_getCurrentResolutionRatio = 2;
        static final int TRANSACTION_getPowerKitVersion = 1;
        static final int TRANSACTION_getPowerMode = 12;
        static final int TRANSACTION_getPowerOptimizeType = 15;
        static final int TRANSACTION_isUserSleeping = 11;
        static final int TRANSACTION_notifyCallingModules = 10;
        static final int TRANSACTION_registerMaintenanceTime = 13;
        static final int TRANSACTION_registerSink = 5;
        static final int TRANSACTION_setActiveState = 16;
        static final int TRANSACTION_setFps = 4;
        static final int TRANSACTION_setPowerOptimizeType = 14;
        static final int TRANSACTION_setPushMessageType = 23;
        static final int TRANSACTION_unregisterSink = 6;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPowerKitApi asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IPowerKitApi)) {
                return (IPowerKitApi) queryLocalInterface;
            }
            return new b(iBinder);
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
                    String powerKitVersion = getPowerKitVersion(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(powerKitVersion);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    float currentResolutionRatio = getCurrentResolutionRatio(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeFloat(currentResolutionRatio);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int currentFps = getCurrentFps(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(currentFps);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int fps = setFps(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(fps);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerSink = registerSink(IStateSink.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(registerSink ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean unregisterSink = unregisterSink(IStateSink.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(unregisterSink ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean enableStateEvent = enableStateEvent(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(enableStateEvent ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean disableStateEvent = disableStateEvent(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(disableStateEvent ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean applyForResourceUse = applyForResourceUse(parcel.readString(), parcel.readInt() != 0, parcel.readString(), parcel.readInt(), parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(applyForResourceUse ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean notifyCallingModules = notifyCallingModules(parcel.readString(), parcel.readString(), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(notifyCallingModules ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isUserSleeping = isUserSleeping(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(isUserSleeping ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    int powerMode = getPowerMode(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(powerMode);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerMaintenanceTime = registerMaintenanceTime(parcel.readString(), parcel.readInt() != 0, parcel.readString(), parcel.readLong(), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(registerMaintenanceTime ? 1 : 0);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean powerOptimizeType = setPowerOptimizeType(parcel.readString(), parcel.readInt() != 0, parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(powerOptimizeType ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    int powerOptimizeType2 = getPowerOptimizeType(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(powerOptimizeType2);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean activeState = setActiveState(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(activeState ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    Map appsUnusedTime = getAppsUnusedTime(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeMap(appsUnusedTime);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<PowerUsageState> appPowerUsage = getAppPowerUsage(parcel.readString(), parcel.readString(), parcel.readLong(), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(appPowerUsage);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean applyForAlarmAdjustInterval = applyForAlarmAdjustInterval(parcel.readString(), parcel.createStringArrayList(), parcel.readInt() != 0, parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(applyForAlarmAdjustInterval ? 1 : 0);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean applyForAlarmExemption = applyForAlarmExemption(parcel.readString(), parcel.createStringArrayList(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(applyForAlarmExemption ? 1 : 0);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<AppCycleUsedInfo> appsCycleUsedInfo = getAppsCycleUsedInfo(parcel.readString(), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(appsCycleUsedInfo);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearCycleUsedInfo(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean pushMessageType = setPushMessageType(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(pushMessageType ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class b implements IPowerKitApi {
            private IBinder e;

            b(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.android.powerkit.adapter.IPowerKitApi
            public String getPowerKitVersion(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.e.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.powerkit.adapter.IPowerKitApi
            public float getCurrentResolutionRatio(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.e.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readFloat();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.powerkit.adapter.IPowerKitApi
            public int getCurrentFps(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.e.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.powerkit.adapter.IPowerKitApi
            public int setFps(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.e.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.powerkit.adapter.IPowerKitApi
            public boolean registerSink(IStateSink iStateSink) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iStateSink != null ? iStateSink.asBinder() : null);
                    this.e.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.powerkit.adapter.IPowerKitApi
            public boolean unregisterSink(IStateSink iStateSink) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iStateSink != null ? iStateSink.asBinder() : null);
                    this.e.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.powerkit.adapter.IPowerKitApi
            public boolean enableStateEvent(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.e.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.powerkit.adapter.IPowerKitApi
            public boolean disableStateEvent(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.e.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.powerkit.adapter.IPowerKitApi
            public boolean applyForResourceUse(String str, boolean z, String str2, int i, long j, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    obtain.writeString(str3);
                    this.e.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.powerkit.adapter.IPowerKitApi
            public boolean notifyCallingModules(String str, String str2, List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStringList(list);
                    this.e.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.powerkit.adapter.IPowerKitApi
            public boolean isUserSleeping(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.e.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.powerkit.adapter.IPowerKitApi
            public int getPowerMode(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.e.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.powerkit.adapter.IPowerKitApi
            public boolean registerMaintenanceTime(String str, boolean z, String str2, long j, long j2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeString(str2);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    this.e.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.powerkit.adapter.IPowerKitApi
            public boolean setPowerOptimizeType(String str, boolean z, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.e.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.powerkit.adapter.IPowerKitApi
            public int getPowerOptimizeType(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.e.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.powerkit.adapter.IPowerKitApi
            public boolean setActiveState(String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.e.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.powerkit.adapter.IPowerKitApi
            public Map getAppsUnusedTime(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.e.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readHashMap(getClass().getClassLoader());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.powerkit.adapter.IPowerKitApi
            public List<PowerUsageState> getAppPowerUsage(String str, String str2, long j, long j2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    this.e.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(PowerUsageState.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.powerkit.adapter.IPowerKitApi
            public boolean applyForAlarmAdjustInterval(String str, List<String> list, boolean z, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStringList(list);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeLong(j);
                    this.e.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.powerkit.adapter.IPowerKitApi
            public boolean applyForAlarmExemption(String str, List<String> list, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStringList(list);
                    obtain.writeInt(z ? 1 : 0);
                    this.e.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.powerkit.adapter.IPowerKitApi
            public List<AppCycleUsedInfo> getAppsCycleUsedInfo(String str, List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStringList(list);
                    this.e.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(AppCycleUsedInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.powerkit.adapter.IPowerKitApi
            public void clearCycleUsedInfo(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.e.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.powerkit.adapter.IPowerKitApi
            public boolean setPushMessageType(String str, String str2, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    this.e.transact(23, obtain, obtain2, 0);
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
