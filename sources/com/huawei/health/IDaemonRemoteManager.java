package com.huawei.health;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.health.IRealStepDataReport;
import com.huawei.health.IResultCallback;
import com.huawei.health.IStepDataReport;

/* loaded from: classes.dex */
public interface IDaemonRemoteManager extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.health.IDaemonRemoteManager";

    void flushCacheToDB(IResultCallback iResultCallback) throws RemoteException;

    void getDebugInfo(IResultCallback iResultCallback) throws RemoteException;

    int getDeviceOriginalClass() throws RemoteException;

    boolean getGoalNotifiState() throws RemoteException;

    boolean getNotificationSupport() throws RemoteException;

    void getSleepData(IResultCallback iResultCallback) throws RemoteException;

    void getStandSteps(IResultCallback iResultCallback) throws RemoteException;

    int getStepCountAuthPermission() throws RemoteException;

    int getStepCounterClass() throws RemoteException;

    boolean getStepCounterSwitchStatus() throws RemoteException;

    boolean getStepsNotifiState() throws RemoteException;

    void getTodaySportData(IResultCallback iResultCallback) throws RemoteException;

    String getVersion() throws RemoteException;

    void isNeedPromptKeepAlive(IResultCallback iResultCallback) throws RemoteException;

    boolean isNotificationShown() throws RemoteException;

    boolean isStepCounterWorking() throws RemoteException;

    void makePromptNoSense() throws RemoteException;

    void notifyUserInfoChanged() throws RemoteException;

    boolean registerRealTimeStepReport(IRealStepDataReport iRealStepDataReport, int i) throws RemoteException;

    boolean registerStepReportCallback(IStepDataReport iStepDataReport) throws RemoteException;

    void setBaseData(long j, int i, int i2, int i3) throws RemoteException;

    void setGoalNotifiEnable(boolean z) throws RemoteException;

    void setNotificationEnable(boolean z) throws RemoteException;

    void setStepCounterSwitchStatus(boolean z) throws RemoteException;

    void setStepsNotifiEnable(boolean z) throws RemoteException;

    void setUserInfo(Bundle bundle) throws RemoteException;

    void switchTrackMonitor(boolean z) throws RemoteException;

    void tickTrackDog() throws RemoteException;

    boolean unRegisterRealTimeStepReport() throws RemoteException;

    boolean unRegisterStepReportCallback(IStepDataReport iStepDataReport) throws RemoteException;

    public static abstract class Stub extends Binder implements IDaemonRemoteManager {
        static final int TRANSACTION_flushCacheToDB = 11;
        static final int TRANSACTION_getDebugInfo = 15;
        static final int TRANSACTION_getDeviceOriginalClass = 27;
        static final int TRANSACTION_getGoalNotifiState = 23;
        static final int TRANSACTION_getNotificationSupport = 19;
        static final int TRANSACTION_getSleepData = 13;
        static final int TRANSACTION_getStandSteps = 24;
        static final int TRANSACTION_getStepCountAuthPermission = 30;
        static final int TRANSACTION_getStepCounterClass = 5;
        static final int TRANSACTION_getStepCounterSwitchStatus = 3;
        static final int TRANSACTION_getStepsNotifiState = 22;
        static final int TRANSACTION_getTodaySportData = 14;
        static final int TRANSACTION_getVersion = 1;
        static final int TRANSACTION_isNeedPromptKeepAlive = 28;
        static final int TRANSACTION_isNotificationShown = 10;
        static final int TRANSACTION_isStepCounterWorking = 4;
        static final int TRANSACTION_makePromptNoSense = 29;
        static final int TRANSACTION_notifyUserInfoChanged = 16;
        static final int TRANSACTION_registerRealTimeStepReport = 18;
        static final int TRANSACTION_registerStepReportCallback = 6;
        static final int TRANSACTION_setBaseData = 8;
        static final int TRANSACTION_setGoalNotifiEnable = 21;
        static final int TRANSACTION_setNotificationEnable = 9;
        static final int TRANSACTION_setStepCounterSwitchStatus = 2;
        static final int TRANSACTION_setStepsNotifiEnable = 20;
        static final int TRANSACTION_setUserInfo = 12;
        static final int TRANSACTION_switchTrackMonitor = 25;
        static final int TRANSACTION_tickTrackDog = 26;
        static final int TRANSACTION_unRegisterRealTimeStepReport = 7;
        static final int TRANSACTION_unRegisterStepReportCallback = 17;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IDaemonRemoteManager.DESCRIPTOR);
        }

        public static IDaemonRemoteManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IDaemonRemoteManager.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDaemonRemoteManager)) {
                return (IDaemonRemoteManager) queryLocalInterface;
            }
            return new b(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IDaemonRemoteManager.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IDaemonRemoteManager.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    String version = getVersion();
                    parcel2.writeNoException();
                    parcel2.writeString(version);
                    return true;
                case 2:
                    setStepCounterSwitchStatus(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 3:
                    boolean stepCounterSwitchStatus = getStepCounterSwitchStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(stepCounterSwitchStatus ? 1 : 0);
                    return true;
                case 4:
                    boolean isStepCounterWorking = isStepCounterWorking();
                    parcel2.writeNoException();
                    parcel2.writeInt(isStepCounterWorking ? 1 : 0);
                    return true;
                case 5:
                    int stepCounterClass = getStepCounterClass();
                    parcel2.writeNoException();
                    parcel2.writeInt(stepCounterClass);
                    return true;
                case 6:
                    boolean registerStepReportCallback = registerStepReportCallback(IStepDataReport.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(registerStepReportCallback ? 1 : 0);
                    return true;
                case 7:
                    boolean unRegisterRealTimeStepReport = unRegisterRealTimeStepReport();
                    parcel2.writeNoException();
                    parcel2.writeInt(unRegisterRealTimeStepReport ? 1 : 0);
                    return true;
                case 8:
                    setBaseData(parcel.readLong(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    setNotificationEnable(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 10:
                    boolean isNotificationShown = isNotificationShown();
                    parcel2.writeNoException();
                    parcel2.writeInt(isNotificationShown ? 1 : 0);
                    return true;
                case 11:
                    flushCacheToDB(IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 12:
                    setUserInfo((Bundle) d.Az_(parcel, Bundle.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 13:
                    getSleepData(IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 14:
                    getTodaySportData(IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 15:
                    getDebugInfo(IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 16:
                    notifyUserInfoChanged();
                    parcel2.writeNoException();
                    return true;
                case 17:
                    boolean unRegisterStepReportCallback = unRegisterStepReportCallback(IStepDataReport.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(unRegisterStepReportCallback ? 1 : 0);
                    return true;
                case 18:
                    boolean registerRealTimeStepReport = registerRealTimeStepReport(IRealStepDataReport.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(registerRealTimeStepReport ? 1 : 0);
                    return true;
                case 19:
                    boolean notificationSupport = getNotificationSupport();
                    parcel2.writeNoException();
                    parcel2.writeInt(notificationSupport ? 1 : 0);
                    return true;
                case 20:
                    setStepsNotifiEnable(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 21:
                    setGoalNotifiEnable(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 22:
                    boolean stepsNotifiState = getStepsNotifiState();
                    parcel2.writeNoException();
                    parcel2.writeInt(stepsNotifiState ? 1 : 0);
                    return true;
                case 23:
                    boolean goalNotifiState = getGoalNotifiState();
                    parcel2.writeNoException();
                    parcel2.writeInt(goalNotifiState ? 1 : 0);
                    return true;
                case 24:
                    getStandSteps(IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 25:
                    switchTrackMonitor(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 26:
                    tickTrackDog();
                    parcel2.writeNoException();
                    return true;
                case 27:
                    int deviceOriginalClass = getDeviceOriginalClass();
                    parcel2.writeNoException();
                    parcel2.writeInt(deviceOriginalClass);
                    return true;
                case 28:
                    isNeedPromptKeepAlive(IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 29:
                    makePromptNoSense();
                    parcel2.writeNoException();
                    return true;
                case 30:
                    int stepCountAuthPermission = getStepCountAuthPermission();
                    parcel2.writeNoException();
                    parcel2.writeInt(stepCountAuthPermission);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* loaded from: classes3.dex */
        static class b implements IDaemonRemoteManager {
            private IBinder c;

            b(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public String getVersion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    this.c.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public void setStepCounterSwitchStatus(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.c.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public boolean getStepCounterSwitchStatus() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    this.c.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public boolean isStepCounterWorking() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    this.c.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public int getStepCounterClass() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    this.c.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public boolean registerStepReportCallback(IStepDataReport iStepDataReport) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    obtain.writeStrongInterface(iStepDataReport);
                    this.c.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public boolean unRegisterRealTimeStepReport() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    this.c.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public void setBaseData(long j, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.c.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public void setNotificationEnable(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.c.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public boolean isNotificationShown() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    this.c.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public void flushCacheToDB(IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    obtain.writeStrongInterface(iResultCallback);
                    this.c.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public void setUserInfo(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    d.AA_(obtain, bundle, 0);
                    this.c.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public void getSleepData(IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    obtain.writeStrongInterface(iResultCallback);
                    this.c.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public void getTodaySportData(IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    obtain.writeStrongInterface(iResultCallback);
                    this.c.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public void getDebugInfo(IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    obtain.writeStrongInterface(iResultCallback);
                    this.c.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public void notifyUserInfoChanged() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    this.c.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public boolean unRegisterStepReportCallback(IStepDataReport iStepDataReport) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    obtain.writeStrongInterface(iStepDataReport);
                    this.c.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public boolean registerRealTimeStepReport(IRealStepDataReport iRealStepDataReport, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    obtain.writeStrongInterface(iRealStepDataReport);
                    obtain.writeInt(i);
                    this.c.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public boolean getNotificationSupport() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    this.c.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public void setStepsNotifiEnable(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.c.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public void setGoalNotifiEnable(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.c.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public boolean getStepsNotifiState() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    this.c.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public boolean getGoalNotifiState() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    this.c.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public void getStandSteps(IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    obtain.writeStrongInterface(iResultCallback);
                    this.c.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public void switchTrackMonitor(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.c.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public void tickTrackDog() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    this.c.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public int getDeviceOriginalClass() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    this.c.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public void isNeedPromptKeepAlive(IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    obtain.writeStrongInterface(iResultCallback);
                    this.c.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public void makePromptNoSense() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    this.c.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IDaemonRemoteManager
            public int getStepCountAuthPermission() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDaemonRemoteManager.DESCRIPTOR);
                    this.c.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class d {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T Az_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void AA_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
