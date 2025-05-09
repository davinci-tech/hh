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
import com.huawei.hihealth.model.SubscribeModel;
import com.huawei.hihealth.option.HiHealthCapabilityQuery;
import com.huawei.hihealthkit.context.OutOfBandData;
import java.util.List;

/* loaded from: classes.dex */
public interface IHiHealthKitEx extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hihealth.IHiHealthKitEx";

    public static class Default implements IHiHealthKitEx {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void connectSportDevice(OutOfBandData outOfBandData, int i, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void deleteSample(OutOfBandData outOfBandData, HiHealthKitData hiHealthKitData, IDataOperateListener iDataOperateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void deleteSamples(OutOfBandData outOfBandData, List list, IDataOperateListener iDataOperateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void execAggregateQuery(OutOfBandData outOfBandData, HiHealthAggregateQuery hiHealthAggregateQuery, int i, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void execQuery(OutOfBandData outOfBandData, HiHealthDataQuery hiHealthDataQuery, int i, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public int getAbilityVersion(OutOfBandData outOfBandData) throws RemoteException {
            return 0;
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void getBirthday(OutOfBandData outOfBandData, ICommonListener iCommonListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public String getCategory(int i) throws RemoteException {
            return null;
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void getCount(OutOfBandData outOfBandData, HiHealthDataQuery hiHealthDataQuery, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void getDataAuthStatus(OutOfBandData outOfBandData, int i, IDataOperateListener iDataOperateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void getDataAuthStatusEx(OutOfBandData outOfBandData, int[] iArr, int[] iArr2, IBaseCallback iBaseCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void getDeviceList(OutOfBandData outOfBandData, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void getGender(OutOfBandData outOfBandData, ICommonListener iCommonListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void getHealthyLivingData(OutOfBandData outOfBandData, HiHealthCapabilityQuery hiHealthCapabilityQuery, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void getHeight(OutOfBandData outOfBandData, ICommonListener iCommonListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public int getServiceApiLevel(OutOfBandData outOfBandData) throws RemoteException {
            return 0;
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void getSwitch(OutOfBandData outOfBandData, String str, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void getUserInfo(OutOfBandData outOfBandData, List<String> list, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void getUserPreference(OutOfBandData outOfBandData, List<String> list, IDataOperateListener iDataOperateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void getWeight(OutOfBandData outOfBandData, ICommonListener iCommonListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void pauseSport(OutOfBandData outOfBandData, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void pushMsgToWearable(OutOfBandData outOfBandData, String str, String str2, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void queryData(OutOfBandData outOfBandData, HealthKitDictQuery healthKitDictQuery, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void querySleepWakeTime(OutOfBandData outOfBandData, HiHealthDataQuery hiHealthDataQuery, int i, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void queryTrends(OutOfBandData outOfBandData, TrendQuery trendQuery, int i, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void readFromWearable(OutOfBandData outOfBandData, String str, String str2, IReadCallback iReadCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void registerDataAutoReport(OutOfBandData outOfBandData, DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void registerPackageName(String str) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void registerRealTimeSportCallback(OutOfBandData outOfBandData, ISportDataCallback iSportDataCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void requestAuthorization(OutOfBandData outOfBandData, int[] iArr, int[] iArr2, IBaseCallback iBaseCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void resumeSport(OutOfBandData outOfBandData, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void saveSample(OutOfBandData outOfBandData, HiHealthKitData hiHealthKitData, IDataOperateListener iDataOperateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void saveSamples(OutOfBandData outOfBandData, List list, IDataOperateListener iDataOperateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void sendDeviceCommand(OutOfBandData outOfBandData, String str, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void sendDeviceControlinstruction(OutOfBandData outOfBandData, String str, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void setKitVersion(String str) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void setUserPreference(OutOfBandData outOfBandData, HiUserPreferenceData hiUserPreferenceData, boolean z, IDataOperateListener iDataOperateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void startReadingAtrial(OutOfBandData outOfBandData, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void startReadingHeartRate(OutOfBandData outOfBandData, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void startReadingRRI(OutOfBandData outOfBandData, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void startSportEnhance(OutOfBandData outOfBandData, StartSportParam startSportParam, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void stopReadingAtrial(OutOfBandData outOfBandData, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void stopReadingHeartRate(OutOfBandData outOfBandData, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void stopReadingRRI(OutOfBandData outOfBandData, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void stopSport(OutOfBandData outOfBandData, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void subscribeData(OutOfBandData outOfBandData, SubscribeModel subscribeModel, ISubScribeCallback iSubScribeCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void syncData(OutOfBandData outOfBandData, int[] iArr, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void unSubscribeData(OutOfBandData outOfBandData, SubscribeModel subscribeModel, ISubScribeCallback iSubScribeCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void unregisterDataAutoReport(OutOfBandData outOfBandData, DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void unregisterRealTimeSportCallback(OutOfBandData outOfBandData, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitEx
        public void writeToWearable(OutOfBandData outOfBandData, String str, byte[] bArr, IWriteCallback iWriteCallback) throws RemoteException {
        }
    }

    void connectSportDevice(OutOfBandData outOfBandData, int i, ICommonCallback iCommonCallback) throws RemoteException;

    void deleteSample(OutOfBandData outOfBandData, HiHealthKitData hiHealthKitData, IDataOperateListener iDataOperateListener) throws RemoteException;

    void deleteSamples(OutOfBandData outOfBandData, List list, IDataOperateListener iDataOperateListener) throws RemoteException;

    void execAggregateQuery(OutOfBandData outOfBandData, HiHealthAggregateQuery hiHealthAggregateQuery, int i, IDataReadResultListener iDataReadResultListener) throws RemoteException;

    void execQuery(OutOfBandData outOfBandData, HiHealthDataQuery hiHealthDataQuery, int i, IDataReadResultListener iDataReadResultListener) throws RemoteException;

    int getAbilityVersion(OutOfBandData outOfBandData) throws RemoteException;

    void getBirthday(OutOfBandData outOfBandData, ICommonListener iCommonListener) throws RemoteException;

    String getCategory(int i) throws RemoteException;

    void getCount(OutOfBandData outOfBandData, HiHealthDataQuery hiHealthDataQuery, IDataReadResultListener iDataReadResultListener) throws RemoteException;

    void getDataAuthStatus(OutOfBandData outOfBandData, int i, IDataOperateListener iDataOperateListener) throws RemoteException;

    void getDataAuthStatusEx(OutOfBandData outOfBandData, int[] iArr, int[] iArr2, IBaseCallback iBaseCallback) throws RemoteException;

    void getDeviceList(OutOfBandData outOfBandData, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException;

    void getGender(OutOfBandData outOfBandData, ICommonListener iCommonListener) throws RemoteException;

    void getHealthyLivingData(OutOfBandData outOfBandData, HiHealthCapabilityQuery hiHealthCapabilityQuery, ICommonCallback iCommonCallback) throws RemoteException;

    void getHeight(OutOfBandData outOfBandData, ICommonListener iCommonListener) throws RemoteException;

    int getServiceApiLevel(OutOfBandData outOfBandData) throws RemoteException;

    void getSwitch(OutOfBandData outOfBandData, String str, ICommonCallback iCommonCallback) throws RemoteException;

    void getUserInfo(OutOfBandData outOfBandData, List<String> list, ICommonCallback iCommonCallback) throws RemoteException;

    void getUserPreference(OutOfBandData outOfBandData, List<String> list, IDataOperateListener iDataOperateListener) throws RemoteException;

    void getWeight(OutOfBandData outOfBandData, ICommonListener iCommonListener) throws RemoteException;

    void pauseSport(OutOfBandData outOfBandData, ICommonCallback iCommonCallback) throws RemoteException;

    void pushMsgToWearable(OutOfBandData outOfBandData, String str, String str2, ICommonCallback iCommonCallback) throws RemoteException;

    void queryData(OutOfBandData outOfBandData, HealthKitDictQuery healthKitDictQuery, IDataReadResultListener iDataReadResultListener) throws RemoteException;

    void querySleepWakeTime(OutOfBandData outOfBandData, HiHealthDataQuery hiHealthDataQuery, int i, IDataReadResultListener iDataReadResultListener) throws RemoteException;

    void queryTrends(OutOfBandData outOfBandData, TrendQuery trendQuery, int i, IDataReadResultListener iDataReadResultListener) throws RemoteException;

    void readFromWearable(OutOfBandData outOfBandData, String str, String str2, IReadCallback iReadCallback) throws RemoteException;

    void registerDataAutoReport(OutOfBandData outOfBandData, DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) throws RemoteException;

    void registerPackageName(String str) throws RemoteException;

    void registerRealTimeSportCallback(OutOfBandData outOfBandData, ISportDataCallback iSportDataCallback) throws RemoteException;

    void requestAuthorization(OutOfBandData outOfBandData, int[] iArr, int[] iArr2, IBaseCallback iBaseCallback) throws RemoteException;

    void resumeSport(OutOfBandData outOfBandData, ICommonCallback iCommonCallback) throws RemoteException;

    void saveSample(OutOfBandData outOfBandData, HiHealthKitData hiHealthKitData, IDataOperateListener iDataOperateListener) throws RemoteException;

    void saveSamples(OutOfBandData outOfBandData, List list, IDataOperateListener iDataOperateListener) throws RemoteException;

    void sendDeviceCommand(OutOfBandData outOfBandData, String str, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException;

    void sendDeviceControlinstruction(OutOfBandData outOfBandData, String str, ICommonCallback iCommonCallback) throws RemoteException;

    void setKitVersion(String str) throws RemoteException;

    void setUserPreference(OutOfBandData outOfBandData, HiUserPreferenceData hiUserPreferenceData, boolean z, IDataOperateListener iDataOperateListener) throws RemoteException;

    void startReadingAtrial(OutOfBandData outOfBandData, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException;

    void startReadingHeartRate(OutOfBandData outOfBandData, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException;

    void startReadingRRI(OutOfBandData outOfBandData, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException;

    void startSportEnhance(OutOfBandData outOfBandData, StartSportParam startSportParam, ICommonCallback iCommonCallback) throws RemoteException;

    void stopReadingAtrial(OutOfBandData outOfBandData, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException;

    void stopReadingHeartRate(OutOfBandData outOfBandData, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException;

    void stopReadingRRI(OutOfBandData outOfBandData, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException;

    void stopSport(OutOfBandData outOfBandData, ICommonCallback iCommonCallback) throws RemoteException;

    void subscribeData(OutOfBandData outOfBandData, SubscribeModel subscribeModel, ISubScribeCallback iSubScribeCallback) throws RemoteException;

    void syncData(OutOfBandData outOfBandData, int[] iArr, ICommonCallback iCommonCallback) throws RemoteException;

    void unSubscribeData(OutOfBandData outOfBandData, SubscribeModel subscribeModel, ISubScribeCallback iSubScribeCallback) throws RemoteException;

    void unregisterDataAutoReport(OutOfBandData outOfBandData, DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) throws RemoteException;

    void unregisterRealTimeSportCallback(OutOfBandData outOfBandData, ICommonCallback iCommonCallback) throws RemoteException;

    void writeToWearable(OutOfBandData outOfBandData, String str, byte[] bArr, IWriteCallback iWriteCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IHiHealthKitEx {
        static final int TRANSACTION_connectSportDevice = 50;
        static final int TRANSACTION_deleteSample = 33;
        static final int TRANSACTION_deleteSamples = 8;
        static final int TRANSACTION_execAggregateQuery = 26;
        static final int TRANSACTION_execQuery = 5;
        static final int TRANSACTION_getAbilityVersion = 28;
        static final int TRANSACTION_getBirthday = 2;
        static final int TRANSACTION_getCategory = 35;
        static final int TRANSACTION_getCount = 6;
        static final int TRANSACTION_getDataAuthStatus = 10;
        static final int TRANSACTION_getDataAuthStatusEx = 29;
        static final int TRANSACTION_getDeviceList = 15;
        static final int TRANSACTION_getGender = 1;
        static final int TRANSACTION_getHealthyLivingData = 32;
        static final int TRANSACTION_getHeight = 3;
        static final int TRANSACTION_getServiceApiLevel = 34;
        static final int TRANSACTION_getSwitch = 24;
        static final int TRANSACTION_getUserInfo = 41;
        static final int TRANSACTION_getUserPreference = 43;
        static final int TRANSACTION_getWeight = 4;
        static final int TRANSACTION_pauseSport = 47;
        static final int TRANSACTION_pushMsgToWearable = 21;
        static final int TRANSACTION_queryData = 36;
        static final int TRANSACTION_querySleepWakeTime = 25;
        static final int TRANSACTION_queryTrends = 51;
        static final int TRANSACTION_readFromWearable = 20;
        static final int TRANSACTION_registerDataAutoReport = 37;
        static final int TRANSACTION_registerPackageName = 31;
        static final int TRANSACTION_registerRealTimeSportCallback = 22;
        static final int TRANSACTION_requestAuthorization = 9;
        static final int TRANSACTION_resumeSport = 48;
        static final int TRANSACTION_saveSample = 7;
        static final int TRANSACTION_saveSamples = 27;
        static final int TRANSACTION_sendDeviceCommand = 16;
        static final int TRANSACTION_sendDeviceControlinstruction = 46;
        static final int TRANSACTION_setKitVersion = 30;
        static final int TRANSACTION_setUserPreference = 42;
        static final int TRANSACTION_startReadingAtrial = 17;
        static final int TRANSACTION_startReadingHeartRate = 11;
        static final int TRANSACTION_startReadingRRI = 13;
        static final int TRANSACTION_startSportEnhance = 39;
        static final int TRANSACTION_stopReadingAtrial = 18;
        static final int TRANSACTION_stopReadingHeartRate = 12;
        static final int TRANSACTION_stopReadingRRI = 14;
        static final int TRANSACTION_stopSport = 40;
        static final int TRANSACTION_subscribeData = 44;
        static final int TRANSACTION_syncData = 49;
        static final int TRANSACTION_unSubscribeData = 45;
        static final int TRANSACTION_unregisterDataAutoReport = 38;
        static final int TRANSACTION_unregisterRealTimeSportCallback = 23;
        static final int TRANSACTION_writeToWearable = 19;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IHiHealthKitEx.DESCRIPTOR);
        }

        public static IHiHealthKitEx asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IHiHealthKitEx.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IHiHealthKitEx)) {
                return (IHiHealthKitEx) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IHiHealthKitEx.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IHiHealthKitEx.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    getGender((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), ICommonListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    getBirthday((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), ICommonListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    getHeight((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), ICommonListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    getWeight((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), ICommonListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    execQuery((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), (HiHealthDataQuery) _Parcel.bvz_(parcel, HiHealthDataQuery.CREATOR), parcel.readInt(), IDataReadResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    getCount((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), (HiHealthDataQuery) _Parcel.bvz_(parcel, HiHealthDataQuery.CREATOR), IDataReadResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    saveSample((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), (HiHealthKitData) _Parcel.bvz_(parcel, HiHealthKitData.CREATOR), IDataOperateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    deleteSamples((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), parcel.readArrayList(getClass().getClassLoader()), IDataOperateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    requestAuthorization((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), parcel.createIntArray(), parcel.createIntArray(), IBaseCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    getDataAuthStatus((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), parcel.readInt(), IDataOperateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 11:
                    startReadingHeartRate((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), IRealTimeDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 12:
                    stopReadingHeartRate((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), IRealTimeDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 13:
                    startReadingRRI((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), IRealTimeDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 14:
                    stopReadingRRI((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), IRealTimeDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 15:
                    getDeviceList((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), IRealTimeDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 16:
                    sendDeviceCommand((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), parcel.readString(), IRealTimeDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 17:
                    startReadingAtrial((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), IRealTimeDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 18:
                    stopReadingAtrial((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), IRealTimeDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 19:
                    writeToWearable((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), parcel.readString(), parcel.createByteArray(), IWriteCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 20:
                    readFromWearable((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), parcel.readString(), parcel.readString(), IReadCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 21:
                    pushMsgToWearable((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), parcel.readString(), parcel.readString(), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 22:
                    registerRealTimeSportCallback((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), ISportDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 23:
                    unregisterRealTimeSportCallback((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 24:
                    getSwitch((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), parcel.readString(), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 25:
                    querySleepWakeTime((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), (HiHealthDataQuery) _Parcel.bvz_(parcel, HiHealthDataQuery.CREATOR), parcel.readInt(), IDataReadResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 26:
                    execAggregateQuery((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), (HiHealthAggregateQuery) _Parcel.bvz_(parcel, HiHealthAggregateQuery.CREATOR), parcel.readInt(), IDataReadResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 27:
                    saveSamples((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), parcel.readArrayList(getClass().getClassLoader()), IDataOperateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 28:
                    int abilityVersion = getAbilityVersion((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(abilityVersion);
                    return true;
                case 29:
                    getDataAuthStatusEx((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), parcel.createIntArray(), parcel.createIntArray(), IBaseCallback.Stub.asInterface(parcel.readStrongBinder()));
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
                    getHealthyLivingData((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), (HiHealthCapabilityQuery) _Parcel.bvz_(parcel, HiHealthCapabilityQuery.CREATOR), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 33:
                    deleteSample((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), (HiHealthKitData) _Parcel.bvz_(parcel, HiHealthKitData.CREATOR), IDataOperateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 34:
                    int serviceApiLevel = getServiceApiLevel((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(serviceApiLevel);
                    return true;
                case 35:
                    String category = getCategory(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(category);
                    return true;
                case 36:
                    queryData((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), (HealthKitDictQuery) _Parcel.bvz_(parcel, HealthKitDictQuery.CREATOR), IDataReadResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 37:
                    registerDataAutoReport((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), (DataReportModel) _Parcel.bvz_(parcel, DataReportModel.CREATOR), IRegisterRealTimeCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 38:
                    unregisterDataAutoReport((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), (DataReportModel) _Parcel.bvz_(parcel, DataReportModel.CREATOR), IRegisterRealTimeCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 39:
                    startSportEnhance((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), (StartSportParam) _Parcel.bvz_(parcel, StartSportParam.CREATOR), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 40:
                    stopSport((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 41:
                    getUserInfo((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), parcel.createStringArrayList(), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 42:
                    setUserPreference((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), (HiUserPreferenceData) _Parcel.bvz_(parcel, HiUserPreferenceData.CREATOR), parcel.readInt() != 0, IDataOperateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 43:
                    getUserPreference((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), parcel.createStringArrayList(), IDataOperateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 44:
                    subscribeData((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), (SubscribeModel) _Parcel.bvz_(parcel, SubscribeModel.CREATOR), ISubScribeCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 45:
                    unSubscribeData((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), (SubscribeModel) _Parcel.bvz_(parcel, SubscribeModel.CREATOR), ISubScribeCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 46:
                    sendDeviceControlinstruction((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), parcel.readString(), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 47:
                    pauseSport((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 48:
                    resumeSport((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 49:
                    syncData((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), parcel.createIntArray(), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 50:
                    connectSportDevice((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), parcel.readInt(), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 51:
                    queryTrends((OutOfBandData) _Parcel.bvz_(parcel, OutOfBandData.CREATOR), (TrendQuery) _Parcel.bvz_(parcel, TrendQuery.CREATOR), parcel.readInt(), IDataReadResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class Proxy implements IHiHealthKitEx {
            private IBinder e;

            Proxy(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void getGender(OutOfBandData outOfBandData, ICommonListener iCommonListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeStrongInterface(iCommonListener);
                    this.e.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void getBirthday(OutOfBandData outOfBandData, ICommonListener iCommonListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeStrongInterface(iCommonListener);
                    this.e.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void getHeight(OutOfBandData outOfBandData, ICommonListener iCommonListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeStrongInterface(iCommonListener);
                    this.e.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void getWeight(OutOfBandData outOfBandData, ICommonListener iCommonListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeStrongInterface(iCommonListener);
                    this.e.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void execQuery(OutOfBandData outOfBandData, HiHealthDataQuery hiHealthDataQuery, int i, IDataReadResultListener iDataReadResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    _Parcel.bvA_(obtain, hiHealthDataQuery, 0);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iDataReadResultListener);
                    this.e.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void getCount(OutOfBandData outOfBandData, HiHealthDataQuery hiHealthDataQuery, IDataReadResultListener iDataReadResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    _Parcel.bvA_(obtain, hiHealthDataQuery, 0);
                    obtain.writeStrongInterface(iDataReadResultListener);
                    this.e.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void saveSample(OutOfBandData outOfBandData, HiHealthKitData hiHealthKitData, IDataOperateListener iDataOperateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    _Parcel.bvA_(obtain, hiHealthKitData, 0);
                    obtain.writeStrongInterface(iDataOperateListener);
                    this.e.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void deleteSamples(OutOfBandData outOfBandData, List list, IDataOperateListener iDataOperateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeList(list);
                    obtain.writeStrongInterface(iDataOperateListener);
                    this.e.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void requestAuthorization(OutOfBandData outOfBandData, int[] iArr, int[] iArr2, IBaseCallback iBaseCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeIntArray(iArr);
                    obtain.writeIntArray(iArr2);
                    obtain.writeStrongInterface(iBaseCallback);
                    this.e.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void getDataAuthStatus(OutOfBandData outOfBandData, int i, IDataOperateListener iDataOperateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iDataOperateListener);
                    this.e.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void startReadingHeartRate(OutOfBandData outOfBandData, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeStrongInterface(iRealTimeDataCallback);
                    this.e.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void stopReadingHeartRate(OutOfBandData outOfBandData, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeStrongInterface(iRealTimeDataCallback);
                    this.e.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void startReadingRRI(OutOfBandData outOfBandData, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeStrongInterface(iRealTimeDataCallback);
                    this.e.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void stopReadingRRI(OutOfBandData outOfBandData, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeStrongInterface(iRealTimeDataCallback);
                    this.e.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void getDeviceList(OutOfBandData outOfBandData, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeStrongInterface(iRealTimeDataCallback);
                    this.e.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void sendDeviceCommand(OutOfBandData outOfBandData, String str, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iRealTimeDataCallback);
                    this.e.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void startReadingAtrial(OutOfBandData outOfBandData, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeStrongInterface(iRealTimeDataCallback);
                    this.e.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void stopReadingAtrial(OutOfBandData outOfBandData, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeStrongInterface(iRealTimeDataCallback);
                    this.e.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void writeToWearable(OutOfBandData outOfBandData, String str, byte[] bArr, IWriteCallback iWriteCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    obtain.writeStrongInterface(iWriteCallback);
                    this.e.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void readFromWearable(OutOfBandData outOfBandData, String str, String str2, IReadCallback iReadCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongInterface(iReadCallback);
                    this.e.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void pushMsgToWearable(OutOfBandData outOfBandData, String str, String str2, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.e.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void registerRealTimeSportCallback(OutOfBandData outOfBandData, ISportDataCallback iSportDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeStrongInterface(iSportDataCallback);
                    this.e.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void unregisterRealTimeSportCallback(OutOfBandData outOfBandData, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.e.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void getSwitch(OutOfBandData outOfBandData, String str, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.e.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void querySleepWakeTime(OutOfBandData outOfBandData, HiHealthDataQuery hiHealthDataQuery, int i, IDataReadResultListener iDataReadResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    _Parcel.bvA_(obtain, hiHealthDataQuery, 0);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iDataReadResultListener);
                    this.e.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void execAggregateQuery(OutOfBandData outOfBandData, HiHealthAggregateQuery hiHealthAggregateQuery, int i, IDataReadResultListener iDataReadResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    _Parcel.bvA_(obtain, hiHealthAggregateQuery, 0);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iDataReadResultListener);
                    this.e.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void saveSamples(OutOfBandData outOfBandData, List list, IDataOperateListener iDataOperateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeList(list);
                    obtain.writeStrongInterface(iDataOperateListener);
                    this.e.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public int getAbilityVersion(OutOfBandData outOfBandData) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    this.e.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void getDataAuthStatusEx(OutOfBandData outOfBandData, int[] iArr, int[] iArr2, IBaseCallback iBaseCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeIntArray(iArr);
                    obtain.writeIntArray(iArr2);
                    obtain.writeStrongInterface(iBaseCallback);
                    this.e.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void setKitVersion(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    obtain.writeString(str);
                    this.e.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void registerPackageName(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    obtain.writeString(str);
                    this.e.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void getHealthyLivingData(OutOfBandData outOfBandData, HiHealthCapabilityQuery hiHealthCapabilityQuery, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    _Parcel.bvA_(obtain, hiHealthCapabilityQuery, 0);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.e.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void deleteSample(OutOfBandData outOfBandData, HiHealthKitData hiHealthKitData, IDataOperateListener iDataOperateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    _Parcel.bvA_(obtain, hiHealthKitData, 0);
                    obtain.writeStrongInterface(iDataOperateListener);
                    this.e.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public int getServiceApiLevel(OutOfBandData outOfBandData) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    this.e.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public String getCategory(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.e.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void queryData(OutOfBandData outOfBandData, HealthKitDictQuery healthKitDictQuery, IDataReadResultListener iDataReadResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    _Parcel.bvA_(obtain, healthKitDictQuery, 0);
                    obtain.writeStrongInterface(iDataReadResultListener);
                    this.e.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void registerDataAutoReport(OutOfBandData outOfBandData, DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    _Parcel.bvA_(obtain, dataReportModel, 0);
                    obtain.writeStrongInterface(iRegisterRealTimeCallback);
                    this.e.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void unregisterDataAutoReport(OutOfBandData outOfBandData, DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    _Parcel.bvA_(obtain, dataReportModel, 0);
                    obtain.writeStrongInterface(iRegisterRealTimeCallback);
                    this.e.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void startSportEnhance(OutOfBandData outOfBandData, StartSportParam startSportParam, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    _Parcel.bvA_(obtain, startSportParam, 0);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.e.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void stopSport(OutOfBandData outOfBandData, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.e.transact(40, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void getUserInfo(OutOfBandData outOfBandData, List<String> list, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeStringList(list);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.e.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void setUserPreference(OutOfBandData outOfBandData, HiUserPreferenceData hiUserPreferenceData, boolean z, IDataOperateListener iDataOperateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    _Parcel.bvA_(obtain, hiUserPreferenceData, 0);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeStrongInterface(iDataOperateListener);
                    this.e.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void getUserPreference(OutOfBandData outOfBandData, List<String> list, IDataOperateListener iDataOperateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeStringList(list);
                    obtain.writeStrongInterface(iDataOperateListener);
                    this.e.transact(43, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void subscribeData(OutOfBandData outOfBandData, SubscribeModel subscribeModel, ISubScribeCallback iSubScribeCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    _Parcel.bvA_(obtain, subscribeModel, 0);
                    obtain.writeStrongInterface(iSubScribeCallback);
                    this.e.transact(44, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void unSubscribeData(OutOfBandData outOfBandData, SubscribeModel subscribeModel, ISubScribeCallback iSubScribeCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    _Parcel.bvA_(obtain, subscribeModel, 0);
                    obtain.writeStrongInterface(iSubScribeCallback);
                    this.e.transact(45, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void sendDeviceControlinstruction(OutOfBandData outOfBandData, String str, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.e.transact(46, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void pauseSport(OutOfBandData outOfBandData, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.e.transact(47, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void resumeSport(OutOfBandData outOfBandData, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.e.transact(48, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void syncData(OutOfBandData outOfBandData, int[] iArr, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeIntArray(iArr);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.e.transact(49, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void connectSportDevice(OutOfBandData outOfBandData, int i, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.e.transact(50, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitEx
            public void queryTrends(OutOfBandData outOfBandData, TrendQuery trendQuery, int i, IDataReadResultListener iDataReadResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitEx.DESCRIPTOR);
                    _Parcel.bvA_(obtain, outOfBandData, 0);
                    _Parcel.bvA_(obtain, trendQuery, 0);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iDataReadResultListener);
                    this.e.transact(51, obtain, obtain2, 0);
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
        public static <T> T bvz_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bvA_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
