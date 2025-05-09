package com.huawei.health.sportservice.dataproducer;

import com.huawei.health.sportservice.ISportDataSaveStatusCallback;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.datasource.BaseSource;
import com.huawei.health.sportservice.inter.SourceToProducer;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.manager.ManagerComponent;
import com.huawei.health.sportservice.manager.SportLinker;
import com.huawei.health.sportservice.manager.TrainDataStorageManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.fgm;
import defpackage.fhm;
import defpackage.fhs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SportComponentType(classify = 1, name = ComponentName.SPORT_DATA_CENTER)
/* loaded from: classes4.dex */
public class SportDataCenter implements ManagerComponent {
    private static final String TAG = "SportService_SportDataCenter";
    private SportDataStorageManager mSportDataStorageManager;
    private SportLaunchParams mSportLaunchParams;
    private final HashMap<String, Object> mDataStagingMap = new LinkedHashMap();
    private final Map<fgm, SportDataNotify> mSportDataNotifyMap = Collections.synchronizedMap(new LinkedHashMap());
    private final SportLinker mLinker = new SportLinker();
    private int mSportType = 0;
    private int mSportModel = -1;
    private final Map<String, BaseSource> mSourceMap = new LinkedHashMap();
    private final Map<String, BaseProducer> mProducerMap = new LinkedHashMap();

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void setParas(SportParamsType sportParamsType, Object obj) {
        SportDataStorageManager sportDataStorageManager;
        if (SportParamsType.SPORT_LAUNCH_PARAS.equals(sportParamsType) && (obj instanceof SportLaunchParams)) {
            SportLaunchParams sportLaunchParams = (SportLaunchParams) obj;
            this.mSportLaunchParams = sportLaunchParams;
            this.mSportType = sportLaunchParams.getSportType();
            this.mSportModel = this.mSportLaunchParams.getDataSource();
            if (this.mSportDataStorageManager == null) {
                if (fhs.b(this.mSportLaunchParams.getSportTarget(), this.mSportType)) {
                    this.mSportDataStorageManager = new TargetSportDataStorageManager();
                } else if (this.mSportType == 700001) {
                    this.mSportDataStorageManager = new TrainDataStorageManager();
                } else {
                    this.mSportDataStorageManager = new SportDataStorageManager();
                }
            }
            this.mSportDataStorageManager.setParas(sportParamsType, obj);
            return;
        }
        if (!SportParamsType.DEVICES_DATA_CHANGE_FIRST.equals(sportParamsType) || (sportDataStorageManager = this.mSportDataStorageManager) == null) {
            return;
        }
        sportDataStorageManager.setParas(sportParamsType, obj);
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public boolean supportParas(SportParamsType sportParamsType) {
        return SportParamsType.SPORT_LAUNCH_PARAS.equals(sportParamsType) || SportParamsType.DEVICES_DATA_CHANGE_FIRST.equals(sportParamsType);
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void init() {
        this.mSportDataStorageManager.init();
        if (this.mSportLaunchParams.isRestart()) {
            Object data = getData("TEMP_ALL_STAGE_DATA");
            if (data instanceof Map) {
                this.mDataStagingMap.putAll((Map) data);
            }
        }
        fhm fhmVar = new fhm(this.mSportType, this.mSportModel);
        for (Map.Entry<SportComponentType, Class<?>> entry : fhmVar.d(3).entrySet()) {
            BaseSource baseSource = (BaseSource) fhmVar.c(entry.getValue(), BaseSource.class);
            if (baseSource != null) {
                this.mSourceMap.put(entry.getKey().name(), baseSource);
            }
        }
        for (Map.Entry<SportComponentType, Class<?>> entry2 : fhmVar.d(2).entrySet()) {
            BaseProducer baseProducer = (BaseProducer) fhmVar.c(entry2.getValue(), BaseProducer.class);
            if (baseProducer != null) {
                this.mProducerMap.put(entry2.getKey().name(), baseProducer);
            }
        }
        LogUtil.a(TAG, "mSourceMap size: ", Integer.valueOf(this.mSourceMap.size()), ", ", this.mSourceMap);
        for (Map.Entry<String, BaseSource> entry3 : this.mSourceMap.entrySet()) {
            if (entry3.getValue() instanceof SportLifecycle) {
                BaseSportManager.getInstance().registerSportStatus(entry3.getKey(), (SportLifecycle) entry3.getValue());
            }
            if (this.mSportLaunchParams.isRestart()) {
                entry3.getValue().recoveryData();
            }
        }
        BaseSportManager.getInstance().registerSportStatus(SportLinker.TAG, this.mLinker);
        LogUtil.a(TAG, "mProducerMap size: ", Integer.valueOf(this.mProducerMap.size()), ", ", this.mProducerMap);
        for (Map.Entry<String, BaseProducer> entry4 : this.mProducerMap.entrySet()) {
            if (entry4.getValue() instanceof SportLifecycle) {
                BaseSportManager.getInstance().registerSportStatus(entry4.getKey(), (SportLifecycle) entry4.getValue());
                if (this.mSportLaunchParams.isRestart()) {
                    entry4.getValue().recoveryData();
                }
            }
        }
        this.mSportDataStorageManager.setParas(SportParamsType.PARAMS_PRODUCER_MAP, this.mProducerMap);
    }

    public void updateSourceData(String str, String str2, Object obj) {
        this.mLinker.updateSourceData(str, str2, obj);
    }

    public Object getData(String str) {
        if (this.mDataStagingMap.containsKey(str)) {
            return this.mDataStagingMap.get(str);
        }
        if (this.mSportDataStorageManager.isStorageData(str)) {
            return this.mSportDataStorageManager.getData(str);
        }
        return 0;
    }

    public void stagingData(String str, Object obj) {
        if (this.mSportDataStorageManager.isStorageData(str)) {
            this.mSportDataStorageManager.stagingData(str, obj);
        } else {
            this.mDataStagingMap.put(str, obj);
        }
    }

    public boolean subscribeNotify(fgm fgmVar, SportDataNotify sportDataNotify) {
        if (fgmVar == null || sportDataNotify == null || this.mSportDataNotifyMap.containsKey(fgmVar)) {
            LogUtil.h(TAG, "subscribeNotify() option or sportDataNotify is null");
            return false;
        }
        LogUtil.a(TAG, "subscribeNotify() option: ", fgmVar.c(), "sportDataNotify: ", sportDataNotify);
        this.mSportDataNotifyMap.put(fgmVar, sportDataNotify);
        return true;
    }

    public boolean cancelSubscribeNotify(fgm fgmVar) {
        if (fgmVar == null || !this.mSportDataNotifyMap.containsKey(fgmVar)) {
            return false;
        }
        LogUtil.a(TAG, "cancelSubscribeNotify option", fgmVar.c());
        this.mSportDataNotifyMap.remove(fgmVar);
        return true;
    }

    private void notifySingleData(List<String> list, fgm fgmVar, SportDataNotify sportDataNotify) {
        HashMap hashMap = new HashMap();
        for (String str : fgmVar.d()) {
            Object data = getData(str);
            if (data != null) {
                hashMap.put(str, data);
            }
        }
        sportDataNotify.onChange(list, hashMap);
    }

    public void stagingAndNotification(String str, Object obj) {
        stagingData(str, obj);
        notifyData(str);
    }

    private void notifyData(String str) {
        LinkedHashMap linkedHashMap;
        synchronized (this.mSportDataNotifyMap) {
            linkedHashMap = new LinkedHashMap(this.mSportDataNotifyMap);
        }
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            if (((fgm) entry.getKey()).d().contains(str)) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(str);
                notifySingleData(arrayList, (fgm) entry.getKey(), (SportDataNotify) entry.getValue());
            }
        }
    }

    public boolean isToSave() {
        return this.mSportDataStorageManager.isToSave();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        this.mSportDataStorageManager.onStartSport();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        this.mSportDataStorageManager.m134x32b3e3a1();
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void destroy() {
        stagingData("TEMP_ALL_STAGE_DATA", this.mDataStagingMap);
        this.mSportDataStorageManager.destroy();
        this.mLinker.onDestroy();
        for (BaseSource baseSource : this.mSourceMap.values()) {
            if (baseSource != null) {
                baseSource.destroy();
            }
        }
    }

    public void sourceLinkToProducer(String str, SourceToProducer sourceToProducer) {
        this.mLinker.registerProducer(str, sourceToProducer);
    }

    public boolean registerSportDataSavedCallback(String str, ISportDataSaveStatusCallback iSportDataSaveStatusCallback) {
        return this.mSportDataStorageManager.registerSportDataSavedCallback(str, iSportDataSaveStatusCallback);
    }

    public boolean unregisterSportDataSavedCallback(String str) {
        return this.mSportDataStorageManager.unregisterSportDataSavedCallback(str);
    }
}
