package com.huawei.health.sportservice.manager;

import android.os.Bundle;
import android.os.SystemClock;
import com.huawei.health.sport.ITargetUpdateListener;
import com.huawei.health.sportservice.ISportDataSaveStatusCallback;
import com.huawei.health.sportservice.SportBleStatus;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.constants.SportObserverType;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.dataproducer.SportDataCenter;
import com.huawei.health.sportservice.inter.DeviceToSource;
import com.huawei.health.sportservice.inter.IndoorToSource;
import com.huawei.health.sportservice.inter.SourceToProducer;
import com.huawei.health.sportservice.inter.SportOutDataInterface;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.health.suggestion.util.HeartRateGetterUtil;
import com.huawei.healthcloud.plugintrack.model.IStepRateCallback;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsportmodel.SportAchieveSubscribe;
import defpackage.fgm;
import defpackage.fhm;
import defpackage.fhs;
import defpackage.gsy;
import defpackage.koq;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class BaseSportManager implements ManagerComponent, SportOutDataInterface {
    private static final Object LOCK = new Object();
    private static final String TAG = "SportService_BaseSportManager";
    private static volatile BaseSportManager sBaseSportManager;
    private EcologyManager mEcologyManager;
    private SportBleManager mSportBleManager;
    private fgm mSportCallbackOption;
    private SportDataCenter mSportDataCenter;
    private SportDataNotify mSportDataNotify;
    private SportDeviceManager mSportDeviceManager;
    private SportLaunchParams mSportLaunchParams;
    private SportLifecycle mSportLifecycle;
    private SportStatusManager mSportStatusManager;
    private BaseTargetManager mTargetManager;
    private int mSportType = 0;
    private boolean mIsAlreadyInit = false;
    private final Map<String, ManagerComponent> mManagerMap = Collections.synchronizedMap(new LinkedHashMap());

    private BaseSportManager() {
        LogUtil.a(TAG, "BaseSportManager");
    }

    public static BaseSportManager getInstance() {
        if (sBaseSportManager == null) {
            synchronized (LOCK) {
                if (sBaseSportManager == null) {
                    sBaseSportManager = new BaseSportManager();
                }
            }
        }
        return sBaseSportManager;
    }

    @Override // com.huawei.health.sportservice.inter.SportOutDataInterface
    public void stagingData(String str, Object obj) {
        SportDataCenter sportDataCenter = this.mSportDataCenter;
        if (sportDataCenter == null) {
            LogUtil.b(TAG, "stagingData() mSportDataCenter == null");
        } else {
            sportDataCenter.stagingData(str, obj);
        }
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void setParas(SportParamsType sportParamsType, Object obj) {
        LogUtil.a(TAG, "set all managers. tag:", sportParamsType);
        if (SportParamsType.SPORT_LAUNCH_PARAS.equals(sportParamsType) && (obj instanceof SportLaunchParams)) {
            LogUtil.a(TAG, "paraData instanceof SportLaunchParams");
            this.mSportLaunchParams = (SportLaunchParams) obj;
        }
        for (ManagerComponent managerComponent : this.mManagerMap.values()) {
            if (managerComponent.supportParas(sportParamsType)) {
                managerComponent.setParas(sportParamsType, obj);
            }
        }
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public <T> T getParas(SportParamsType sportParamsType) {
        LogUtil.a(TAG, "getParas ", sportParamsType);
        if (SportParamsType.SPORT_LAUNCH_PARAS.equals(sportParamsType)) {
            return (T) this.mSportLaunchParams;
        }
        return null;
    }

    @Override // com.huawei.health.sportservice.inter.SportOutDataInterface
    public boolean subscribeNotify(fgm fgmVar, SportDataNotify sportDataNotify) {
        SportDataCenter sportDataCenter = this.mSportDataCenter;
        if (sportDataCenter != null) {
            return sportDataCenter.subscribeNotify(fgmVar, sportDataNotify);
        }
        if (fgmVar == null) {
            return false;
        }
        if ("Track_LocalToRemoteProxy".equals(fgmVar.c())) {
            this.mSportCallbackOption = fgmVar;
            this.mSportDataNotify = sportDataNotify;
        }
        LogUtil.b(TAG, "subscribeNotify() mSportDataCenter == null");
        return false;
    }

    public boolean cancelSubscribeNotify(fgm fgmVar) {
        this.mSportCallbackOption = null;
        this.mSportLifecycle = null;
        this.mSportDataNotify = null;
        SportDataCenter sportDataCenter = this.mSportDataCenter;
        if (sportDataCenter == null) {
            LogUtil.b(TAG, "cancelSubscribeNotify mSportDataCenter == null");
            return false;
        }
        return sportDataCenter.cancelSubscribeNotify(fgmVar);
    }

    @Override // com.huawei.health.sportservice.inter.SportOutDataInterface
    public Object getData(String str) {
        Object data;
        SportDataCenter sportDataCenter = this.mSportDataCenter;
        if (sportDataCenter == null || (data = sportDataCenter.getData(str)) == null) {
            return 0;
        }
        return data;
    }

    @Override // com.huawei.health.sportservice.inter.SportOutDataInterface
    public int getSportType() {
        return this.mSportType;
    }

    @Override // com.huawei.health.sportservice.inter.SportOutDataInterface
    public boolean isToSave() {
        SportDataCenter sportDataCenter = this.mSportDataCenter;
        if (sportDataCenter == null) {
            ReleaseLogUtil.c(TAG, "isToSave failed.");
            return false;
        }
        return sportDataCenter.isToSave();
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public boolean addObserver(SportObserverType sportObserverType, String str, Object obj) {
        LogUtil.a(TAG, "addObserver, observerType is ", sportObserverType, " tag is ", str);
        for (ManagerComponent managerComponent : this.mManagerMap.values()) {
            if (managerComponent.supportObserver(sportObserverType)) {
                return managerComponent.addObserver(sportObserverType, str, obj);
            }
        }
        return false;
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public boolean removeObserver(SportObserverType sportObserverType, String str) {
        LogUtil.a(TAG, "removeObserver, observerType is ", sportObserverType, " tag is ", str);
        for (ManagerComponent managerComponent : this.mManagerMap.values()) {
            if (managerComponent.supportObserver(sportObserverType)) {
                return managerComponent.removeObserver(sportObserverType, str);
            }
        }
        return false;
    }

    public void initModel(Bundle bundle) {
        SportDataNotify sportDataNotify;
        if (bundle == null) {
            ReleaseLogUtil.c(TAG, "init model failed with null params.");
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        SportLaunchParams sportLaunchParams = (SportLaunchParams) bundle.getParcelable("bundle_key_sport_launch_paras");
        this.mSportLaunchParams = sportLaunchParams;
        if (sportLaunchParams == null) {
            ReleaseLogUtil.c(TAG, "init model failed with null params.");
            return;
        }
        ReleaseLogUtil.e(TAG, "init model with params:", sportLaunchParams.toString());
        this.mSportType = this.mSportLaunchParams.getSportType();
        fhm fhmVar = new fhm(this.mSportLaunchParams.getSportType(), this.mSportLaunchParams.getDataSource());
        for (Map.Entry<SportComponentType, Class<?>> entry : fhmVar.d(1).entrySet()) {
            ManagerComponent managerComponent = (ManagerComponent) fhmVar.c(entry.getValue(), ManagerComponent.class);
            if (managerComponent == null) {
                ReleaseLogUtil.d(TAG, "getInstance failed tag:", entry.getKey(), " value:", entry.getValue());
            } else {
                this.mManagerMap.put(entry.getKey().name(), managerComponent);
            }
        }
        setBaseManagerFiled();
        ReleaseLogUtil.e(TAG, "set launch paras and init all managers.");
        for (ManagerComponent managerComponent2 : this.mManagerMap.values()) {
            managerComponent2.setParas(SportParamsType.SPORT_LAUNCH_PARAS, this.mSportLaunchParams);
            managerComponent2.init();
        }
        ReleaseLogUtil.e(TAG, "register sport status.");
        for (Map.Entry<String, ManagerComponent> entry2 : this.mManagerMap.entrySet()) {
            registerSportStatus(entry2.getKey(), entry2.getValue());
        }
        fgm fgmVar = this.mSportCallbackOption;
        if (fgmVar != null && (sportDataNotify = this.mSportDataNotify) != null) {
            subscribeNotify(fgmVar, sportDataNotify);
        }
        SportLifecycle sportLifecycle = this.mSportLifecycle;
        if (sportLifecycle != null) {
            registerSportStatus("Track_LocalToRemoteProxy", sportLifecycle);
        }
        if (getDataSource() != 7) {
            this.mIsAlreadyInit = true;
        }
        ReleaseLogUtil.e(TAG, "init sport model cost:", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void setBaseManagerFiled() {
        ManagerComponent managerComponent = this.mManagerMap.get(ComponentName.SPORT_DATA_CENTER);
        if (managerComponent instanceof SportDataCenter) {
            LogUtil.a(TAG, "manager init");
            this.mSportDataCenter = (SportDataCenter) managerComponent;
        }
        ManagerComponent managerComponent2 = this.mManagerMap.get(ComponentName.SPORT_STATUS_MANAGER);
        if (managerComponent2 instanceof SportStatusManager) {
            this.mSportStatusManager = (SportStatusManager) managerComponent2;
        }
        ManagerComponent managerComponent3 = this.mManagerMap.get(ComponentName.TARGET_MANAGER);
        if (managerComponent3 instanceof BaseTargetManager) {
            this.mTargetManager = (BaseTargetManager) managerComponent3;
        }
        ManagerComponent managerComponent4 = this.mManagerMap.get(ComponentName.SPORT_DEVICE_MANAGER);
        if (managerComponent4 instanceof SportDeviceManager) {
            this.mSportDeviceManager = (SportDeviceManager) managerComponent4;
        }
        ManagerComponent managerComponent5 = this.mManagerMap.get(ComponentName.SPORT_BLE_MANAGER);
        if (managerComponent5 instanceof SportBleManager) {
            this.mSportBleManager = (SportBleManager) managerComponent5;
        }
        ManagerComponent managerComponent6 = this.mManagerMap.get(ComponentName.ECOLOGY_MANAGER);
        if (managerComponent6 instanceof EcologyManager) {
            this.mEcologyManager = (EcologyManager) managerComponent6;
        }
    }

    public boolean isRestart() {
        SportLaunchParams sportLaunchParams = this.mSportLaunchParams;
        if (sportLaunchParams == null) {
            LogUtil.a(TAG, "isRestart params is null");
            return false;
        }
        return sportLaunchParams.isRestart();
    }

    public boolean registerSportDataSavedCallback(String str, ISportDataSaveStatusCallback iSportDataSaveStatusCallback) {
        SportDataCenter sportDataCenter = this.mSportDataCenter;
        if (sportDataCenter == null) {
            LogUtil.b(TAG, "registerSportDataSavedCallback failed");
            return false;
        }
        return sportDataCenter.registerSportDataSavedCallback(str, iSportDataSaveStatusCallback);
    }

    public boolean unregisterSportDataSavedCallback(String str) {
        SportDataCenter sportDataCenter = this.mSportDataCenter;
        if (sportDataCenter == null) {
            LogUtil.b(TAG, "unregisterSportDataSavedCallback failed");
            return false;
        }
        return sportDataCenter.unregisterSportDataSavedCallback(str);
    }

    public boolean isAlreadyInit() {
        return this.mIsAlreadyInit;
    }

    public void setIsAlreadyInit(boolean z) {
        this.mIsAlreadyInit = z;
    }

    public int getTargetSportStatus() {
        BaseTargetManager baseTargetManager = this.mTargetManager;
        if (baseTargetManager == null) {
            return 0;
        }
        return baseTargetManager.getTargetSportStatus();
    }

    public void destroyModel() {
        LogUtil.a(TAG, "destroyModel enter");
        Iterator<ManagerComponent> it = this.mManagerMap.values().iterator();
        while (it.hasNext()) {
            it.next().destroy();
        }
        destroySportManager();
        this.mIsAlreadyInit = false;
    }

    private static void destroySportManager() {
        sBaseSportManager = null;
    }

    public void registerDeviceDataCallback(ISportDataCallback iSportDataCallback) {
        if (this.mSportDeviceManager == null) {
            this.mSportDeviceManager = new SportDeviceManager();
        }
        this.mSportDeviceManager.setDeviceDataCallback(iSportDataCallback);
        LogUtil.a(TAG, "registerDeviceDataCallback is sus");
    }

    public void registerConnectCallback(HeartRateGetterUtil.HeartRateConnectStateCallBack heartRateConnectStateCallBack) {
        if (this.mSportDeviceManager == null) {
            this.mSportDeviceManager = new SportDeviceManager();
        }
        this.mSportDeviceManager.setConnectStateCallback(heartRateConnectStateCallBack);
    }

    public void unRegisterConnectCallback() {
        if (this.mSportDeviceManager == null) {
            this.mSportDeviceManager = new SportDeviceManager();
        }
        this.mSportDeviceManager.setConnectStateCallback(null);
    }

    public void updateSourceData(String str, String str2, Object obj) {
        SportDataCenter sportDataCenter = this.mSportDataCenter;
        if (sportDataCenter == null) {
            return;
        }
        sportDataCenter.updateSourceData(str, str2, obj);
    }

    public boolean registerSportStatus(String str, SportLifecycle sportLifecycle) {
        LogUtil.a(TAG, "registerSportStatus() tag: ", str, ", sportLifecycle: ", sportLifecycle);
        if (str == null || sportLifecycle == null) {
            return false;
        }
        SportStatusManager sportStatusManager = this.mSportStatusManager;
        if (sportStatusManager == null) {
            LogUtil.h(TAG, "registerSportStatus SportStatusManager == null ");
            if ("Track_LocalToRemoteProxy".equals(str)) {
                this.mSportLifecycle = sportLifecycle;
            }
            return false;
        }
        sportStatusManager.registerStatusListener(str, sportLifecycle);
        return true;
    }

    public boolean unRegisterSportStatus(String str) {
        SportStatusManager sportStatusManager;
        LogUtil.a(TAG, "unRegisterSportStatus() tag: ", str);
        if (str == null || (sportStatusManager = this.mSportStatusManager) == null) {
            return false;
        }
        sportStatusManager.unRegisterStatus(str);
        return true;
    }

    public int getStatus() {
        SportStatusManager sportStatusManager = this.mSportStatusManager;
        if (sportStatusManager == null) {
            return 0;
        }
        return sportStatusManager.getSportStatus();
    }

    public int getDataSource() {
        SportLaunchParams sportLaunchParams = this.mSportLaunchParams;
        if (sportLaunchParams == null) {
            LogUtil.h(TAG, "mSportDataCenter == null");
            return -1;
        }
        return sportLaunchParams.getDataSource();
    }

    @Override // com.huawei.health.sportservice.inter.SportOutDataInterface
    public int getTargetType() {
        SportLaunchParams sportLaunchParams = this.mSportLaunchParams;
        if (sportLaunchParams == null) {
            ReleaseLogUtil.d(TAG, "getTargetType with null mSportLaunchParams");
            return -1;
        }
        return sportLaunchParams.getSportTarget();
    }

    @Override // com.huawei.health.sportservice.inter.SportOutDataInterface
    public float getTargetValue() {
        SportLaunchParams sportLaunchParams = this.mSportLaunchParams;
        if (sportLaunchParams == null) {
            ReleaseLogUtil.d(TAG, "getTargetValue with null mSportLaunchParams");
            return -1.0f;
        }
        return sportLaunchParams.getTargetValue();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        onPreSport("APP");
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport(String str) {
        SportStatusManager sportStatusManager = this.mSportStatusManager;
        if (sportStatusManager == null) {
            ReleaseLogUtil.c(TAG, "mSportStatusManager is not init");
        } else {
            sportStatusManager.onPreSport(str);
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onCountDown() {
        onCountDown("APP");
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onCountDown(String str) {
        SportStatusManager sportStatusManager = this.mSportStatusManager;
        if (sportStatusManager == null) {
            ReleaseLogUtil.c(TAG, "mSportStatusManager is not init");
        } else {
            sportStatusManager.onCountDown(str);
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        onStartSport("APP");
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport(String str) {
        SportStatusManager sportStatusManager = this.mSportStatusManager;
        if (sportStatusManager == null) {
            ReleaseLogUtil.c(TAG, "mSportStatusManager is not init");
        } else {
            sportStatusManager.onStartSport(str);
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        onResumeSport("APP");
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport(String str) {
        SportStatusManager sportStatusManager = this.mSportStatusManager;
        if (sportStatusManager == null) {
            LogUtil.b(TAG, "mSportStatusManager is not init");
        } else {
            sportStatusManager.onResumeSport(str);
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        onPauseSport("APP");
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport(String str) {
        SportStatusManager sportStatusManager = this.mSportStatusManager;
        if (sportStatusManager == null) {
            ReleaseLogUtil.c(TAG, "mSportStatusManager is not init");
        } else {
            sportStatusManager.onPauseSport(str);
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStopSport() {
        onStopSport("APP");
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStopSport(String str) {
        SportStatusManager sportStatusManager = this.mSportStatusManager;
        if (sportStatusManager == null) {
            ReleaseLogUtil.c(TAG, "mSportStatusManager is not init");
        } else {
            sportStatusManager.onStopSport(str);
        }
    }

    public void registerAchieveLevel(String str, SportAchieveSubscribe sportAchieveSubscribe) {
        ManagerComponent e = fhs.e(this.mManagerMap, this.mSportType);
        if (!(e instanceof AchieveModule)) {
            LogUtil.b(TAG, "registerAchieveLevel achieveManagerObject == null ");
        } else {
            ((AchieveModule) e).registerAchieveLevel(str, sportAchieveSubscribe);
        }
    }

    public void subscribeTargetStatus(String str, ITargetUpdateListener iTargetUpdateListener) {
        BaseTargetManager baseTargetManager = this.mTargetManager;
        if (baseTargetManager == null) {
            LogUtil.b(TAG, "mTargetManager == null");
        } else {
            baseTargetManager.subscribeTargetStatus(str, iTargetUpdateListener);
        }
    }

    public void subscribeToDevice(List<String> list, DeviceToSource deviceToSource) {
        SportDeviceManager sportDeviceManager = this.mSportDeviceManager;
        if (sportDeviceManager == null) {
            LogUtil.b(TAG, "mSportDeviceManager == null");
        } else {
            sportDeviceManager.setDataCallback(list, deviceToSource);
        }
    }

    public void subscribeToSensor(List<String> list, DeviceToSource deviceToSource) {
        SportDeviceManager sportDeviceManager = this.mSportDeviceManager;
        if (sportDeviceManager == null) {
            LogUtil.b(TAG, "mSportDeviceManager == null");
        } else {
            sportDeviceManager.setSensorDataCallback(list, deviceToSource);
        }
    }

    public void subscribeToSource(String str, SourceToProducer sourceToProducer) {
        SportDataCenter sportDataCenter = this.mSportDataCenter;
        if (sportDataCenter == null) {
            LogUtil.b(TAG, "subscribeToSource mSportDataCenter == null");
        } else {
            sportDataCenter.sourceLinkToProducer(str, sourceToProducer);
        }
    }

    public void subscribeToIndoorEquipment(List<Integer> list, IndoorToSource indoorToSource) {
        LogUtil.a(TAG, "subscribeToIndoorEquipment() tag: ", list);
        EcologyManager ecologyManager = this.mEcologyManager;
        if (ecologyManager != null) {
            ecologyManager.subscribeToIndoorEquipment(list, indoorToSource);
        } else {
            LogUtil.h(TAG, "subscribeToIndoorEquipment() mEcologyManager == null");
        }
    }

    public void onDestroy() {
        LogUtil.a(TAG, "onDestroy");
        this.mSportStatusManager = null;
        this.mIsAlreadyInit = false;
    }

    public void stagingAndNotification(String str, Object obj) {
        SportDataCenter sportDataCenter = this.mSportDataCenter;
        if (sportDataCenter == null) {
            LogUtil.b(TAG, "stagingAndNotification() mSportDataCenter == null");
        } else {
            sportDataCenter.stagingAndNotification(str, obj);
        }
    }

    public void registerStep(IStepRateCallback iStepRateCallback) {
        ManagerComponent managerComponent = this.mManagerMap.get(ComponentName.STEP_MANAGER);
        if (!(managerComponent instanceof StepManager)) {
            LogUtil.b(TAG, "registerStep stepManager == null ");
        } else {
            ((StepManager) managerComponent).initPhoneStep(iStepRateCallback);
        }
    }

    public void setBleState(String str) {
        SportBleManager sportBleManager = this.mSportBleManager;
        if (sportBleManager != null) {
            sportBleManager.setBleState(str);
        } else {
            LogUtil.h(TAG, "setBleState() mSportBleManager = null");
        }
    }

    public String getBleState() {
        SportBleManager sportBleManager = this.mSportBleManager;
        if (sportBleManager != null) {
            return sportBleManager.getBleState();
        }
        LogUtil.h(TAG, "getBleState() mSportBleManager = null");
        return "unknown";
    }

    public boolean registerSportBleStatus(String str, SportBleStatus sportBleStatus) {
        SportBleManager sportBleManager;
        if (str == null || sportBleStatus == null || (sportBleManager = this.mSportBleManager) == null) {
            return false;
        }
        sportBleManager.registerBleStatusListener(str, sportBleStatus);
        return true;
    }

    public boolean unRegisterSportBleStatus(String str) {
        SportBleManager sportBleManager;
        if (str == null || (sportBleManager = this.mSportBleManager) == null) {
            return false;
        }
        sportBleManager.unregisterBleStatusListener(str);
        return true;
    }

    public boolean isSportServiceRunning() {
        if (getStatus() == 0 || getStatus() == 3) {
            return false;
        }
        LogUtil.a(TAG, "isSportServiceRunning() true");
        return true;
    }

    public boolean isConnectBolt() {
        return !koq.b(gsy.b().e(this.mSportType));
    }

    public List<Integer> subDeviceConnectNumber(List<Integer> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().intValue() == 554) {
                arrayList.add(Integer.valueOf(gsy.b().e(this.mSportType).size()));
            } else {
                arrayList.add(0);
            }
        }
        return arrayList;
    }

    public String getSportMode() {
        SportLaunchParams sportLaunchParams = (SportLaunchParams) getParas(SportParamsType.SPORT_LAUNCH_PARAS);
        if (sportLaunchParams != null) {
            String str = (String) sportLaunchParams.getExtra("sportMode", String.class);
            LogUtil.a(TAG, "getSportMode() sportMode = ", str);
            if (str != null) {
                return str;
            }
        }
        return "";
    }

    public void addDeviceStatusCallback(String str, IBaseResponseCallback iBaseResponseCallback) {
        if (this.mSportDeviceManager != null) {
            LogUtil.a(TAG, "addDeviceStatusCallback() tag: ", str, ", callback: ", iBaseResponseCallback);
            this.mSportDeviceManager.addDeviceStatusCallback(str, iBaseResponseCallback);
        }
    }

    public void removeDeviceStatusCallback(String str) {
        if (this.mSportDeviceManager != null) {
            LogUtil.a(TAG, "removeDeviceStatusCallback() tag: ", str);
            this.mSportDeviceManager.removeDeviceStatusCallback(str);
        }
    }

    public void setBoltStatusListener(IBaseResponseCallback iBaseResponseCallback) {
        SportDeviceManager sportDeviceManager = this.mSportDeviceManager;
        if (sportDeviceManager != null) {
            sportDeviceManager.setCallback(iBaseResponseCallback);
        }
    }

    public void unregBoltAbnormalCallback() {
        SportDeviceManager sportDeviceManager = this.mSportDeviceManager;
        if (sportDeviceManager != null) {
            sportDeviceManager.unregBoltAbnormalCallback();
        }
    }

    public boolean isVoiceEnable() {
        SportLaunchParams sportLaunchParams = this.mSportLaunchParams;
        if (sportLaunchParams == null) {
            return true;
        }
        return ((Boolean) sportLaunchParams.getExtra("KEY_IS_VOICE_ENABLE", Boolean.class, true)).booleanValue();
    }
}
