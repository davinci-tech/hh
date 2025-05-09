package com.huawei.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.hihealth.IAggregateListener;
import com.huawei.hihealth.IAggregateListenerEx;
import com.huawei.hihealth.IAuthorizationListener;
import com.huawei.hihealth.ICommonCallback;
import com.huawei.hihealth.ICommonListener;
import com.huawei.hihealth.IDataClientListener;
import com.huawei.hihealth.IDataHiDeviceInfoListener;
import com.huawei.hihealth.IDataOperateListener;
import com.huawei.hihealth.IDataReadResultListener;
import com.huawei.hihealth.IDeleteListenerEx;
import com.huawei.hihealth.IMultiDataClientListener;
import com.huawei.hihealth.IRegisterClientListener;
import com.huawei.hihealth.ISubscribeListener;
import com.huawei.hihealth.ISupportedTypesListener;
import com.huawei.hihealth.IUnSubscribeListener;
import java.util.List;

/* loaded from: classes.dex */
public interface IHiHealth extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hihealth.IHiHealth";

    public static class Default implements IHiHealth {
        @Override // com.huawei.hihealth.IHiHealth
        public boolean addExternalSubscribeTrigger(int i, int i2, HiSubscribeTrigger hiSubscribeTrigger) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hihealth.IHiHealth
        public int addHiHealthDataCustomType(String str) throws RemoteException {
            return 0;
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void aggregateHiHealthData(HiAggregateOption hiAggregateOption, IAggregateListener iAggregateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void aggregateHiHealthDataEx(List list, IAggregateListenerEx iAggregateListenerEx) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void aggregateHiHealthDataPro(HiDataAggregateProOption hiDataAggregateProOption, IAggregateListener iAggregateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void aggregateHiHealthDataProEx(List list, IAggregateListenerEx iAggregateListenerEx) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void aggregateSportStatData(HiSportStatDataAggregateOption hiSportStatDataAggregateOption, IAggregateListener iAggregateListener) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void checkDataStatus(List list, ICommonListener iCommonListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public boolean checkHiHealthLogin(String str) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void deleteAllKitHealthData(int i, IDataOperateListener iDataOperateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void deleteHiHealthData(HiDataDeleteOption hiDataDeleteOption, IDataOperateListener iDataOperateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void deleteHiHealthDataPro(HiDataDeleteProOption hiDataDeleteProOption, IDataOperateListener iDataOperateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void deleteHiHealthDataProEx(List list, IDeleteListenerEx iDeleteListenerEx) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void deleteSampleConfig(HiSampleConfigProcessOption hiSampleConfigProcessOption, IDataOperateListener iDataOperateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void fetchAccountInfo(ICommonListener iCommonListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void fetchBuildInDataClient(IDataClientListener iDataClientListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void fetchDataClientByUniqueID(int i, String str, IDataClientListener iDataClientListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void fetchDataSource(HiDataSourceFetchOption hiDataSourceFetchOption, IDataClientListener iDataClientListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void fetchDataSourceByType(int i, HiTimeInterval hiTimeInterval, IDataClientListener iDataClientListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void fetchDataSourceByTypes(List list, HiTimeInterval hiTimeInterval, boolean z, IMultiDataClientListener iMultiDataClientListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void fetchGoalInfo(int i, int i2, ICommonListener iCommonListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public int fetchHiHealthDataCustomType(String str) throws RemoteException {
            return 0;
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void fetchManualDataClient(IDataClientListener iDataClientListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void fetchPhoneDataClient(IDataClientListener iDataClientListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public HiHealthUnit fetchPreferUnit(int i) throws RemoteException {
            return null;
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void fetchRegisteredDataClient(int i, IDataClientListener iDataClientListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void fetchSequenceDataBySportType(HiDataReadOption hiDataReadOption, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void fetchSportTypeList(HiDataReadOption hiDataReadOption, ICommonListener iCommonListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void fetchSupportedTypes(int i, ISupportedTypesListener iSupportedTypesListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void fetchUserData(ICommonListener iCommonListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public int getHiHealthVersionCode() throws RemoteException {
            return 0;
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void getSampleConfig(HiSampleConfigProcessOption hiSampleConfigProcessOption, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public HiUserPreference getUserPreference(String str) throws RemoteException {
            return null;
        }

        @Override // com.huawei.hihealth.IHiHealth
        public List<HiUserPreference> getUserPreferenceList(List<String> list) throws RemoteException {
            return null;
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void hiLogin(HiAccountInfo hiAccountInfo, ICommonListener iCommonListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void hiLogout(ICommonListener iCommonListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void initHiHealth(HiAppInfo hiAppInfo) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void insertFitnessData(String str) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void insertHiHealthData(HiDataInsertOption hiDataInsertOption, IDataOperateListener iDataOperateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void insertRealTimeHiHealthData(HiDataInsertOption hiDataInsertOption, IDataOperateListener iDataOperateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void queryHealthKitPermission(int i, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void queryKitAppInfo(IDataReadResultListener iDataReadResultListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void queryWearKitAppInfo(IDataReadResultListener iDataReadResultListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void queryWearKitPermission(int i, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void readDeviceInfo(IDataHiDeviceInfoListener iDataHiDeviceInfoListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void readHiHealthData(HiDataReadOption hiDataReadOption, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void readHiHealthDataEx(List list, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void readHiHealthDataPro(HiDataReadProOption hiDataReadProOption, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void registerDataClient(HiDeviceInfo hiDeviceInfo, List list, IRegisterClientListener iRegisterClientListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void registerDataClientWithUserInfo(HiDeviceInfo hiDeviceInfo, HiUserInfo hiUserInfo, List list, IRegisterClientListener iRegisterClientListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void requestAuthorization(HiAuthorizationOption hiAuthorizationOption, IAuthorizationListener iAuthorizationListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void setBinder(String str, IBinder iBinder, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void setGoalInfo(int i, List list, ICommonListener iCommonListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void setPreferUnit(int i, HiHealthUnit hiHealthUnit) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void setSampleConfig(List list, IDataOperateListener iDataOperateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void setUserData(HiUserInfo hiUserInfo, ICommonListener iCommonListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public boolean setUserPreference(HiUserPreference hiUserPreference, boolean z) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void subscribeHiHealthData(List list, ISubscribeListener iSubscribeListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void synCloud(HiSyncOption hiSyncOption, ICommonListener iCommonListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void synCloudCancel() throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void unBindHiHealth() throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void unSubscribeHiHealthData(List list, IUnSubscribeListener iUnSubscribeListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void updateHealthKitPermission(int i, int i2, int i3, boolean z, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void updateHiHealthData(HiDataUpdateOption hiDataUpdateOption, IDataOperateListener iDataOperateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealth
        public void updateWearKitPermission(int i, int i2, boolean z, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        }
    }

    boolean addExternalSubscribeTrigger(int i, int i2, HiSubscribeTrigger hiSubscribeTrigger) throws RemoteException;

    int addHiHealthDataCustomType(String str) throws RemoteException;

    void aggregateHiHealthData(HiAggregateOption hiAggregateOption, IAggregateListener iAggregateListener) throws RemoteException;

    void aggregateHiHealthDataEx(List list, IAggregateListenerEx iAggregateListenerEx) throws RemoteException;

    void aggregateHiHealthDataPro(HiDataAggregateProOption hiDataAggregateProOption, IAggregateListener iAggregateListener) throws RemoteException;

    void aggregateHiHealthDataProEx(List list, IAggregateListenerEx iAggregateListenerEx) throws RemoteException;

    void aggregateSportStatData(HiSportStatDataAggregateOption hiSportStatDataAggregateOption, IAggregateListener iAggregateListener) throws RemoteException;

    void checkDataStatus(List list, ICommonListener iCommonListener) throws RemoteException;

    boolean checkHiHealthLogin(String str) throws RemoteException;

    void deleteAllKitHealthData(int i, IDataOperateListener iDataOperateListener) throws RemoteException;

    void deleteHiHealthData(HiDataDeleteOption hiDataDeleteOption, IDataOperateListener iDataOperateListener) throws RemoteException;

    void deleteHiHealthDataPro(HiDataDeleteProOption hiDataDeleteProOption, IDataOperateListener iDataOperateListener) throws RemoteException;

    void deleteHiHealthDataProEx(List list, IDeleteListenerEx iDeleteListenerEx) throws RemoteException;

    void deleteSampleConfig(HiSampleConfigProcessOption hiSampleConfigProcessOption, IDataOperateListener iDataOperateListener) throws RemoteException;

    void fetchAccountInfo(ICommonListener iCommonListener) throws RemoteException;

    void fetchBuildInDataClient(IDataClientListener iDataClientListener) throws RemoteException;

    void fetchDataClientByUniqueID(int i, String str, IDataClientListener iDataClientListener) throws RemoteException;

    void fetchDataSource(HiDataSourceFetchOption hiDataSourceFetchOption, IDataClientListener iDataClientListener) throws RemoteException;

    void fetchDataSourceByType(int i, HiTimeInterval hiTimeInterval, IDataClientListener iDataClientListener) throws RemoteException;

    void fetchDataSourceByTypes(List list, HiTimeInterval hiTimeInterval, boolean z, IMultiDataClientListener iMultiDataClientListener) throws RemoteException;

    void fetchGoalInfo(int i, int i2, ICommonListener iCommonListener) throws RemoteException;

    int fetchHiHealthDataCustomType(String str) throws RemoteException;

    void fetchManualDataClient(IDataClientListener iDataClientListener) throws RemoteException;

    void fetchPhoneDataClient(IDataClientListener iDataClientListener) throws RemoteException;

    HiHealthUnit fetchPreferUnit(int i) throws RemoteException;

    void fetchRegisteredDataClient(int i, IDataClientListener iDataClientListener) throws RemoteException;

    void fetchSequenceDataBySportType(HiDataReadOption hiDataReadOption, IDataReadResultListener iDataReadResultListener) throws RemoteException;

    void fetchSportTypeList(HiDataReadOption hiDataReadOption, ICommonListener iCommonListener) throws RemoteException;

    void fetchSupportedTypes(int i, ISupportedTypesListener iSupportedTypesListener) throws RemoteException;

    void fetchUserData(ICommonListener iCommonListener) throws RemoteException;

    int getHiHealthVersionCode() throws RemoteException;

    void getSampleConfig(HiSampleConfigProcessOption hiSampleConfigProcessOption, IDataReadResultListener iDataReadResultListener) throws RemoteException;

    HiUserPreference getUserPreference(String str) throws RemoteException;

    List<HiUserPreference> getUserPreferenceList(List<String> list) throws RemoteException;

    void hiLogin(HiAccountInfo hiAccountInfo, ICommonListener iCommonListener) throws RemoteException;

    void hiLogout(ICommonListener iCommonListener) throws RemoteException;

    void initHiHealth(HiAppInfo hiAppInfo) throws RemoteException;

    void insertFitnessData(String str) throws RemoteException;

    void insertHiHealthData(HiDataInsertOption hiDataInsertOption, IDataOperateListener iDataOperateListener) throws RemoteException;

    void insertRealTimeHiHealthData(HiDataInsertOption hiDataInsertOption, IDataOperateListener iDataOperateListener) throws RemoteException;

    void queryHealthKitPermission(int i, IDataReadResultListener iDataReadResultListener) throws RemoteException;

    void queryKitAppInfo(IDataReadResultListener iDataReadResultListener) throws RemoteException;

    void queryWearKitAppInfo(IDataReadResultListener iDataReadResultListener) throws RemoteException;

    void queryWearKitPermission(int i, IDataReadResultListener iDataReadResultListener) throws RemoteException;

    void readDeviceInfo(IDataHiDeviceInfoListener iDataHiDeviceInfoListener) throws RemoteException;

    void readHiHealthData(HiDataReadOption hiDataReadOption, IDataReadResultListener iDataReadResultListener) throws RemoteException;

    void readHiHealthDataEx(List list, IDataReadResultListener iDataReadResultListener) throws RemoteException;

    void readHiHealthDataPro(HiDataReadProOption hiDataReadProOption, IDataReadResultListener iDataReadResultListener) throws RemoteException;

    void registerDataClient(HiDeviceInfo hiDeviceInfo, List list, IRegisterClientListener iRegisterClientListener) throws RemoteException;

    void registerDataClientWithUserInfo(HiDeviceInfo hiDeviceInfo, HiUserInfo hiUserInfo, List list, IRegisterClientListener iRegisterClientListener) throws RemoteException;

    void requestAuthorization(HiAuthorizationOption hiAuthorizationOption, IAuthorizationListener iAuthorizationListener) throws RemoteException;

    void setBinder(String str, IBinder iBinder, ICommonCallback iCommonCallback) throws RemoteException;

    void setGoalInfo(int i, List list, ICommonListener iCommonListener) throws RemoteException;

    void setPreferUnit(int i, HiHealthUnit hiHealthUnit) throws RemoteException;

    void setSampleConfig(List list, IDataOperateListener iDataOperateListener) throws RemoteException;

    void setUserData(HiUserInfo hiUserInfo, ICommonListener iCommonListener) throws RemoteException;

    boolean setUserPreference(HiUserPreference hiUserPreference, boolean z) throws RemoteException;

    void subscribeHiHealthData(List list, ISubscribeListener iSubscribeListener) throws RemoteException;

    void synCloud(HiSyncOption hiSyncOption, ICommonListener iCommonListener) throws RemoteException;

    void synCloudCancel() throws RemoteException;

    void unBindHiHealth() throws RemoteException;

    void unSubscribeHiHealthData(List list, IUnSubscribeListener iUnSubscribeListener) throws RemoteException;

    void updateHealthKitPermission(int i, int i2, int i3, boolean z, IDataReadResultListener iDataReadResultListener) throws RemoteException;

    void updateHiHealthData(HiDataUpdateOption hiDataUpdateOption, IDataOperateListener iDataOperateListener) throws RemoteException;

    void updateWearKitPermission(int i, int i2, boolean z, IDataReadResultListener iDataReadResultListener) throws RemoteException;

    public static abstract class Stub extends Binder implements IHiHealth {
        static final int TRANSACTION_addExternalSubscribeTrigger = 6;
        static final int TRANSACTION_addHiHealthDataCustomType = 14;
        static final int TRANSACTION_aggregateHiHealthData = 8;
        static final int TRANSACTION_aggregateHiHealthDataEx = 9;
        static final int TRANSACTION_aggregateHiHealthDataPro = 55;
        static final int TRANSACTION_aggregateHiHealthDataProEx = 60;
        static final int TRANSACTION_aggregateSportStatData = 52;
        static final int TRANSACTION_checkDataStatus = 29;
        static final int TRANSACTION_checkHiHealthLogin = 38;
        static final int TRANSACTION_deleteAllKitHealthData = 49;
        static final int TRANSACTION_deleteHiHealthData = 11;
        static final int TRANSACTION_deleteHiHealthDataPro = 56;
        static final int TRANSACTION_deleteHiHealthDataProEx = 65;
        static final int TRANSACTION_deleteSampleConfig = 63;
        static final int TRANSACTION_fetchAccountInfo = 33;
        static final int TRANSACTION_fetchBuildInDataClient = 18;
        static final int TRANSACTION_fetchDataClientByUniqueID = 22;
        static final int TRANSACTION_fetchDataSource = 53;
        static final int TRANSACTION_fetchDataSourceByType = 25;
        static final int TRANSACTION_fetchDataSourceByTypes = 64;
        static final int TRANSACTION_fetchGoalInfo = 35;
        static final int TRANSACTION_fetchHiHealthDataCustomType = 15;
        static final int TRANSACTION_fetchManualDataClient = 19;
        static final int TRANSACTION_fetchPhoneDataClient = 20;
        static final int TRANSACTION_fetchPreferUnit = 23;
        static final int TRANSACTION_fetchRegisteredDataClient = 21;
        static final int TRANSACTION_fetchSequenceDataBySportType = 51;
        static final int TRANSACTION_fetchSportTypeList = 50;
        static final int TRANSACTION_fetchSupportedTypes = 4;
        static final int TRANSACTION_fetchUserData = 27;
        static final int TRANSACTION_getHiHealthVersionCode = 39;
        static final int TRANSACTION_getSampleConfig = 62;
        static final int TRANSACTION_getUserPreference = 37;
        static final int TRANSACTION_getUserPreferenceList = 57;
        static final int TRANSACTION_hiLogin = 31;
        static final int TRANSACTION_hiLogout = 32;
        static final int TRANSACTION_initHiHealth = 1;
        static final int TRANSACTION_insertFitnessData = 59;
        static final int TRANSACTION_insertHiHealthData = 10;
        static final int TRANSACTION_insertRealTimeHiHealthData = 47;
        static final int TRANSACTION_queryHealthKitPermission = 42;
        static final int TRANSACTION_queryKitAppInfo = 41;
        static final int TRANSACTION_queryWearKitAppInfo = 44;
        static final int TRANSACTION_queryWearKitPermission = 45;
        static final int TRANSACTION_readDeviceInfo = 40;
        static final int TRANSACTION_readHiHealthData = 12;
        static final int TRANSACTION_readHiHealthDataEx = 58;
        static final int TRANSACTION_readHiHealthDataPro = 54;
        static final int TRANSACTION_registerDataClient = 16;
        static final int TRANSACTION_registerDataClientWithUserInfo = 17;
        static final int TRANSACTION_requestAuthorization = 3;
        static final int TRANSACTION_setBinder = 48;
        static final int TRANSACTION_setGoalInfo = 34;
        static final int TRANSACTION_setPreferUnit = 24;
        static final int TRANSACTION_setSampleConfig = 61;
        static final int TRANSACTION_setUserData = 26;
        static final int TRANSACTION_setUserPreference = 36;
        static final int TRANSACTION_subscribeHiHealthData = 5;
        static final int TRANSACTION_synCloud = 28;
        static final int TRANSACTION_synCloudCancel = 30;
        static final int TRANSACTION_unBindHiHealth = 2;
        static final int TRANSACTION_unSubscribeHiHealthData = 7;
        static final int TRANSACTION_updateHealthKitPermission = 43;
        static final int TRANSACTION_updateHiHealthData = 13;
        static final int TRANSACTION_updateWearKitPermission = 46;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IHiHealth.DESCRIPTOR);
        }

        public static IHiHealth asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IHiHealth.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IHiHealth)) {
                return (IHiHealth) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IHiHealth.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IHiHealth.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    initHiHealth((HiAppInfo) _Parcel.bvi_(parcel, HiAppInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    unBindHiHealth();
                    parcel2.writeNoException();
                    return true;
                case 3:
                    requestAuthorization((HiAuthorizationOption) _Parcel.bvi_(parcel, HiAuthorizationOption.CREATOR), IAuthorizationListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    fetchSupportedTypes(parcel.readInt(), ISupportedTypesListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    subscribeHiHealthData(parcel.readArrayList(getClass().getClassLoader()), ISubscribeListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    boolean addExternalSubscribeTrigger = addExternalSubscribeTrigger(parcel.readInt(), parcel.readInt(), (HiSubscribeTrigger) _Parcel.bvi_(parcel, HiSubscribeTrigger.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(addExternalSubscribeTrigger ? 1 : 0);
                    return true;
                case 7:
                    unSubscribeHiHealthData(parcel.readArrayList(getClass().getClassLoader()), IUnSubscribeListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    aggregateHiHealthData((HiAggregateOption) _Parcel.bvi_(parcel, HiAggregateOption.CREATOR), IAggregateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    aggregateHiHealthDataEx(parcel.readArrayList(getClass().getClassLoader()), IAggregateListenerEx.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    insertHiHealthData((HiDataInsertOption) _Parcel.bvi_(parcel, HiDataInsertOption.CREATOR), IDataOperateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 11:
                    deleteHiHealthData((HiDataDeleteOption) _Parcel.bvi_(parcel, HiDataDeleteOption.CREATOR), IDataOperateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 12:
                    readHiHealthData((HiDataReadOption) _Parcel.bvi_(parcel, HiDataReadOption.CREATOR), IDataReadResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 13:
                    updateHiHealthData((HiDataUpdateOption) _Parcel.bvi_(parcel, HiDataUpdateOption.CREATOR), IDataOperateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 14:
                    int addHiHealthDataCustomType = addHiHealthDataCustomType(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(addHiHealthDataCustomType);
                    return true;
                case 15:
                    int fetchHiHealthDataCustomType = fetchHiHealthDataCustomType(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(fetchHiHealthDataCustomType);
                    return true;
                case 16:
                    registerDataClient((HiDeviceInfo) _Parcel.bvi_(parcel, HiDeviceInfo.CREATOR), parcel.readArrayList(getClass().getClassLoader()), IRegisterClientListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 17:
                    registerDataClientWithUserInfo((HiDeviceInfo) _Parcel.bvi_(parcel, HiDeviceInfo.CREATOR), (HiUserInfo) _Parcel.bvi_(parcel, HiUserInfo.CREATOR), parcel.readArrayList(getClass().getClassLoader()), IRegisterClientListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 18:
                    fetchBuildInDataClient(IDataClientListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 19:
                    fetchManualDataClient(IDataClientListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 20:
                    fetchPhoneDataClient(IDataClientListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 21:
                    fetchRegisteredDataClient(parcel.readInt(), IDataClientListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 22:
                    fetchDataClientByUniqueID(parcel.readInt(), parcel.readString(), IDataClientListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 23:
                    HiHealthUnit fetchPreferUnit = fetchPreferUnit(parcel.readInt());
                    parcel2.writeNoException();
                    _Parcel.bvk_(parcel2, fetchPreferUnit, 1);
                    return true;
                case 24:
                    setPreferUnit(parcel.readInt(), (HiHealthUnit) _Parcel.bvi_(parcel, HiHealthUnit.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 25:
                    fetchDataSourceByType(parcel.readInt(), (HiTimeInterval) _Parcel.bvi_(parcel, HiTimeInterval.CREATOR), IDataClientListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 26:
                    setUserData((HiUserInfo) _Parcel.bvi_(parcel, HiUserInfo.CREATOR), ICommonListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 27:
                    fetchUserData(ICommonListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 28:
                    synCloud((HiSyncOption) _Parcel.bvi_(parcel, HiSyncOption.CREATOR), ICommonListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 29:
                    checkDataStatus(parcel.readArrayList(getClass().getClassLoader()), ICommonListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 30:
                    synCloudCancel();
                    parcel2.writeNoException();
                    return true;
                case 31:
                    hiLogin((HiAccountInfo) _Parcel.bvi_(parcel, HiAccountInfo.CREATOR), ICommonListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 32:
                    hiLogout(ICommonListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 33:
                    fetchAccountInfo(ICommonListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 34:
                    setGoalInfo(parcel.readInt(), parcel.readArrayList(getClass().getClassLoader()), ICommonListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 35:
                    fetchGoalInfo(parcel.readInt(), parcel.readInt(), ICommonListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 36:
                    boolean userPreference = setUserPreference((HiUserPreference) _Parcel.bvi_(parcel, HiUserPreference.CREATOR), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(userPreference ? 1 : 0);
                    return true;
                case 37:
                    HiUserPreference userPreference2 = getUserPreference(parcel.readString());
                    parcel2.writeNoException();
                    _Parcel.bvk_(parcel2, userPreference2, 1);
                    return true;
                case 38:
                    boolean checkHiHealthLogin = checkHiHealthLogin(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(checkHiHealthLogin ? 1 : 0);
                    return true;
                case 39:
                    int hiHealthVersionCode = getHiHealthVersionCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(hiHealthVersionCode);
                    return true;
                case 40:
                    readDeviceInfo(IDataHiDeviceInfoListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 41:
                    queryKitAppInfo(IDataReadResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 42:
                    queryHealthKitPermission(parcel.readInt(), IDataReadResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 43:
                    updateHealthKitPermission(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0, IDataReadResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 44:
                    queryWearKitAppInfo(IDataReadResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 45:
                    queryWearKitPermission(parcel.readInt(), IDataReadResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 46:
                    updateWearKitPermission(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0, IDataReadResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 47:
                    insertRealTimeHiHealthData((HiDataInsertOption) _Parcel.bvi_(parcel, HiDataInsertOption.CREATOR), IDataOperateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 48:
                    setBinder(parcel.readString(), parcel.readStrongBinder(), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 49:
                    deleteAllKitHealthData(parcel.readInt(), IDataOperateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 50:
                    fetchSportTypeList((HiDataReadOption) _Parcel.bvi_(parcel, HiDataReadOption.CREATOR), ICommonListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 51:
                    fetchSequenceDataBySportType((HiDataReadOption) _Parcel.bvi_(parcel, HiDataReadOption.CREATOR), IDataReadResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 52:
                    aggregateSportStatData((HiSportStatDataAggregateOption) _Parcel.bvi_(parcel, HiSportStatDataAggregateOption.CREATOR), IAggregateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 53:
                    fetchDataSource((HiDataSourceFetchOption) _Parcel.bvi_(parcel, HiDataSourceFetchOption.CREATOR), IDataClientListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 54:
                    readHiHealthDataPro((HiDataReadProOption) _Parcel.bvi_(parcel, HiDataReadProOption.CREATOR), IDataReadResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 55:
                    aggregateHiHealthDataPro((HiDataAggregateProOption) _Parcel.bvi_(parcel, HiDataAggregateProOption.CREATOR), IAggregateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 56:
                    deleteHiHealthDataPro((HiDataDeleteProOption) _Parcel.bvi_(parcel, HiDataDeleteProOption.CREATOR), IDataOperateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 57:
                    List<HiUserPreference> userPreferenceList = getUserPreferenceList(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    _Parcel.bvj_(parcel2, userPreferenceList, 1);
                    return true;
                case 58:
                    readHiHealthDataEx(parcel.readArrayList(getClass().getClassLoader()), IDataReadResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 59:
                    insertFitnessData(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 60:
                    aggregateHiHealthDataProEx(parcel.readArrayList(getClass().getClassLoader()), IAggregateListenerEx.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 61:
                    setSampleConfig(parcel.readArrayList(getClass().getClassLoader()), IDataOperateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 62:
                    getSampleConfig((HiSampleConfigProcessOption) _Parcel.bvi_(parcel, HiSampleConfigProcessOption.CREATOR), IDataReadResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 63:
                    deleteSampleConfig((HiSampleConfigProcessOption) _Parcel.bvi_(parcel, HiSampleConfigProcessOption.CREATOR), IDataOperateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 64:
                    fetchDataSourceByTypes(parcel.readArrayList(getClass().getClassLoader()), (HiTimeInterval) _Parcel.bvi_(parcel, HiTimeInterval.CREATOR), parcel.readInt() != 0, IMultiDataClientListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 65:
                    deleteHiHealthDataProEx(parcel.readArrayList(getClass().getClassLoader()), IDeleteListenerEx.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class Proxy implements IHiHealth {
            private IBinder d;

            Proxy(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void initHiHealth(HiAppInfo hiAppInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    _Parcel.bvk_(obtain, hiAppInfo, 0);
                    this.d.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void unBindHiHealth() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    this.d.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void requestAuthorization(HiAuthorizationOption hiAuthorizationOption, IAuthorizationListener iAuthorizationListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    _Parcel.bvk_(obtain, hiAuthorizationOption, 0);
                    obtain.writeStrongInterface(iAuthorizationListener);
                    this.d.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void fetchSupportedTypes(int i, ISupportedTypesListener iSupportedTypesListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iSupportedTypesListener);
                    this.d.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void subscribeHiHealthData(List list, ISubscribeListener iSubscribeListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeList(list);
                    obtain.writeStrongInterface(iSubscribeListener);
                    this.d.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public boolean addExternalSubscribeTrigger(int i, int i2, HiSubscribeTrigger hiSubscribeTrigger) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    _Parcel.bvk_(obtain, hiSubscribeTrigger, 0);
                    this.d.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void unSubscribeHiHealthData(List list, IUnSubscribeListener iUnSubscribeListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeList(list);
                    obtain.writeStrongInterface(iUnSubscribeListener);
                    this.d.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void aggregateHiHealthData(HiAggregateOption hiAggregateOption, IAggregateListener iAggregateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    _Parcel.bvk_(obtain, hiAggregateOption, 0);
                    obtain.writeStrongInterface(iAggregateListener);
                    this.d.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void aggregateHiHealthDataEx(List list, IAggregateListenerEx iAggregateListenerEx) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeList(list);
                    obtain.writeStrongInterface(iAggregateListenerEx);
                    this.d.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void insertHiHealthData(HiDataInsertOption hiDataInsertOption, IDataOperateListener iDataOperateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    _Parcel.bvk_(obtain, hiDataInsertOption, 0);
                    obtain.writeStrongInterface(iDataOperateListener);
                    this.d.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void deleteHiHealthData(HiDataDeleteOption hiDataDeleteOption, IDataOperateListener iDataOperateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    _Parcel.bvk_(obtain, hiDataDeleteOption, 0);
                    obtain.writeStrongInterface(iDataOperateListener);
                    this.d.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void readHiHealthData(HiDataReadOption hiDataReadOption, IDataReadResultListener iDataReadResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    _Parcel.bvk_(obtain, hiDataReadOption, 0);
                    obtain.writeStrongInterface(iDataReadResultListener);
                    this.d.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void updateHiHealthData(HiDataUpdateOption hiDataUpdateOption, IDataOperateListener iDataOperateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    _Parcel.bvk_(obtain, hiDataUpdateOption, 0);
                    obtain.writeStrongInterface(iDataOperateListener);
                    this.d.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public int addHiHealthDataCustomType(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeString(str);
                    this.d.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public int fetchHiHealthDataCustomType(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeString(str);
                    this.d.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void registerDataClient(HiDeviceInfo hiDeviceInfo, List list, IRegisterClientListener iRegisterClientListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    _Parcel.bvk_(obtain, hiDeviceInfo, 0);
                    obtain.writeList(list);
                    obtain.writeStrongInterface(iRegisterClientListener);
                    this.d.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void registerDataClientWithUserInfo(HiDeviceInfo hiDeviceInfo, HiUserInfo hiUserInfo, List list, IRegisterClientListener iRegisterClientListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    _Parcel.bvk_(obtain, hiDeviceInfo, 0);
                    _Parcel.bvk_(obtain, hiUserInfo, 0);
                    obtain.writeList(list);
                    obtain.writeStrongInterface(iRegisterClientListener);
                    this.d.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void fetchBuildInDataClient(IDataClientListener iDataClientListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeStrongInterface(iDataClientListener);
                    this.d.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void fetchManualDataClient(IDataClientListener iDataClientListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeStrongInterface(iDataClientListener);
                    this.d.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void fetchPhoneDataClient(IDataClientListener iDataClientListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeStrongInterface(iDataClientListener);
                    this.d.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void fetchRegisteredDataClient(int i, IDataClientListener iDataClientListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iDataClientListener);
                    this.d.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void fetchDataClientByUniqueID(int i, String str, IDataClientListener iDataClientListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iDataClientListener);
                    this.d.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public HiHealthUnit fetchPreferUnit(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.d.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    return (HiHealthUnit) _Parcel.bvi_(obtain2, HiHealthUnit.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void setPreferUnit(int i, HiHealthUnit hiHealthUnit) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.bvk_(obtain, hiHealthUnit, 0);
                    this.d.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void fetchDataSourceByType(int i, HiTimeInterval hiTimeInterval, IDataClientListener iDataClientListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.bvk_(obtain, hiTimeInterval, 0);
                    obtain.writeStrongInterface(iDataClientListener);
                    this.d.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void setUserData(HiUserInfo hiUserInfo, ICommonListener iCommonListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    _Parcel.bvk_(obtain, hiUserInfo, 0);
                    obtain.writeStrongInterface(iCommonListener);
                    this.d.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void fetchUserData(ICommonListener iCommonListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeStrongInterface(iCommonListener);
                    this.d.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void synCloud(HiSyncOption hiSyncOption, ICommonListener iCommonListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    _Parcel.bvk_(obtain, hiSyncOption, 0);
                    obtain.writeStrongInterface(iCommonListener);
                    this.d.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void checkDataStatus(List list, ICommonListener iCommonListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeList(list);
                    obtain.writeStrongInterface(iCommonListener);
                    this.d.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void synCloudCancel() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    this.d.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void hiLogin(HiAccountInfo hiAccountInfo, ICommonListener iCommonListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    _Parcel.bvk_(obtain, hiAccountInfo, 0);
                    obtain.writeStrongInterface(iCommonListener);
                    this.d.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void hiLogout(ICommonListener iCommonListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeStrongInterface(iCommonListener);
                    this.d.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void fetchAccountInfo(ICommonListener iCommonListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeStrongInterface(iCommonListener);
                    this.d.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void setGoalInfo(int i, List list, ICommonListener iCommonListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeList(list);
                    obtain.writeStrongInterface(iCommonListener);
                    this.d.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void fetchGoalInfo(int i, int i2, ICommonListener iCommonListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeStrongInterface(iCommonListener);
                    this.d.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public boolean setUserPreference(HiUserPreference hiUserPreference, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    _Parcel.bvk_(obtain, hiUserPreference, 0);
                    obtain.writeInt(z ? 1 : 0);
                    this.d.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public HiUserPreference getUserPreference(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeString(str);
                    this.d.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                    return (HiUserPreference) _Parcel.bvi_(obtain2, HiUserPreference.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public boolean checkHiHealthLogin(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeString(str);
                    this.d.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public int getHiHealthVersionCode() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    this.d.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void readDeviceInfo(IDataHiDeviceInfoListener iDataHiDeviceInfoListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeStrongInterface(iDataHiDeviceInfoListener);
                    this.d.transact(40, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void queryKitAppInfo(IDataReadResultListener iDataReadResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeStrongInterface(iDataReadResultListener);
                    this.d.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void queryHealthKitPermission(int i, IDataReadResultListener iDataReadResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iDataReadResultListener);
                    this.d.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void updateHealthKitPermission(int i, int i2, int i3, boolean z, IDataReadResultListener iDataReadResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeStrongInterface(iDataReadResultListener);
                    this.d.transact(43, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void queryWearKitAppInfo(IDataReadResultListener iDataReadResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeStrongInterface(iDataReadResultListener);
                    this.d.transact(44, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void queryWearKitPermission(int i, IDataReadResultListener iDataReadResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iDataReadResultListener);
                    this.d.transact(45, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void updateWearKitPermission(int i, int i2, boolean z, IDataReadResultListener iDataReadResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeStrongInterface(iDataReadResultListener);
                    this.d.transact(46, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void insertRealTimeHiHealthData(HiDataInsertOption hiDataInsertOption, IDataOperateListener iDataOperateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    _Parcel.bvk_(obtain, hiDataInsertOption, 0);
                    obtain.writeStrongInterface(iDataOperateListener);
                    this.d.transact(47, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void setBinder(String str, IBinder iBinder, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.d.transact(48, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void deleteAllKitHealthData(int i, IDataOperateListener iDataOperateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iDataOperateListener);
                    this.d.transact(49, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void fetchSportTypeList(HiDataReadOption hiDataReadOption, ICommonListener iCommonListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    _Parcel.bvk_(obtain, hiDataReadOption, 0);
                    obtain.writeStrongInterface(iCommonListener);
                    this.d.transact(50, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void fetchSequenceDataBySportType(HiDataReadOption hiDataReadOption, IDataReadResultListener iDataReadResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    _Parcel.bvk_(obtain, hiDataReadOption, 0);
                    obtain.writeStrongInterface(iDataReadResultListener);
                    this.d.transact(51, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void aggregateSportStatData(HiSportStatDataAggregateOption hiSportStatDataAggregateOption, IAggregateListener iAggregateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    _Parcel.bvk_(obtain, hiSportStatDataAggregateOption, 0);
                    obtain.writeStrongInterface(iAggregateListener);
                    this.d.transact(52, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void fetchDataSource(HiDataSourceFetchOption hiDataSourceFetchOption, IDataClientListener iDataClientListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    _Parcel.bvk_(obtain, hiDataSourceFetchOption, 0);
                    obtain.writeStrongInterface(iDataClientListener);
                    this.d.transact(53, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void readHiHealthDataPro(HiDataReadProOption hiDataReadProOption, IDataReadResultListener iDataReadResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    _Parcel.bvk_(obtain, hiDataReadProOption, 0);
                    obtain.writeStrongInterface(iDataReadResultListener);
                    this.d.transact(54, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void aggregateHiHealthDataPro(HiDataAggregateProOption hiDataAggregateProOption, IAggregateListener iAggregateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    _Parcel.bvk_(obtain, hiDataAggregateProOption, 0);
                    obtain.writeStrongInterface(iAggregateListener);
                    this.d.transact(55, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void deleteHiHealthDataPro(HiDataDeleteProOption hiDataDeleteProOption, IDataOperateListener iDataOperateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    _Parcel.bvk_(obtain, hiDataDeleteProOption, 0);
                    obtain.writeStrongInterface(iDataOperateListener);
                    this.d.transact(56, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public List<HiUserPreference> getUserPreferenceList(List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeStringList(list);
                    this.d.transact(57, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(HiUserPreference.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void readHiHealthDataEx(List list, IDataReadResultListener iDataReadResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeList(list);
                    obtain.writeStrongInterface(iDataReadResultListener);
                    this.d.transact(58, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void insertFitnessData(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeString(str);
                    this.d.transact(59, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void aggregateHiHealthDataProEx(List list, IAggregateListenerEx iAggregateListenerEx) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeList(list);
                    obtain.writeStrongInterface(iAggregateListenerEx);
                    this.d.transact(60, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void setSampleConfig(List list, IDataOperateListener iDataOperateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeList(list);
                    obtain.writeStrongInterface(iDataOperateListener);
                    this.d.transact(61, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void getSampleConfig(HiSampleConfigProcessOption hiSampleConfigProcessOption, IDataReadResultListener iDataReadResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    _Parcel.bvk_(obtain, hiSampleConfigProcessOption, 0);
                    obtain.writeStrongInterface(iDataReadResultListener);
                    this.d.transact(62, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void deleteSampleConfig(HiSampleConfigProcessOption hiSampleConfigProcessOption, IDataOperateListener iDataOperateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    _Parcel.bvk_(obtain, hiSampleConfigProcessOption, 0);
                    obtain.writeStrongInterface(iDataOperateListener);
                    this.d.transact(63, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void fetchDataSourceByTypes(List list, HiTimeInterval hiTimeInterval, boolean z, IMultiDataClientListener iMultiDataClientListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeList(list);
                    _Parcel.bvk_(obtain, hiTimeInterval, 0);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeStrongInterface(iMultiDataClientListener);
                    this.d.transact(64, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealth
            public void deleteHiHealthDataProEx(List list, IDeleteListenerEx iDeleteListenerEx) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealth.DESCRIPTOR);
                    obtain.writeList(list);
                    obtain.writeStrongInterface(iDeleteListenerEx);
                    this.d.transact(65, obtain, obtain2, 0);
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
        public static <T> T bvi_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bvk_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bvj_(Parcel parcel, List<T> list, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                bvk_(parcel, list.get(i2), i);
            }
        }
    }
}
