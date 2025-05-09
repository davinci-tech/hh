package com.huawei.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.hihealth.IBaseCallback;
import com.huawei.hihealth.ICommonCallback;
import com.huawei.hihealth.ICommonListener;
import com.huawei.hihealth.IDataOperateListener;
import com.huawei.hihealth.IDataReadResultListener;
import com.huawei.hihealth.IReadCallback;
import com.huawei.hihealth.IRealTimeDataCallback;
import com.huawei.hihealth.IRegisterRealTimeCallback;
import com.huawei.hihealth.ISportDataCallback;
import com.huawei.hihealth.ISubScribeCallback;
import com.huawei.hihealth.IWriteCallback;
import com.huawei.hihealth.model.EventTypeInfo;
import com.huawei.hihealth.model.SubscribeModel;
import com.huawei.hihealth.model.Subscriber;
import com.huawei.hihealth.option.HiHealthCapabilityQuery;
import java.util.List;

/* loaded from: classes.dex */
public interface IHiHealthKit extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hihealth.IHiHealthKit";

    public static class Default implements IHiHealthKit {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void bindDevice(String str, String str2, String str3, int i, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void connectSportDevice(int i, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void deleteSample(int i, HiHealthKitData hiHealthKitData, IDataOperateListener iDataOperateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void deleteSamples(int i, List list, IDataOperateListener iDataOperateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void execQuery(int i, HiHealthDataQuery hiHealthDataQuery, int i2, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void getBirthday(int i, ICommonListener iCommonListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public String getCategory(int i) throws RemoteException {
            return null;
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void getCount(int i, HiHealthDataQuery hiHealthDataQuery, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void getDataAuthStatus(int i, int i2, IDataOperateListener iDataOperateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void getDataAuthStatusEx(int i, int[] iArr, int[] iArr2, IBaseCallback iBaseCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void getDeviceList(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void getGender(int i, ICommonListener iCommonListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void getHealthyLivingData(int i, HiHealthCapabilityQuery hiHealthCapabilityQuery, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void getHeight(int i, ICommonListener iCommonListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public int getServiceApiLevel() throws RemoteException {
            return 0;
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void getSwitch(int i, String str, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void getUserInfo(List<String> list, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void getUserPreference(List<String> list, IDataOperateListener iDataOperateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void getWeight(int i, ICommonListener iCommonListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void pauseSport(ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public String preRequestAuth(int i, int[] iArr, int[] iArr2, IBaseCallback iBaseCallback) throws RemoteException {
            return null;
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void pushMsgToWearable(String str, String str2, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void queryData(HealthKitDictQuery healthKitDictQuery, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void querySleepWakeTime(int i, HiHealthDataQuery hiHealthDataQuery, int i2, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void queryTrends(TrendQuery trendQuery, int i, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void readFromWearable(String str, String str2, IReadCallback iReadCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void registerDataAutoReport(DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void registerPackageName(String str) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void registerRealTimeSportCallback(ISportDataCallback iSportDataCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void requestAuthorization(int i, int[] iArr, int[] iArr2, IBaseCallback iBaseCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void resumeSport(ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void saveSample(int i, HiHealthKitData hiHealthKitData, IDataOperateListener iDataOperateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void saveSamples(int i, List<HiHealthKitData> list, IDataOperateListener iDataOperateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void sendDeviceCommand(int i, String str, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void sendDeviceControlinstruction(String str, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void setBinder(IBinder iBinder) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void setKitVersion(String str) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void setUserPreference(HiUserPreferenceData hiUserPreferenceData, boolean z, IDataOperateListener iDataOperateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void startReadingAtrial(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void startReadingHeartRate(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void startReadingRRI(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void startSport(int i, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void startSportEnhance(StartSportParam startSportParam, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void stopReadingAtrial(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void stopReadingHeartRate(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void stopReadingRRI(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void stopSport(ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void subscribeData(SubscribeModel subscribeModel, ISubScribeCallback iSubScribeCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void subscribeDataEx(Subscriber subscriber, EventTypeInfo eventTypeInfo, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void syncData(int[] iArr, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void unSubscribeData(SubscribeModel subscribeModel, ISubScribeCallback iSubScribeCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void unSubscribeDataEx(Subscriber subscriber, EventTypeInfo eventTypeInfo, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void unregisterDataAutoReport(DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void unregisterRealTimeSportCallback(ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKit
        public void writeToWearable(String str, String str2, byte[] bArr, String str3, IWriteCallback iWriteCallback) throws RemoteException {
        }
    }

    void bindDevice(String str, String str2, String str3, int i, ICommonCallback iCommonCallback) throws RemoteException;

    void connectSportDevice(int i, ICommonCallback iCommonCallback) throws RemoteException;

    void deleteSample(int i, HiHealthKitData hiHealthKitData, IDataOperateListener iDataOperateListener) throws RemoteException;

    void deleteSamples(int i, List list, IDataOperateListener iDataOperateListener) throws RemoteException;

    void execQuery(int i, HiHealthDataQuery hiHealthDataQuery, int i2, IDataReadResultListener iDataReadResultListener) throws RemoteException;

    void getBirthday(int i, ICommonListener iCommonListener) throws RemoteException;

    String getCategory(int i) throws RemoteException;

    void getCount(int i, HiHealthDataQuery hiHealthDataQuery, IDataReadResultListener iDataReadResultListener) throws RemoteException;

    void getDataAuthStatus(int i, int i2, IDataOperateListener iDataOperateListener) throws RemoteException;

    void getDataAuthStatusEx(int i, int[] iArr, int[] iArr2, IBaseCallback iBaseCallback) throws RemoteException;

    void getDeviceList(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException;

    void getGender(int i, ICommonListener iCommonListener) throws RemoteException;

    void getHealthyLivingData(int i, HiHealthCapabilityQuery hiHealthCapabilityQuery, ICommonCallback iCommonCallback) throws RemoteException;

    void getHeight(int i, ICommonListener iCommonListener) throws RemoteException;

    int getServiceApiLevel() throws RemoteException;

    void getSwitch(int i, String str, ICommonCallback iCommonCallback) throws RemoteException;

    void getUserInfo(List<String> list, ICommonCallback iCommonCallback) throws RemoteException;

    void getUserPreference(List<String> list, IDataOperateListener iDataOperateListener) throws RemoteException;

    void getWeight(int i, ICommonListener iCommonListener) throws RemoteException;

    void pauseSport(ICommonCallback iCommonCallback) throws RemoteException;

    String preRequestAuth(int i, int[] iArr, int[] iArr2, IBaseCallback iBaseCallback) throws RemoteException;

    void pushMsgToWearable(String str, String str2, ICommonCallback iCommonCallback) throws RemoteException;

    void queryData(HealthKitDictQuery healthKitDictQuery, IDataReadResultListener iDataReadResultListener) throws RemoteException;

    void querySleepWakeTime(int i, HiHealthDataQuery hiHealthDataQuery, int i2, IDataReadResultListener iDataReadResultListener) throws RemoteException;

    void queryTrends(TrendQuery trendQuery, int i, IDataReadResultListener iDataReadResultListener) throws RemoteException;

    void readFromWearable(String str, String str2, IReadCallback iReadCallback) throws RemoteException;

    void registerDataAutoReport(DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) throws RemoteException;

    void registerPackageName(String str) throws RemoteException;

    void registerRealTimeSportCallback(ISportDataCallback iSportDataCallback) throws RemoteException;

    void requestAuthorization(int i, int[] iArr, int[] iArr2, IBaseCallback iBaseCallback) throws RemoteException;

    void resumeSport(ICommonCallback iCommonCallback) throws RemoteException;

    void saveSample(int i, HiHealthKitData hiHealthKitData, IDataOperateListener iDataOperateListener) throws RemoteException;

    void saveSamples(int i, List<HiHealthKitData> list, IDataOperateListener iDataOperateListener) throws RemoteException;

    void sendDeviceCommand(int i, String str, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException;

    void sendDeviceControlinstruction(String str, ICommonCallback iCommonCallback) throws RemoteException;

    void setBinder(IBinder iBinder) throws RemoteException;

    void setKitVersion(String str) throws RemoteException;

    void setUserPreference(HiUserPreferenceData hiUserPreferenceData, boolean z, IDataOperateListener iDataOperateListener) throws RemoteException;

    void startReadingAtrial(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException;

    void startReadingHeartRate(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException;

    void startReadingRRI(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException;

    void startSport(int i, ICommonCallback iCommonCallback) throws RemoteException;

    void startSportEnhance(StartSportParam startSportParam, ICommonCallback iCommonCallback) throws RemoteException;

    void stopReadingAtrial(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException;

    void stopReadingHeartRate(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException;

    void stopReadingRRI(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException;

    void stopSport(ICommonCallback iCommonCallback) throws RemoteException;

    void subscribeData(SubscribeModel subscribeModel, ISubScribeCallback iSubScribeCallback) throws RemoteException;

    void subscribeDataEx(Subscriber subscriber, EventTypeInfo eventTypeInfo, ICommonCallback iCommonCallback) throws RemoteException;

    void syncData(int[] iArr, ICommonCallback iCommonCallback) throws RemoteException;

    void unSubscribeData(SubscribeModel subscribeModel, ISubScribeCallback iSubScribeCallback) throws RemoteException;

    void unSubscribeDataEx(Subscriber subscriber, EventTypeInfo eventTypeInfo, ICommonCallback iCommonCallback) throws RemoteException;

    void unregisterDataAutoReport(DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) throws RemoteException;

    void unregisterRealTimeSportCallback(ICommonCallback iCommonCallback) throws RemoteException;

    void writeToWearable(String str, String str2, byte[] bArr, String str3, IWriteCallback iWriteCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IHiHealthKit {
        static final int TRANSACTION_bindDevice = 41;
        static final int TRANSACTION_connectSportDevice = 54;
        static final int TRANSACTION_deleteSample = 34;
        static final int TRANSACTION_deleteSamples = 8;
        static final int TRANSACTION_execQuery = 5;
        static final int TRANSACTION_getBirthday = 2;
        static final int TRANSACTION_getCategory = 37;
        static final int TRANSACTION_getCount = 6;
        static final int TRANSACTION_getDataAuthStatus = 10;
        static final int TRANSACTION_getDataAuthStatusEx = 29;
        static final int TRANSACTION_getDeviceList = 15;
        static final int TRANSACTION_getGender = 1;
        static final int TRANSACTION_getHealthyLivingData = 33;
        static final int TRANSACTION_getHeight = 3;
        static final int TRANSACTION_getServiceApiLevel = 32;
        static final int TRANSACTION_getSwitch = 24;
        static final int TRANSACTION_getUserInfo = 43;
        static final int TRANSACTION_getUserPreference = 45;
        static final int TRANSACTION_getWeight = 4;
        static final int TRANSACTION_pauseSport = 51;
        static final int TRANSACTION_preRequestAuth = 35;
        static final int TRANSACTION_pushMsgToWearable = 21;
        static final int TRANSACTION_queryData = 40;
        static final int TRANSACTION_querySleepWakeTime = 25;
        static final int TRANSACTION_queryTrends = 55;
        static final int TRANSACTION_readFromWearable = 20;
        static final int TRANSACTION_registerDataAutoReport = 38;
        static final int TRANSACTION_registerPackageName = 31;
        static final int TRANSACTION_registerRealTimeSportCallback = 22;
        static final int TRANSACTION_requestAuthorization = 9;
        static final int TRANSACTION_resumeSport = 52;
        static final int TRANSACTION_saveSample = 7;
        static final int TRANSACTION_saveSamples = 36;
        static final int TRANSACTION_sendDeviceCommand = 16;
        static final int TRANSACTION_sendDeviceControlinstruction = 50;
        static final int TRANSACTION_setBinder = 28;
        static final int TRANSACTION_setKitVersion = 30;
        static final int TRANSACTION_setUserPreference = 44;
        static final int TRANSACTION_startReadingAtrial = 17;
        static final int TRANSACTION_startReadingHeartRate = 11;
        static final int TRANSACTION_startReadingRRI = 13;
        static final int TRANSACTION_startSport = 26;
        static final int TRANSACTION_startSportEnhance = 42;
        static final int TRANSACTION_stopReadingAtrial = 18;
        static final int TRANSACTION_stopReadingHeartRate = 12;
        static final int TRANSACTION_stopReadingRRI = 14;
        static final int TRANSACTION_stopSport = 27;
        static final int TRANSACTION_subscribeData = 46;
        static final int TRANSACTION_subscribeDataEx = 48;
        static final int TRANSACTION_syncData = 53;
        static final int TRANSACTION_unSubscribeData = 47;
        static final int TRANSACTION_unSubscribeDataEx = 49;
        static final int TRANSACTION_unregisterDataAutoReport = 39;
        static final int TRANSACTION_unregisterRealTimeSportCallback = 23;
        static final int TRANSACTION_writeToWearable = 19;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IHiHealthKit.DESCRIPTOR);
        }

        public static IHiHealthKit asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IHiHealthKit.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IHiHealthKit)) {
                return (IHiHealthKit) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IHiHealthKit.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IHiHealthKit.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    getGender(parcel.readInt(), ICommonListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    getBirthday(parcel.readInt(), ICommonListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    getHeight(parcel.readInt(), ICommonListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    getWeight(parcel.readInt(), ICommonListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    execQuery(parcel.readInt(), (HiHealthDataQuery) _Parcel.bvo_(parcel, HiHealthDataQuery.CREATOR), parcel.readInt(), IDataReadResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    getCount(parcel.readInt(), (HiHealthDataQuery) _Parcel.bvo_(parcel, HiHealthDataQuery.CREATOR), IDataReadResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    saveSample(parcel.readInt(), (HiHealthKitData) _Parcel.bvo_(parcel, HiHealthKitData.CREATOR), IDataOperateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    deleteSamples(parcel.readInt(), parcel.readArrayList(getClass().getClassLoader()), IDataOperateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    requestAuthorization(parcel.readInt(), parcel.createIntArray(), parcel.createIntArray(), IBaseCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    getDataAuthStatus(parcel.readInt(), parcel.readInt(), IDataOperateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 11:
                    startReadingHeartRate(parcel.readInt(), IRealTimeDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 12:
                    stopReadingHeartRate(parcel.readInt(), IRealTimeDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 13:
                    startReadingRRI(parcel.readInt(), IRealTimeDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 14:
                    stopReadingRRI(parcel.readInt(), IRealTimeDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 15:
                    getDeviceList(parcel.readInt(), IRealTimeDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 16:
                    sendDeviceCommand(parcel.readInt(), parcel.readString(), IRealTimeDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 17:
                    startReadingAtrial(parcel.readInt(), IRealTimeDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 18:
                    stopReadingAtrial(parcel.readInt(), IRealTimeDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 19:
                    writeToWearable(parcel.readString(), parcel.readString(), parcel.createByteArray(), parcel.readString(), IWriteCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 20:
                    readFromWearable(parcel.readString(), parcel.readString(), IReadCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 21:
                    pushMsgToWearable(parcel.readString(), parcel.readString(), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 22:
                    registerRealTimeSportCallback(ISportDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 23:
                    unregisterRealTimeSportCallback(ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 24:
                    getSwitch(parcel.readInt(), parcel.readString(), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 25:
                    querySleepWakeTime(parcel.readInt(), (HiHealthDataQuery) _Parcel.bvo_(parcel, HiHealthDataQuery.CREATOR), parcel.readInt(), IDataReadResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 26:
                    startSport(parcel.readInt(), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 27:
                    stopSport(ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 28:
                    setBinder(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 29:
                    getDataAuthStatusEx(parcel.readInt(), parcel.createIntArray(), parcel.createIntArray(), IBaseCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 30:
                    setKitVersion(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 31:
                    registerPackageName(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 32:
                    int serviceApiLevel = getServiceApiLevel();
                    parcel2.writeNoException();
                    parcel2.writeInt(serviceApiLevel);
                    return true;
                case 33:
                    getHealthyLivingData(parcel.readInt(), (HiHealthCapabilityQuery) _Parcel.bvo_(parcel, HiHealthCapabilityQuery.CREATOR), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 34:
                    deleteSample(parcel.readInt(), (HiHealthKitData) _Parcel.bvo_(parcel, HiHealthKitData.CREATOR), IDataOperateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 35:
                    String preRequestAuth = preRequestAuth(parcel.readInt(), parcel.createIntArray(), parcel.createIntArray(), IBaseCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeString(preRequestAuth);
                    return true;
                case 36:
                    saveSamples(parcel.readInt(), parcel.createTypedArrayList(HiHealthKitData.CREATOR), IDataOperateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 37:
                    String category = getCategory(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(category);
                    return true;
                case 38:
                    registerDataAutoReport((DataReportModel) _Parcel.bvo_(parcel, DataReportModel.CREATOR), IRegisterRealTimeCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 39:
                    unregisterDataAutoReport((DataReportModel) _Parcel.bvo_(parcel, DataReportModel.CREATOR), IRegisterRealTimeCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 40:
                    queryData((HealthKitDictQuery) _Parcel.bvo_(parcel, HealthKitDictQuery.CREATOR), IDataReadResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 41:
                    bindDevice(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 42:
                    startSportEnhance((StartSportParam) _Parcel.bvo_(parcel, StartSportParam.CREATOR), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 43:
                    getUserInfo(parcel.createStringArrayList(), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 44:
                    setUserPreference((HiUserPreferenceData) _Parcel.bvo_(parcel, HiUserPreferenceData.CREATOR), parcel.readInt() != 0, IDataOperateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 45:
                    getUserPreference(parcel.createStringArrayList(), IDataOperateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 46:
                    subscribeData((SubscribeModel) _Parcel.bvo_(parcel, SubscribeModel.CREATOR), ISubScribeCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 47:
                    unSubscribeData((SubscribeModel) _Parcel.bvo_(parcel, SubscribeModel.CREATOR), ISubScribeCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 48:
                    subscribeDataEx((Subscriber) _Parcel.bvo_(parcel, Subscriber.CREATOR), (EventTypeInfo) _Parcel.bvo_(parcel, EventTypeInfo.CREATOR), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 49:
                    unSubscribeDataEx((Subscriber) _Parcel.bvo_(parcel, Subscriber.CREATOR), (EventTypeInfo) _Parcel.bvo_(parcel, EventTypeInfo.CREATOR), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 50:
                    sendDeviceControlinstruction(parcel.readString(), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 51:
                    pauseSport(ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 52:
                    resumeSport(ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 53:
                    syncData(parcel.createIntArray(), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 54:
                    connectSportDevice(parcel.readInt(), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 55:
                    queryTrends((TrendQuery) _Parcel.bvo_(parcel, TrendQuery.CREATOR), parcel.readInt(), IDataReadResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class Proxy implements IHiHealthKit {
            private IBinder d;

            Proxy(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void getGender(int i, ICommonListener iCommonListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iCommonListener);
                    this.d.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void getBirthday(int i, ICommonListener iCommonListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iCommonListener);
                    this.d.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void getHeight(int i, ICommonListener iCommonListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iCommonListener);
                    this.d.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void getWeight(int i, ICommonListener iCommonListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iCommonListener);
                    this.d.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void execQuery(int i, HiHealthDataQuery hiHealthDataQuery, int i2, IDataReadResultListener iDataReadResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.bvq_(obtain, hiHealthDataQuery, 0);
                    obtain.writeInt(i2);
                    obtain.writeStrongInterface(iDataReadResultListener);
                    this.d.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void getCount(int i, HiHealthDataQuery hiHealthDataQuery, IDataReadResultListener iDataReadResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.bvq_(obtain, hiHealthDataQuery, 0);
                    obtain.writeStrongInterface(iDataReadResultListener);
                    this.d.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void saveSample(int i, HiHealthKitData hiHealthKitData, IDataOperateListener iDataOperateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.bvq_(obtain, hiHealthKitData, 0);
                    obtain.writeStrongInterface(iDataOperateListener);
                    this.d.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void deleteSamples(int i, List list, IDataOperateListener iDataOperateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeList(list);
                    obtain.writeStrongInterface(iDataOperateListener);
                    this.d.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void requestAuthorization(int i, int[] iArr, int[] iArr2, IBaseCallback iBaseCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeIntArray(iArr);
                    obtain.writeIntArray(iArr2);
                    obtain.writeStrongInterface(iBaseCallback);
                    this.d.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void getDataAuthStatus(int i, int i2, IDataOperateListener iDataOperateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeStrongInterface(iDataOperateListener);
                    this.d.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void startReadingHeartRate(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iRealTimeDataCallback);
                    this.d.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void stopReadingHeartRate(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iRealTimeDataCallback);
                    this.d.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void startReadingRRI(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iRealTimeDataCallback);
                    this.d.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void stopReadingRRI(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iRealTimeDataCallback);
                    this.d.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void getDeviceList(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iRealTimeDataCallback);
                    this.d.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void sendDeviceCommand(int i, String str, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iRealTimeDataCallback);
                    this.d.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void startReadingAtrial(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iRealTimeDataCallback);
                    this.d.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void stopReadingAtrial(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iRealTimeDataCallback);
                    this.d.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void writeToWearable(String str, String str2, byte[] bArr, String str3, IWriteCallback iWriteCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeByteArray(bArr);
                    obtain.writeString(str3);
                    obtain.writeStrongInterface(iWriteCallback);
                    this.d.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void readFromWearable(String str, String str2, IReadCallback iReadCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongInterface(iReadCallback);
                    this.d.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void pushMsgToWearable(String str, String str2, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.d.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void registerRealTimeSportCallback(ISportDataCallback iSportDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeStrongInterface(iSportDataCallback);
                    this.d.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void unregisterRealTimeSportCallback(ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.d.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void getSwitch(int i, String str, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.d.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void querySleepWakeTime(int i, HiHealthDataQuery hiHealthDataQuery, int i2, IDataReadResultListener iDataReadResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.bvq_(obtain, hiHealthDataQuery, 0);
                    obtain.writeInt(i2);
                    obtain.writeStrongInterface(iDataReadResultListener);
                    this.d.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void startSport(int i, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.d.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void stopSport(ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.d.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void setBinder(IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    this.d.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void getDataAuthStatusEx(int i, int[] iArr, int[] iArr2, IBaseCallback iBaseCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeIntArray(iArr);
                    obtain.writeIntArray(iArr2);
                    obtain.writeStrongInterface(iBaseCallback);
                    this.d.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void setKitVersion(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeString(str);
                    this.d.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void registerPackageName(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeString(str);
                    this.d.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public int getServiceApiLevel() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    this.d.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void getHealthyLivingData(int i, HiHealthCapabilityQuery hiHealthCapabilityQuery, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.bvq_(obtain, hiHealthCapabilityQuery, 0);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.d.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void deleteSample(int i, HiHealthKitData hiHealthKitData, IDataOperateListener iDataOperateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.bvq_(obtain, hiHealthKitData, 0);
                    obtain.writeStrongInterface(iDataOperateListener);
                    this.d.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public String preRequestAuth(int i, int[] iArr, int[] iArr2, IBaseCallback iBaseCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeIntArray(iArr);
                    obtain.writeIntArray(iArr2);
                    obtain.writeStrongInterface(iBaseCallback);
                    this.d.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void saveSamples(int i, List<HiHealthKitData> list, IDataOperateListener iDataOperateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.bvp_(obtain, list, 0);
                    obtain.writeStrongInterface(iDataOperateListener);
                    this.d.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public String getCategory(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.d.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void registerDataAutoReport(DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    _Parcel.bvq_(obtain, dataReportModel, 0);
                    obtain.writeStrongInterface(iRegisterRealTimeCallback);
                    this.d.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void unregisterDataAutoReport(DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    _Parcel.bvq_(obtain, dataReportModel, 0);
                    obtain.writeStrongInterface(iRegisterRealTimeCallback);
                    this.d.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void queryData(HealthKitDictQuery healthKitDictQuery, IDataReadResultListener iDataReadResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    _Parcel.bvq_(obtain, healthKitDictQuery, 0);
                    obtain.writeStrongInterface(iDataReadResultListener);
                    this.d.transact(40, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void bindDevice(String str, String str2, String str3, int i, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.d.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void startSportEnhance(StartSportParam startSportParam, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    _Parcel.bvq_(obtain, startSportParam, 0);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.d.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void getUserInfo(List<String> list, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeStringList(list);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.d.transact(43, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void setUserPreference(HiUserPreferenceData hiUserPreferenceData, boolean z, IDataOperateListener iDataOperateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    _Parcel.bvq_(obtain, hiUserPreferenceData, 0);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeStrongInterface(iDataOperateListener);
                    this.d.transact(44, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void getUserPreference(List<String> list, IDataOperateListener iDataOperateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeStringList(list);
                    obtain.writeStrongInterface(iDataOperateListener);
                    this.d.transact(45, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void subscribeData(SubscribeModel subscribeModel, ISubScribeCallback iSubScribeCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    _Parcel.bvq_(obtain, subscribeModel, 0);
                    obtain.writeStrongInterface(iSubScribeCallback);
                    this.d.transact(46, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void unSubscribeData(SubscribeModel subscribeModel, ISubScribeCallback iSubScribeCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    _Parcel.bvq_(obtain, subscribeModel, 0);
                    obtain.writeStrongInterface(iSubScribeCallback);
                    this.d.transact(47, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void subscribeDataEx(Subscriber subscriber, EventTypeInfo eventTypeInfo, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    _Parcel.bvq_(obtain, subscriber, 0);
                    _Parcel.bvq_(obtain, eventTypeInfo, 0);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.d.transact(48, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void unSubscribeDataEx(Subscriber subscriber, EventTypeInfo eventTypeInfo, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    _Parcel.bvq_(obtain, subscriber, 0);
                    _Parcel.bvq_(obtain, eventTypeInfo, 0);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.d.transact(49, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void sendDeviceControlinstruction(String str, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.d.transact(50, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void pauseSport(ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.d.transact(51, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void resumeSport(ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.d.transact(52, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void syncData(int[] iArr, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeIntArray(iArr);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.d.transact(53, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void connectSportDevice(int i, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.d.transact(54, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKit
            public void queryTrends(TrendQuery trendQuery, int i, IDataReadResultListener iDataReadResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKit.DESCRIPTOR);
                    _Parcel.bvq_(obtain, trendQuery, 0);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iDataReadResultListener);
                    this.d.transact(55, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class _Parcel {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T bvo_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bvq_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bvp_(Parcel parcel, List<T> list, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                bvq_(parcel, list.get(i2), i);
            }
        }
    }
}
