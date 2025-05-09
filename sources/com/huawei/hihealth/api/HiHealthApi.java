package com.huawei.hihealth.api;

import android.os.IBinder;
import com.huawei.hihealth.HiAccountInfo;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.HiAuthorizationOption;
import com.huawei.hihealth.HiDataAggregateProOption;
import com.huawei.hihealth.HiDataDeleteOption;
import com.huawei.hihealth.HiDataDeleteProOption;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiDataSourceFetchOption;
import com.huawei.hihealth.HiDataUpdateOption;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiGoalInfo;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthUnit;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.HiSampleConfigProcessOption;
import com.huawei.hihealth.HiSportStatDataAggregateOption;
import com.huawei.hihealth.HiSubscribeTrigger;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiAggregateListenerEx;
import com.huawei.hihealth.data.listener.HiAuthorizationListener;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hihealth.data.listener.HiDataHiDeviceInfoListener;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.listener.HiDeleteListenerEx;
import com.huawei.hihealth.data.listener.HiMultiDataClientListener;
import com.huawei.hihealth.data.listener.HiRegisterClientListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiSupportedTypesListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.data.type.HiHealthDataType;
import java.util.List;

/* loaded from: classes.dex */
public interface HiHealthApi {
    boolean addExternalSubscribeTrigger(int i, HiHealthDataType.TriggerParam triggerParam, HiSubscribeTrigger hiSubscribeTrigger);

    int addHiHealthDataCustomType(String str);

    void aggregateHiHealthData(HiAggregateOption hiAggregateOption, HiAggregateListener hiAggregateListener);

    void aggregateHiHealthDataEx(List<HiAggregateOption> list, HiAggregateListenerEx hiAggregateListenerEx);

    void aggregateHiHealthDataPro(HiDataAggregateProOption hiDataAggregateProOption, HiAggregateListener hiAggregateListener);

    void aggregateHiHealthDataProEx(List<HiDataAggregateProOption> list, HiAggregateListenerEx hiAggregateListenerEx);

    void aggregateSportStatData(HiSportStatDataAggregateOption hiSportStatDataAggregateOption, HiAggregateListener hiAggregateListener);

    void checkDataStatus(List<Integer> list, HiCommonListener hiCommonListener);

    boolean checkHiHealthLogin(String str);

    boolean checkHiHealthServiceExist();

    void deleteAllKitHealthData(int i, HiDataOperateListener hiDataOperateListener);

    void deleteHiHealthData(HiDataDeleteOption hiDataDeleteOption, HiDataOperateListener hiDataOperateListener);

    void deleteHiHealthDataPro(HiDataDeleteProOption hiDataDeleteProOption, HiDataOperateListener hiDataOperateListener);

    void deleteHiHealthDataProEx(List<HiDataDeleteProOption> list, HiDeleteListenerEx hiDeleteListenerEx);

    void deleteSampleConfig(HiSampleConfigProcessOption hiSampleConfigProcessOption, HiDataOperateListener hiDataOperateListener);

    void fetchAccountInfo(HiCommonListener hiCommonListener);

    void fetchBuildInDataClient(HiDataClientListener hiDataClientListener);

    void fetchDataClientByUniqueId(int i, String str, HiDataClientListener hiDataClientListener);

    void fetchDataSource(HiDataSourceFetchOption hiDataSourceFetchOption, HiDataClientListener hiDataClientListener);

    void fetchDataSourceByType(int i, HiTimeInterval hiTimeInterval, HiDataClientListener hiDataClientListener);

    void fetchDataSourceByTypes(List<Integer> list, HiTimeInterval hiTimeInterval, boolean z, HiMultiDataClientListener hiMultiDataClientListener);

    void fetchGoalInfo(int i, int i2, HiCommonListener hiCommonListener);

    int fetchHiHealthDataCustomType(String str);

    void fetchManualDataClient(HiDataClientListener hiDataClientListener);

    void fetchPhoneDataClient(HiDataClientListener hiDataClientListener);

    HiHealthUnit fetchPreferUnit(int i);

    void fetchRegisteredDataClient(int i, HiDataClientListener hiDataClientListener);

    void fetchSequenceDataBySportType(HiDataReadOption hiDataReadOption, HiDataReadResultListener hiDataReadResultListener);

    void fetchSportTypeList(HiDataReadOption hiDataReadOption, HiCommonListener hiCommonListener);

    void fetchSupportedTypes(HiHealthDataType.TypeSelector typeSelector, HiSupportedTypesListener hiSupportedTypesListener);

    void fetchUserData(HiCommonListener hiCommonListener);

    int getHiHealthVersionCode();

    void getSampleConfig(HiSampleConfigProcessOption hiSampleConfigProcessOption, HiDataReadResultListener hiDataReadResultListener);

    @Deprecated
    HiUserPreference getUserPreference(String str);

    List<HiUserPreference> getUserPreferenceList(List<String> list);

    void hiLogin(HiAccountInfo hiAccountInfo, HiCommonListener hiCommonListener);

    void hiLogout(HiCommonListener hiCommonListener);

    void initHiHealth(HiAppInfo hiAppInfo);

    void insertFitnessData(String str);

    void insertHiHealthData(HiDataInsertOption hiDataInsertOption, HiDataOperateListener hiDataOperateListener);

    void insertRealTimeHiHealthData(HiDataInsertOption hiDataInsertOption, HiDataOperateListener hiDataOperateListener);

    void queryHealthKitPermission(int i, HiDataOperateListener hiDataOperateListener);

    void queryKitAppInfo(HiDataOperateListener hiDataOperateListener);

    void queryWearKitAppInfo(HiDataOperateListener hiDataOperateListener);

    void queryWearKitPermission(int i, HiDataOperateListener hiDataOperateListener);

    void readDeviceInfo(HiDataHiDeviceInfoListener hiDataHiDeviceInfoListener);

    void readHiHealthData(int i, HiDataReadOption hiDataReadOption, HiDataReadResultListener hiDataReadResultListener);

    void readHiHealthData(HiDataReadOption hiDataReadOption, HiDataReadResultListener hiDataReadResultListener);

    void readHiHealthDataEx(List<HiDataReadProOption> list, HiDataReadResultListener hiDataReadResultListener);

    void readHiHealthDataPro(HiDataReadProOption hiDataReadProOption, HiDataReadResultListener hiDataReadResultListener);

    void registerDataClient(HiDeviceInfo hiDeviceInfo, HiUserInfo hiUserInfo, List<Integer> list, HiRegisterClientListener hiRegisterClientListener);

    void registerDataClient(HiDeviceInfo hiDeviceInfo, List<Integer> list, HiRegisterClientListener hiRegisterClientListener);

    void requestAuthorization(HiAuthorizationOption hiAuthorizationOption, HiAuthorizationListener hiAuthorizationListener);

    void setBinder(String str, IBinder iBinder, HiCommonListener hiCommonListener);

    void setGoalInfo(int i, List<HiGoalInfo> list, HiCommonListener hiCommonListener);

    void setPreferUnit(int i, HiHealthUnit hiHealthUnit);

    void setSampleConfig(List<HiSampleConfig> list, HiDataOperateListener hiDataOperateListener);

    void setUserData(HiUserInfo hiUserInfo, HiCommonListener hiCommonListener);

    boolean setUserPreference(HiUserPreference hiUserPreference);

    boolean setUserPreference(HiUserPreference hiUserPreference, boolean z);

    void subscribeHiHealthData(int i, HiSubscribeListener hiSubscribeListener);

    void subscribeHiHealthData(HiHealthClient hiHealthClient, HiSubscribeListener hiSubscribeListener);

    void subscribeHiHealthData(List<Integer> list, HiSubscribeListener hiSubscribeListener);

    void synCloud(HiSyncOption hiSyncOption, HiCommonListener hiCommonListener);

    void synCloudCancel();

    void unBindHiHealth();

    void unSubscribeHiHealthData(List<Integer> list, HiUnSubscribeListener hiUnSubscribeListener);

    void updateHealthKitPermission(int i, int i2, int i3, boolean z, HiDataOperateListener hiDataOperateListener);

    void updateHiHealthData(HiDataUpdateOption hiDataUpdateOption, HiDataOperateListener hiDataOperateListener);

    void updateWearKitPermission(int i, int i2, boolean z, HiDataOperateListener hiDataOperateListener);
}
